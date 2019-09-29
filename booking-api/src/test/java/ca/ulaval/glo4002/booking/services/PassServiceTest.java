package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.domainObjects.passes.Pass;
import ca.ulaval.glo4002.booking.exceptions.passes.PassNotFoundException;
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

        assertEquals(context.aPass.getId(), pass.getId());
    }

    @Test
    public void findAll_shouldReturnCorrectPasses() {
        List<Long> ids = new ArrayList<>();

        subject.findAll().forEach(pass -> ids.add(pass.getId()));

        assertEquals(2, ids.size());
        assertTrue(ids.contains(context.aPass.getId()));
        assertTrue(ids.contains(context.anotherPass.getId()));
    }

    @Test
    public void saveAll_shouldSavePasses() {
        context.setUpRepositoryForSaveAll();
        List<Pass> newPasses = new ArrayList<>(Collections.singletonList(context.aNonExistentPass));

        subject.saveAll(newPasses);
        Pass pass = subject.findById(PassServiceContext.A_NON_EXISTANT_PASS_ID);

        assertEquals(context.aNonExistentPass.getId(), pass.getId());
    }
}
