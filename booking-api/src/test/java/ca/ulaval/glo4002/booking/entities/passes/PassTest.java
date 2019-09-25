package ca.ulaval.glo4002.booking.entities.passes;

import ca.ulaval.glo4002.booking.entities.passes.categories.NebulaPassCategory;
import ca.ulaval.glo4002.booking.entities.passes.categories.PassCategory;
import ca.ulaval.glo4002.booking.entities.passes.options.PassOption;
import ca.ulaval.glo4002.booking.entities.passes.options.SinglePassOption;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class PassTest {

    private Pass subjectWithDefaultPrice;
    private Pass subjectWithCustomPrice;


    @BeforeEach
    void setUp() {
        PassCategory passCategory = mock(PassCategory.class);
        PassOption passOption = mock(PassOption.class);
        Long id = 1L;
        Double price = 200d;
        subjectWithDefaultPrice = new Pass(passCategory, passOption, id);
        subjectWithCustomPrice = new Pass(passCategory, passOption, id, price);
    }

    @Test
    void whenPassIsCreated_thenNewUniqueIdIsAssigned(){
        assertNotNull(subjectWithDefaultPrice.id);
        assertNotNull(subjectWithDefaultPrice.id);
    }

    @Test
    void whenPassIsCreated_thenPassCategoryIsAssigned(){
        assertNotNull(subjectWithDefaultPrice.getCategory());
        assertNotNull(subjectWithCustomPrice.getCategory());
    }

    @Test
    void whenPassIsCreated_thenPassOptionIsAssigned(){
        assertNotNull(subjectWithDefaultPrice.getOption());
        assertNotNull(subjectWithCustomPrice.getOption());
    }
}