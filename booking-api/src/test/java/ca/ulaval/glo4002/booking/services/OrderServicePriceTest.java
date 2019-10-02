package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.constants.PassConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderServicePriceTest {

    private static final int AMOUNT_OF_SUPERGIANT_PASSES = 5;
    private static final int AMOUNT_OF_NEBULA_PASSES = 4;
    private static final double DELTA = 0.01;
    private OrderServiceTestContext context;

    @BeforeEach
    void setUp(){ context = new OrderServiceTestContext();}


    @Test
    void whenPassesIsPackage_ThenNoRebateIsApplied(){
        context.aOrder.setPasses(Collections.singletonList(context.aSupernovaPackagePass));

        Double price = context.subject.getOrderPrice(context.aOrder);

        assertEquals(PassConstants.Categories.SUPERNOVA_PACKAGE_PRICE, price, DELTA);
    }

    @Test
    void whenPassesIsOneSinglePass_ThenNoRebateIsApplied(){
        context.aOrder.setPasses(Collections.singletonList(context.aSupergiantSinglePass));

        Double price = context.subject.getOrderPrice(context.aOrder);

        assertEquals(PassConstants.Categories.SUPERGIANT_SINGLE_PASS_PRICE, price, DELTA);
    }

    @Test
    void whenPassesHasFiveOrMoreSupergiantSinglePass_thenTenPercentRebateIsApplied(){
        context.aOrder.setPasses(Collections.nCopies(AMOUNT_OF_SUPERGIANT_PASSES, context.aSupergiantSinglePass));

        Double price = context.subject.getOrderPrice(context.aOrder);
        double expectedPrice = AMOUNT_OF_SUPERGIANT_PASSES *
                PassConstants.Categories.SUPERGIANT_SINGLE_PASS_PRICE *
                PassConstants.Categories.SUPERGIANT_SINGLE_PASS_REBATE;

        assertEquals(expectedPrice, price, DELTA);
    }

    @Test
    void whenPassesHasMoreThanThreeNebulaSinglePass_thenTenPercentRebateIsApplied(){
        context.aOrder.setPasses(Collections.nCopies(AMOUNT_OF_NEBULA_PASSES, context.aNebulaSinglePass));

        Double price = context.subject.getOrderPrice(context.aOrder);
        double expectedPrice = AMOUNT_OF_NEBULA_PASSES *
                PassConstants.Categories.NEBULA_SINGLE_PASS_PRICE *
                PassConstants.Categories.NEBULA_SINGLE_PASS_REBATE;

        assertEquals(expectedPrice, price, DELTA);
    }
}
