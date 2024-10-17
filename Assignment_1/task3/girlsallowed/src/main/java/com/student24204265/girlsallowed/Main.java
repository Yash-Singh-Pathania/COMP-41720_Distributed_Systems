package com.student24204265.girlsallowed;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import service.core.Constants;
import service.core.QuotationService;
import service.dodgygeezers.DGQService;

public class Main {

    public static void main(String[] args) {
        QuotationService dgqService = new DGQService();
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
            registry.bind(Constants.DODGY_GEEZERS_SERVICE, quotationService);
            System.out.println("Dodgy Geezers Service bound successfully");

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
