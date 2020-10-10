package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
  
  DatabaseAccessor db = new DatabaseAccessorObject();

  public static void main(String[] args) {
    FilmQueryApp app = new FilmQueryApp();
    app.test();
//    app.launch();
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

  private void launch() {
    Scanner input = new Scanner(System.in);
    
    startUserInterface(input);
    
    input.close();
  }

  private void startUserInterface(Scanner input) {
    
  }

}

