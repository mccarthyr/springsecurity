package com.fireduptech.springsecurity.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import java.util.Set;


@Entity( name = "User" )
@Table( name = "users" )
public class User {


	@Id
	private String username;

	@Column( name = "password", nullable = false, length = 150 )
	private String password;

	@Column( name = "enabled", nullable = false )
	private short enabled;

	// The Authorities has a username FK to this entity
	@OneToMany( cascade = CascadeType.ALL, mappedBy = "id.username" )  // NOTE - UPDATED THE MAPPING TO USED THE EMBEDDED CLASS COMPOSITE PRIMARY KEY STRUCTURE NOW...
	private Set<Authorities> authorities;


	public User() {}

	public User( String username, String password, short enabled ) {

		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	// Getters

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

 	public short getEnabled() {
		return this.enabled;
	}

	// Setters

	public void setUsername( String username ) {
		this.username = username;
	}

	public void setPassword( String password ) {
		this.password = password;
	}

	public void setEnabled( short enabled ) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		//return "User [username=" + username + ", enabled=" + enabled +"]";
		return this.username;
	}

	@Override
	public boolean equals( Object obj ) {
		
		if ( !( obj instanceof User ) ) {
			return false;
		}

		User user = (User) obj;
		if (  !user.getUsername().equals( this.getUsername() ) ) {
			return false;
		}

		return true;
	}


}	// End of class User...
