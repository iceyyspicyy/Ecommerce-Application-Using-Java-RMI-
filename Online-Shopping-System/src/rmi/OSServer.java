/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi;

/**
 *
 * @author Aryan
 */
import dao.LoginDaoImp;
import dao.UserDaoImp;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class OSServer {
    public static void main(String args[]){
        try{
            UserDaoImp imp = new UserDaoImp();
            LoginDaoImp lg = new LoginDaoImp();
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.bind("UserDao", imp);    
            reg.bind("LoginInterface", lg);

            System.out.println("3..2..1..Server has launched successfully");


        } catch (AlreadyBoundException | RemoteException e){
            e.printStackTrace();
        }
    }
}
