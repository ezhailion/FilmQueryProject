package com.skilldistillery.filmquery.entities;

import java.util.List;

public class Film {
	private int id;
	private String title;
	private String description;
	private short releaseYear;
	private int langId;
	private int rentDur;
	private double rate;
	private int length;
	private double repCost;
	private String rating;
	private String features;
	private List<Actor> actors;


	public Film(int id, String title, String description, short releaseYear, int langId, int rentDur, double rate,
			int length, double repCost, String rating, String features) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.langId = langId;
		this.rentDur = rentDur;
		this.rate = rate;
		this.length = length;
		this.repCost = repCost;
		this.rating = rating;
		this.features = features;
	}
}
