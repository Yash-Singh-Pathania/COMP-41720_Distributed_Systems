
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;

import service.auldfellas.AFQService;
import service.core.ClientInfo;
import service.core.Constants;
import service.core.Quotation;
import service.core.QuotationService;

public class AuldfellasUnitTest {

    private static Registry registry;

    @BeforeClass
    public static void setup() {
        QuotationService afqService = new AFQService();
        try {
            registry = LocateRegistry.createRegistry(1099);
            QuotationService quotationService = (QuotationService) UnicastRemoteObject.exportObject(afqService, 0);
            registry.bind(Constants.AULD_FELLAS_SERVICE, quotationService);
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }

    @Test
    public void connectionTest() throws Exception {
        QuotationService service = (QuotationService) registry.lookup(Constants.AULD_FELLAS_SERVICE);
        assertNotNull(service);
    }

    // New test method to check generateQuotation()
    @Test
    public void generateQuotationTest() throws Exception {
        QuotationService service = (QuotationService) registry.lookup(Constants.AULD_FELLAS_SERVICE);
        assertNotNull(service);  // Ensure the service lookup is successful

        // Use one of the ClientInfo objects from client.Main for testing
        ClientInfo clientInfo = new ClientInfo("Niki Collier", ClientInfo.FEMALE, 49, 1.5494, 80, false, false);

        // Generate a quotation for the given client info
        Quotation quotation = service.generateQuotation(clientInfo);
        assertNotNull(quotation);  // Ensure the quotation is not null

        // Optionally, print the result for verification
        System.out.println("Generated Quotation: " + quotation.company + " - " + quotation.reference + " - " + quotation.price);
    }
}
