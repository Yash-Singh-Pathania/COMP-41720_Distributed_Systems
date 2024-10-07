package service.core;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface QuotationService extends Remote {
    public Quotation generateQuotation(ClientInfo info) throws RemoteException;
}
