package ca.ulaval.glo4002.booking.dao;

import ca.ulaval.glo4002.booking.domain.Id;
import ca.ulaval.glo4002.booking.domain.passes.Pass;

public class PassDao {

    private Id nextId;

    public PassDao() {
        nextId = new Id(0L);
    }

    public void save(Pass pass) {
        pass.setId(nextId);
        nextId = new Id(nextId.getValue() + 1);
    }
}
