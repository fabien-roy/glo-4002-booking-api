package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.constants.PassConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderServicePriceTest {

    private static final int AMOUNT_OF_SUPERGIANT_PASSES_MORE_THAN_THRESHOLD = PassConstants.Categories.SUPERGIANT_SINGLE_PASS_REBATE_THRESHOLD;
    private static final int AMOUNT_OF_SUPERGIANT_PASSES_LESS_THAN_THRESHOLD = PassConstants.Categories.SUPERGIANT_SINGLE_PASS_REBATE_THRESHOLD - 1;
    private static final int AMOUNT_OF_NEBULA_PASSES_MORE_THAN_THRESHOLD = PassConstants.Categories.NEBULA_SINGLE_PASS_REBATE_THRESHOLD;
    private static final int AMOUNT_OF_NEBULA_PASSES_LESS_THAN_THRESHOLD = PassConstants.Categories.NEBULA_SINGLE_PASS_REBATE_THRESHOLD - 1;
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
    void whenTheresLessSupergiantPassesThanThreshold_thenRebateIsNotApplied(){
        context.aOrder.setPasses(Collections.nCopies(AMOUNT_OF_SUPERGIANT_PASSES_LESS_THAN_THRESHOLD, context.aSupergiantSinglePass));
        double expectedPrice = AMOUNT_OF_SUPERGIANT_PASSES_LESS_THAN_THRESHOLD *
                PassConstants.Categories.SUPERGIANT_SINGLE_PASS_PRICE;

        Double price = context.subject.getOrderPrice(context.aOrder);

        assertEquals(expectedPrice,price,DELTA);
    }

    @Test
    void whenTheresMoreSupergiantPassesThanThreshold_thenRebateIsApplied(){
        context.aOrder.setPasses(Collections.nCopies(AMOUNT_OF_SUPERGIANT_PASSES_MORE_THAN_THRESHOLD, context.aSupergiantSinglePass));
        double expectedPrice = AMOUNT_OF_SUPERGIANT_PASSES_MORE_THAN_THRESHOLD *
                PassConstants.Categories.SUPERGIANT_SINGLE_PASS_PRICE *
                PassConstants.Categories.SUPERGIANT_SINGLE_PASS_REBATE;

        Double price = context.subject.getOrderPrice(context.aOrder);

        assertEquals(expectedPrice, price, DELTA);
    }

    @Test
    void whenTheresLessNebulaPassesThanThreshold_thenRebateIsApplied(){
        context.aOrder.setPasses(Collections.nCopies(AMOUNT_OF_NEBULA_PASSES_LESS_THAN_THRESHOLD, context.aNebulaSinglePass));
        double expectedPrice = AMOUNT_OF_NEBULA_PASSES_LESS_THAN_THRESHOLD *
                PassConstants.Categories.NEBULA_SINGLE_PASS_PRICE;

        Double price = context.subject.getOrderPrice(context.aOrder);

        assertEquals(expectedPrice, price, DELTA);
    }

    @Test
    void whenTheresMoreNebulaPassesThanThreshold_thenTenPercentRebateIsApplied(){
        context.aOrder.setPasses(Collections.nCopies(AMOUNT_OF_NEBULA_PASSES_MORE_THAN_THRESHOLD, context.aNebulaSinglePass));

        Double price = context.subject.getOrderPrice(context.aOrder);
        double expectedPrice = AMOUNT_OF_NEBULA_PASSES_MORE_THAN_THRESHOLD *
                PassConstants.Categories.NEBULA_SINGLE_PASS_PRICE *
                PassConstants.Categories.NEBULA_SINGLE_PASS_REBATE;

        assertEquals(expectedPrice, price, DELTA);
    }
}
