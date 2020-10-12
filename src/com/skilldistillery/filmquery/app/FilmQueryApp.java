package com.skilldistillery.filmquery.app;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
	Scanner input = new Scanner(System.in);
  DatabaseAccessor db = new DatabaseAccessorObject();

  public static void main(String[] args) {
    FilmQueryApp app = new FilmQueryApp();
  //  app.test();
    app.launch();
    app.db.close();
  }
  
  private void launch() {
		System.out.println("Welome to the movie database");

		while (true) {
			System.out.println();
			System.out.println("Please press '1' to search for a movie by ID or '2' to search for films by keyword, or 3 to exit?");
			String selection = input.nextLine();
			switch (selection) {
			case "1": {
				menuFindMovieById();
				break;
			}
			case "2": {
				menuSearchMovies();
				break;
			}
			case "3": {
				System.out.println("Goodbye");
				return;
			}
			
			default: {
				System.out.println("Invalid entry. Please select a valid input.");
				break;
			}
			}
		}
	}

  private void menuFindMovieById() {
	  System.out.println("Please enter film ID number and hit 'ENTER'.");
		try {
			int id = input.nextInt();
			input.nextLine(); // go to the next line after we get our number
			Film film = db.findFilmById(id);
			if(film == null) {
				System.out.println("Film not found. Please try again.");
			}
			else {
				System.out.println(film);
			}
		} catch (InputMismatchException e) {
			input.nextLine(); // go to the next line after we get our number
			System.out.println("Invalid film ID: try using numbers.");
			//Recursion in case of invalid entry so user is not taken all the way back to original menu.
			menuFindMovieById();
		}
  }
  
  private void menuSearchMovies() {
	  System.out.println("Please enter key word and hit 'ENTER'.");
		String keyWord = input.nextLine();
		List<Film> filmList = db.searchFilms(keyWord);
		if(filmList == null || filmList.size() == 0) {
			System.out.println("No films found. Please try again.");
		}
		else {
			System.out.println(filmList);
		}
  }

  private void test() {
    Film film = db.findFilmById(1);
    Actor actor = db.findActorById(1);
    System.out.println(film);
    System.out.println(actor);

    List<Film> search1 = db.searchFilms("Super");
    List<Film> search2 = db.searchFilms("Teacher");
    System.out.println(search1);
    System.out.println(search2);
  }

//  private void launch() {
//    Scanner input = new Scanner(System.in);
//    
//    startUserInterface(input);
//    
//    input.close();
//  }

  private void startUserInterface(Scanner input) {
    
  }

}

