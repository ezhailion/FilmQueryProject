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
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private static final String USER = "student";
	private static final String PASS = "student";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		  Actor actor = null;
		  //...
		  Connection conn = DriverManager.getConnection(URL, USER, PASS);
		  String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
		  PreparedStatement stmt = conn.prepareStatement(sql);
		  stmt.setInt(1,actorId);
		  ResultSet actorResult = stmt.executeQuery();
		  if (actorResult.next()) {
		    actor = new Actor(); // Create the object
		    // Here is our mapping of query columns to our object fields:
		    actor.setId(actorResult.getInt(1));
		    actor.setFirstName(actorResult.getString(2));
		    actor.setLastName(actorResult.getString(3));
		    actor.setFilms(findFilmsByActorId(actorId)); // An Actor has Films
		  }
		  //...
		  return actor;
		}

		public List<Film> findFilmsByActorId(int actorId) {
		  List<Film> films = new ArrayList<>();
		  try {
		    Connection conn = DriverManager.getConnection(URL, USER, PASS);
		    String sql = "SELECT * from film join film_actor on film.id = film_actor.film_id where actor_id = ?";
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setInt(1, actorId);
		    ResultSet rs = stmt.executeQuery();
		    while (rs.next()) {
		      int filmId = rs.getInt("id");
		      String title = rs.getString("title");
		      String desc = rs.getString("description");
		      short releaseYear = rs.getShort("release_year");
		      int langId = rs.getInt("language_id");
		      int rentDur = rs.getInt("rental_duration");
		      double rate = rs.getDouble("rate");
		      int length = rs.getInt("length");
		      double repCost = rs.getDouble(9);
		      String rating = rs.getString(10);
		      String features = rs.getString(11);
		      Film film = new Film(filmId, title, desc, releaseYear, langId,
		                           rentDur, rate, length, repCost, rating, features);
		      films.add(film);
		    }
		    rs.close();
		    stmt.close();
		    conn.close();
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }
		  return films;
		}

		@Override
		public Film findFilmById(int filmId) {
			// TODO Auto-generated method stub
			return null;
		}



}
