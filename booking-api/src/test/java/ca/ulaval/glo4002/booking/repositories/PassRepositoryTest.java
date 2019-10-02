package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.entities.PassEntity;
import ca.ulaval.glo4002.booking.exceptions.passes.PassAlreadyCreatedException;
import ca.ulaval.glo4002.booking.exceptions.passes.PassNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// TODO : Solve persistence problem

public class PassRepositoryTest {

    private PassRepository subject;
    private PassRepositoryContext context;

    @BeforeEach
    public void setUp() {
        context = new PassRepositoryContext();
        subject = new PassRepositoryImpl(context.entityManager);
    }

    @Test
    public void findById_shouldThrowPassNotFoundException_whenPassDoesNotExist() {
        PassNotFoundException thrown = assertThrows(
                PassNotFoundException.class,
                () -> subject.findById(PassRepositoryContext.A_NON_EXISTANT_PASS_ID)
        );

        assertEquals(ExceptionConstants.Pass.NOT_FOUND_ERROR, thrown.getMessage());
    }

    @Test
    public void findById_shouldReturnCorrectPass() {
        PassEntity pass = subject.findById(PassRepositoryContext.A_PASS_ID).get();

        assertEquals(context.aPass, pass);
    }

    @Test
    public void findAll_shouldReturnCorrectPasses() {
        List<PassEntity> passes = new ArrayList<>();

        subject.findAll().forEach(passes::add);

        assertEquals(2, passes.size());
        assertTrue(passes.contains(context.aPass));
        assertTrue(passes.contains(context.anotherPass));
    }

    @Test
    public void saveAll_shouldThrowPassAlreadyCreatedException_whenAPassAlreadyExists() {
        List<PassEntity> passes = new ArrayList<>(Collections.singletonList(context.aPass));

        PassAlreadyCreatedException thrown = assertThrows(
                PassAlreadyCreatedException.class,
                () -> subject.saveAll(passes)
        );

        assertEquals(ExceptionConstants.Pass.ALREADY_CREATED_ERROR, thrown.getMessage());
    }

    @Test
    public void saveAll_shouldSavePasses() {
        context.setUpEntityManagerForSaveAll();
        List<PassEntity> newPasses = new ArrayList<>(Collections.singletonList(context.aNonExistentPass));

        subject.saveAll(newPasses);
        PassEntity pass = subject.findById(PassRepositoryContext.A_NON_EXISTANT_PASS_ID).get();

        assertEquals(context.aNonExistentPass, pass);
    }
}
