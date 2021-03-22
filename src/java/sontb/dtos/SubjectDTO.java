/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sontb.dtos;

/**
 *
 * @author ADMIN
 */
public class SubjectDTO {
    
    private String subjectID;
    private String subjectName;
    private int numberOfQuestions;
    private int timeToDo;

    public SubjectDTO(String subjectID, String subjectName, int numberOfQuestions, int timeToDo) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.numberOfQuestions = numberOfQuestions;
        this.timeToDo = timeToDo;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public int getTimeToDo() {
        return timeToDo;
    }

    public void setTimeToDo(int timeToDo) {
        this.timeToDo = timeToDo;
    }
    
    
    
    
}
