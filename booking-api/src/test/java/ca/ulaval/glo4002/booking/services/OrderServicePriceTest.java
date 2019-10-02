package ca.ulaval.glo4002.booking.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderServicePriceTest {

    private OrderServiceContextForPriceTests context;

    @BeforeEach
    void setUp(){ context = new OrderServiceContextForPriceTests();}

    /*
    @Test
    void whenPassesIsPackage_ThenNoRebateIsApplied(){
        context.aOrder.setOrderItems(Collections.nCopies(4, context.aSupernovaPackagePass));

        Double price = context.subject.getOrderPrice(context.aOrder);

        assertEquals(java.util.Optional.of(6000000.00), price);
    }
    */
}
