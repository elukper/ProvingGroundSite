package com.feeddit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="WebEntry")
public class DbEntry{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", nullable=false, unique=true)
	private Long identity;

	@Column(name="name", nullable=false)
	private String name;

	@ManyToOne
	@JoinColumn(name="parent_Id", nullable=true)
	private DbEntry parent;

	public Long getIdentity() {
		return identity;
	}

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
