package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
  Scanner sc = new Scanner(System.in);
  DatabaseAccessor db = new DatabaseAccessorObject();

  public static void main(String[] args) throws SQLException {
    FilmQueryApp app = new FilmQueryApp();
    app.test();
    app.launch();
  }

  private void test() throws SQLException {
    Film film = db.findFilmById(1);
    Actor actor = db.findActorById(1);
    System.out.println(actor);
  }

  private void launch() {
    
    startUserInterface();
    
  }

  private void startUserInterface() {
  boolean on = true;
	  do {
    	displayMenu();
    	int input = sc.nextInt();
    	sc.nextLine();
    	switch(input) {
    	case 1: 
    		System.out.println("Enter your film id.");
    		Film filmResult = findFilmById(input);
    		if(filmResult != null) {
    			List<Film> searchResults = idSearchCopies(filmResult);
    			displayFilmsDetails(searchResults);
    		}
    		else {
    			System.out.println("Sorry. There were no results.");
    		}
    		break;
    	case 2:	
    		System.out.println("What do you want to search?");
    		String searchKeyword = sc.nextLine();
    		List<Film> searchResults = findFilmBySearch(searchKeyword);
    		if(!searchResults.isEmpty()) {
    			displayFilmsDetails(searchResults);
    		}
    		else {
    			System.out.println("Sorry. There were no results.");
    		}
    	case 3: System.out.println("Goodbye!");
    	 on = false;
    	 System.exit(0);
    	default: break;
    	}
    }
    	while(on);
	  sc.close();
  }
  private void displayMenu() {
	  System.out.println(" Please make a choice to find the film you want.");
	  System.out.println(" Choose ID to sort by the film id.");
	  System.out.println(" --------------------------------------");
	  System.out.println(" Choose to find by a keyword.");
	  System.out.println(" --------------------------------------");
	  System.out.println(" Type 'Exit' to exit the menu.");
  }
  private Film findFilmById(int input) {
		System.out.println("Enter The Id Of The Film You're Searching For ");
		Film film = db.findFilmById(input);

		if (film != null) {
			System.out.println(film.getTitle());
		}
		return film;
	}
  private void displayFilmsDetails(List<Film> films) {
		for (Film film : films) {
			System.out.println("Title : " + film.getTitle());
			System.out.println("Year : " + film.getReleaseYear());
			System.out.println("Rating : " + film.getRating());
			System.out.println("Description : " + film.getDescription());
			System.out.println(" ");
		}
	}
  private List<Film> idSearchCopies(Film film) {
		List<Film> films = db.findCopiesCondition(film);
		return films;
	}
  private List<Film> findFilmBySearch(String searchStatement) {
		List<Film> films = db.findFilmsByKeyword(searchStatement);
		if (!films.isEmpty()) {
			for (Film film : films) {
				System.out.println("Title : " + film.getTitle());
				System.out.println(" ");
			}
		}
		return films;

	}
}
