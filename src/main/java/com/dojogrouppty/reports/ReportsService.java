/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.reports;

import com.dojogrouppty.catalogs.CatalogsService;
import com.dojogrouppty.common.DateUtils;
import com.dojogrouppty.common.ERROR_REPORTS;
import com.dojogrouppty.common.ParentControllerService;
import com.dojogrouppty.common.STATUS;
import com.dojogrouppty.config.SystemParameters;
import com.dojogrouppty.config.SystemParametersRepository;
import com.dojogrouppty.error.GenericBZKException;
import com.dojogrouppty.products.Products;
import com.dojogrouppty.products.ProductsRepository;
import com.dojogrouppty.students.Student;
import com.dojogrouppty.students.StudentService;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;

/**
 *
 * @author lrodriguezn
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ReportsService extends ParentControllerService{
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFact;
    @Autowired
    private CatalogsService catalogsService;
    @Autowired
    NumberFormat currencyFormatter;
    @Autowired
   private SystemParametersRepository systemParametersRepository;
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private StudentService studentService;
    private static final Logger logger
            = LoggerFactory.getLogger(ReportsService.class);
    private Integer countActive=0,countNotActive=0,countAll=0;
    
    public Map<String, Object> paymentHistoryByStudent(ReportForm reportForm)  throws ParseException{
        Map <String,Object> map =   new TreeMap<String, Object>();
          logger.debug("InitialDate:["+reportForm.getInitialDate()+"] FinalDate:["+reportForm.getFinalDate()+"]");   
         if(reportForm.getInitialDate()==null){
            String message = messageSource.getMessage(ERROR_REPORTS.ERROR_INITIAL_DATE.getValue(), null, Locale.getDefault()); 
            map.put(GENERAL_MODAL_MESSAGE,message);
             logger.debug("1");
         }
         else if(reportForm.getFinalDate()==null){
            String message = messageSource.getMessage(ERROR_REPORTS.ERROR_FINAL_DATE.getValue(), null, Locale.getDefault()); 
            map.put(GENERAL_MODAL_MESSAGE,message);
             logger.debug("2");
         }
         else{
            Date iniDate = dateformat.parse(reportForm.getInitialDate());
            Date finalDate = dateformat.parse(reportForm.getFinalDate());
            if (iniDate.compareTo(finalDate) > 0) {
             String message = messageSource.getMessage(ERROR_REPORTS.ERROR_THE_INITIAL_DATE_IS_GREATER_THAN_FINAL_DATE.getValue(), null, Locale.getDefault()); 
              map.put(GENERAL_MODAL_MESSAGE,message);
              logger.debug("3");
            }
            else{   
               Integer typeProduct=Integer.parseInt(reportForm.getTypeProduct()); 
               Integer idStudent=reportForm.getIdStudent(); 
               List<ReportDetailDTO> list = buildPaymentHistoryByStudent(iniDate,finalDate,typeProduct,idStudent);
                if (list == null || list.isEmpty()) {
                    String message = messageSource.getMessage(EMPTY_LIST, null, Locale.getDefault());
                    map.put(GENERAL_MODAL_MESSAGE, message);
                    logger.debug("4");
                }
                else{
                    map.put(REPORT_DETAIL_LIST, list);
                    map.put(REPORT_FOOD, getFoodpaymentHistoryByStudent( iniDate,finalDate,typeProduct,idStudent));
                    ReportHeaderDTO header = new ReportHeaderDTO();  
                    ProfileStudent profileStudent = getProfileStudent(idStudent);
                    map.put(PROFILE_STUDENT, profileStudent);
                    String descriptionType = "";
                    if (reportForm.getTypeProduct() != null && !reportForm.getTypeProduct().equals("0")) {
                    	 descriptionType=	getDescriptionType(Long.parseLong(reportForm.getTypeProduct()));
                    }
                    header.setTitle(messageSource.getMessage(TITLE_PAYMENT_HISTORY, null, Locale.getDefault()).replaceFirst("%s", reportForm.getInitialDate().substring(0,10)).replaceFirst("%s", reportForm.getFinalDate().substring(0,10)).replaceFirst("%s", profileStudent.getName()).replaceFirst("%s", descriptionType));
                    header.setNamefile(messageSource.getMessage(NAME_PAYMENT_HISTORY, null, Locale.getDefault()));
                    map.put(REPORT_HEAD, header);
                    logger.debug("5");
                }
            }
         }  
        return map;   
    }
    /**
     * Get the description of a product by its id
     * @param typeProduct
     * @return String DescriptionType
     */
    private String getDescriptionType(Long typeProduct) {
    	String descriptionType="";
    	Products prod = productsRepository.findOne(typeProduct);
        descriptionType = catalogsService.getDescriptionCode(prod.getType());
        return descriptionType;
    }
    
    /**
     * State student account
     * @param reportForm
     * @return 
     * @throws com.dojogrouppty.error.GenericBZKException 
     */
     public Map<String, Object> stateStudentAccount(ReportForm reportForm) throws GenericBZKException {
             Map <String,Object> map =   new TreeMap<String, Object>();
            List<ReportStateStudentAccountDTO> list = buildStateStudentAccount(new Short(reportForm.getActive()));
            if(list==null || list.isEmpty()){
            String message = messageSource.getMessage(EMPTY_LIST, null, Locale.getDefault()); 
            map.put(GENERAL_MODAL_MESSAGE,message);
            logger.error("Error,list student null");
            throw new GenericBZKException("Error,list student null");
            }
            else{
             map.put(REPORT_DETAIL_LIST,list);
             Short op = new Short(reportForm.getActive());
             map.put(REPORT_FOOD,getStateStudentAccount());
             ReportHeaderDTO header = new ReportHeaderDTO();
             if(STATUS.ACTIVE.getStatus().equals(op) || STATUS.ALL_STUDENTS.getStatus().equals(op)){
            	   logger.debug("STATUS.ALL_STUDENTS.getString():"+STATUS.ALL_STUDENTS.getString());
            	 map.put(STATES_STUDENTS_ACCOUNT,STATUS.ALL_STUDENTS.getString()); 
             }
             else {
            	 logger.debug("STATUS.INACTIVE.getString():"+STATUS.INACTIVE.getString());
            	 map.put(STATES_STUDENTS_ACCOUNT,STATUS.INACTIVE.getString()); 
             }
             header.setNamefile(messageSource.getMessage(NAME_STUDENTS_REPORT, null, Locale.getDefault()));                        
             header.setTitle(messageSource.getMessage(TITLE_STUDENTS_REPORT, null, Locale.getDefault()).replaceFirst("%s",dateformat.format(new Date())));
             map.put(REPORT_HEAD,header);
            } 
        return map;     
     }
    
    /**
     * Administrative report
     * @param reportForm
     * @return
     * @throws ParseException 
     */
    public Map<String, Object> administrativeReport(ReportForm reportForm) throws ParseException{
         Map <String,Object> map =   new TreeMap<String, Object>();
           logger.debug("InitialDate:["+reportForm.getInitialDate()+"] FinalDate:["+reportForm.getFinalDate()+"]");   
         if(reportForm.getInitialDate()==null){
            String message = messageSource.getMessage(ERROR_REPORTS.ERROR_INITIAL_DATE.getValue(), null, Locale.getDefault()); 
            map.put(GENERAL_MODAL_MESSAGE,message);
             logger.debug("1");
         }
         else if(reportForm.getFinalDate()==null){
            String message = messageSource.getMessage(ERROR_REPORTS.ERROR_FINAL_DATE.getValue(), null, Locale.getDefault()); 
            map.put(GENERAL_MODAL_MESSAGE,message);
             logger.debug("2");
         }
         else{
            Date iniDate = dateformat.parse(reportForm.getInitialDate());
            Date finalDate = dateformat.parse(reportForm.getFinalDate());
            if (iniDate.compareTo(finalDate) > 0) {
             String message = messageSource.getMessage(ERROR_REPORTS.ERROR_THE_INITIAL_DATE_IS_GREATER_THAN_FINAL_DATE.getValue(), null, Locale.getDefault()); 
              map.put(GENERAL_MODAL_MESSAGE,message);
              logger.debug("3");
            }
            else{                
               List<ReportDetailDTO> list =  buildAdministrativeReport(iniDate,finalDate,reportForm.getTypePayment());
                if (list == null || list.isEmpty()) {
                    String message = messageSource.getMessage(EMPTY_LIST, null, Locale.getDefault());
                    map.put(GENERAL_MODAL_MESSAGE, message);
                    logger.debug("4");
                }
                else{
                    map.put(REPORT_DETAIL_LIST, list);
                    map.put(REPORT_FOOD, getFoodadministrativeReport(iniDate, finalDate,reportForm.getTypePayment()));
                    ReportHeaderDTO header = new ReportHeaderDTO();              
                    header.setTitle(messageSource.getMessage(TITLE_ADMINISTRATIVE_REPORT, null, Locale.getDefault()).replaceFirst("%s", reportForm.getInitialDate().substring(0,10)).replaceFirst("%s", reportForm.getFinalDate().substring(0,10)));
                    header.setNamefile(messageSource.getMessage(NAME_REPORT_ADMINISTRATIVE_REPORT, null, Locale.getDefault()));
                    map.put(REPORT_HEAD, header);
                    logger.debug("5");
                }
            }}
      return map;    
    } 
    /**
     * Method to build the download report
     * @param reportForm
     * @return
     * @throws ParseException 
     */
     public Map<String, Object> reportForDownload(ReportForm reportForm) throws ParseException{
            Map <String,Object> map =   new TreeMap<String, Object>();
         logger.debug("InitialDate:["+reportForm.getInitialDate()+"] FinalDate:["+reportForm.getFinalDate()+"] TypeProduct:["+reportForm.getTypeProduct()+"]");   
         if(reportForm.getInitialDate()==null){
            String message = messageSource.getMessage(ERROR_REPORTS.ERROR_INITIAL_DATE.getValue(), null, Locale.getDefault()); 
            map.put(GENERAL_MODAL_MESSAGE,message);
             logger.debug("1");
         }
         else if(reportForm.getFinalDate()==null){
            String message = messageSource.getMessage(ERROR_REPORTS.ERROR_FINAL_DATE.getValue(), null, Locale.getDefault()); 
            map.put(GENERAL_MODAL_MESSAGE,message);
             logger.debug("2");
         }
         else{
            Date iniDate = dateformat.parse(reportForm.getInitialDate());
            Date finalDate = dateformat.parse(reportForm.getFinalDate());
            if (iniDate.compareTo(finalDate) > 0) {
             String message = messageSource.getMessage(ERROR_REPORTS.ERROR_THE_INITIAL_DATE_IS_GREATER_THAN_FINAL_DATE.getValue(), null, Locale.getDefault()); 
              map.put(GENERAL_MODAL_MESSAGE,message);
              logger.debug("3");
            }
            else{
            Integer typeProduct=Integer.parseInt(reportForm.getTypeProduct());    
            List<ReportDetailDTO> list =buildReportForDownload( iniDate,finalDate,typeProduct);
            if(list==null || list.isEmpty()){
                String message = messageSource.getMessage(EMPTY_LIST, null, Locale.getDefault()); 
                map.put(GENERAL_MODAL_MESSAGE,message);
                logger.debug("4");
            }
            else{                
                map.put(REPORT_DETAIL_LIST, list);
                map.put(REPORT_FOOD, getFoodReportForDownload(iniDate, finalDate, typeProduct));
                ReportHeaderDTO header = new ReportHeaderDTO();
                String description = "";
                if (reportForm.getTypeProduct() != null && !reportForm.getTypeProduct().equals("0")) {
                	description=	getDescriptionType(Long.parseLong(reportForm.getTypeProduct()));
                }
                header.setTitle(messageSource.getMessage(TITLE_REPORT_FOR_DOWNLOAD, null, Locale.getDefault()).replaceFirst("%s", reportForm.getInitialDate().substring(0,10)).replaceFirst("%s", reportForm.getFinalDate().substring(0,10)).replace("%s", description));
                header.setNamefile(messageSource.getMessage(NAME_REPORT_FOR_DOWNLOAD, null, Locale.getDefault()));
                map.put(REPORT_HEAD, header);
                logger.debug("5");
            }
            }
         }
        return map;    
     }
    /**
     * Payment detail method
     * @param reportForm
     * @return 
     */
    public Map<String, Object>  receiptsReport(ReportForm reportForm){
        Map <String,Object> map =   new TreeMap<String, Object>();
        logger.debug("InitialReceipt:["+reportForm.getInitialReceipt()+"] FinalReceipt:["+reportForm.getFinalReceipt()+"] TypePayment:["+reportForm.getTypePayment()+"]");
        if(reportForm.getInitialReceipt()==null || reportForm.getInitialReceipt().isEmpty()){
            String message = messageSource.getMessage(ERROR_REPORTS.ERROR_INITIAL_RECEIPT.getValue(), null, Locale.getDefault()); 
            map.put(GENERAL_MODAL_MESSAGE,message);
            logger.debug("1");
        }
        else if(reportForm.getFinalReceipt()==null || reportForm.getFinalReceipt().isEmpty()){
            String message = messageSource.getMessage(ERROR_REPORTS.ERROR_FINAL_RECEIPT.getValue(), null, Locale.getDefault()); 
            map.put(GENERAL_MODAL_MESSAGE,message);
            logger.debug("2");
        }
        else{
            List<ReportDetailDTO> list = buildReceiptsReport(reportForm);
            if(list==null || list.isEmpty()){
            String message = messageSource.getMessage(EMPTY_LIST, null, Locale.getDefault()); 
            map.put(GENERAL_MODAL_MESSAGE,message);
            logger.debug("3");
            }
            else{
             map.put(REPORT_DETAIL_LIST,list);
             map.put(REPORT_FOOD,getFoodReceiptsReport(reportForm));
             ReportHeaderDTO header = new ReportHeaderDTO();
             header.setNamefile(messageSource.getMessage(NAME_RECEIPTS, null, Locale.getDefault()));
             String description ="";
             if(reportForm.getTypePayment()!=null && !reportForm.getTypePayment().equals("0")){
               description =catalogsService.getDescriptionCode(Long.parseLong(reportForm.getTypePayment()));  
             }             
             header.setTitle(messageSource.getMessage(TITLE_RECEIPTS, null, Locale.getDefault()).replaceFirst("%d",reportForm.getInitialReceipt()).replaceFirst("%d", reportForm.getFinalReceipt()).replace("%s",description));
             map.put(REPORT_HEAD,header);
             logger.debug("4");
            }
        }
       return map; 
    }
    /**
     * Method that builds report for download
     * @param reportForm
     * @return 
     */
       private List<ReportDetailDTO> buildReportForDownload(Date iniDate,Date finalDate,Integer typeProduct){
       List<ReportDetailDTO> listDto = new ArrayList<ReportDetailDTO>();
       Query query = entityManagerFact.getObject().createEntityManager().createNamedQuery(SQL_REPORT_FOR_DOWNLOAD);
       query.setParameter("parm1",iniDate);
       query.setParameter("parm2",finalDate);
       query.setParameter("parm3",typeProduct);
       List<Object[]> listObj = query.getResultList();
       for(Object[] objs:listObj){
           ReportDetailDTO dto = new ReportDetailDTO(); 
           dto.setDate(objs[0].toString());
           dto.setName(objs[1].toString());
           dto.setNumber(objs[2].toString());
           dto.setProduct(objs[3].toString());
           dto.setPaymentMethod(objs[4].toString());
           dto.setAmount(objs[5].toString());
           dto.setIntDate(Integer.parseInt(objs[6].toString()));
           dto.setComment(objs[7].toString());
           listDto.add(dto);
       }
       return listDto;
       }
      private List<ReportDetailDTO> buildPaymentHistoryByStudent(Date iniDate, Date finalDate,Integer typeProduct,Integer idStudent){
          logger.debug("buildPaymentHistoryByStudent..");
        List<ReportDetailDTO> listDto = new ArrayList<ReportDetailDTO>();
        Query query = entityManagerFact.getObject().createEntityManager().createNamedQuery(SQL_PAYMENT_HISTORY_BY_STUDENT);
        query.setParameter("parm1",iniDate);
        query.setParameter("parm2", finalDate);
        query.setParameter("parm3",typeProduct);
        query.setParameter("parm4",idStudent);        
        logger.debug("iniDate:["+iniDate.toString()+"] finalDate:["+finalDate.toString()+"] typeProduct:["+typeProduct+"] idStudent:["+idStudent+"]");
        List<Object[]> listObj = query.getResultList();
             for (Object[] objsT : listObj) {
                logger.debug("2");
                ReportDetailDTO dto = new ReportDetailDTO();
                dto.setDate(objsT[0].toString());
                dto.setName(objsT[1].toString());
                dto.setNumber(objsT[2].toString());
                dto.setProduct(objsT[3].toString());
                dto.setPaymentMethod(objsT[4].toString());
                dto.setAmount(objsT[5].toString());
                dto.setIntDate(Integer.parseInt(objsT[6].toString()));
                dto.setComment(objsT[7].toString());
                listDto.add(dto);
             }
          return listDto;
       }
       /**
        * Method that builds administrative report
        * @param iniDate
        * @param finalDate
        * @return 
     * @throws ParseException 
        */
     private List<ReportDetailDTO> buildAdministrativeReport(Date iniDate, Date finalDate,String typePayment) throws ParseException {
        logger.debug("buildAdministrativeReport..");
        List<ReportDetailDTO> listDto = new ArrayList<ReportDetailDTO>();
        Query query = entityManagerFact.getObject().createEntityManager().createNamedQuery(SQL_ADMINISTRATIVE_REPORT);
        query.setParameter("parm1", iniDate);
        query.setParameter("parm2", finalDate);
        query.setParameter("parm3", Long.parseLong(typePayment));
        List<Object[]> listObj = query.getResultList();

        int size=listObj.size(),intDate=0;
        if (size > 0) {
            logger.debug("--------------------------------------------------");
            logger.debug("1");
            for (Object[] objsT : listObj) {
                logger.debug("2");
                ReportDetailDTO dto = new ReportDetailDTO();
                intDate =Integer.parseInt(objsT[7].toString());
                dto.setDate(objsT[0].toString());
                dto.setIntDate(intDate);
                dto.setName(objsT[1].toString());
                dto.setNumber(objsT[2].toString());
                dto.setProduct(objsT[3].toString());
                dto.setPaymentMethod(objsT[4].toString());
                dto.setAmount(objsT[5].toString());
                dto.setComment(objsT[8].toString());
                logger.debug("Amount:[" + objsT[5].toString() + "]");
                listDto.add(dto);
            }
        }

        logger.debug("--------------------------------------------------");
        listDto.sort(Comparator.comparing(ReportDetailDTO::getDate).reversed());
        for (ReportDetailDTO dto : listDto) {
            logger.debug("Date:[" + dto.getDate() + "] intDate:[ "+dto.getIntDate()+"] Number:["+dto.getNumber()+"] amount:[" + dto.getAmount() + "]");
        }
        logger.debug("return listDto..");
        return listDto;
    }

    /**
     * Method that builds the payment detail
     *
     * @param reportForm
     * @return
     */
    private List<ReportDetailDTO> buildReceiptsReport(ReportForm reportForm) {
        List<ReportDetailDTO> listDto = new ArrayList<ReportDetailDTO>();
        Query query = entityManagerFact.getObject().createEntityManager().createNamedQuery(SQL_RECEIPTS_REPORT);
        query.setParameter("parm1", reportForm.getInitialReceipt());
        query.setParameter("parm2", reportForm.getFinalReceipt());
        query.setParameter("parm3", reportForm.getTypePayment());
        List<Object[]> listObj = query.getResultList();
        logger.debug("listObj.size:" + listObj.size());
        for (Object[] objs : listObj) {
            ReportDetailDTO dto = new ReportDetailDTO();
            dto.setDate(objs[0].toString());
            dto.setNumber(objs[1].toString());
            dto.setPaymentMethod(objs[2].toString());
            dto.setAmount(objs[3].toString());
            dto.setIntDate(Integer.parseInt(objs[4].toString()));
            logger.debug("Date:" + dto.getDate() + ", Number:" + dto.getNumber() + ", PaymentMethod:" + dto.getPaymentMethod());
            listDto.add(dto);
        }
        return listDto;
    }
    private List<ReportStateStudentAccountDTO>buildStateStudentAccount(Short status){
        List<ReportStateStudentAccountDTO> listDto= new ArrayList<ReportStateStudentAccountDTO>();
        Query query = entityManagerFact.getObject().createEntityManager().createNamedQuery(SQL_STATE_STUDENTS);
        List<Object[]> listObj = query.getResultList(); 
        countActive=0;countNotActive=0;countAll=0;
        for (Object[] objs : listObj) {
        ReportStateStudentAccountDTO dto = new ReportStateStudentAccountDTO();
       
        if(((objs[4].toString().equals(YES_ES)) && STATUS.ACTIVE.getStatus().equals(status))) {
        dto.setName(objs[0].toString());       
        dto.setStudentID(Integer.parseInt(objs[1].toString()));
        dto.setLastMonthlyPayment(objs[2].toString());
        dto.setPreviousAnnuityDate(objs[3].toString());
        dto.setActive(objs[4].toString());
        dto.setEnrollmentInTheCurrentMonth(objs[5].toString());
        dto.setContractMonth(objs[6].toString());
        dto.setEnrollment(objs[7].toString());   
        dto.setIntLastMonthlyPayment(Integer.parseInt(objs[9].toString()));
        dto.setIntPreviousAnnuityDate(Integer.parseInt(objs[10].toString()));
        listDto.add(dto);  
        countActive++;
        }
        else if(((objs[4].toString().equals(NO_ES)) && STATUS.INACTIVE.getStatus().equals(status))) {
            dto.setName(objs[0].toString());       
            dto.setStudentID(Integer.parseInt(objs[1].toString()));
            dto.setLastMonthlyPayment(objs[2].toString());
            dto.setPreviousAnnuityDate(objs[3].toString());
            dto.setActive(objs[4].toString());
            dto.setEnrollmentInTheCurrentMonth(objs[5].toString());
            //FIXME Error in the field
            dto.setContractMonth(objs[6].toString());
            dto.setEnrollment(objs[7].toString());   
            dto.setIntLastMonthlyPayment(Integer.parseInt(objs[9].toString()));
            dto.setIntPreviousAnnuityDate(Integer.parseInt(objs[10].toString()));
            listDto.add(dto); 
            countNotActive++;
        }
        else if( STATUS.ALL_STUDENTS.getStatus().equals(status)){
            dto.setName(objs[0].toString());       
            dto.setStudentID(Integer.parseInt(objs[1].toString()));
            dto.setLastMonthlyPayment(objs[2].toString());            
            dto.setPreviousAnnuityDate(objs[3].toString());
            dto.setActive(objs[4].toString());
            dto.setEnrollmentInTheCurrentMonth(objs[5].toString());
            dto.setContractMonth(objs[6].toString());
            dto.setEnrollment(objs[7].toString());   
            dto.setIntLastMonthlyPayment(Integer.parseInt(objs[9].toString()));
            dto.setIntPreviousAnnuityDate(Integer.parseInt(objs[10].toString()));
            listDto.add(dto); 
            countAll++;
        }
//        logger.debug("NAME:" + dto.getName()+ ", getStudentID:" + dto.getStudentID() + ", getLastMonthlyPayment:" + dto.getLastMonthlyPayment()+" dto.getPreviousAnnuityDate:"+dto.getPreviousAnnuityDate()+" dto.getActive:"+dto.getActive()+" dto.getEnrollmentInTheCurrentMonth"+dto.getEnrollmentInTheCurrentMonth()+" dto.getContractMonth:"+dto.getContractMonth()+" dto.getEnrollment:"+dto.getEnrollment()+" getIntLastMonthlyPayment:"+dto.getIntLastMonthlyPayment());	
        }
        logger.debug("YES_ES ACTIVE COUNT:"+countActive);
        logger.debug("NO_ES INACTIVE COUNT:"+countNotActive);
        logger.debug("ALL COUNT:"+countAll); 
       return listDto;
    }
    /**
    * Get food
    * @return 
    */
    private FootDTO getStateStudentAccount(){
       FootDTO food= new FootDTO();
       	Integer count =countActive+countNotActive+countAll;
       logger.debug("getStateStudentAccount count:" + count);
       
       food.setActive(count.toString());
       return food;   
    }
   /**
    * Get food
    * @return 
    */
   private FootDTO getFoodReportForDownload(Date iniDate,Date finalDate,Integer typeProduct){
       FootDTO food= new FootDTO();
       Query query = entityManagerFact.getObject().createEntityManager().createNamedQuery(SQL_REPORT_FOR_DOWNLOAD_FOOD);
       query.setParameter("parm1",iniDate);
       query.setParameter("parm2",finalDate);
       query.setParameter("parm3",typeProduct);
       Object obj = query.getSingleResult();
       String total="$".concat(obj.toString());
       food.setTotal(total);
     return food;   
   }
     /**
    * Get food administrative report
    * @return 
    */
   private FootDTO getFoodadministrativeReport(Date iniDate,Date finalDate,String typePayment){
       FootDTO food= new FootDTO();
       Query query = entityManagerFact.getObject().createEntityManager().createNamedQuery(SQL_ADMINISTRATIVE_REPORT_FOOD);
       query.setParameter("parm1",iniDate);
       query.setParameter("parm2",finalDate);  
       query.setParameter("parm3",Long.parseLong(typePayment));
       Object obj = query.getSingleResult();
       String total="$".concat(obj.toString());
       food.setTotal(total);
     return food;   
   }
      /**
    * Get food administrative report
    * @return 
    */
   private FootDTO getFoodpaymentHistoryByStudent(Date iniDate, Date finalDate,Integer typeProduct,Integer idStudent){
       logger.debug("getFoodpaymentHistoryByStudent..");
       FootDTO food= new FootDTO();
       Query query = entityManagerFact.getObject().createEntityManager().createNamedQuery(SQL_PAYMENT_HISTORY_BY_STUDENT_FOOD);
       query.setParameter("parm1",iniDate);
       query.setParameter("parm2",finalDate);       
       query.setParameter("parm3",typeProduct); 
       query.setParameter("parm4",idStudent); 
       Object obj = query.getSingleResult();
       String total="$".concat(obj.toString());
       food.setTotal(total);
     return food;   
   }
    /**
    * Get food Receipts report
    * @return 
    */
   private FootDTO getFoodReceiptsReport(ReportForm reportForm){
       FootDTO food= new FootDTO();
       Query query = entityManagerFact.getObject().createEntityManager().createNamedQuery(SQL_RECEIPTS_REPORT_FOOD);
       query.setParameter("parm1",reportForm.getInitialReceipt());
       query.setParameter("parm2",reportForm.getFinalReceipt());
       query.setParameter("parm3",reportForm.getTypePayment());
       Object obj = query.getSingleResult();
       String total="$".concat(obj.toString());
       food.setTotal(total);
     return food;   
   }
   /**
    * Get profile Student
    * @param iniDate
    * @param finalDate
    * @param typeProduct
    * @param idStudent
    * @return ProfileStudent
    */
   @SuppressWarnings("unchecked")
   private ProfileStudent getProfileStudent(Integer idStudent){
       logger.debug("getProfileStudent..");
       ProfileStudent profileStudent = new ProfileStudent();
       Query query = entityManagerFact.getObject().createEntityManager().createNamedQuery(SQL_PAYMENT_HISTORY_BY_STUDENT_CARD);
       query.setParameter("parm1",idStudent);     
	List<Object[]> listObj = query.getResultList(); 
       Object[] singleRow = listObj.get(0);
       profileStudent.setName(singleRow[0].toString());
       profileStudent.setCategory(messageSource.getMessage(CATEGORY, null, Locale.getDefault()).replaceFirst("%s",singleRow[3].toString()));
       profileStudent.setDocId(messageSource.getMessage(DOC_ID, null, Locale.getDefault()).replaceFirst("%s",singleRow[1].toString()));
       profileStudent.setTelephone(messageSource.getMessage(TELEPHONE, null, Locale.getDefault()).replaceFirst("%s",singleRow[5].toString()));
       profileStudent.setEmail(messageSource.getMessage(EMAIL, null, Locale.getDefault()).replaceFirst("%s",singleRow[4].toString()));
       profileStudent.setAddress(singleRow[2].toString());
       profileStudent.setRegistrationDate(messageSource.getMessage(REGISTRATION_DATE_PROFILE, null, Locale.getDefault()).replaceFirst("%s",singleRow[6].toString()));
       return profileStudent;
   } 
   /**
    * Method for graph of tyoes of payment per year
    * @return String list json to TypesPaymentVsYearsDTO
    */
   @SuppressWarnings("unchecked")
   public String typesPaymentVsYears(){
       SystemParameters parameter = systemParametersRepository.getParameter(PARAMETER_YEAR_AGO);
       int year =1;
       if(parameter !=null){
           year = Integer.parseInt(parameter.getValue());
       }
       Date param2= new Date();
       Date param1 = DateUtils.yearsAgo(year);
       Query query = entityManagerFact.getObject().createEntityManager().createNamedQuery(SQL_CHART_TYPES_PAYMENT_VS_YEARS);
       query.setParameter("parm1",param1);
       query.setParameter("parm2",param2);
       List<Object[]> listObj = query.getResultList(); 
       List<TypesPaymentVsYearsDTO> listDto = new ArrayList<TypesPaymentVsYearsDTO>();
       for(Object[] objs:listObj){
           TypesPaymentVsYearsDTO dto = new TypesPaymentVsYearsDTO();
           dto.setYear(objs[0].toString());
           dto.setAch(objs[1].toString());
           dto.setCheck(objs[2].toString());
           dto.setCash(objs[3].toString());
           listDto.add(dto);
       }
     return gson.toJson(listDto);    
   }
   /**
    * Method that loads the payment history form
    * @param idStudent
    * @return ReportForm with the student
    */
   public ReportForm getReportForm(Integer idStudent) {
	  SystemParameters parameter = systemParametersRepository.getParameter(PARAMETER_NUMBER_OF_DAYS_SEARCH_HISTORY) ;  
   	Student s = studentService.getStudent(idStudent.longValue());
   	ReportForm reportForm = new ReportForm(); 	
   	reportForm.setFinalDate(DateUtils.toString(new Date(), DDMMYYYY_HHMMSS_24h));
   	reportForm.setInitialDate(DateUtils.toString(DateUtils.addDay(Integer.parseInt(parameter.getValue())), DDMMYYYY_HHMMSS_24h));
   	reportForm.setIdStudent(s.getIdStudent().intValue());  	
   	reportForm.setTypeProduct("0");
   	return reportForm;
   }
}
