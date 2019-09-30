package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.builders.passes.PassCategoryBuilder;
import ca.ulaval.glo4002.booking.builders.passes.PassOptionBuilder;
import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.domainObjects.passes.Pass;
import ca.ulaval.glo4002.booking.entities.PassEntity;
import ca.ulaval.glo4002.booking.parsers.PassParser;
import ca.ulaval.glo4002.booking.repositories.PassRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PassServiceContext {

    private final static LocalDate A_PASS_EVENT_DATE = FestivalConstants.Dates.START_DATE;
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

        aPassEntity = parser.toEntity(aPass);
        anotherPassEntity = parser.toEntity(anotherPass);
        aNonExistentPassEntity = parser.toEntity(aNonExistentPass);
    }

    private void setUpRepository() {
        repository = mock(PassRepository.class);

        when(repository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(aPassEntity, anotherPassEntity)));
        when(repository.findById(A_PASS_ID)).thenReturn(Optional.of(aPassEntity));
        when(repository.findById(ANOTHER_PASS_ID)).thenReturn(Optional.of(anotherPassEntity));
        when(repository.findById(A_NON_EXISTANT_PASS_ID)).thenReturn(Optional.empty());
    }

    public void setUpRepositoryForSaveAll() {
        when(repository.findById(A_NON_EXISTANT_PASS_ID)).thenReturn(Optional.of(aNonExistentPassEntity));
    }
}
