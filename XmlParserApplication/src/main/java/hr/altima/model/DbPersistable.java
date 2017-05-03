package hr.altima.model;

public interface DbPersistable {

	public int getIdentity();
	public String getName();
	DbEntry getParent();

}
