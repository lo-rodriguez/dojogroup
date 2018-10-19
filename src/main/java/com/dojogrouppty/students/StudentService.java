/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.students;

import com.dojogrouppty.catalogs.CatalogsService;
import com.dojogrouppty.common.ParentControllerService;
import com.dojogrouppty.common.STATUS;
import com.dojogrouppty.error.GenericBZKException;
import java.io.ByteArrayInputStream;
import java.io.File;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.nio.file.Files;
import javax.persistence.Query;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.multipart.MultipartFile;

/**
 * Servicio de estudiante
 *
 * @author lrodriguezn
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StudentService extends ParentControllerService {

    @Autowired
    private StudentRepository studentRepository;
    private static final Logger logger
            = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private CatalogsService catalogsService;
    // Beyond that size spring will throw exception.    
    /**
     * Save object Student
     *
     * @param studentForm
     * @return new Student
     * @throws ParseException
     */
    private Path rootLocation;
    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFact;

    @PostConstruct
    void iniPath() {
        rootLocation = Paths.get(LOCATION);
    }

    @Transactional
    public Student saveStudent(StudentForm studentForm) throws ParseException, IOException, GenericBZKException {
        logger.debug("Entrando al metodo saveStudent..");
        return studentRepository.saveAndFlush(getStudentForm(studentForm));
    }

    public boolean validEmailStudent(String email, long id) {
    	if( email==null || "".equals(email)) return true;
        if (id > 0) {
            return studentRepository.exists(email, id) <= 0;
        }
        return studentRepository.exists(email) <= 0;
    }

    public boolean savePhoto(StudentForm s) throws IOException, GenericBZKException {
        if (s.getPhoto() != null && !s.getPhoto().isEmpty() && s.getPhoto().getSize() < getMaxFileSize()) {
            storeOnDisk(s.getPhoto(), s.getId());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public boolean savePhoto(byte[] data, Long id) throws GenericBZKException {
        if (data != null && data.length > 0) {
            storeOnDisk(data, id);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * return list Student enable
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<StudentDTO> getStudentsEnable() {
        logger.debug("Entrando al metodo getStudentsEnable..");
        ArrayList<StudentDTO> listDto = new ArrayList<StudentDTO>();
        Query query = entityManagerFact.getObject().createEntityManager().createNamedQuery(SQL_STUDENTS_STATUS_1);        
		List<Object[]> listObj = query.getResultList();
        Map<Long, String> codes = catalogsService.getAllCodes();

        if (listObj != null && !listObj.isEmpty()) {
            for (Object[] array : listObj) {
                StudentDTO dto = new StudentDTO();
                dto.setId(Long.parseLong(array[0].toString()));
                dto.setName(array[1].toString());
                dto.setSurname(array[2].toString());
                dto.setSurname2(array[3].toString());
                dto.setDocid(array[4].toString());
                dto.setCategory(codes.get(new Long(array[5].toString())));
                listDto.add(dto);
                logger.debug("Nombre:[" + dto.getName() + "] "
                        + "apellido:[" + dto.getSurname() + "] "
                        + "apellido2:[" + dto.getSurname2() + "]"
                        + "Cedula:[" + dto.getDocid() + "]"
                        + "Categoria:[" + dto.getCategory() + "]");
            }
        }
        logger.debug("Saliendo del metodo getStudentsEnable..");
        return listDto;
    }
    /**
     * Get all Description of active student
     * @return List DescriptionStudentDTO
     */
    @SuppressWarnings("unchecked")
     public List<DescriptionStudentDTO> getDescriptionStudentDTO() {
        logger.debug("In getStudentsEnable..");
        ArrayList<DescriptionStudentDTO> listDto = new ArrayList<DescriptionStudentDTO>();
        Query query = entityManagerFact.getObject().createEntityManager().createNamedQuery(SQL_STUDENTS_STATUS_1);
        List<Object[]> listObj = query.getResultList();
        if (listObj != null && !listObj.isEmpty()) {
            for (Object[] array : listObj) {
                DescriptionStudentDTO dto = new DescriptionStudentDTO();                
                dto.setId(Long.parseLong(array[0].toString()));
                String description = new StringBuilder().
                        append(array[1].toString()).
                        append(",").append(array[2].toString()).
                        append(",").
                        append(array[3].toString()).
                        append("(").append(array[4].toString()).append(")").toString();
                dto.setDescription(description);
                listDto.add(dto);
                logger.debug("ID:[" + dto.getId() + "] "
                        + "DESCRIPTION:[" + dto.getDescription() + "]");

            }
        }
        logger.debug("Saliendo del metodo getStudentsEnable..");
        return listDto;
    }
    /**
     * Get Student form
     *
     * @param studentForm
     * @return
     * @throws ParseException
     * @throws java.io.IOException
     * @throws com.dojogrouppty.error.GenericBZKException
     */
    protected Student getStudentForm(StudentForm studentForm) throws ParseException, IOException, GenericBZKException {
        Student student = new Student();
        try{
        logger.debug("<<<getStudentForm >>>");    
        student.setAddress(studentForm.getAddress());
        student.setAllergy(studentForm.getAllergy());
        student.setBirthdate(dateformat.parse(studentForm.getBirthdate()));
        student.setBloodtype(studentForm.getBloodGroup());
        student.setCategory(studentForm.getCategory());
        student.setCellPhone(studentForm.getCellPhone1());
        student.setCellPhone2(studentForm.getCellPhone2());
           logger.debug("<<<getStudentForm 2 >>>");   
        if (studentForm.getId() != null && studentForm.getId() > 0) {
               logger.debug("<<<getStudentForm 3 >>>");   
            student.setIdStudent(studentForm.getId());
            if (studentForm.getStatus() != null && STATUS.DELETED.getStatus().toString().equals(studentForm.getStatus())) {
                logger.info("the student with id was discharged:" + studentForm.getId());
                student.setStatus(STATUS.DELETED.getStatus());
                   logger.debug("<<<getStudentForm 4 >>>");   
                student.setDateOfLow(new Date());
            } else {
                student.setStatus(STATUS.ACTIVE.getStatus());
            }
           
        } else {
               logger.debug("<<<getStudentForm 5>>>");              
            student.setStatus(STATUS.ACTIVE.getStatus());
        }
        student.setDayOfIncome(dateformat.parse(studentForm.getDayOfIncome()));
        student.setDocId(studentForm.getIdentificationCard());
        student.setDocIdGuardian(studentForm.getParentsCertificate());
        student.setFirstName(studentForm.getFirstName());
        logger.debug("<<<getStudentForm 6>>>");   
        if (studentForm.getPhoto() != null && !studentForm.getPhoto().isEmpty()) {
            student.setPhoto(studentForm.getPhoto().getBytes());
            studentForm.setData(studentForm.getPhoto().getBytes());
            logger.debug("<<<getStudentForm 7>>>");   
        } else if (studentForm.getData() != null) {
            student.setPhoto(studentForm.getData());
        } else if(student.getIdStudent()!=null){
            File serverFile = new File(LOCATION + student.getIdStudent() + "." + TYPE_IMAGE_SUPPORTED_PHOTO);
            if(serverFile.exists()) {
            student.setPhoto(Files.readAllBytes(serverFile.toPath()));
               logger.debug("<<<getStudentForm 7>>>");
            }   
        }
        student.setGuardianSurname(studentForm.getLastNameOfTheGuardian());
        student.setHeight(studentForm.getHeight());
        student.setMailContact(studentForm.getEmail());
        student.setMailContact2(studentForm.getEmail2());
        student.setMailGuardian(studentForm.getEmailOfTheGuardian());
        student.setNameGuardian(studentForm.getNameOfTheGuard());
        student.setPhoneGuardian(studentForm.getTelephonOfTheGuardian());
        student.setPhoneGuardian2(studentForm.getTelephonOfTheGuardian2());
        student.setSex(studentForm.getGender());
        student.setSize(studentForm.getSize());
        student.setSurname(studentForm.getSurname());
        student.setSurname2(studentForm.getSurname2());
        student.setTelephoneContact(studentForm.getPhone1());
        student.setTelephoneContact2(studentForm.getPhone2());
        student.setLastName(studentForm.getLastName());
        student.setWeight(studentForm.getWeight());
        student.setIdEmployment(studentForm.getEmployment());
        student.setIdTeacher(ID_TEACHER_DEFAULT);
           logger.debug("<<<getStudentForm 8 >>>");   
         }catch(NullPointerException e){
            logger.error("Error of null pointer in:"+e.getMessage());
            throw new GenericBZKException("Null pointer exception:"+e.getMessage());
        }
        return student;
    }

    /**
     * Load form student
     *
     * @param id
     * @return new object form student
     * @throws java.io.IOException
     * @throws com.dojogrouppty.error.GenericBZKException
     */
    public StudentForm loadFormStudent(Long id) throws IOException, GenericBZKException {
        StudentForm studentForm = new StudentForm();
        try{
        Student st = studentRepository.findOne(id);
        studentForm.setAddress(st.getAddress());
        studentForm.setAllergy(st.getAllergy());
        studentForm.setBirthdate(dateformat.format(st.getBirthdate()));
        studentForm.setBloodGroup(st.getBloodtype());
        studentForm.setCategory(st.getCategory());
        studentForm.setPhone1(st.getTelephoneContact());
        studentForm.setPhone2(st.getTelephoneContact2());
        studentForm.setIdentificationCard(st.getDocId());
        studentForm.setParentsCertificate(st.getDocIdGuardian());
        studentForm.setFirstName(st.getFirstName());
        studentForm.setLastNameOfTheGuardian(st.getGuardianSurname());
        studentForm.setHeight(st.getHeight());
        studentForm.setEmail(st.getMailContact());
        studentForm.setEmail2(st.getMailContact2());
        studentForm.setEmailOfTheGuardian(st.getMailGuardian());
        studentForm.setNameOfTheGuard(st.getNameGuardian());
        studentForm.setTelephonOfTheGuardian(st.getPhoneGuardian());
        studentForm.setTelephonOfTheGuardian2(st.getPhoneGuardian2());
        studentForm.setGender(st.getSex());
        studentForm.setSize(st.getSize());
        studentForm.setEmployment(st.getIdEmployment());
        studentForm.setSurname(st.getSurname());
        studentForm.setSurname2(st.getSurname2());
        studentForm.setCellPhone1(st.getCellPhone());
        studentForm.setCellPhone2(st.getCellPhone2());
        studentForm.setWeight(st.getWeight());
        studentForm.setLastName(st.getLastName());
        studentForm.setId(id);
        studentForm.setData(st.getPhoto());
        studentForm.setDayOfIncome(dateformat.format(st.getDayOfIncome()));
        }catch(NullPointerException e){
            logger.error("Error of null pointer in:"+e.getMessage());
//            e.printStackTrace();
            throw new GenericBZKException("Null pointer exception:"+e.getMessage());
        }
        return studentForm;
    }

    /**
     * method that you return the last inscriptions
     *
     * @return int
     */
    public int accountLatestInscriptions() {
        return studentRepository.accountLatestInscriptions();
    }

    private void storeOnDisk(MultipartFile file, Long id) throws GenericBZKException {
        try {
            if (file.isEmpty()) {
                throw new GenericBZKException("Failed to store empty file " + file.getOriginalFilename());
            }
            String stFile = new StringBuilder().append(id).append(".").append(TYPE_IMAGE_SUPPORTED_PHOTO).toString();
            Files.deleteIfExists(rootLocation.resolve(stFile));
            Files.copy(file.getInputStream(), rootLocation.resolve(stFile));
        } catch (IOException e) {
            logger.error("Error I/O", e);
            throw new GenericBZKException("Failed to store file " + file.getOriginalFilename());
        }
    }

    private void storeOnDisk(byte[] data, Long id) throws GenericBZKException {
        String stFile = new StringBuilder().append(id).append(".").append(TYPE_IMAGE_SUPPORTED_PHOTO).toString();
        try {
            Files.deleteIfExists(rootLocation.resolve(stFile));
            Files.copy(new ByteArrayInputStream(data), rootLocation.resolve(stFile));
        } catch (IOException ex) {
            logger.error("Error I/O", ex);
            throw new GenericBZKException("Failed to store file ");
        }
    }
    public Student getStudent(Long id){
      return studentRepository.findOne(id);
    }
    public boolean existPhoto(Long id){
      Student s = studentRepository.findOne(id);
      if(s.getPhoto()!=null && s.getPhoto().length>0){
          return true;
      }
      return false;
    }
}
