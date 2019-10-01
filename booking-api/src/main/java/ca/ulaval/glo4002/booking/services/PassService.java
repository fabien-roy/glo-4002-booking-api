package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.passes.Pass;

public interface PassService extends Service<Pass> {

    Pass findById(Long id);

    Iterable<Pass> findAll();

    Iterable<Pass> saveAll(Iterable<Pass> passes);
}
