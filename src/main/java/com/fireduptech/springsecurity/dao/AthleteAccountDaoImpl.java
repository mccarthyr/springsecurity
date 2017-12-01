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


import com.fireduptech.springsecurity.domain.AthleteAccount;


@Repository
public class AthleteAccountDaoImpl implements AthleteAccountDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;




	@Override
	public AthleteAccount getAthleteAccount( int athleteAccountId ) {

		final String sql = "select * from athlete_account where id = :athleteAccountId";

		SqlParameterSource namedParameters = new MapSqlParameterSource( "athleteAccountId", athleteAccountId );

		return namedParameterJdbcTemplate.query( sql, namedParameters,
				new ResultSetExtractor<AthleteAccount>() {
					
					AthleteAccount acd = new AthleteAccount();

					@Override
					public AthleteAccount extractData( ResultSet rs ) throws SQLException, DataAccessException {
						while ( rs.next() ) {
							acd.setId( Integer.parseInt( rs.getString( "id" ) ) );
							acd.setAccountType( rs.getString( "account_type" ) );
							acd.setFirstName( rs.getString( "first_name" ) );
							acd.setLastName( rs.getString( "last_name" ) );
							acd.setEmail( rs.getString( "email" ) );
							acd.setPrimaryActivity( rs.getString( "primary_activity" ) );
						}
						return acd;
					}
				} );

	}	// End of method getAthleteAccount()...


	@Override
	public List<AthleteAccount> getAllAthleteAccounts() {

		final String sql = "select * from athlete_account";

		return namedParameterJdbcTemplate.query( sql, 
				new ResultSetExtractor<List<AthleteAccount>>() {

					List<AthleteAccount> acds = new ArrayList<AthleteAccount>();

					@Override
					public List<AthleteAccount> extractData( ResultSet rs ) throws SQLException, DataAccessException {
							while ( rs.next() ) {
								AthleteAccount acd = new AthleteAccount();
								acd.setId( Integer.parseInt( rs.getString( "id" ) ) );
								acd.setAccountType( rs.getString( "account_type" ) );
								acd.setFirstName( rs.getString( "first_name" ) );
								acd.setLastName( rs.getString( "last_name" ) );
								acd.setEmail( rs.getString( "email" ) );
								acd.setPrimaryActivity( rs.getString( "primary_activity" ) );
								acds.add( acd );
							}

						return acds;
					} 
				});

	}	// End of method getAllAthleteAccounts()...


	@Override
	public void saveAthleteAccount(AthleteAccount athleteAccount ) {

		final String sql = "insert into athlete_account( account_type, first_name, last_name, email, primary_activity ) "
												+ "values( :account_type, :name, :email, :primary_activity )";

		Map<String, String> namedParams = new HashMap<String, String>();
		namedParams.put( "account_type", athleteAccount.getAccountType() );
		namedParams.put( "first_name", athleteAccount.getFirstName() );
		namedParams.put( "last_name", athleteAccount.getLastName() );
		namedParams.put( "email", athleteAccount.getEmail() );
		namedParams.put( "primary_activity", athleteAccount.getPrimaryActivity() );

		SqlParameterSource sqlParams = new MapSqlParameterSource( namedParams );
		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update( sql, sqlParams, keyHolder );

		athleteAccount.setId( keyHolder.getKey().intValue() );

		//athleteAccount.setId( keyHolder.getKey().longValue() );

	}	// End of method saveAthleteAccount()...



	@Override
	public void closeAthleteAccount( int athleteAccountId ) {

		final String sql = "delete from athlete_account where id = :athleteAccountId";

		SqlParameterSource namedParameters = new MapSqlParameterSource( "athleteAccountId", athleteAccountId );

		namedParameterJdbcTemplate.update( sql, namedParameters );

	}	// End of method closeAthleteAccount()...



	@Override
	public void editAthleteAccount( AthleteAccount modifiedAthleteAccount ) {

		final String sql = "update athlete_account "
							+ "set account_type = :account_type, first_name = :first_name, last_name = :last_name, email = :email, primary_activity = :primary_activity "
							+ "where id = :athleteAccountId";

		Map<String, String> namedParams = new HashMap<String, String>();
		namedParams.put( "account_type", modifiedAthleteAccount.getAccountType() );
		namedParams.put( "first_name", modifiedAthleteAccount.getFirstName() );
		namedParams.put( "last_name", modifiedAthleteAccount.getLastName() );
		namedParams.put( "email", modifiedAthleteAccount.getEmail() );
		namedParams.put( "primary_activity", modifiedAthleteAccount.getPrimaryActivity() );
		namedParams.put( "athleteAccountId", Integer.toString( modifiedAthleteAccount.getId() ) );
//		namedParams.put( "athleteAccountId", Long.toString( modifiedAthleteAccount.getId() ) );

		SqlParameterSource sqlParams = new MapSqlParameterSource( namedParams );

		namedParameterJdbcTemplate.update( sql, sqlParams );

	}	// End of method editAthleteAccount()...



}

