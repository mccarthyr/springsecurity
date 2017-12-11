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

import javax.persistence.EmbeddedId;


/*
---------------------------------------------------------------------------------------------------------------
NOTE: IN RELATION TO JPA ENTITY PRIMARY KEY REQUIREMENT AND A DATABASE TABLE THAT DOES NOT HAVE A PRIMARY KEY
---------------------------------------------------------------------------------------------------------------

The database table for this Entity ('authorities' - standard ACL set up) does not have a Primary Key but JPA 
requires every Entity to have an @Id (Primary Key).

On the authorities database table the combination of the two fields 'username' and 'authorities' is unique so 
setting a unique constraint on those two columns here. 
The actual column names does not appear as properties directly in this Entity as using @EmbeddedId which sets up another class called
AuthorityId that contains a Compostite Primary Key of both the username and authority fields.
*/


@Entity( name = "Authorities" )
@Table( name = "authorities",
				uniqueConstraints = @UniqueConstraint( columnNames = { "username", "authority" } ) )
public class Authorities {


	@EmbeddedId
	private AuthorityId id;


	public Authorities() {}


	public Authorities( AuthorityId id ) {
		this.id = id;
	}


	public AuthorityId getId() {
		return id;
	}

	public void setId( AuthorityId id ) {
		this.id = id;
	}

}


