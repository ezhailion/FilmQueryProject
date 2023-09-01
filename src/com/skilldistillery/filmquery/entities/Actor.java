package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

public class Actor {
private int id;
private String firstName;
private String lastName;
private List<Film> films;
public Actor() {}

public Actor(int newID, String fName, String lName) {
	id = newID;
	firstName = fName;
	lastName = lName;
}
public List<Film> getFilms() {
	return films;
}

public void setFilms(List<Film> films) {
	this.films = films;
}

public Actor(String fName, String lName) {
	firstName = fName;
	lastName = lName;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Actor [id=");
	builder.append(id);
	builder.append(", firstName=");
	builder.append(firstName);
	builder.append(", lastName=");
	builder.append(lastName);
	builder.append("]");
	return builder.toString();
}

@Override
public int hashCode() {
	return Objects.hash(firstName, id, lastName);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Actor other = (Actor) obj;
	return Objects.equals(firstName, other.firstName) && id == other.id && Objects.equals(lastName, other.lastName);
}

}
