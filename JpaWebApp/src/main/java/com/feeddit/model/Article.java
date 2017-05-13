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
@Table(name="articles")
public class Article {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", nullable=false, unique=true)
	private Long identity;

	@Column(name="votes")
	private Integer votes;

	@Column(name="author")
	private String author;

	@Column(name="url")
	private String url;

	@ManyToOne
	@JoinColumn(name="createdBy", nullable=false)
	private User createdBy;

	public String getAuthor() {
		return author;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public Long getIdentity() {
		return identity;
	}

	public String getUrl() {
		return url;
	}

	public Integer getVotes() {
		return votes;
	}

	public void setAuthor(final String author) {
		this.author = author;
	}

	public void setCreatedBy(final User createdBy) {
		this.createdBy = createdBy;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public void setVotes(final Integer votes) {
		this.votes = votes;
	}



}
