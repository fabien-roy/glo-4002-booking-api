package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.dao.PassDao;
import ca.ulaval.glo4002.booking.domain.passes.Pass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PassRepositoryTest {

    private PassRepository subject;
    private PassDao dao;

    @BeforeEach
    void setUpSubject() {
        dao = mock(PassDao.class);
        subject = new PassRepository(dao);
    }

    @Test
    public void addPass_shouldSavePass() {
        Pass pass = new Pass();

        subject.addPass(pass);

        verify(dao).save(pass);
    }
}