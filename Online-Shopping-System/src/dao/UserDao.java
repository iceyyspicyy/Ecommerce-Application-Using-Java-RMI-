/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;
import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.swing.JTable;
/**
 *
 * @author ARYEN
 */
public interface UserDao extends Remote{
    public String[] getUserValue(String email)throws RemoteException;
    public void insertPurchase(int id, int uid, String uName, String uPhone, int pid, String pname, int qty, double price, double total, String pDate, String address, String rDate, String supplier, String status)throws RemoteException;
    public int getProductQty(int pid)throws RemoteException;
    public void qtyUpdate(int pid, int qty)throws RemoteException;
    
    public String[] getSuppliers() throws RemoteException;
    
    
    public boolean isUserEmailExist(String email) throws RemoteException;
    public boolean isUserPhoneExist(String phone) throws RemoteException;
            
            
    public void insertUser(int id, String username, String email, String pass, String phone, 
            String seq, String ans, String address1, String address2)throws RemoteException;
    public int getMaxUserRow() throws RemoteException;
    public int getUserId(String email)throws RemoteException;
    public String[] getUserValue(int id) throws RemoteException;
    public void deleteUser(int id) throws RemoteException;
    public void updateUser(int id, String username, String email, String pass, String phone, 
            String seq, String ans, String address1, String address2) throws RemoteException;
    public void getUsersValue(JTable table, String search)throws RemoteException;
    
    //Supplier part
    public int getMaxSuppRow() throws RemoteException;
    public boolean isSuppEmailExist(String email)throws RemoteException;
    public boolean isSuppPhoneExist(String phone)throws RemoteException;
    public boolean isSuppUsernameExist(String name)throws RemoteException;
     public void insertSupp(int id, String username, String email, String pass, String phone,
            String address1, String address2) throws RemoteException;
     public void getSupplierValue(JTable table, String search)throws RemoteException;
     public void updateSupp (int id, String username, String email, String pass, String phone,
            String address1, String address2) throws RemoteException;
     public void deleteSupp(int id) throws RemoteException;
     public int getSupplierId(String email) throws RemoteException;
     public String getSupplierName(String email) throws RemoteException;
     public String[] getSupplierValue(int id) throws RemoteException;
     public int countSuppliers() throws RemoteException;
     
     
     //purchase part

     
     
     
     public void setSuppStatus(int id, String supp, String status)throws RemoteException;
      public void setDateStatus(int id, String rDate, String status)throws RemoteException;
      public void getProductsValue(JTable table, String search, int uid)throws RemoteException;
      public void getProductsValue(JTable table, String search)throws RemoteException;
       public void getOnTheWayProducts(JTable table, String search, String supplier)throws RemoteException;
       public void getSuppDeliProducts(JTable table, String search, String supplier)throws RemoteException;
       
     //product
       public int getMaxProductRow() throws RemoteException;
       public int countCategories()throws RemoteException;
       public String[] getCat()throws RemoteException;
       public boolean isProductIDExist(int id) throws RemoteException;
       public boolean isProCatExist(String pro, String cat)throws RemoteException;
       public void insertProduct(int id, String pname, String cname, int  pqty, double pprice)throws RemoteException;
       public void updateProduct(int id, String pname, String cname, int qty, double price) throws RemoteException;
       public void deleteProduct(int id)throws RemoteException;
//       public void getProductsDValue(JTable table, String search) throws RemoteException;
       
       //category
       public int getMaxCatRow() throws RemoteException;
       public boolean isCatIDExist(int id)throws RemoteException;
       public boolean isCategoryNameExist(String cname)throws RemoteException;
       public void insertCategory(int id, String cname, String desc)throws RemoteException;
       public void getCategoriesValue(JTable table, String search)throws RemoteException ;
       public void updateCategory(int cid, String cname, String cdesc)throws RemoteException;
       public void deleteCategory(int id)throws RemoteException;
       
       public void refund(int id)throws RemoteException;
       public void transaction(JTable table, String search) throws RemoteException;
       
       

     
     
    
    
    
    
}

