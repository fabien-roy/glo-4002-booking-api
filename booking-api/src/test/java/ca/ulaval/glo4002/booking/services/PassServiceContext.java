package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.builders.passes.PassCategoryBuilder;
import ca.ulaval.glo4002.booking.builders.passes.PassOptionBuilder;
import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.domainObjects.passes.Pass;
import ca.ulaval.glo4002.booking.entities.PassEntity;
import ca.ulaval.glo4002.booking.parsers.PassParser;
import ca.ulaval.glo4002.booking.repositories.PassRepository;
import ca.ulaval.glo4002.booking.repositories.PassRepositoryImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PassServiceContext {

    private final static LocalDate A_PASS_EVENT_DATE = LocalDate.of(2050, 7, 17);
    private final static PassCategoryBuilder categoryBuilder = new PassCategoryBuilder();
    private final static PassOptionBuilder optionBuilder = new PassOptionBuilder();
    private PassParser parser = new PassParser();
    private PassEntity aPassEntity;
    private PassEntity anotherPassEntity;
    private PassEntity aNonExistentPassEntity;
    public PassRepository repository;
    public Pass aPass;
    public Pass anotherPass;
    public Pass aNonExistentPass;
    public final static Long A_PASS_ID = 1L;
    public final static Long ANOTHER_PASS_ID = 2L;
    public final static Long A_NON_EXISTANT_PASS_ID = 0L;

    public PassServiceContext() {
        setUpPasses();
        setUpRepository();
    }

    private void setUpPasses() {
        aPass = new Pass(
                A_PASS_ID,
                categoryBuilder.buildById(PassConstants.Categories.SUPERNOVA_ID),
                optionBuilder.buildById(PassConstants.Options.SINGLE_ID),
                A_PASS_EVENT_DATE
        );

        anotherPass = new Pass(
                ANOTHER_PASS_ID,
                categoryBuilder.buildById(PassConstants.Categories.NEBULA_ID),
                optionBuilder.buildById(PassConstants.Options.PACKAGE_ID)
        );

        aNonExistentPass = new Pass(
                A_NON_EXISTANT_PASS_ID,
                categoryBuilder.buildById(PassConstants.Categories.NEBULA_ID),
                optionBuilder.buildById(PassConstants.Options.PACKAGE_ID)
        );

        aPassEntity = parser.toEntity(new ArrayList<>(Collections.singletonList(aPass)));
        anotherPassEntity = parser.toEntity(new ArrayList<>(Collections.singletonList(anotherPass)));
        aNonExistentPassEntity = parser.toEntity(new ArrayList<>(Collections.singletonList(aNonExistentPass)));
    }

    private void setUpRepository() {
        repository = mock(PassRepositoryImpl.class);

        when(repository.findById(A_PASS_ID)).thenReturn(Optional.of(aPassEntity));
        when(repository.findById(ANOTHER_PASS_ID)).thenReturn(Optional.of(anotherPassEntity));
        when(repository.findById(A_NON_EXISTANT_PASS_ID)).thenReturn(Optional.empty());
    }

    public void setUpRepositoryForSaveAll() {
        when(repository.findById(A_NON_EXISTANT_PASS_ID)).thenReturn(Optional.of(aNonExistentPassEntity));
    }
}
