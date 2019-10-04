package ca.ulaval.glo4002.booking.repositories;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ca.ulaval.glo4002.booking.constants.RepositoryConstants;
import ca.ulaval.glo4002.booking.entities.OxygenTankEntity;
import ca.ulaval.glo4002.booking.entities.PassengerEntity;

public class PassengerRepositoryContext {

	public final static Long A_ID = 1L;
    public final static Long ANOTHER_ID = 2L;
    public final static Long A_THIRD_ID = 3L;
    public final static Long NON_EXISTENT_ID = 0L;
    
    public EntityManager entityManager;
    public PassengerEntity aPassenger;
    public PassengerEntity anotherPassenger;
    public PassengerEntity aThirdPassenger;
    public PassengerEntity nonExistentPassenger;
    
    public PassengerRepositoryContext() {
    	setUpPassengers();
    	setUpEntityManager();
    }
    
    public void setUpPassengers() {
    	aPassenger = new PassengerEntity(A_ID);
    	anotherPassenger = new PassengerEntity(ANOTHER_ID);
    	aThirdPassenger = new PassengerEntity(A_THIRD_ID);
    }
    
    public void setUpEntityManager() {
    	entityManager = mock(EntityManager.class);
    	TypedQuery<PassengerEntity> createQuery = mock(TypedQuery.class);
    	when(entityManager.find(PassengerEntity.class, A_THIRD_ID)).thenReturn(this.aThirdPassenger);
    	when(createQuery.getResultList()).thenReturn(Arrays.asList(aPassenger, anotherPassenger));
        when(entityManager.createQuery(RepositoryConstants.PASSENGER_FIND_ALL_QUERY, PassengerEntity.class)).thenReturn(createQuery);
        when(entityManager.find(PassengerEntity.class, NON_EXISTENT_ID)).thenReturn(null);
    }

}
