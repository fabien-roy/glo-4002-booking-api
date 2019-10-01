package ca.ulaval.glo4002.booking.endToEnd;

import ca.ulaval.glo4002.booking.constants.DateConstants;
import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.constants.VendorConstants;
import ca.ulaval.glo4002.booking.entities.OrderEntity;
import ca.ulaval.glo4002.booking.entities.PassEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PassEndToEndContext {

    private static final LocalDate A_VALID_PASS_DATE = DateConstants.START_DATE.plusDays(1L);
    private static final LocalDate ANOTHER_VALID_PASS_DATE = DateConstants.START_DATE.plusDays(2L);
    private static final Long A_VENDOR_ID = VendorConstants.TEAM_VENDOR_ID;
    private static final LocalDateTime A_VALID_ORDER_DATE = DateConstants.ORDER_START_DATE_TIME;
    PassEntity aSinglePass = new PassEntity(PassConstants.Categories.SUPERNOVA_ID, PassConstants.Options.SINGLE_ID, A_VALID_PASS_DATE);
    PassEntity anotherSinglePass = new PassEntity(PassConstants.Categories.SUPERGIANT_ID, PassConstants.Options.SINGLE_ID, ANOTHER_VALID_PASS_DATE);
    OrderEntity anOrderEntity = new OrderEntity(A_VALID_ORDER_DATE, A_VENDOR_ID);

    PassEndToEndContext(){
        setUpOrder();
    }

    private void setUpOrder() {
        //anOrderEntity.orderItems.add(aSinglePass);
    }


}
