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



/* NOTE NOTE NOTE 	--- ABOUT LACK OF PRIMARY KEY AND JPA REQUIREMENTS ---

THE TABLE FOR THIS DOES NOT HAVE A PRIMARY KEY BUT JPA REQUIRES EVERY ENTITY TO HAVE AN @ID (PRIMARY KEY)...
SEE ABOUT USING A COMPOSITE KEY OF BOTH FIELDS FOR THE PRIMARY KEY ON THE ENTITY ???  NEED THIS FOR SETTING UP THE REPOSITORY INTERFACE ...

@EmbeddedId is used to embed a class. The class that is embedded contains a COMPOSITE PRIMARY KEY...It is the class name then that is used in the Repository Interface name.
*/


// ******** LOOK AT MAKING THE EMBEDDED EBTITY IMPLEMENTING SERIALIZABLE WHICH IT MUST - CHECK DOCUMENTATION ON THIS IF WRITE UP ABOUT IT ... *********

// RE-WRITE THIS USING EMBEDDED ANNOTATION CLASS AND SET UP THE USERNAME/AUTHORITY FIELD PAIR AS A COMPOSITE PRIMARY KEY (WHICH THEY ARE NOT IN THE DATABASE RIGHT NOW) AND SEE HOW IT GOES ???


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



	/*
	@Override
	public String toString() {
		return "Authority [username=" + username + ", role=" + authority +"]";
	}


	@Override
	public boolean equals( Object obj ) {
		
		if ( !( obj instanceof Authorities ) ) {
			return false;
		}

		Authorities authorities = (Authorities) obj;
		if ( !authorities.getUsername().equals( this.getUsername() ) || !authorities.getAuthority().equals( this.getAuthority() ) ) {
			return false;
		}

		return true;
	}
	*/


}	// End of class Authorities...


