package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.dao.PassDao;
import ca.ulaval.glo4002.booking.domain.passes.Pass;

// TODO : Rendered useless, since passNumber will be generated elsewhere
public class PassRepository {

    private PassDao dao;

    public PassRepository(PassDao dao) {
        this.dao = dao;
    }

    void addPass(Pass pass) {
        dao.save(pass);
    }
}
