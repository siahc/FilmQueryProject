package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	
	private Connection conn;
	
	public DatabaseAccessorObject() {
		String url = "jdbc:mysql://localhost:3306/sdvid";
		String user = "student";
		String pass = "student";
		
		// when constructing a DB Accessor, let's already create one connection
		// all of our methods can use this.conn (connection) to access the db without
		// recreating it every single time
		try {
			this.conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		try {
			String sql = "SELECT * \n"
					+ "FROM film \n"
					+ "WHERE film.id = ?";
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet filmResult = stmt.executeQuery();
			if (filmResult.next()) {
				// Here is our mapping of query columns to our object fields:
				int id = filmResult.getInt(1);
				String title = filmResult.getString(2);
				String desc = filmResult.getString(3);
				int rYear = filmResult.getInt(4);
				int lID = filmResult.getInt(5);
			 	int rd = filmResult.getInt(7);
				Double rr = filmResult.getDouble(6);
				int length = filmResult.getInt(8);
				Double repC = filmResult.getDouble(9); 
				String rating = filmResult.getString(10);
				// Get the set values as one String, then split it.
				String sp_ft_str = filmResult.getString(11);
				String[] special_features = sp_ft_str.split(",");
				
				// let's also query for all of the Actors for this film:
				List<Actor> actorList = findActorsByFilmId(id);
				
				
				film = new Film(); // Create the object
				
				film.setId(id);
				film.setTitle(title);
				film.setDescription(desc);
				film.setReleaseYear(rYear);
				film.setLanguageId(lID);
				film.setRentalDuration(rd);
				film.setRentalRate(rr);
				film.setLength(length);
				film.setReplacementCost(repC);
				film.setRating(rating);
				film.setSpecialFeatures(special_features);
				film.setActors(actorList);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		// ...
		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		try {
			String sql = "SELECT * \n"
					+ "FROM actor \n"
					+ "WHERE actor.id = ?";
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet filmResult = stmt.executeQuery();
			if (filmResult.next()) {
				// Here is our mapping of query columns to our object fields:
				int id = filmResult.getInt(1);
				String fName = filmResult.getString(2);
				String lName = filmResult.getString(3);
				
				actor = new Actor(id, fName, lName); // Create the object
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		// ...
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actorList = new ArrayList<Actor>();
		try {
			String sql = "SELECT DISTINCT actor.id, actor.first_name, actor.last_name \n"
					+ "FROM film_actor \n"
					+ "INNER JOIN actor ON film_actor.actor_id = actor.id \n"
					+ "WHERE film_actor.film_id = ? \n";
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet filmResult = stmt.executeQuery();
			
			while (filmResult.next()) {
				// Here is our mapping of query columns to our object fields:
				int id = filmResult.getInt(1);
				String fName = filmResult.getString(2);
				String lName = filmResult.getString(3);
				
				actorList.add( new Actor(id, fName, lName) );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		// ...
		return actorList;
	}
	
	public List<Film> searchFilms(String keyword) {
		List<Film> filmList = new ArrayList<Film>();
		try {
			String sql = "SELECT * \n"
					+ "FROM film \n"
					// Searches films titles allowing for titles and descriptions
					+ "WHERE film.title LIKE ? \n"
					+ "OR film.description LIKE ? \n"
					// Make search case-insensitive
					+ "COLLATE utf8_general_ci";
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			System.out.println(stmt);
			ResultSet filmResult = stmt.executeQuery();
			
			while (filmResult.next()) {
				// Here is our mapping of query columns to our object fields:
				int id = filmResult.getInt(1);
				String title = filmResult.getString(2);
				String desc = filmResult.getString(3);
				int rYear = filmResult.getInt(4);
				int lID = filmResult.getInt(5);
			 	int rd = filmResult.getInt(7);
				Double rr = filmResult.getDouble(6);
				int length = filmResult.getInt(8);
				Double repC = filmResult.getDouble(9); 
				String rating = filmResult.getString(10);
				// Get the set values as one String, then split it.
				String sp_ft_str = filmResult.getString(11);
				String[] special_features = sp_ft_str.split(",");
				
				// let's also query for all of the Actors for this film:
				List<Actor> actorList = findActorsByFilmId(id);
				
				
				Film film = new Film(); // Create the object
				
				film.setId(id);
				film.setTitle(title);
				film.setDescription(desc);
				film.setReleaseYear(rYear);
				film.setLanguageId(lID);
				film.setRentalDuration(rd);
				film.setRentalRate(rr);
				film.setLength(length);
				film.setReplacementCost(repC);
				film.setRating(rating);
				film.setSpecialFeatures(special_features);
				film.setActors(actorList);		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		// ...
		return filmList;
	}

}
