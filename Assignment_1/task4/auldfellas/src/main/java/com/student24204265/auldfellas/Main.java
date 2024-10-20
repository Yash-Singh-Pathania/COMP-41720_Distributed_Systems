package com.student24204265.auldfellas;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import service.auldfellas.AFQService;
import service.core.Constants;
import service.core.QuotationService;

public class Main {

    public static void main(String[] args) {
        QuotationService afqService = new AFQService();
        try {
            Registry registry = null;
            if (args.length == 0) {
                registry = LocateRegistry.createRegistry(1099);
            } else {
                registry = LocateRegistry.getRegistry(args[0], 1099);
            }

            // Create the Remote Object
            QuotationService quotationService = (QuotationService) UnicastRemoteObject.exportObject(afqService, 0);

            registry.bind(Constants.AULD_FELLAS_SERVICE, quotationService);
            System.out.println("Auld Fellas Service bound successfully");

            System.out.println("STOPPING SERVER SHUTDOWN");
            while (true) {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }
}
