package com.ao.roomdatabasemvvm.dataBase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.ao.roomdatabasemvvm.dataBase.Dao.PlayerDao;
import com.ao.roomdatabasemvvm.model.Player;

@Database( entities = {Player.class},version = 1)
public abstract class PlayerDatabase extends RoomDatabase{

	public static PlayerDatabase instance;

	public abstract PlayerDao playerDao();

	public static synchronized PlayerDatabase getInstance(Context context){

		if (instance == null){
			instance = Room.databaseBuilder(context.getApplicationContext(),PlayerDatabase.class,"player_database")
					.fallbackToDestructiveMigration()
					.addCallback(roomCallBack)
					.build();
		}
		return instance;
	}


	private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
		@Override
		public void onCreate(@NonNull SupportSQLiteDatabase db) {
			super.onCreate(db);
		}
	};

 	}
