package ca.ulaval.glo4002.booking.repositories;

import static org.mockito.Mockito.*;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.constants.RepositoryConstants;
import ca.ulaval.glo4002.booking.entities.OxygenTankEntity;

public class OxygenTankRepositoryContext {
	public EntityManager entityManager;
	public OxygenTankEntity oxygenTankA;
	public OxygenTankEntity oxygenTankB;
	public OxygenTankEntity oxygenTankE;
	public OxygenTankEntity oxygenTankNotExist;
	public final static Long A_OXYGEN_ID = 1L;
	public final static Long B_OXYGEN_ID = 2L;
	public final static Long E_OXYGEN_ID = 3L;
	public final static Long NON_EXISTANT_OXYGEN_ID = 0L;
	private final static String OXYGEN_DATE_TIME = FestivalConstants.Dates.ORDER_START_DATE_TIME.toString();

	public OxygenTankRepositoryContext() {
		this.setUpOxygenTanks();
		this.setUpEntityManager();
	}

	private void setUpOxygenTanks() {
		this.oxygenTankA = new OxygenTankEntity(this.A_OXYGEN_ID, OxygenConstants.Categories.A_ID,
				this.OXYGEN_DATE_TIME);
		this.oxygenTankB = new OxygenTankEntity(this.B_OXYGEN_ID, OxygenConstants.Categories.B_ID,
				this.OXYGEN_DATE_TIME);
		this.oxygenTankE = new OxygenTankEntity(this.E_OXYGEN_ID, OxygenConstants.Categories.E_ID,
				this.OXYGEN_DATE_TIME);
		this.oxygenTankNotExist = new OxygenTankEntity(this.NON_EXISTANT_OXYGEN_ID, OxygenConstants.Categories.A_ID,
				this.OXYGEN_DATE_TIME);
	}

	private void setUpEntityManager() {
		entityManager = mock(EntityManager.class);
		TypedQuery<OxygenTankEntity> createQuery = mock(TypedQuery.class);

		when(createQuery.getResultList())
				.thenReturn(Arrays.asList(this.oxygenTankA, this.oxygenTankB, this.oxygenTankE));
		when(entityManager.createQuery(RepositoryConstants.ORDER_FIND_ALL_QUERY, OxygenTankEntity.class))
				.thenReturn(createQuery);
		when(entityManager.find(OxygenTankEntity.class, A_OXYGEN_ID)).thenReturn(this.oxygenTankA);
		when(entityManager.find(OxygenTankEntity.class, B_OXYGEN_ID)).thenReturn(this.oxygenTankB);
		when(entityManager.find(OxygenTankEntity.class, E_OXYGEN_ID)).thenReturn(this.oxygenTankE);
		when(entityManager.find(OxygenTankEntity.class, NON_EXISTANT_OXYGEN_ID)).thenReturn(null);
	}
}
