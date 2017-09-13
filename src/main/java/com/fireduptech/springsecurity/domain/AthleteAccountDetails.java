package com.fireduptech.springsecurity.domain;

public class AthleteAccountDetails {

	// Used internally in this example to simulate incremental Id in persistent storage
	private long id;	 

	private String accountId;

	private String accountType;
	// @TODO - EVENTUALLY THIS WILL BE AN ENUM OR final static constant array...

	private String name;

	private String email;

	private String primaryActivity;	
	// @TODO - EVENTUALLY THIS WILL BE AN ENUM OR final static constant array...


	public AthleteAccountDetails() {}

	public AthleteAccountDetails( long id, String accountId, String accountType,
																String name, String email, String primaryActivity ) {

		this.id = id;
		this.accountId = accountId;
		this.accountType = accountType;
		this.name = name;
		this.email = email;
		this.primaryActivity = primaryActivity;
	}


	public long getId() {
		return id;
	}

	public void setId( long id ) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId( String accountId ) {
		this.accountId = accountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType( String accountType ) {
		this.accountType = accountType;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	public String getPrimaryActivity() {
		return primaryActivity;
	}

	public void setPrimaryActivity( String primaryActivity ){
		this.primaryActivity = primaryActivity;
	}

	@Override
	public String toString() {
		return "AthleteAccountDetails [id=" + id + ", accountId=" + accountId
				+ ", accountType=" + accountType + ", name=" + name
				+ ", email=" + email + ", primaryActivity=" + primaryActivity + "]";
	}

}

