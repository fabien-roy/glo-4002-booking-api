package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.domainobjects.orders.Order;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    private OrderServiceTestContext context;

    @BeforeEach
    public void setUp() {
        context = new OrderServiceTestContext();
    }

    @Test
    public void findById_shouldThrowOrderNotFoundException_whenOrderDoesNotExist() {
        OrderNotFoundException thrown = assertThrows(
                OrderNotFoundException.class,
                () -> context.subject.findById(OrderServiceTestContext.A_NON_EXISTANT_ORDER_ID)
        );

        assertEquals(ExceptionConstants.Order.NOT_FOUND_ERROR, thrown.getMessage());
    }

    @Test
    public void findById_shouldReturnCorrectOrder() {
        Order order = context.subject.findById(OrderServiceTestContext.A_ORDER_ID);

        assertEquals(context.aOrder.getId(), order.getId());
    }

    @Test
    public void findAll_shouldReturnCorrectOrders() {
        List<Long> ids = new ArrayList<>();

        context.subject.findAll().forEach(order -> ids.add(order.getId()));

        assertEquals(2, ids.size());
        assertTrue(ids.contains(context.aOrder.getId()));
        assertTrue(ids.contains(context.anotherOrder.getId()));
    }

    @Test
    public void save_shouldSaveOrder() {
        context.setUpForSave();

        context.subject.order(context.aNonExistentOrder);
        Order order = context.subject.findById(OrderServiceTestContext.A_NON_EXISTANT_ORDER_ID);

        assertEquals(context.aNonExistentOrder.getId(), order.getId());
    }

    @Test
    void whenPassesIsPackage_ThenNoRebateIsApplied(){
        context.aOrder.setPasses(Collections.singletonList(context.aSupernovaPackagePass));

        double price = context.subject.getOrderPrice(context.aOrder);

        assertEquals(PassConstants.Categories.SUPERNOVA_PACKAGE_PRICE, price, OrderServiceTestContext.DELTA);
    }

    @Test
    void whenPassesIsOneSinglePass_ThenNoRebateIsApplied(){
        context.aOrder.setPasses(Collections.singletonList(context.aSupergiantSinglePass));

        double price = context.subject.getOrderPrice(context.aOrder);

        assertEquals(PassConstants.Categories.SUPERGIANT_SINGLE_PASS_PRICE, price, OrderServiceTestContext.DELTA);
    }

    @Test
    void whenTheresLessSupergiantPassesThanThreshold_thenRebateIsNotApplied(){
        double expectedPrice = OrderServiceTestContext.AMOUNT_OF_SUPERGIANT_PASSES_LESS_THAN_THRESHOLD * PassConstants.Categories.SUPERGIANT_SINGLE_PASS_PRICE;
        context.aOrder.setPasses(Collections.nCopies(OrderServiceTestContext.AMOUNT_OF_SUPERGIANT_PASSES_LESS_THAN_THRESHOLD, context.aSupergiantSinglePass));

        double price = context.subject.getOrderPrice(context.aOrder);

        assertEquals(expectedPrice, price, OrderServiceTestContext.DELTA);
    }

    @Test
    void whenTheresMoreSupergiantPassesThanThreshold_thenRebateIsApplied(){
        context.aOrder.setPasses(Collections.nCopies(OrderServiceTestContext.AMOUNT_OF_SUPERGIANT_PASSES_MORE_THAN_THRESHOLD, context.aSupergiantSinglePass));
        double expectedPrice = OrderServiceTestContext.AMOUNT_OF_SUPERGIANT_PASSES_MORE_THAN_THRESHOLD * PassConstants.Categories.SUPERGIANT_SINGLE_PASS_PRICE * PassConstants.Categories.SUPERGIANT_SINGLE_PASS_REBATE;

        double price = context.subject.getOrderPrice(context.aOrder);

        assertEquals(expectedPrice, price, OrderServiceTestContext.DELTA);
    }

    @Test
    void whenTheresLessNebulaPassesThanThreshold_thenRebateIsApplied(){
        context.aOrder.setPasses(Collections.nCopies(OrderServiceTestContext.AMOUNT_OF_NEBULA_PASSES_LESS_THAN_THRESHOLD, context.aNebulaSinglePass));
        double expectedPrice = OrderServiceTestContext.AMOUNT_OF_NEBULA_PASSES_LESS_THAN_THRESHOLD * PassConstants.Categories.NEBULA_SINGLE_PASS_PRICE;

        double price = context.subject.getOrderPrice(context.aOrder);

        assertEquals(expectedPrice, price, OrderServiceTestContext.DELTA);
    }

    @Test
    void whenTheresMoreNebulaPassesThanThreshold_thenTenPercentRebateIsApplied(){
        context.aOrder.setPasses(Collections.nCopies(OrderServiceTestContext.AMOUNT_OF_NEBULA_PASSES_MORE_THAN_THRESHOLD, context.aNebulaSinglePass));
        double expectedPrice = OrderServiceTestContext.AMOUNT_OF_NEBULA_PASSES_MORE_THAN_THRESHOLD * PassConstants.Categories.NEBULA_SINGLE_PASS_PRICE * PassConstants.Categories.NEBULA_SINGLE_PASS_REBATE;

        double price = context.subject.getOrderPrice(context.aOrder);

        assertEquals(expectedPrice, price, OrderServiceTestContext.DELTA);
    }
}
