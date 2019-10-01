package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domainobjects.report.Report;

import java.util.ArrayList;
import java.util.List;

public class ReportServiceImpl implements ReportService {

    private OxygenTankService oxygenTankService;

    public ReportServiceImpl(OxygenTankService oxygenTankService) {
        this.oxygenTankService = oxygenTankService;
    }

    @Override
    public Report getReport() {
        List<OxygenTank> oxygenTanks = new ArrayList<>();
        oxygenTankService.findAll().forEach(oxygenTanks::add);

        // TODO

        return null;
    }
}
