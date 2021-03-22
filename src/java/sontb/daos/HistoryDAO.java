/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sontb.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Logger;
import sontb.dtos.AnswerDTO;
import sontb.dtos.HistoryDTO;
import sontb.dtos.QuestionDTO;
import sontb.utils.MyConnection;

/**
 *
 * @author ADMIN
 */
public class HistoryDAO {

    private static final Logger LOGGER = Logger.getLogger(HistoryDAO.class);

    public int saveHistory(HistoryDTO history, ArrayList<QuestionDTO> listQuestion) throws SQLException {
        
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int result = -1;

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                //Luu 1 history roi lay ID de luu detail
                String userID = history.getUserID();
                String subjectID = history.getSubjectID();
                int numOfCorrect = history.getNumOfCorrect();
                float total = history.getTotal();

                String sqlHistory = "INSERT dbo.tblHistory\n"
                        + "        ( userID ,\n"
                        + "          subjectID ,\n"
                        + "          historyDate ,\n"
                        + "          numOfCorrect ,\n"
                        + "          total\n"
                        + "        )\n"
                        + "VALUES  ( '" + userID + "' , -- userID - varchar(50)\n"
                        + "          '" + subjectID + "' , -- subjectID - varchar(20)\n"
                        + "          GETDATE() , -- historyDate - date\n"
                        + "          " + numOfCorrect + " , -- numOfCorrect - int\n"
                        + "          " + total + "  -- total - float\n"
                        + "        )";
                pst = cn.prepareStatement(sqlHistory);
                result = pst.executeUpdate();
                if (result != -1) {
                    //Lay id cua history moi them
                    String sqlHistoryID = "SELECT MAX(historyID) AS historyID FROM dbo.tblHistory";
                    pst = cn.prepareStatement(sqlHistoryID);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        int newHistoryID = rs.getInt("historyID");

                        //Luu Detail 
                        for (QuestionDTO question : listQuestion) {
                            String questionID = question.getQuestionID();
                            String answerSeletedID = "";
                            String sqlHistoryDetail = "";
                            for (AnswerDTO answer : question.getAnswer()) {

                                if (answer.getIsSelected().equals("selected")) {
                                    answerSeletedID = answer.getAnswerID();

                                }
                            }

                            if (!answerSeletedID.equals("")) {
                                sqlHistoryDetail = "INSERT dbo.tblHistory_detail\n"
                                        + "        ( historyID ,\n"
                                        + "          questionID ,\n"
                                        + "          answerID_Selected\n"
                                        + "        )\n"
                                        + "VALUES  ( " + newHistoryID + " , -- historyID - int\n"
                                        + "          " + questionID + " , -- questionID - int\n"
                                        + "          " + answerSeletedID + "  -- answerID_Selected - int\n"
                                        + "        )";
                            } else {
                                answerSeletedID = null;
                                sqlHistoryDetail = "INSERT dbo.tblHistory_detail\n"
                                        + "        ( historyID ,\n"
                                        + "          questionID ,\n"
                                        + "          answerID_Selected\n"
                                        + "        )\n"
                                        + "VALUES  ( " + newHistoryID + " , -- historyID - int\n"
                                        + "          " + questionID + " , -- questionID - int\n"
                                        + "          " + answerSeletedID + "  -- answerID_Selected - int\n"
                                        + "        )";
                            }

                            pst = cn.prepareStatement(sqlHistoryDetail);
                            result = pst.executeUpdate();
                        }

                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return result;

    }

    public ArrayList<HistoryDTO> getHistory(String userID, String subjectID, String activePage) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<HistoryDTO> listHistory = new ArrayList<>();

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {

                int index = Integer.parseInt(activePage);
                index = (index - 1) * 5;
                activePage = index + "";

                String sql = "";
                if (subjectID.equals("")) {
                    sql = "	SELECT historyID, userID, subjectID, historyDate,numOfCorrect,total\n"
                            + "FROM dbo.tblHistory\n"
                            + "	WHERE  userID = '" + userID + "' \n"
                            + "	ORDER BY historyID\n"
                            + "	OFFSET  " + activePage + " ROWS\n"
                            + "	FETCH FIRST 5 ROWS ONLY";
                } else {
                    sql = "SELECT historyID, userID, subjectID, historyDate,numOfCorrect,total\n"
                            + "	FROM dbo.tblHistory\n"
                            + "	WHERE  userID = '" + userID + "'  AND subjectID = '" + subjectID + "'\n"
                            + "	ORDER BY historyID\n"
                            + "	OFFSET  " + activePage + " ROWS\n"
                            + "	FETCH FIRST 5 ROWS ONLY";
                }

                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int historyID = rs.getInt("historyID");
                    String userIDD = rs.getString("userID");
                    String subjectIDD = rs.getString("subjectID");
                    Date historyDate = rs.getDate("historyDate");
                    int numberOfCorrect = rs.getInt("numOfCorrect");
                    float total = rs.getFloat("total");
                    HistoryDTO history = new HistoryDTO(historyID, userIDD, subjectIDD, historyDate, numberOfCorrect, total);
                    listHistory.add(history);
                }
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return listHistory;

    }

