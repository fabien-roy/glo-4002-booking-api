package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainObjects.report.Report;

public interface ReportService extends Service<Report>{

    Report getReport();
}
