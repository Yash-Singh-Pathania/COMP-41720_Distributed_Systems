package com.student24204265.broker;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.junit.BeforeClass;
import org.junit.Test;

import service.broker.LocalBrokerService;
import service.core.BrokerService;
import service.core.ClientInfo;

public class BrokerServiceTest {

    private static BrokerService brokerService;

    @BeforeClass
    public static void setup() {
        try {
            // Initialize the broker service (no services registered yet)
            brokerService = new LocalBrokerService();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to set up BrokerService: " + e.getMessage());
        }
    }

    @Test
    public void testNoRegisteredServices() throws RemoteException {
        ClientInfo client = new ClientInfo("Test Client", ClientInfo.MALE, 30, 1.8, 75, false, false);
        // Should return an empty list since no quotation services are registered
        assertTrue("Expected empty quotation list when no services are registered.",
                brokerService.getQuotations(client).isEmpty());
    }
}
