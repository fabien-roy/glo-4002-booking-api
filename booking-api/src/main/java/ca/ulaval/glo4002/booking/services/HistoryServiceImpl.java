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

	// TODO : OXY : Test
	@Override
	public History get() {
		List<OxygenTank> oxygenTanks = new ArrayList<>();

		oxygenTankService.findAll().forEach(oxygenTanks::add);

		List<LocalDate> readyDates = new ArrayList<>();
		List<LocalDate> requestDates = new ArrayList<>();
		Map<LocalDate, List<OxygenTank>> producedOxygenTanks = new HashMap<>();
		Map<LocalDate, List<OxygenTank>> requestedOxygenTanks = new HashMap<>();

		oxygenTanks.forEach(oxygenTank -> {
			if (!readyDates.contains(oxygenTank.getReadyDate())) {
				readyDates.add(oxygenTank.getReadyDate());
				producedOxygenTanks.putIfAbsent(oxygenTank.getReadyDate(), new ArrayList<>());
			}

			if (!requestDates.contains(oxygenTank.getRequestDate())) {
				requestDates.add(oxygenTank.getRequestDate());
				requestedOxygenTanks.putIfAbsent(oxygenTank.getRequestDate(), new ArrayList<>());
			}
		});

		oxygenTanks.forEach(oxygenTank -> {
			if (readyDates.contains(oxygenTank.getReadyDate())) {
			    producedOxygenTanks.get(oxygenTank.getReadyDate()).add(oxygenTank);
			}

			if (requestDates.contains(oxygenTank.getRequestDate())) {
				requestedOxygenTanks.get(oxygenTank.getReadyDate()).add(oxygenTank);
			}
		});

		return new History(producedOxygenTanks, requestedOxygenTanks);
	}
}
