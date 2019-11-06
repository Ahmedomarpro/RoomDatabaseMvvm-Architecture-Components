package com.ao.roomdatabasemvvm.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "players_table")
public class Player {
	@PrimaryKey(autoGenerate = true)
	private int id;
	private String name;
	private String position;
	private String age;

	public Player(String name, String position, String age) {
		this.id = id;
		this.name = name;
		this.position = position;
		this.age = age;
	}

	public Player() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
}
