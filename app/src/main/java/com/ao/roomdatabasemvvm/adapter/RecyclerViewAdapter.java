package com.ao.roomdatabasemvvm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ao.roomdatabasemvvm.R;
import com.ao.roomdatabasemvvm.model.Player;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

	private List<Player> players = new ArrayList<>();

	private OnPlayerClickListner onPlayerClickListner;

	public interface OnPlayerClickListner{
		void onPlayerClick(Player player);
	}

	public void setOnPlayerClickListner(OnPlayerClickListner onPlayerClickListner) {
		this.onPlayerClickListner = onPlayerClickListner;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
		notifyDataSetChanged();
	}


	public Player getPlayerAt(int position)
	{   Player player = players.get(position);
		player.setId(players.get(position).getId());
		return player;
	}


	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_layout,null);
		ViewHolder recyclerViewHolder = new ViewHolder(view,onPlayerClickListner);
		return recyclerViewHolder;
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		Player currentPlayer = players.get(position);
		holder.mName.setText("Name: "+currentPlayer.getName());
		holder.mAge.setText("Age: " +currentPlayer.getAge());
		holder.mPosition.setText("Position: " +currentPlayer.getPosition());

	}

	@Override
	public int getItemCount() {
		if (players ==null )return 0;
		return players.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		private TextView mName;
		private TextView mAge;
		private TextView mPosition;
		private OnPlayerClickListner mListener;

		public ViewHolder(@NonNull View itemView, OnPlayerClickListner onPlayerClickListner) {
			super(itemView);
			this.mListener = onPlayerClickListner;
			itemView.setOnClickListener(this);
			mName = itemView.findViewById(R.id.tv_name);
			mAge = itemView.findViewById(R.id.tv_age);
			mPosition = itemView.findViewById(R.id.tv_position);

		}

		@Override
		public void onClick(View v) {
			int position = getAdapterPosition();
			Player currentPlayer = players.get(position);
			Player player = new Player(currentPlayer.getName(),currentPlayer.getPosition(),currentPlayer.getAge());
			player.setId(currentPlayer.getId());
			mListener.onPlayerClick(player);
		}
	}
}
