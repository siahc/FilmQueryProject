package com.skilldistillery.filmquery.entities;

public class Film {
	
	private int id;
	private String title;
	private String description;
	private int release_year;
	private int language_id;
	private int rental_duration;
	private double rental_rate;
	private int length;
	private double replacement_cost;
	private enum rating;
	private String special_features;
	private List<Actor> actors;
}
