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
import org.apache.log4j.Logger;
import sontb.dtos.SubjectDTO;
import sontb.utils.MyConnection;

/**
 *
 * @author ADMIN
 */
public class SubjectDAO {
    private static final Logger LOGGER = Logger.getLogger(SubjectDAO.class);
    public ArrayList<SubjectDTO> getSubject() throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<SubjectDTO> listSubject = new ArrayList<>();

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {

                String sql = "SELECT subjectID, subjectName,numOfQuestion,timeToDO\n"
                        + "FROM dbo.tblSubjects";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();

                while (rs.next()) {
                    String subjectID = rs.getString("subjectID");
                    String subjectName = rs.getString("subjectName");
                    int numOfQuestion = rs.getInt("numOfQuestion");
                    int timeToDo = rs.getInt("timeToDO");
                    SubjectDTO subject = new SubjectDTO(subjectID, subjectName, numOfQuestion, timeToDo);
                    listSubject.add(subject);
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
        return listSubject;
    }

}
