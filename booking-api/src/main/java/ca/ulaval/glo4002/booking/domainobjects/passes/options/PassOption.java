package ca.ulaval.glo4002.booking.domainobjects.passes.options;

public abstract class PassOption {
	protected Long id;
	protected String name;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
