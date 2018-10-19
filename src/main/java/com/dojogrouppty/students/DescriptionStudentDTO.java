/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.students;

/**
 *
 * @author lrodriguezn
 */
public class DescriptionStudentDTO {
private Long id;
private String description;

    public DescriptionStudentDTO() {
    }

    public DescriptionStudentDTO(Long idStudent, String description) {
        this.id = idStudent;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