    public int getHistoryCount(String userID, String subjectID) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "";
                if (subjectID.equals("")) {
                    sql = "SELECT COUNT(historyID) countt\n"
                            + "	FROM dbo.tblHistory\n"
                            + "	WHERE  userID = '" + userID + "' ";
                } else {
                    sql = "SELECT COUNT(historyID) countt\n"
                            + "	FROM dbo.tblHistory\n"
                            + "	WHERE  userID = '" + userID + "' AND subjectID = '" + subjectID + "'";
                }

                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    count = rs.getInt("countt");
                }

                if (count % 5 == 0) {
                    //chia hết 5 vừa đủ k qua trang mới
                    count = count / 5;
                } else {
                    count /= 5;
                    count++;
                }
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return count;

    }

    public ArrayList<QuestionDTO> getHistoryDetail(String userID, String historyID) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<QuestionDTO> listQuestion = new ArrayList<>();

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "SELECT dbo.tblQuestion.questionID, questionContent, answerID_Selected , answerContent, answerID, isCorrect\n"
                        + "FROM dbo.tblHistory JOIN dbo.tblHistory_detail ON tblHistory_detail.historyID = tblHistory.historyID JOIN dbo.tblQuestion ON tblQuestion.questionID = tblHistory_detail.questionID JOIN dbo.tblAnswer ON tblAnswer.questionID = tblQuestion.questionID\n"
                        + "WHERE tblHistory.historyID = '" + historyID + "' AND userID = '" + userID + "'";

                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();

                while (rs.next()) {

                    String questionID = rs.getString("questionID");
                    String questionContent = rs.getString("questionContent");

                    String answerID = rs.getString("answerID");
                    String answerContent = rs.getString("answerContent");
                    boolean isCorrect = rs.getBoolean("isCorrect");
                    String answerSeletedID = rs.getString("answerID_Selected");

                    //Tao 1 Question sao do addVaoList
                    QuestionDTO question = new QuestionDTO(questionID, null, questionContent, null, null, null);

                    boolean flag = false;
                    for (QuestionDTO ques : listQuestion) {
                        if (!ques.getQuestionID().equals(question.getQuestionID())) {
                            flag = false;
                        } else {
                            flag = true;
                        }
                    }
                    if (!flag) {
                        //check Cau nay co dc chon ko
                        String isSelected = "NOTselected";
                        if (answerSeletedID == null) {
                            isSelected = "NOTselected";
                        } else {
                            if (answerSeletedID.equals(answerID)) {
                                isSelected = "selected";
                            }
                        }
                        // End check
                        AnswerDTO answer = new AnswerDTO(answerID, questionID, answerContent, isCorrect, isSelected);
                        ArrayList<AnswerDTO> listAnswer = new ArrayList<AnswerDTO>();
                        listAnswer.add(answer);
                        question.setAnswer(listAnswer);
                        listQuestion.add(question);
                    } else {
                        for (QuestionDTO ques : listQuestion) {
                            if (ques.getQuestionID().equals(question.getQuestionID())) {
                                ArrayList<AnswerDTO> listAns = ques.getAnswer();
                                // check cau nay co dc chon ko
                                String isSelected = "NOTselected";
                                if (answerSeletedID == null) {
                                    isSelected = "NOTselected";
                                } else {
                                    if (answerSeletedID.equals(answerID)) {
                                        isSelected = "selected";
                                    }
                                }
                                // End check
                                AnswerDTO answer = new AnswerDTO(answerID, questionID, answerContent, isCorrect, isSelected);
                                listAns.add(answer);
                                ques.setAnswer(listAns);
                            }
                        }

                    }
//                for (QuestionDTO value : map.values()) {
//                    listQuestion.add(value);
//                }
                }
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);LOGGER.error("error: ", e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return listQuestion;

    }

}
