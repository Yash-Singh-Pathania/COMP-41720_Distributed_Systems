package service.broker;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import java.util.List;

import service.core.BrokerService;
import service.core.ClientInfo;
import service.core.Quotation;
import service.core.QuotationService;

/**
 * Implementation of the broker service that uses the RMI Registry.
 */
public class LocalBrokerService implements BrokerService {

    @Override
    public List<Quotation> getQuotations(ClientInfo info) throws RemoteException {
        List<Quotation> quotations = new LinkedList<>();
        try {
            // Connect to the RMI Registry
            Registry registry = LocateRegistry.getRegistry();

            // Get the list of registered services
            String[] services = registry.list();

            for (String serviceName : services) {
                if (serviceName.startsWith("qs-")) {
                    // Lookup the service
                    QuotationService service = (QuotationService) registry.lookup(serviceName);

                    // Generate a quotation
                    quotations.add(service.generateQuotation(info));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quotations;
    }
}
