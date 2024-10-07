import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;

import service.core.Constants;
import service.core.QuotationService;
import service.dodgygeezers.DGQService;

public class GirlsAllowed {

    private static Registry registry;

    @BeforeClass
    public static void setup() {
        QuotationService dgqService = new DGQService();
        try {
            registry = LocateRegistry.createRegistry(1099);
            QuotationService quotationService = (QuotationService) UnicastRemoteObject.exportObject(dgqService, 0);
            registry.bind(Constants.DODGY_GEEZERS_SERVICE, quotationService);
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }

    @Test
    public void connectionTest() throws Exception {
        QuotationService service = (QuotationService) registry.lookup(Constants.DODGY_GEEZERS_SERVICE);
        assertNotNull(service);
    }
}
