package com.fireduptech.springsecurity.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;


import org.springframework.stereotype.Repository;


import com.fireduptech.springsecurity.domain.AthleteAccountDetails;

/*
 * NOTE: Here in the Constructor we hardcode a few AthleteAccountDetails
         domain objects so as to simulate accounts being held in 
         persistent storage.
 */

@Repository
public class AthleteAccountDaoImpl implements AthleteAccountDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;




	@Override
	public AthleteAccountDetails getAthleteAccount( int athleteAccountId ) {

		final String sql = "select * from athlete_account_details where id = :athleteAccountId";

		SqlParameterSource namedParameters = new MapSqlParameterSource( "athleteAccountId", athleteAccountId );

		return namedParameterJdbcTemplate.query( sql, namedParameters,
				new ResultSetExtractor<AthleteAccountDetails>() {
					
					AthleteAccountDetails acd = new AthleteAccountDetails();

					@Override
					public AthleteAccountDetails extractData( ResultSet rs ) throws SQLException, DataAccessException {
						while ( rs.next() ) {
							acd.setAccountType( rs.getString( "accountType" ) );
							acd.setName( rs.getString( "name" ) );
							acd.setEmail( rs.getString( "email" ) );
							acd.setPrimaryActivity( rs.getString( "primaryActivity" ) );
						}
						return acd;
					}
				} );

	}	// End of method getAthleteAccount()...


	@Override
	public List<AthleteAccountDetails> getAllAthleteAccounts() {

		final String sql = "select * from athlete_account_details";

		return namedParameterJdbcTemplate.query( sql, 
				new ResultSetExtractor<List<AthleteAccountDetails>>() {

					List<AthleteAccountDetails> acds = new ArrayList<AthleteAccountDetails>();

					@Override
					public List<AthleteAccountDetails> extractData( ResultSet rs ) throws SQLException, DataAccessException {
							while ( rs.next() ) {
								AthleteAccountDetails acd = new AthleteAccountDetails();
								acd.setAccountType( rs.getString( "accountType" ) );
								acd.setName( rs.getString( "name" ) );
								acd.setEmail( rs.getString( "email" ) );
								acd.setPrimaryActivity( rs.getString( "primaryActivity" ) );
								acds.add( acd );
							}

						return acds;
					} 
				});

	}	// End of method getAllAthleteAccounts()...


	@Override
	public void saveAthleteAccount(AthleteAccountDetails athleteAccountDetails ) {

		final String sql = "insert into athlete_account_details( accountType, name, email, primaryActivity ) "
												+ "values( :accountType, :name, :email, :primaryActivity )";

		Map<String, String> namedParams = new HashMap<String, String>();
		namedParams.put( "accountType", athleteAccountDetails.getAccountType() );
		namedParams.put( "name", athleteAccountDetails.getName() );
		namedParams.put( "email", athleteAccountDetails.getEmail() );
		namedParams.put( "primaryActivity", athleteAccountDetails.getPrimaryActivity() );

		SqlParameterSource sqlParams = new MapSqlParameterSource( namedParams );
		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update( sql, sqlParams, keyHolder );

		athleteAccountDetails.setId( keyHolder.getKey().longValue() );

	}	// End of method saveAthleteAccount()...



	@Override
	public void closeAthleteAccount( int athleteAccountId ) {

		final String sql = "delete from athlete_account_details where id = :athleteAccountId";

		SqlParameterSource namedParameters = new MapSqlParameterSource( "athleteAccountId", athleteAccountId );

		namedParameterJdbcTemplate.update( sql, namedParameters );

	}	// End of method closeAthleteAccount()...



	@Override
	public void editAthleteAccount( AthleteAccountDetails modifiedAthleteAccountDetails ) {

		final String sql = "update athlete_account_details "
							+ "set accountType = :accountType, name = :name, email = :email, primaryActivity = :primaryActivity "
							+ "where id = :athleteAccountId";

		Map<String, String> namedParams = new HashMap<String, String>();
		namedParams.put( "accountType", modifiedAthleteAccountDetails.getAccountType() );
		namedParams.put( "name", modifiedAthleteAccountDetails.getName() );
		namedParams.put( "email", modifiedAthleteAccountDetails.getEmail() );
		namedParams.put( "primaryActivity", modifiedAthleteAccountDetails.getPrimaryActivity() );
		namedParams.put( "athleteAccountId", Long.toString( modifiedAthleteAccountDetails.getId() ) );

		SqlParameterSource sqlParams = new MapSqlParameterSource( namedParams );

		namedParameterJdbcTemplate.update( sql, sqlParams );

	}	// End of method editAthleteAccount()...



}

