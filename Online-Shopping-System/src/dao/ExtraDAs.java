/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import connection.MyConnection;
import java.rmi.RemoteException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aryen
 */
public class ExtraDAs {
    Connection con = MyConnection.getConnection();
    PreparedStatement ps;
    ResultSet rs;
    Statement st;
    JFrame frame;
    
    public int getMaxPurchaseRow()  {
        int row = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select max(id) from purchase");

            while (rs.next()) {
                row = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExtraDAs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row + 1;
    }
    
    
    //get product data
    public void getProductsDValue(JTable table, String search) {
        String sql = "select * from product where concat(pid, pname, cname) like ? order by pid desc";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[5];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getInt(4);
                row[4] = rs.getDouble(5);

                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExtraDAs.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public int getUserId(String email) {
        int id = 0;
        try {
            ps = con.prepareStatement("select uid from user where uemail = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExtraDAs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    } 
    
    
    public void getProductsValue(JTable table, String search, int uid) {
        String sql = "select * from purchase where concat(id, pid, product_name) like ? and uid =? order by pid desc";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ps.setInt(2, uid);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[10];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(5);
                row[2] = rs.getString(6);
                row[3] = rs.getInt(7);
                row[4] = rs.getDouble(8);
                row[5] = rs.getDouble(9);
                row[6] = rs.getString(10);
                row[7] = rs.getString(12);
                row[8] = rs.getString(13);
                row[9] = rs.getString(14);


                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExtraDAs.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public int getMaxCatRow()  {
        int row = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select max(cid) from category");

            while (rs.next()) {
                row = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExtraDAs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row + 1;
    }
    
    
    public void getCategoriesValue(JTable table, String search) {
        String sql = "select * from category where concat(cid, cname) like ? order by cid desc";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[3];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);

                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExtraDAs.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public boolean isCatIDExist(int id){
        try {
            ps = con.prepareStatement("select * from category where cid = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExtraDAs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void updateCategory(int cid, String cname, String cdesc) {
        String sql = "update category set cname=?, cdesc=? where cid = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cname);
            ps.setString(2, cdesc);            
            ps.setInt(3, cid);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Category data successfully updated!");

            }
        } catch (SQLException ex) {
            Logger.getLogger(ExtraDAs.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void getProductsValue(JTable table, String search){
        String sql = "select * from purchase where concat(id, pid,uname, product_name) like ? and status = 'Pending' order by pid desc";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[14];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getInt(5);
                row[5] = rs.getString(6);
                row[6] = rs.getInt(7);
                row[7] = rs.getDouble(8);
                row[8] = rs.getDouble(9);
                row[9] = rs.getString(10);
                row[10] = rs.getString(11);
                row[11] = rs.getString(12);
                row[12] = rs.getString(13);
                row[13] = rs.getString(14);



                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExtraDAs.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public int countSuppliers()  {
        int total = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select count(*) as 'total' from supplier");
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExtraDAs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
    
    public void setSuppStatus(int id, String supp, String status){
        String sql = "update purchase set supplier = ?, status = ? where id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, supp);
            ps.setString(2, status);
            ps.setInt(3, id);
            if(ps.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Supplier successfully selected!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExtraDAs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getSupplierName(String email) {
        String supplierName = "";
        try {
            ps = con.prepareStatement("select sname from supplier where semail = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                supplierName = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExtraDAs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return supplierName;
    }
    
    public void getOnTheWayProducts(JTable table, String search, String supplier) {
        String sql = "select * from purchase where concat(id, pid,uname, product_name) like ? and supplier =? and status = 'On the way' order by pid desc";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ps.setString(2, supplier);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[14];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getInt(5);
                row[5] = rs.getString(6);
                row[6] = rs.getInt(7);
                row[7] = rs.getDouble(8);
                row[8] = rs.getDouble(9);
                row[9] = rs.getString(10);
                row[10] = rs.getString(11);
                row[11] = rs.getString(12);
                row[12] = rs.getString(13);
                row[13] = rs.getString(14);



                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExtraDAs.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void setDateStatus(int id, String rDate, String status){
        String sql = "update purchase set receive_date = ?, status = ? where id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, rDate);
            ps.setString(2, status);
            ps.setInt(3, id);
            if(ps.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Product Successfully Delivered..!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExtraDAs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isUserEmailExist(String email)  {
        try {
            ps = con.prepareStatement("select * from user where uemail = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void insertUser(int id, String username, String email, String pass, String phone, 
            String seq, String ans, String address1, String address2)
    {
        String sql = "insert into user values (?,?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, username);
            ps.setString(3, email);
            ps.setString(4, pass);
            ps.setString(5, phone);
            ps.setString(6, seq);
            ps.setString(7, ans);
            ps.setString(8, address1);
            ps.setString(9, address2);
            if(ps.executeUpdate()> 0){
                JOptionPane.showMessageDialog(null, "User added successfully");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void getUsersValue(JTable table, String search){
        String sql = "select * from user where concat(uid, uname, uemail) like ? order by uid desc";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,"%"+search+"%");
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();   
            Object[] row;
            while(rs.next()){
                row = new Object[9];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getString(8);
                row[8] = rs.getString(9);
                model.addRow(row);
                
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ExtraDAs.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }   
    
    public void updateUser(int id, String username, String email, String pass, String phone, 
            String seq, String ans, String address1, String address2)  {
        String sql = "update user set uname=?, uemail=?, upassword=?, uphone=?, usecqus=?, uans=?, uaddress1=?, uaddress2=? where uid = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, pass);
            ps.setString(4, phone);
            ps.setString(5, seq);
            ps.setString(6, ans);
            ps.setString(7, address1);
            ps.setString(8, address2);
            ps.setInt(9, id);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "User data successfully updated!");

            }
        } catch (SQLException ex) {
            Logger.getLogger(ExtraDAs.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void deleteUser(int id) {
        int x = JOptionPane.showConfirmDialog(null, "Are you sure to delete this account", "Delete Account", JOptionPane.OK_CANCEL_OPTION,0);
        if(x == JOptionPane.OK_OPTION){
            try {
                ps = con.prepareStatement("delete from user where uid=?");
                ps.setInt(1,id);
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null, "Account deleted!");
                    
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(ExtraDAs.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public void getSupplierValue(JTable table, String search)throws RemoteException {
        String sql = "select * from supplier where concat(sid, sname, semail) like ? order by sid desc";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[7];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);

                model.addRow(row);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateSupp (int id, String username, String email, String pass, String phone,
            String address1, String address2) throws RemoteException {
        String sql = "update supplier set sname=?, semail=?, spassword=?, sphone=?, saddress1=?, saddress2=? where sid = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, pass);
            ps.setString(4, phone);
            ps.setString(5, address1);
            ps.setString(6, address2);
            ps.setInt(7, id);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Supplier data successfully updated!");

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public String[] getSupplierValue(int id) throws RemoteException {
        String[] value = new String[7];
        try {
            ps = con.prepareStatement("select * from supplier where sid = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                value[0] = rs.getString(1);
                value[1] = rs.getString(2);
                value[2] = rs.getString(3);
                value[3] = rs.getString(4);
                value[4] = rs.getString(5);
                value[5] = rs.getString(6);
                value[6] = rs.getString(7);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }
    
    public void deleteSupp(int id) throws RemoteException {
        int x = JOptionPane.showConfirmDialog(null, "Are you sure to delete this account", "Delete Account", JOptionPane.OK_CANCEL_OPTION, 0);
        if (x == JOptionPane.OK_OPTION) {
            try {
                ps = con.prepareStatement("delete from supplier where sid=?");
                ps.setInt(1, id);
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Account deleted!");

                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
    public int getMaxSuppRow() throws RemoteException {
        int row = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select max(sid) from supplier");

            while (rs.next()) {
                row = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row + 1;
    }
    
    public boolean isSuppEmailExist(String email)throws RemoteException {
        try {
            ps = con.prepareStatement("select * from supplier where semail = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    //check phone number already exists

    public boolean isSuppPhoneExist(String phone)throws RemoteException {
        try {
            ps = con.prepareStatement("select * from supplier where sphone = ?");
            ps.setString(1, phone);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //check supplier username already exists

    public boolean isSuppUsernameExist(String name)throws RemoteException {
        try {
            ps = con.prepareStatement("select * from supplier where sname = ?");
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //insert data into supplier table database

    public void insertSupp(int id, String username, String email, String pass, String phone,
            String address1, String address2) throws RemoteException{
        String sql = "insert into supplier values (?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, username);
            ps.setString(3, email);
            ps.setString(4, pass);
            ps.setString(5, phone);
            ps.setString(6, address1);
            ps.setString(7, address2);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Supplier added successfully");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
      
    public int getSupplierId(String email) throws RemoteException {
        int id = 0;
        try {
            ps = con.prepareStatement("select sid from supplier where semail = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public String[] getSuppliers() throws RemoteException{
        String[] suppliers = new String[countSuppliers()];
        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from supplier");
            int i = 0;
            while(rs.next()){
                suppliers[i] = rs.getString(2);
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return suppliers;
    }
    
    public String[] getUserValue(String email)throws RemoteException {
        String[] value = new String[5];
        String sql = "select uid,uname,uphone,uaddress1,uaddress2  from user where uemail = '" + email + "'";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                value[0] = rs.getString(1);
                value[1] = rs.getString(2);
                value[2] = rs.getString(3);
                value[3] = rs.getString(4);
                value[4] = rs.getString(5);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }

    //insert data into purchase table
    public void insertPurchase(int id, int uid, String uName, String uPhone, int pid, String pname, int qty, double price, double total, String pDate, String address, String rDate, String supplier, String status)throws RemoteException {
        String sql = "insert into purchase values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, uid);
            ps.setString(3, uName);
            ps.setString(4, uPhone);
            ps.setInt(5, pid);
            ps.setString(6, pname);
            ps.setInt(7, qty);
            ps.setDouble(8, price);
            ps.setDouble(9, total);
            ps.setString(10, pDate);
            ps.setString(11, address);
            ps.setString(12, rDate);
            ps.setString(13, supplier);
            ps.setString(14, status);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //getProduct quantity
    public int getProductQty(int pid)throws RemoteException {
        int qty = 0;

        try {
            st = con.createStatement();
            rs = st.executeQuery("select pqty from product where pid =" + pid + "");
            if (rs.next()) {
                qty = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return qty;
    }

    //update product quantity
    public void qtyUpdate(int pid, int qty)throws RemoteException {
        String sql = "update product set pqty =? where pid =?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, qty);
            ps.setInt(2, pid);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    //get supplier delivered products
    public void getSuppDeliProducts(JTable table, String search, String supplier)throws RemoteException {
        String sql = "select * from purchase where concat(id, pid,uname, product_name) like ? and supplier =? and status = 'Received' order by pid desc";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ps.setString(2, supplier);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[14];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getInt(5);
                row[5] = rs.getString(6);
                row[6] = rs.getInt(7);
                row[7] = rs.getDouble(8);
                row[8] = rs.getDouble(9);
                row[9] = rs.getString(10);
                row[10] = rs.getString(11);
                row[11] = rs.getString(12);
                row[12] = rs.getString(13);
                row[13] = rs.getString(14);



                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    //for refund 
 
    public void refund(int id)throws RemoteException{
        int x = JOptionPane.showConfirmDialog(null, "Are you sure to refund this product", "Refund Product", JOptionPane.OK_CANCEL_OPTION,0);
        if(x == JOptionPane.OK_OPTION){
            try {
                ps = con.prepareStatement("delete from purchase where id=?");
                ps.setInt(1,id);
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null, "Product Refund Sucessful!");
                    
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    //transactopm

     public void transaction(JTable table, String search)throws RemoteException {
        String sql = "select * from purchase where concat(id, pid,uid) like ? and status = 'Received' order by id desc";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[8];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getInt(5);
                row[3] = rs.getInt(7);
                row[4] = rs.getDouble(8);
                row[5] = rs.getDouble(9);
                row[6] = rs.getString(12);
                row[7] = rs.getString(13);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
     
     
     //*********************************Product
     
     
     
     
     
     //get product table max row
    
    public int getMaxProductRow() throws RemoteException {
        int row = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select max(pid) from product");

            while (rs.next()) {
                row = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row + 1;
    }
   
    public int countCategories()throws RemoteException{
        int total = 0;
        try {
            st= con.createStatement();
            rs = st.executeQuery("select count(*) as 'total' from category");
            if(rs.next()){
                total = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;        
    }
    
    public String[] getCat()throws RemoteException{
        String[] categories = new String[countCategories()];
        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from category");
            int i = 0;
            while(rs.next()){
                categories[i] = rs.getString(2);
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }
    
     //check product id already exists
    public boolean isProductIDExist(int id) throws RemoteException{
        try {
            ps = con.prepareStatement("select * from product where pid = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
      //check product and category exists
    public boolean isProCatExist(String pro, String cat)throws RemoteException {
        try {
            ps = con.prepareStatement("select * from product where pname =? and cname =?");
            ps.setString(1, pro);
            ps.setString(2, cat);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //insert data into product table database
    public void insertProduct(int id, String pname, String cname, int  pqty, double pprice)throws RemoteException {
        String sql = "insert into product values (?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, pname);
            ps.setString(3, cname);
            ps.setInt(4,pqty);
            ps.setDouble(5,pprice);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Product added successfully");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//    
//    //get product data
//    public void getProductsDValue(JTable table, String search) throws RemoteException{
//        String sql = "select * from product where concat(pid, pname, cname) like ? order by pid desc";
//        try {
//            ps = con.prepareStatement(sql);
//            ps.setString(1, "%" + search + "%");
//            rs = ps.executeQuery();
//            DefaultTableModel model = (DefaultTableModel) table.getModel();
//            Object[] row;
//            while (rs.next()) {
//                row = new Object[5];
//                row[0] = rs.getInt(1);
//                row[1] = rs.getString(2);
//                row[2] = rs.getString(3);
//                row[3] = rs.getInt(4);
//                row[4] = rs.getDouble(5);
//
//                model.addRow(row);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }

    
     //update product data
    public void updateProduct(int id, String pname, String cname, int qty, double price) throws RemoteException{
        String sql = "update product set pname=?, cname=?, pqty=?, pprice=? where pid = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pname);
            ps.setString(2, cname);
            ps.setInt(3, qty);
            ps.setDouble(4,price);
            ps.setInt(5,id);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Product successfully updated!");

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
        //delete product
    
    public void deleteProduct(int id)throws RemoteException{
        int x = JOptionPane.showConfirmDialog(null, "Are you sure to delete this product", "Delete Product", JOptionPane.OK_CANCEL_OPTION,0);
        if(x == JOptionPane.OK_OPTION){
            try {
                ps = con.prepareStatement("delete from product where pid=?");
                ps.setInt(1,id);
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null, "Product deleted!");
                    
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    

    
    
   
    


    //check category name already exists
    public boolean isCategoryNameExist(String cname)throws RemoteException {
        try {
            ps = con.prepareStatement("select * from category where cname = ?");
            ps.setString(1, cname);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //insert data into category table database
    public void insertCategory(int id, String cname, String desc)throws RemoteException {
        String sql = "insert into category values (?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, cname);
            ps.setString(3, desc);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Category added successfully");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    
    
    
    //delete category
    
    public void deleteCategory(int id)throws RemoteException{
        int x = JOptionPane.showConfirmDialog(null, "Are you sure to delete this category", "Delete Category", JOptionPane.OK_CANCEL_OPTION,0);
        if(x == JOptionPane.OK_OPTION){
            try {
                ps = con.prepareStatement("delete from category where cid=?");
                ps.setInt(1,id);
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null, "Creatory deleted!");
                    
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    
    
}



