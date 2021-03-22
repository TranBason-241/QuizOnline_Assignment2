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
import org.apache.log4j.Logger;

import sontb.dtos.UserDTO;
import sontb.utils.MyConnection;

/**
 *
 * @author ADMIN
 */
public class UsersDAO {
private static final Logger LOGGER = Logger.getLogger(UsersDAO.class);
    public UserDTO getUser(String email, String password) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        UserDTO user = null;

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "SELECT userID, userName,roleID,password, status\n"
                        + "FROM dbo.tblUsers \n"
                        + "WHERE userID ='" + email + "' AND password = '" + password + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String userEmail = rs.getString("userID");
                    String name = rs.getString("userName");
                    String roleID = rs.getString("roleID");
                    String userPassword = rs.getString("password");
                    String status = rs.getString("status");
                    user = new UserDTO(userEmail, name, userPassword, roleID, status);

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
        return user;

    }

    public int createuser(String email, String password, String name) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        int rs = -1;

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "INSERT dbo.tblUsers\n"
                        + "        ( userID ,\n"
                        + "          roleID ,\n"
                        + "          userName ,\n"
                        + "          password ,\n"
                        + "          status\n"
                        + "        )\n"
                        + "VALUES  ( '"+email+"' , -- userID - varchar(50)\n"
                        + "          'student' , -- roleID - varchar(20)\n"
                        + "          '"+name+"' , -- userName - varchar(40)\n"
                        + "          '"+password+"' , -- password - varchar(500)\n"
                        + "          'New'  -- status - varchar(20)\n"
                        + "        )";
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

    public int checkUser(String userID) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int result = -1;

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "SELECT userID\n"
                        + "FROM dbo.tblUsers\n"
                        + "WHERE userID = '" + userID + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = 1;
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

}
