import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import service.dodgygeezers.DGQService;
import service.core.QuotationService;
import service.core.Constants;

public class Main {

    public static void main(String[] args) {
        QuotationService dgqService = new DGQService();
        try {
            Registry registry;

            if (args.length == 0) {
                registry = LocateRegistry.createRegistry(1099);
                System.out.println("New RMI Registry created at port 1099");
            } else {
                registry = LocateRegistry.getRegistry(args[0], 1099);
                System.out.println("Connected to existing RMI Registry at " + args[0]);
            }

            QuotationService quotationService = (QuotationService) UnicastRemoteObject.exportObject(dgqService, 0);

            registry.bind(Constants.DODGY_GEEZERS_SERVICE, quotationService);
            System.out.println("Dodgy Geezers Service is running and bound to the RMI Registry...");

            while (true) {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }
}
