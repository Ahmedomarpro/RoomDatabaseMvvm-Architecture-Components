package com.ao.roomdatabasemvvm.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ao.roomdatabasemvvm.Repository.PlayerRepository;
import com.ao.roomdatabasemvvm.model.Player;

import java.util.List;

public class PlayerViewModel extends AndroidViewModel {

	private PlayerRepository repository;
	private LiveData<List<Player>> allPlayers;


	public PlayerViewModel (Application application){
		super(application);
		repository = new PlayerRepository(application);
		allPlayers = repository.getAllplaters();

	}
	public void insert (Player player){
		repository.insert(player);
	}

	public void update (Player player){
		repository.update(player);
	}

	public void delete (Player player){
		repository.delete(player);
	}

	public void deleteAllPlayers (){
		repository.deleteAllPlayers();
	}
	public LiveData<List<Player>> getAllPlayers()   {
		return allPlayers;
	}

	}
