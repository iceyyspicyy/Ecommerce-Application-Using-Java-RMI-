/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import connection.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Administrator
 */
public class LoginDaoImp extends UnicastRemoteObject implements LoginInterface{
    public LoginDaoImp() throws RemoteException {
        super();
    }

   @Override
     public boolean loginUser(String email, String password) throws RemoteException {
        try {
            Connection con = MyConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from user where uemail =? and upassword =?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

     @Override
    public boolean loginSupplier(String email, String password)throws RemoteException {
        try {
            Connection con = MyConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from supplier where semail =? and spassword =?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean loginAdmin(String email, String password)throws RemoteException {
        try {
            Connection con = MyConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from admin where email =? and password =?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
