package ca.ulaval.glo4002.booking.dao;

import ca.ulaval.glo4002.booking.domain.passes.Pass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PassDaoTest {

    private PassDao subject;

    @BeforeEach
    void setUpSubject() {
        this.subject = new PassDao();
    }

    @Test
    void save_shouldReturnUniqueIds() {
        Pass aPass = new Pass();
        Pass anotherPass = new Pass();

        subject.save(aPass);
        subject.save(anotherPass);

        assertNotEquals(aPass.getId(), anotherPass.getId());
    }
}