package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domainobjects.report.History;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryServiceImpl implements HistoryService {

	private OxygenTankService oxygenTankService;

	public HistoryServiceImpl(OxygenTankService oxygenTankService) {
		this.oxygenTankService = oxygenTankService;
	}

	@Override
	public History get() {
		List<OxygenTank> oxygenTanks = new ArrayList<>();

		oxygenTankService.findAll().forEach(oxygenTanks::add);

		Map<LocalDate, List<OxygenTank>> producedOxygenTanks = new HashMap<>();
		Map<LocalDate, List<OxygenTank>> requestedOxygenTanks = new HashMap<>();

		oxygenTanks.forEach(oxygenTank -> {
			producedOxygenTanks.putIfAbsent(oxygenTank.getReadyDate(), new ArrayList<>());
			producedOxygenTanks.get(oxygenTank.getReadyDate()).add(oxygenTank);

			requestedOxygenTanks.putIfAbsent(oxygenTank.getRequestDate(), new ArrayList<>());
			requestedOxygenTanks.get(oxygenTank.getRequestDate()).add(oxygenTank);
		});

		return new History(producedOxygenTanks, requestedOxygenTanks);
	}
}
