package hr.altima.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Entry")
public class DbEntry extends AbstractDbPersistable{



	@Column(name="name", nullable=false)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

}
