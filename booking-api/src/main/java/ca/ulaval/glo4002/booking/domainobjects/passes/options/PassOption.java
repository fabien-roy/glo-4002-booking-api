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

	@Override
	public boolean equals (Object obj)
	{
		if (this==obj) return true;
		if (this.getClass() != obj.getClass()) return false;
		// Class name is Employ & have lastname
		PassOption passOption = (PassOption) obj ;
		return this.id.equals(passOption.id);
	}

	@Override
	public int hashCode(){
		return this.id.intValue();
	}
}
