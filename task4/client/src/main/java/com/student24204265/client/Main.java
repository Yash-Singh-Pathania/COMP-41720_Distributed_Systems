package com.student24204265.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import service.core.BrokerService;
import service.core.ClientInfo;
import service.core.Constants;
import service.core.Quotation;

public class Main {

    public static void main(String[] args) {
        try {
            // Set the security manager if needed (for RMI)
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }

            // Get the RMI registry (default host and port)
            Registry registry = LocateRegistry.getRegistry();

            // Lookup the broker service
            BrokerService brokerService = (BrokerService) registry.lookup(Constants.BROKER_SERVICE);

            // Create an array of test clients
            ClientInfo[] clients = {
                new ClientInfo("John Smith", ClientInfo.MALE, 35, 1.75, 70, false, false),
                new ClientInfo("Jane Doe", ClientInfo.FEMALE, 28, 1.65, 60, true, true),
                new ClientInfo("Bob Brown", ClientInfo.MALE, 50, 1.80, 85, true, false), // Add more clients as needed
            };

            // Loop through each client and get quotations
            for (ClientInfo client : clients) {
                System.out.println("==================================================");
                System.out.println("Client: " + client.name);
                System.out.println("==================================================");

                // Get quotations from the broker service
                List<Quotation> quotations = brokerService.getQuotations(client);

                // Check if any quotations were returned
                if (quotations.isEmpty()) {
                    System.out.println("No quotations available for this client.");
                } else {
                    // Loop through each quotation and print it
                    for (Quotation quotation : quotations) {
                        displayQuotation(quotation);
                    }
                }
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Display the quotation in a nice formatted manner
     */
    public static void displayQuotation(Quotation quotation) {
        System.out.println(String.format("| Company: %-35s |", quotation.company));
        System.out.println(String.format("| Reference: %-33s |", quotation.reference));
        System.out.println(String.format("| Price: £%-36.2f |", quotation.price));
        System.out.println("--------------------------------------------------");
    }
}
