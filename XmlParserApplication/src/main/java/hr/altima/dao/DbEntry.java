package hr.altima.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Entry")
public class DbEntry {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", nullable=false, unique=true)
	private Integer identity;


	@ManyToOne
	@JoinColumn(name="parent_Id", nullable=true)
	private DbEntry parent;


	@Column(name="name", nullable=false)
	private String name;

	public Integer getIdentity() {
		return identity;
	}

	public DbEntry getParent() {
		return parent;
	}

	public void setParent(final DbEntry parent) {
		this.parent = parent;
	}


	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

}
