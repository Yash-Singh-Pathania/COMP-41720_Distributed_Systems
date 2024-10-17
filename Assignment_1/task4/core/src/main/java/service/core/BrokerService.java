package service.core;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BrokerService extends Remote {

    List<Quotation> getQuotations(ClientInfo info) throws RemoteException;
}
