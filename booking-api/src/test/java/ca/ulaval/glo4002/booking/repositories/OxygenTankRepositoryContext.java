package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.constants.RepositoryConstants;
import ca.ulaval.glo4002.booking.entities.OxygenTankEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OxygenTankRepositoryContext {

	public EntityManager entityManager;
	public OxygenTankEntity oxygenTankA;
	public OxygenTankEntity oxygenTankB;
	public OxygenTankEntity oxygenTankE;
	public OxygenTankEntity nonExistentOxygenTank;
	public final static Long A_OXYGEN_ID = 1L;
	public final static Long B_OXYGEN_ID = 2L;
	public final static Long E_OXYGEN_ID = 3L;
	public final static Long NON_EXISTENT_OXYGEN_ID = 0L;
	private final static LocalDate A_DATE_BEFORE_FESTIVAL = FestivalConstants.Dates.START_DATE.minusDays(30);

	public OxygenTankRepositoryContext() {
		this.setUpOxygenTanks();
		this.setUpEntityManager();
	}

	private void setUpOxygenTanks() {
		oxygenTankA = new OxygenTankEntity(
				A_OXYGEN_ID,
				OxygenConstants.Categories.A_ID,
				A_DATE_BEFORE_FESTIVAL
		);

		oxygenTankB = new OxygenTankEntity(
				B_OXYGEN_ID,
				OxygenConstants.Categories.B_ID,
				A_DATE_BEFORE_FESTIVAL
		);

		oxygenTankE = new OxygenTankEntity(
				E_OXYGEN_ID,
				OxygenConstants.Categories.E_ID,
				A_DATE_BEFORE_FESTIVAL
		);

		nonExistentOxygenTank = new OxygenTankEntity(
				NON_EXISTENT_OXYGEN_ID,
				OxygenConstants.Categories.A_ID,
				A_DATE_BEFORE_FESTIVAL
		);
	}

	private void setUpEntityManager() {
		entityManager = mock(EntityManager.class);
		TypedQuery<OxygenTankEntity> createQuery = mock(TypedQuery.class);

		when(createQuery.getResultList()).thenReturn(Arrays.asList(this.oxygenTankA, this.oxygenTankB, this.oxygenTankE));
		when(entityManager.createQuery(RepositoryConstants.ORDER_FIND_ALL_QUERY, OxygenTankEntity.class)).thenReturn(createQuery);
		when(entityManager.find(OxygenTankEntity.class, A_OXYGEN_ID)).thenReturn(this.oxygenTankA);
		when(entityManager.find(OxygenTankEntity.class, B_OXYGEN_ID)).thenReturn(this.oxygenTankB);
		when(entityManager.find(OxygenTankEntity.class, E_OXYGEN_ID)).thenReturn(this.oxygenTankE);
		when(entityManager.find(OxygenTankEntity.class, NON_EXISTENT_OXYGEN_ID)).thenReturn(null);
	}

	public void setUpEntityManagerForSave() {
		nonExistentOxygenTank.id = null;
		when(entityManager.find(OxygenTankEntity.class, NON_EXISTENT_OXYGEN_ID)).thenReturn(nonExistentOxygenTank);
	}
}
