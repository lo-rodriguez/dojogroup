/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.common;

import com.dojogrouppty.students.StudentForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FileValidator implements Validator {
 private static final Logger logger
            = LoggerFactory.getLogger(FileValidator.class);
    @Override
    public boolean supports(Class<?> clazz) {
        return StudentForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        StudentForm form = (StudentForm) target;
        long MAX_FILE_SIZE = Long.parseLong(System.getProperty("MAX_FILE_SIZE"));
        logger.debug("Into validate..");
        if (form.getPhoto() != null && form.getPhoto().isEmpty() && form.getPhoto().getSize()>MAX_FILE_SIZE){
            errors.rejectValue("photo", "photo.size");
        }
    }
}