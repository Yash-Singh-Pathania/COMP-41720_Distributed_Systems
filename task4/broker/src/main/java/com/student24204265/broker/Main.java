package com.student24204265.broker;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import service.broker.LocalBrokerService;
import service.core.BrokerService;
import service.core.Constants;

public class Main {
    public static void main(String[] args) {
        try {
            BrokerService brokerService = new LocalBrokerService();
            BrokerService stub = (BrokerService) UnicastRemoteObject.exportObject(brokerService, 0);

            Registry registry = LocateRegistry.createRegistry(1099);

            registry.rebind(Constants.BROKER_SERVICE, stub);

            System.out.println("Broker service bound and ready for use.");

            while (true) {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.err.println("Broker startup error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}