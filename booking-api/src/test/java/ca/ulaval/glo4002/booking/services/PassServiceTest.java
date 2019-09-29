package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.domainObjects.passes.Pass;
import ca.ulaval.glo4002.booking.exceptions.passes.PassNotFoundException;
import ca.ulaval.glo4002.booking.repositories.PassRepositoryContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PassServiceTest {

    private PassServiceImpl subject;
    private PassServiceContext context;

    @BeforeEach
    public void setUp() {
        context = new PassServiceContext();
        subject = new PassServiceImpl(context.repository);
    }

    @Test
    public void findById_shouldThrowPassNotFoundException_whenPassDoesNotExist() {
        PassNotFoundException thrown = assertThrows(
                PassNotFoundException.class,
                () -> subject.findById(PassServiceContext.A_NON_EXISTANT_PASS_ID)
        );

        assertEquals(ExceptionConstants.PASS_NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    public void findById_shouldReturnCorrectPass() {
        Pass pass = subject.findById(PassServiceContext.A_PASS_ID);

        assertEquals(context.aPass, pass);
    }

    @Test
    public void findAll_shouldReturnCorrectPasses() {
        List<Pass> passes = new ArrayList<>();

        subject.findAll().forEach(passes::add);

        assertEquals(2, passes.size());
        assertTrue(passes.contains(context.aPass));
        assertTrue(passes.contains(context.anotherPass));
    }

    @Test
    public void saveAll_shouldSavePasses() {
        context.setUpRepositoryForSaveAll();
        List<Pass> newPasses = new ArrayList<>(Collections.singletonList(context.aNonExistentPass));

        subject.saveAll(newPasses);
        Pass pass = subject.findById(PassServiceContext.A_NON_EXISTANT_PASS_ID);

        assertEquals(context.aNonExistentPass, pass);
    }
}
