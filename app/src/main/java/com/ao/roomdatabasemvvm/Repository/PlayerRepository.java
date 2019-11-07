package com.ao.roomdatabasemvvm.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ao.roomdatabasemvvm.dataBase.Dao.PlayerDao;
import com.ao.roomdatabasemvvm.dataBase.PlayerDatabase;
import com.ao.roomdatabasemvvm.model.Player;

import java.util.List;

public class PlayerRepository {


	private PlayerDao playerDao;

	private LiveData<List<Player>> allplaters;

	public PlayerRepository(Application application) {

		PlayerDatabase playerDatabase = PlayerDatabase.getInstance(application);
		playerDao = playerDatabase.playerDao();

		allplaters = playerDao.getAllPlayers();

	}
	public void insert (Player player){

		new InsertPlayerAsyncTask(playerDao).execute(player);
	}

	public void update(Player player){
		new UpdatePlayerAsyncTask(playerDao).execute(player);
	}
	public void delete(Player player){
		new DeletePlayerAsyncTask(playerDao).execute(player);
	}
	public void deleteAllPlayers(){
		new DeleteAllPlayersAsyncTask(playerDao).execute();
	}

	public LiveData<List<Player>>getAllplaters(){
		return allplaters;
	}


	//AsyncTask for create new player
	private static class  InsertPlayerAsyncTask extends AsyncTask<Player,Void,Void>{

		private PlayerDao playerDao;

		public InsertPlayerAsyncTask(PlayerDao playerDao) {
			this.playerDao = playerDao;
		}

		@Override
		protected Void doInBackground(Player... players) {
			playerDao.insert(players[0]);
			return null;
		}
	}

	//AsyncTask for update existing player
	private static class UpdatePlayerAsyncTask extends AsyncTask<Player,Void,Void>{
		private PlayerDao playerDao;

		public UpdatePlayerAsyncTask(PlayerDao playerDao) {
			this.playerDao = playerDao;
		}

		@Override
		protected Void doInBackground(Player... players) {
			playerDao.update(players[0]);
			return null;
		}
	}

	//AsyncTask for delete existing player
	private static class DeletePlayerAsyncTask extends AsyncTask<Player,Void,Void>{
		private PlayerDao playerDao;

		public DeletePlayerAsyncTask(PlayerDao playerDao) {
			this.playerDao = playerDao;
		}

		@Override
		protected Void doInBackground(Player... players) {
			playerDao.delete(players[0]);
			return null;
		}
	}

	//AsyncTask for Alldelete existing player
	private static class DeleteAllPlayersAsyncTask extends AsyncTask<Player,Void,Void>{
		private PlayerDao playerDao;

		public DeleteAllPlayersAsyncTask (PlayerDao playerDao) {
			this.playerDao = playerDao;
		}

		@Override
		protected Void doInBackground(Player... players) {
			playerDao.deletAllPlayer();
			return null;
		}
	}


}
