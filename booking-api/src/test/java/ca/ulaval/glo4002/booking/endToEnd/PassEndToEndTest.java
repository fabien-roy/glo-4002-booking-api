package ca.ulaval.glo4002.booking.endToEnd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PassEndToEndTest {

    private PassEndToEndContext context;

    @BeforeEach
    void setUp(){
        context = new PassEndToEndContext();
    }

    @Test
    void SkeletonTest(){
        int un = 1;
        assertEquals(1, un);
    }

}
