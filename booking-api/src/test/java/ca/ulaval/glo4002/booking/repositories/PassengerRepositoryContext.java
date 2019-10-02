package ca.ulaval.glo4002.booking.repositories;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ca.ulaval.glo4002.booking.constants.RepositoryConstants;
import ca.ulaval.glo4002.booking.entities.PassengerEntity;

public class PassengerRepositoryContext {

	private final static Long A_ID = 1L;
    private final static Long ANOTHER_ID = 2L;
    
    public EntityManager entityManager;
    public PassengerEntity aPassenger;
    public PassengerEntity anotherPassenger;
    
    public PassengerRepositoryContext() {
    	setUpPassengers();
    	setUpEntityManager();
    	
    }
    
    public void setUpPassengers() {
    	aPassenger = new PassengerEntity(A_ID);
    	anotherPassenger = new PassengerEntity(ANOTHER_ID);
    }
    
    public void setUpEntityManager() {
    	entityManager = mock(EntityManager.class);
    	TypedQuery<PassengerEntity> createQuery = mock(TypedQuery.class);
    	
    	when(createQuery.getResultList()).thenReturn(Arrays.asList(aPassenger, anotherPassenger));
        when(entityManager.createQuery(RepositoryConstants.PASSENGER_FIND_ALL_QUERY, PassengerEntity.class)).thenReturn(createQuery);
    }
}
