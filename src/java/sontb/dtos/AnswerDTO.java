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
public class AnswerDTO {

    private String answerID;
    private String questionID;
    private String answerContent;
    private boolean isCorrect;
    private String isSelected;

    public AnswerDTO(String answerID, String questionID, String answerContent, boolean isCorrect, String isSelected) {
        this.answerID = answerID;
        this.questionID = questionID;
        this.answerContent = answerContent;
        this.isCorrect = isCorrect;
        this.isSelected = isSelected;
    }

    public String getAnswerID() {
        return answerID;
    }

    public void setAnswerID(String answerID) {
        this.answerID = answerID;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public boolean isIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public String getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(String isSelected) {
        this.isSelected = isSelected;
    }

   
    
    
}
