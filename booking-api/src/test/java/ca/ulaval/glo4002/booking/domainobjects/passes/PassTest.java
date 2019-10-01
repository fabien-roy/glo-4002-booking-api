package ca.ulaval.glo4002.booking.domainobjects.passes;

import ca.ulaval.glo4002.booking.domainobjects.passes.categories.PassCategory;
import ca.ulaval.glo4002.booking.domainobjects.passes.options.PassOption;
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
        subject = new Pass(aId, passCategory, passOption, aEventDate);
    }

    @Test
    void whenPassIsCreated_thenIdIsAssigned(){
        assertNotNull(subject.getId());
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