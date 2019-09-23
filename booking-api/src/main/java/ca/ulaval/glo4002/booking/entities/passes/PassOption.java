package ca.ulaval.glo4002.booking.entities.passes;

public class PassOption {
	private Long id;
	private String name;

	public PassOption(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
