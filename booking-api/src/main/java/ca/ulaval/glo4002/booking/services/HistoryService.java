package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.report.History;

public interface HistoryService extends Service<History> {
	
	History get();
}
