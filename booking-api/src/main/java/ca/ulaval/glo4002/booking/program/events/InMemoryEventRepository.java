package ca.ulaval.glo4002.booking.program.events;

import java.util.ArrayList;
import java.util.List;

public class InMemoryEventRepository implements EventRepository {

    private List<Event> events;

    public InMemoryEventRepository() {
        events = new ArrayList<>();
    }

    @Override
    public void addAll(List<Event> events) {
        this.events.addAll(events);
    }

	@Override
	public List<Event> findAll() {
		return events;
	}
}
