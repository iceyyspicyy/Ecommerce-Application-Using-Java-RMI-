/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Aryen
 */
public interface LoginInterface extends Remote {
    
    public boolean loginSupplier(String email, String password)throws RemoteException;
    
    public boolean loginUser(String email, String password) throws RemoteException;
    
    public boolean loginAdmin(String email, String password)throws RemoteException;
}
