package rmi;

/**
 *
 * @author aryen
 */

import dao.LoginInterface;
import dao.UserDao;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class OSClient {
    private String host = "127.0.0.1";
    private String serviceName = "UserDao";
    private String service2Name = "LoginInterface";
    private int port = 1099;


    // return UserDao object
    public UserDao getRemoteService(){
        try{
            
            Registry reg = LocateRegistry.getRegistry(host, port);
            return (UserDao) reg.lookup(serviceName);

        } catch (RemoteException | NotBoundException e){
            e.printStackTrace();
            return null;
        }
    }
     public LoginInterface getRemote2Service(){
        try{
            
            Registry reg = LocateRegistry.getRegistry(host, port);
            return (LoginInterface) reg.lookup(service2Name);

        } catch (RemoteException | NotBoundException e){
            e.printStackTrace();
            return null;
        }
    }

    

}