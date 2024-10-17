package service.core;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BrokerService extends Remote {
    public void someBrokerMethod() throws RemoteException;
}
