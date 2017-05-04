package hr.altima.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractDbPersistable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", nullable=false, unique=true)
	private int identity;

	@ManyToOne
	@JoinColumn(name="parent_Id", nullable=true)
	private DbEntry parent;

	public int getIdentity() {
		return identity;
	}

	public DbEntry getParent() {
		return parent;
	}

	public void setParent(final DbEntry parent) {
		this.parent = parent;
	}



}
