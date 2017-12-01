package com.fireduptech.springsecurity.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.UniqueConstraint;
import javax.persistence.CascadeType;

import javax.persistence.Embeddable;

import java.io.Serializable;


@Embeddable
public class AuthorityId implements Serializable {


		// This is a FK to the username PK on the User entity
	@ManyToOne( cascade = CascadeType.ALL )
	@JoinColumn( name = "username" )
	private User username;


	@Column( name = "authority", nullable = false )
	private String authority;


	public AuthorityId() {}

	public AuthorityId( User username, String authority ) {
		this.username = username;
		this.authority = authority;
	}


	public User getUsername() {
		return username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setUsername( User username ) {
		this.username = username;
	}

	public void setAuthority( String authority ) {
		this.authority = authority;
	}


}


