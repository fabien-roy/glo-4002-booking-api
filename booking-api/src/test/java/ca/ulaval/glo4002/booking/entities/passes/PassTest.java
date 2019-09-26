package ca.ulaval.glo4002.booking.entities.passes;

import ca.ulaval.glo4002.booking.entities.passes.categories.PassCategory;
import ca.ulaval.glo4002.booking.entities.passes.options.PassOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class PassTest {

    private Pass subject;
    private final static Long aId = 1L;
    private final static LocalDate aEventDate = LocalDate.of(2050, 6, 24);

    @BeforeEach
    void setUp() {
        PassCategory passCategory = mock(PassCategory.class);
        PassOption passOption = mock(PassOption.class);
        subject = new Pass(passCategory, passOption, aId, aEventDate);
    }

    @Test
    void whenPassIsCreated_thenNewUniqueIdIsAssigned(){
        assertNotNull(subject.id);
    }

    @Test
    void whenPassIsCreated_thenPassCategoryIsAssigned(){
        assertNotNull(subject.getCategory());
    }

    @Test
    void whenPassIsCreated_thenPassOptionIsAssigned(){
        assertNotNull(subject.getOption());
    }
}