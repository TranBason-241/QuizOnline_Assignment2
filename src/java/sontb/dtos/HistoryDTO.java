/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sontb.dtos;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class HistoryDTO {

    private int historyID;
    private String userID;
    private String subjectID;
    private Date HistoryDate;
    private int NumOfCorrect;
    private float total;

    public HistoryDTO(int historyID, String userID, String subjectID, Date HistoryDate, int NumOfCorrect, float total) {
        this.historyID = historyID;
        this.userID = userID;
        this.subjectID = subjectID;
        this.HistoryDate = HistoryDate;
        this.NumOfCorrect = NumOfCorrect;
        this.total = total;
    }

    public int getHistoryID() {
        return historyID;
    }

    public void setHistoryID(int historyID) {
        this.historyID = historyID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public Date getHistoryDate() {
        return HistoryDate;
    }

    public void setHistoryDate(Date HistoryDate) {
        this.HistoryDate = HistoryDate;
    }

    public int getNumOfCorrect() {
        return NumOfCorrect;
    }

    public void setNumOfCorrect(int NumOfCorrect) {
        this.NumOfCorrect = NumOfCorrect;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    
    
}
