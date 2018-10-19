/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.payments;

import com.dojogrouppty.catalogs.CatalogsService;
import com.dojogrouppty.common.ParentControllerService;
import com.dojogrouppty.config.SystemParameters;
import com.dojogrouppty.config.SystemParametersRepository;
import com.dojogrouppty.products.Products;
import com.dojogrouppty.products.ProductsService;
import com.dojogrouppty.students.Student;
import com.dojogrouppty.students.StudentService;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.text.NumberFormat;
import java.util.Locale;
import javax.servlet.ServletContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

/**
 *
 * @author lrodriguezn
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ReceiptPDFService extends ParentControllerService {
    @Autowired
    ServletContext context;
    @Autowired
    MessageSource messageSource;
    @Autowired
    private CatalogsService catalogsService;
    @Autowired
    private StudentService studentService;
     @Autowired
    private ProductsService productsService;
    @Autowired
   private NumberFormat currencyFormatter;
    @Autowired
   private SystemParametersRepository systemParametersRepository;
      private static final Logger logger
            = LoggerFactory.getLogger(ReceiptPDFService.class);
    private static final Font HELVETICA = new Font(Font.FontFamily.HELVETICA, 18,Font.BOLD);
    private static final Font HELVETICA_SMALL = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
    	private static void creteEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
    	private  void addTitlePage(Document document,String datePay,String numberReceipt,String typePayment)
			throws DocumentException {
                logger.debug("addTitlePage 1..");  
		Paragraph preface = new Paragraph();
		creteEmptyLine(preface, 1);                
		preface.add(new Paragraph(messageSource.getMessage(PROOF_OF_PAYMENT, null, Locale.getDefault()), HELVETICA));
		creteEmptyLine(preface, 1);		
		preface.add(new Paragraph(messageSource.getMessage(PARAGRAPH_1, null, Locale.getDefault()).replaceFirst("%s", datePay).replaceFirst("%s", numberReceipt).replaceFirst("%s", typePayment)
				, HELVETICA_SMALL));
		document.add(preface);
                logger.debug("addTitlePage 2..");  

	}

    /**
     * Method for build table food
     * @param resp
     * @return
     * @throws DocumentException 
     */
    private  PdfPTable buildTableFood(PaymentResponse resp) throws DocumentException{
        logger.debug("buildTableFood 1..");  
        PdfPTable table = new PdfPTable(2);         
        table.setTotalWidth(new float[]{ 160, 120 });
        table.setLockedWidth(true);
        table.setWidthPercentage(90);
        table.setSpacingBefore(10);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        PdfPCell headerCell = new PdfPCell();
        headerCell.addElement(new Phrase(messageSource.getMessage(SUB_TOTAL, null, Locale.getDefault()), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        headerCell.setBorderColor(BaseColor.LIGHT_GRAY);
        headerCell.setBackgroundColor(new BaseColor(6, 106, 218));//ok
        headerCell.setPadding(8);
        table.addCell(headerCell);
        table.addCell(currencyFormatter.format(resp.getSubTotal()));
        headerCell = new PdfPCell();
        headerCell.addElement(new Phrase(messageSource.getMessage(TAX, null, Locale.getDefault()), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        headerCell.setBorderColor(BaseColor.LIGHT_GRAY);
        headerCell.setBackgroundColor(new BaseColor(6, 106, 218));//ok
        headerCell.setPadding(8);
        table.addCell(headerCell);
        table.addCell(currencyFormatter.format(resp.getTotalTax()));
        headerCell = new PdfPCell();
        headerCell.addElement(new Phrase(messageSource.getMessage(TOTAL, null, Locale.getDefault()), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        headerCell.setBorderColor(BaseColor.LIGHT_GRAY);
        headerCell.setBackgroundColor(new BaseColor(6, 106, 218));//ok
        headerCell.setPadding(8);
        table.addCell(headerCell);
        table.addCell(currencyFormatter.format(resp.getTotalPayment()));
        logger.debug("buildTableFood 2..");
        return table;
    }
    /**
     * Create receipt PDF
     * @param dto
     * @param resp
     * @param userName
     * @throws URISyntaxException
     * @throws DocumentException
     * @throws IOException 
     */
    public void createReceiptPDF(PaymentDTO dto, PaymentResponse resp, String userName) throws URISyntaxException, DocumentException, IOException {
        SystemParameters parameter =systemParametersRepository.getParameter(PARAMETER_DELETE_PDF);
        //If it is equal to 1 delete files
        if(parameter!=null && parameter.getValue().contains("1")){
           logger.debug("If it is equal to 1 delete files"); 
            deleteFilePDFOld();
        }
        String dest=new StringBuilder().append(LOCATION_PDF).append(File.separator).append(resp.getNumPayment()).append(".").append(PDF).toString();        
        createPdf(dto,  resp,userName, dest);
    }
    /**
     * Delete file old in the dir pdf
     */
    private void deleteFilePDFOld() throws IOException{
        String pathFile =new StringBuilder().append(LOCATION_PDF).toString();
        logger.debug("deleteFilePDFOld pathFile:"+pathFile); 
        File dir = new File(pathFile);
        for (File f:dir.listFiles()){
           logger.debug("deleteFilePDFOld f:"+f.getName()); 
           Files.delete(f.toPath());
        }
    }
    /**
     * Get Image to the header
     * @return 
     */
    private Image getImageHeader(){
     Image img =null;   
      try {
        logger.debug("getImageHeader 1..");  
        img = Image.getInstance(context.getRealPath("/resources/images/bzk.png"));
        img.scaleToFit(476f, 108f);
        img.setAbsolutePosition(50, (float) (PageSize.A4.getHeight() - 80.0));
        img.setAlignment(Element.ALIGN_CENTER);                
      } catch (BadElementException | IOException x) {
        logger.error("Error in getImageHeader:"+x.getMessage());        
      }
      logger.debug("getImageHeader 2..");  
      return img;   
    }
    /**
     * Create PDF 
     * @param dto
     * @param resp
     * @param userName
     * @param dest
     * @throws DocumentException
     * @throws IOException 
     */
    public void createPdf(PaymentDTO dto, PaymentResponse resp, String userName,String dest) throws DocumentException, IOException {
      logger.debug("createPdf..");
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.setMargins(15, 15, 55, 35);
        document.open();    
        document.add(getImageHeader());
        logger.debug("createPdf 1 resp.getNumPayment():"+resp.getNumPayment());
        String typePaymentDescription = catalogsService.getDescriptionCode(dto.getTypePayment());
        addTitlePage(document,dto.getDatePay(),resp.getNumPayment().toString(),typePaymentDescription); 
        Paragraph preface = new Paragraph();
        creteEmptyLine(preface, 1);  
        document.add(preface);
        LineSeparator separator = new LineSeparator();
        separator.setPercentage(98);
        separator.setLineColor(BaseColor.LIGHT_GRAY);
        Chunk linebreak = new Chunk(separator);
        document.add(linebreak);
            
        PdfPTable table = new PdfPTable(NAMES_CELL_TABLE.length);
	table.setHeaderRows(1);
	table.setWidths(new int[] { 3, 2, 4, 3, 2 });
        table.setWidthPercentage(98);
        table.setSpacingBefore(15);
        table.setSplitLate(false);
	for (String columnHeader : NAMES_CELL_TABLE) {
            PdfPCell headerCell = new PdfPCell();
            headerCell.addElement(new Phrase(messageSource.getMessage(columnHeader, null, Locale.getDefault()), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setBorderColor(BaseColor.LIGHT_GRAY);
            headerCell.setBackgroundColor(new BaseColor(6, 106, 218));//ok
            headerCell.setPadding(8);
            table.addCell(headerCell);
	}
        logger.debug("createPdf 2..");
            for (DetailPaymentDTO dt:dto.getDetails()) {
        logger.debug("createPdf 3..");       
            Student s =studentService.getStudent(dt.getIdStudent());            
            Products p = productsService.getProduct(dt.getIdProduct());            
            String typeProuctDescription = catalogsService.getDescriptionCode(p.getType());
            String nameStudent =new StringBuilder().
                                    append(s.getFirstName()).
                                    append(", ").
                                    append(s.getLastName()).
                                    append(", ").
                                    append(s.getSurname()).
                                    append(", ").
                                    append(s.getSurname2()).
                                    toString();

                PdfPCell cell1 = new PdfPCell();
                cell1.addElement(new Phrase(nameStudent, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
                cell1.setBorderColor(BaseColor.LIGHT_GRAY);
                cell1.setPadding(5);
                table.addCell(cell1);
                
                PdfPCell cell2 = new PdfPCell();
                cell2.addElement(new Phrase(typeProuctDescription, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
                cell2.setBorderColor(BaseColor.LIGHT_GRAY);
                cell2.setPadding(5);
                table.addCell(cell2);
                
                PdfPCell cell3 = new PdfPCell();
                BigDecimal t = dt.getTotal();
                if(t==null){
                    t = new BigDecimal("0");
                }
                cell3.addElement(new Phrase(currencyFormatter.format(t), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
                cell3.setBorderColor(BaseColor.LIGHT_GRAY);
                cell3.setPadding(5);
                table.addCell(cell3);
                t =dt.getTax();
                if(t==null){
                    t = new BigDecimal("0");
                }
                PdfPCell cell4 = new PdfPCell();
                cell4.addElement(new Phrase(currencyFormatter.format(t), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
                cell4.setBorderColor(BaseColor.LIGHT_GRAY);
                cell4.setPadding(5);
                table.addCell(cell4);
                t = dt.getTotal();
                if (t == null) {
                    t = new BigDecimal("0");
                } else {
                    t = dt.getTotal().add(dt.getTax());
                }
                PdfPCell cell5 = new PdfPCell();
                cell5.addElement(new Phrase(currencyFormatter.format(t), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
                cell5.setBorderColor(BaseColor.LIGHT_GRAY);
                cell5.setPadding(5);
                table.addCell(cell5);
            }     
        logger.debug("createPdf 4..");    
        document.add(table);
        document.add(new Phrase("\n"));       
        document.add(linebreak);
        document.add(buildTableFood(resp));
        logger.debug("createPdf 5.."); 
        document.close();
    }
}
