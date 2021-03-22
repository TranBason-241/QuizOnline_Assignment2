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
import sontb.dtos.QuestionDTO;
import sontb.dtos.SubjectDTO;
import sontb.utils.MyConnection;

/**
 *
 * @author ADMIN
 */
public class QuestionDAO {

    private static final Logger LOGGER = Logger.getLogger(QuestionDAO.class);

    public int getCount(String optionSearch, String valueSearch) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = -1;

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "";
                if (optionSearch.equals("") && optionSearch.equals("")) {
                    sql = "SELECT COUNT(questionID) countt \n"
                            + "FROM dbo.tblQuestion";
                } else {
                    if (optionSearch.equals("txtSubjectSEARCH")) {
                        sql = "SELECT COUNT(questionID) countt \n"
                                + "FROM dbo.tblQuestion JOIN dbo.tblSubjects ON tblSubjects.subjectID = tblQuestion.subjectID\n"
                                + "WHERE tblQuestion.subjectID = '" + valueSearch + "'";
                    }
                    if (optionSearch.equals("txtStatusSEARCH")) {
                        sql = "SELECT COUNT(questionID) countt \n"
                                + "FROM dbo.tblQuestion\n"
                                + "WHERE status = '" + valueSearch + "'";
                    }
                    if (optionSearch.equals("txtNameSEARCH")) {
                        sql = "SELECT COUNT(questionID) countt \n"
                                + "FROM dbo.tblQuestion JOIN dbo.tblSubjects ON tblSubjects.subjectID = tblQuestion.subjectID\n"
                                + "WHERE dbo.tblQuestion.questionContent LIKE '%" + valueSearch + "%'";
                    }
                }

                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("countt");
                }

                if (count % 20 == 0) {
                    //chia hết 20 vừa đủ k qua trang mới
                    count = count / 20;
                } else {
                    count /= 20;
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

    public ArrayList<QuestionDTO> getQuestions(String activePage, String optionSearch, String valueSearch) throws SQLException {

        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<QuestionDTO> listQuestion = new ArrayList<>();
//        HashMap<String, QuestionDTO> map = new HashMap<String, QuestionDTO>();
        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                //Xử lí page
                int index = Integer.parseInt(activePage);
                index = (index - 1) * 80;
                activePage = index + "";

                String sql = "";
                //Case1: Dữ liệu search và option search đưa vào đều rỗng
                if (optionSearch.equals("") && optionSearch.equals("")) {
                    sql = "SELECT tblQuestion.subjectID, numOfQuestion,timeToDO, tblAnswer.questionID,questionContent,createDate,status, answerID,answerContent,isCorrect\n"
                            + "FROM dbo.tblSubjects JOIN dbo.tblQuestion ON tblQuestion.subjectID = tblSubjects.subjectID JOIN dbo.tblAnswer ON tblAnswer.questionID = tblQuestion.questionID\n"
                            + "ORDER BY tblQuestion.subjectID\n"
                            + "OFFSET " + activePage + "  ROWS\n"
                            + "FETCH FIRST 80 ROWS ONLY";
                }
                if (optionSearch.equals("txtSubjectSEARCH")) {
                    sql = "SELECT tblQuestion.subjectID, numOfQuestion,timeToDO, tblAnswer.questionID,questionContent,createDate,status, answerID,answerContent,isCorrect\n"
                            + " FROM dbo.tblSubjects JOIN dbo.tblQuestion ON tblQuestion.subjectID = tblSubjects.subjectID JOIN dbo.tblAnswer ON tblAnswer.questionID = tblQuestion.questionID\n"
                            + "	WHERE tblQuestion.subjectID = '" + valueSearch + "'\n"
                            + "	ORDER BY tblQuestion.subjectID\n"
                            + "	OFFSET " + activePage + "  ROWS\n"
                            + "	FETCH FIRST 80 ROWS ONLY";
                }
                if (optionSearch.equals("txtStatusSEARCH")) {
                    sql = "SELECT tblQuestion.subjectID, numOfQuestion,timeToDO, tblAnswer.questionID,questionContent,createDate,status, answerID,answerContent,isCorrect\n"
                            + "FROM dbo.tblSubjects JOIN dbo.tblQuestion ON tblQuestion.subjectID = tblSubjects.subjectID JOIN dbo.tblAnswer ON tblAnswer.questionID = tblQuestion.questionID\n"
                            + "	WHERE status = '" + valueSearch + "'\n"
                            + "	ORDER BY tblQuestion.subjectID\n"
                            + "	OFFSET " + activePage + "  ROWS\n"
                            + "	FETCH FIRST 80 ROWS ONLY";
                }

                if (optionSearch.equals("txtNameSEARCH")) {
                    sql = "SELECT tblQuestion.subjectID, numOfQuestion,timeToDO, tblAnswer.questionID,questionContent,createDate,status, answerID,answerContent,isCorrect\n"
                            + "FROM dbo.tblSubjects JOIN dbo.tblQuestion ON tblQuestion.subjectID = tblSubjects.subjectID JOIN dbo.tblAnswer ON tblAnswer.questionID = tblQuestion.questionID\n"
                            + "WHERE questionContent LIKE '%" + valueSearch + "%'\n"
                            + "	ORDER BY tblQuestion.subjectID\n"
                            + "	OFFSET " + activePage + "  ROWS\n"
                            + "	FETCH FIRST 80 ROWS ONLY";
                }

                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();

                while (rs.next()) {
                    String subjectID = rs.getString("subjectID");

                    String questionID = rs.getString("questionID");
                    String questionContent = rs.getString("questionContent");
                    Date createDate = rs.getDate("createDate");
                    String status = rs.getString("status");

                    String answerID = rs.getString("answerID");
                    String answerContent = rs.getString("answerContent");
                    boolean isCorrect = rs.getBoolean("isCorrect");

                    //Tao 1 Question sao do addVaoList
                    QuestionDTO question = new QuestionDTO(questionID, subjectID, questionContent, null, createDate, status);

                    boolean flag = false;
                    for (QuestionDTO ques : listQuestion) {
                        if (!ques.getQuestionID().equals(question.getQuestionID())) {
                            flag = false;
                        } else {
                            flag = true;
                        }
                    }
                    if (!flag) {
                        AnswerDTO answer = new AnswerDTO(answerID, questionID, answerContent, isCorrect, null);
                        ArrayList<AnswerDTO> listAnswer = new ArrayList<AnswerDTO>();
                        listAnswer.add(answer);
                        question.setAnswer(listAnswer);
                        listQuestion.add(question);
                    } else {
                        for (QuestionDTO ques : listQuestion) {
                            if (ques.getQuestionID().equals(question.getQuestionID())) {
                                ArrayList<AnswerDTO> listAns = ques.getAnswer();
                                AnswerDTO answer = new AnswerDTO(answerID, questionID, answerContent, isCorrect, null);
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
        return listQuestion;

    }

    public int updateQuestion(String questionID, String subjectID, String status, String questionContent) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        int rs = -1;

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "UPDATE dbo.tblQuestion SET subjectID='" + subjectID + "', status = '" + status + "',questionContent='" + questionContent + "'\n"
                        + "WHERE questionID = '" + questionID + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeUpdate();

            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return rs;
    }

    public ArrayList<AnswerDTO> getListAnswer(String id) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<AnswerDTO> list = new ArrayList<>();

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "SELECT  answerID,answerContent,isCorrect,a.questionID\n"
                        + "FROM dbo.tblQuestion a JOIN dbo.tblAnswer ON tblAnswer.questionID = a.questionID\n"
                        + "WHERE a.questionID = '" + id + "'";

                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();

                while (rs.next()) {

                    String questionID = rs.getString("questionID");
                    String answerID = rs.getString("answerID");
                    String answerContent = rs.getString("answerContent");
                    boolean isCorrect = rs.getBoolean("isCorrect");

                    AnswerDTO answer = new AnswerDTO(answerID, questionID, answerContent, isCorrect, null);
                    list.add(answer);
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
        return list;

    }

    public int deleteQuestion(String questionID) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        int rs = -1;

        try {
            cn = MyConnection.getConnection();

            if (cn != null) {
                String sql = "UPDATE dbo.tblQuestion SET status = 'deActive'\n"
                        + "WHERE questionID = '" + questionID + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeUpdate();

            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return rs;

    }

    public int updateAnswerIsCorrect(String answerID, int value) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        int rs = -1;

        try {
            cn = MyConnection.getConnection();

            if (cn != null) {
                String sql = "UPDATE dbo.tblAnswer SET isCorrect = " + value + "\n"
                        + "WHERE answerID = '" + answerID + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeUpdate();

            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return rs;

    }

    public int updateAnswerContent(String answerID, String content) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        int rs = -1;

        try {
            cn = MyConnection.getConnection();

            if (cn != null) {
                String sql = "UPDATE dbo.tblAnswer SET answerContent = '" + content + "'\n"
                        + "WHERE answerID = '" + answerID + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeUpdate();

            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
            
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return rs;

    }

    public int createQuestion(String questionContent, String subject, ArrayList<AnswerDTO> listAnswer) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int result = -1;

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                //callBack
                cn.setAutoCommit(false);
                //thêm 1 question
                String sql = "INSERT dbo.tblQuestion\n"
                        + "        ( subjectID ,\n"
                        + "          questionContent ,\n"
                        + "          createDate ,\n"
                        + "          status\n"
                        + "        )\n"
                        + "VALUES  ( '" + subject + "' , -- subjectID - varchar(20)\n"
                        + "          '" + questionContent + "' , -- questionContent - varchar(1000)\n"
                        + "          GETDATE() , -- createDate - date\n"
                        + "          'active'  -- status - varchar(20)\n"
                        + "        )";
                pst = cn.prepareStatement(sql);
                result = pst.executeUpdate();
                if (result != -1) {
                    //Lấy id của question mới thêm là question cuối để add answer
                    String sqlQuestionID = "SELECT MAX(questionID) AS questionID FROM dbo.tblQuestion";
                    pst = cn.prepareStatement(sqlQuestionID);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        int questionID = rs.getInt("questionID");

                        //Xử lí thêm answer từ mảng answer nè
                        for (AnswerDTO ans : listAnswer) {

                            String answerContent = ans.getAnswerContent();
                            boolean isCorrect = ans.isIsCorrect();
                            String sqlCreateAnswer = "INSERT dbo.tblAnswer\n"
                                    + "		        ( questionID ,\n"
                                    + "		          isCorrect ,\n"
                                    + "		          answerContent\n"
                                    + "		        )\n"
                                    + "		VALUES  ( " + questionID + " , -- questionID - int\n"
                                    + "		          '" + isCorrect + "' , -- isCorrect - bit\n"
                                    + "		          '" + answerContent + "'  -- answerContent - varchar(1000)\n"
                                    + "		        )";
                            pst = cn.prepareCall(sqlCreateAnswer);
                            result = pst.executeUpdate();
                        }
                    }
                } else {
                    result = -1;
                }
                //End callback
                cn.commit();
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
            
            cn.rollback();
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

    public ArrayList<QuestionDTO> getQuestionExam(SubjectDTO subject) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<QuestionDTO> listQuestion = new ArrayList<>();

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String subID = subject.getSubjectID();
                int number = subject.getNumberOfQuestions();

                String sql = "SELECT tmp.questionID,tmp.subjectID,questionContent, answerID, answerContent, isCorrect\n"
                        + "FROM   dbo.tblAnswer JOIN (SELECT TOP " + number + " questionID,questionContent,tblQuestion.subjectID\n"
                        + "FROM dbo.tblQuestion JOIN dbo.tblSubjects ON tblSubjects.subjectID = tblQuestion.subjectID\n"
                        + "WHERE tblQuestion.subjectID = '" + subID + "' AND status = 'active'\n"
                        + "ORDER BY NEWID()) tmp ON tmp.questionID = tblAnswer.questionID";

                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();

                while (rs.next()) {
                    String subjectID = rs.getString("subjectID");
                    String questionID = rs.getString("questionID");
                    String questionContent = rs.getString("questionContent");

                    String answerID = rs.getString("answerID");
                    String answerContent = rs.getString("answerContent");
                    boolean isCorrect = rs.getBoolean("isCorrect");

                    //Tao 1 Question sao do addVaoList
                    QuestionDTO question = new QuestionDTO(questionID, subjectID, questionContent, null, null, null);

                    boolean flag = false;
                    for (QuestionDTO ques : listQuestion) {
                        if (!ques.getQuestionID().equals(question.getQuestionID())) {
                            flag = false;
                        } else {
                            flag = true;
                        }
                    }
                    if (!flag) {
                        AnswerDTO answer = new AnswerDTO(answerID, questionID, answerContent, isCorrect, null);
                        ArrayList<AnswerDTO> listAnswer = new ArrayList<AnswerDTO>();
                        listAnswer.add(answer);
                        question.setAnswer(listAnswer);
                        listQuestion.add(question);
                    } else {
                        for (QuestionDTO ques : listQuestion) {
                            if (ques.getQuestionID().equals(question.getQuestionID())) {
                                ArrayList<AnswerDTO> listAns = ques.getAnswer();
                                AnswerDTO answer = new AnswerDTO(answerID, questionID, answerContent, isCorrect, null);
                                listAns.add(answer);
                                ques.setAnswer(listAns);
                            }
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
        return listQuestion;

    }

}
