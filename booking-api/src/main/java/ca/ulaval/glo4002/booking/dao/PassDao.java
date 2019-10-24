package ca.ulaval.glo4002.booking.dao;

import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.passes.Pass;

public class PassDao {

    private Number nextId;

    public PassDao() {
        nextId = new Number(0L);
    }

    public void save(Pass pass) {
        pass.setId(nextId);
        nextId = new Number(nextId.getValue() + 1);
    }
}
