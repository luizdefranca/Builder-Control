package ca.programmerlife.utils.all;

public enum PersistenceStatus {

	ERROR("Error."), 
	SUCCESS("Success."),
	REFERENCED_OBJECT("This object can not be deleted because it has references.");
	
	private String name;
	
	PersistenceStatus(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		
		return name;
	}
	
}
