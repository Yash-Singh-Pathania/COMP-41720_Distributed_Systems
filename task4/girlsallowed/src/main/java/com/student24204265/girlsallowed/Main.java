package com.student24204265.girlsallowed;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import service.core.Constants;
import service.core.QuotationService;
import service.girlsallowed.GAQService;

public class Main {

    public static void main(String[] args) {
        QuotationService dgqService = new GAQService();
        try {
            // Connect to the RMI Registry - creating the registry will be the responsibility of the broker.
            Registry registry = null;
            if (args.length == 0) {
                registry = LocateRegistry.createRegistry(1099);
            } else {
                registry = LocateRegistry.getRegistry(args[0], 1099);
            }

            // Create the Remote Object
            QuotationService quotationService = (QuotationService) UnicastRemoteObject.exportObject(dgqService, 0);

            // Register the object with the RMI Registry
            registry.bind(Constants.GIRLS_ALLOWED_SERVICE, quotationService);
            System.out.println("Girls  Service bound successfully");

            // Keep the server alive
            System.out.println("STOPPING SERVER SHUTDOWN");
            while (true) {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }
}
