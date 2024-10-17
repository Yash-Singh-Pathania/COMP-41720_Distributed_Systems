package com.student24204265.client;

import java.rmi.RemoteException;
import java.text.NumberFormat;

import service.auldfellas.AFQService;
import service.broker.LocalBrokerService;
import service.core.BrokerService;
import service.core.ClientInfo;
import service.core.Quotation;
import service.dodgygeezers.DGQService;
import service.girlsallowed.GAQService;

public class Main {

    // Service instances as static fields
    private static BrokerService brokerService = new LocalBrokerService();
    private static GAQService gaqService = new GAQService();
    private static AFQService afqService = new AFQService();
    private static DGQService dgqService = new DGQService();

    static {
        // Initialize services if needed, currently instantiated above
    }

    public static void main(String[] args) {
        // Use brokerService directly without looking it up
        for (ClientInfo info : clients) {
            displayProfile(info);
            try {
                for (Quotation quotation : brokerService.getQuotations(info)) {
                    displayQuotation(quotation);
                }
            } catch (RemoteException e) {
                System.err.println("Failed to retrieve quotations: " + e.getMessage());
            }
            System.out.println("\n");
        }
    }

    public static void displayProfile(ClientInfo info) {
        System.out.println("|=================================================================================================================|");
        System.out.println("|                                     |                                     |                                     |");
        System.out.println("| Name: " + String.format("%1$-29s", info.name)
                + " | Gender: " + String.format("%1$-27s", (info.gender == ClientInfo.MALE ? "Male" : "Female"))
                + " | Age: " + String.format("%1$-30s", info.age) + " |");
        System.out.println("| Weight/Height: " + String.format("%1$-20s", info.weight + "kg/" + info.height + "m")
                + " | Smoker: " + String.format("%1$-27s", info.smoker ? "YES" : "NO")
                + " | Medical Problems: " + String.format("%1$-17s", info.medicalIssues ? "YES" : "NO") + " |");
        System.out.println("|                                     |                                     |                                     |");
        System.out.println("|=================================================================================================================|");
    }

    public static void displayQuotation(Quotation quotation) {
        System.out.println("| Company: " + String.format("%1$-26s", quotation.company)
                + " | Reference: " + String.format("%1$-24s", quotation.reference)
                + " | Price: " + String.format("%1$-28s", NumberFormat.getCurrencyInstance().format(quotation.price)) + " |");
        System.out.println("|=================================================================================================================|");
    }

    public static final ClientInfo[] clients = {
        new ClientInfo("Niki Collier", ClientInfo.FEMALE, 49, 1.5494, 80, false, false),
        new ClientInfo("Old Geeza", ClientInfo.MALE, 65, 1.6, 100, true, true),
        new ClientInfo("Hannah Montana", ClientInfo.FEMALE, 21, 1.78, 65, false, false),
        new ClientInfo("Rem Collier", ClientInfo.MALE, 49, 1.8, 120, false, true),
        new ClientInfo("Jim Quinn", ClientInfo.MALE, 55, 1.9, 75, true, false),
        new ClientInfo("Donald Duck", ClientInfo.MALE, 35, 0.45, 1.6, false, false)
    };
}
