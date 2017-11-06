# Spring Security Learning Project

v1.0 release contains the basic secured hello world set up using in-memory users (configured in applicationContext-security.xml). Only when you have logged in will the message be displayed.
https://github.com/mccarthyr/springsecurity/releases/tag/v1.0

Tomcat Maven Deploy is configured in the pom.xml and set to localhost. Adjust as required. 

v2.0 release contains a full CRUD In-Memory demo, using Security Roles, Service, Dao and Domain objects. It comes
pre-configured with a few entries that will display for two of the registered In-Memory users ( in the applicationContext-security.xml file) which can be edited and deleted. New entries can and also be created/edited/deleted.

The entries that are created and already set up and held in an ArrayList in the domain object (com.fireduptech.springsecurity.domain.AthleteAccountDetails) in this version as no database is used.
https://github.com/mccarthyr/springsecurity/releases/tag/v2.0

v3.0 release contains a full CRUD ACL database driven solution with an exiting admin and two regular users and roles. Functionality has been added to allow a regular user to provide access to the admin user who can then close an account. 

The Domain object that is secured is an AthleteAccountDetails object which is simulating account details for an athlete on what could be a sports data system. An Athlete role can create, edit and update their account but only an Admin role can close then account. The Admin role does not automatically have access to the Athlete account and has to be given access by the owner of the account.

*** PROVIDE A SCRIPT OF THE DATABASE SCHEMA ***
