package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainObjects.shuttles.ShuttleManifest;

import java.time.LocalDate;

public interface ShuttleManifestService extends Service<ShuttleManifest>{

    ShuttleManifest findByDate(LocalDate date);
}
