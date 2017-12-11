package com.fireduptech.springsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.CrudRepository;

import com.fireduptech.springsecurity.domain.AthleteAccount;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.Modifying;

//import org.springframework.stereotype.Repository;

//@Repository

//public interface AthleteAccountRepository extends JpaRepository<AthleteAccount, Integer> {
//public interface AthleteAccountRepository extends Repository<AthleteAccount, Integer> {
//public interface AthleteAccountRepository extends CrudRepository<AthleteAccount, Long> {



//public interface AthleteAccountRepository extends Repository<AthleteAccount, String> {
//@Transactional
public interface AthleteAccountRepository extends JpaRepository<AthleteAccount, Integer> {

	//@Modifying
	//@Transactional
	AthleteAccount save( AthleteAccount entity );



	//AthleteAccount findOne( Integer id );

	//long count();

	//@Query( "select athleteAccount from AthleteAccount athleteAccount where athleteAccount.primaryActivity = ?1" )
	//List<AthleteAccount> findByCustomQuery( String primaryActivity );

}




