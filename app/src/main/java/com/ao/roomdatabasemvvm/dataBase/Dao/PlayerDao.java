package com.ao.roomdatabasemvvm.dataBase.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ao.roomdatabasemvvm.model.Player;

import java.util.List;

@Dao
public interface PlayerDao {

	@Insert
	void insert (Player player);

	@Update
	void update (Player player);

	@Delete
	void delete (Player player);

	@Query("DELETE FROM players_table")
	void deletAllPlayer ();

	//Get all the list of player from table by descending order
	@Query("SELECT * FROM PLAYERS_TABLE")
	LiveData <List<Player>> getAllPlayers();
}
