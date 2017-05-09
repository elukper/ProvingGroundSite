package com.feeddit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="WebEntry")
public class DbEntry extends AbstractEntry{

	@Column(name="name", nullable=false)
	private String name;

	@ManyToOne
	@JoinColumn(name="parent_Id", nullable=true)
	private DbEntry parent;

	public String getName() {
		return name;
	}

	public DbEntry getParent() {
		return parent;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setParent(final DbEntry parent) {
		this.parent = parent;
	}








}
