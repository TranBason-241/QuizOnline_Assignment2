/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sontb.dtos;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class QuestionDTO {

    private String questionID;
    private String subjectID;
    private String questionContent;
    private ArrayList<AnswerDTO> answer;
    private Date createDate;
    private String status;

    public QuestionDTO(String questionID, String subjectID, String questionContent, ArrayList<AnswerDTO> answer, Date createDate, String status) {
        this.questionID = questionID;
        this.subjectID = subjectID;
        this.questionContent = questionContent;
        this.answer = answer;
        this.createDate = createDate;
        this.status = status;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public ArrayList<AnswerDTO> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<AnswerDTO> answer) {
        this.answer = answer;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
