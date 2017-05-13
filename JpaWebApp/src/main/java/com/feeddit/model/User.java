package com.feeddit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", nullable=false, unique=true)
	private Long identity;

	@Column(name="username")
	private String username;

	@Column(name="password")
	private String password;

	@Column(name="admin")
	private boolean admin;

	public Long getIdentity() {
		return identity;
	}


}
