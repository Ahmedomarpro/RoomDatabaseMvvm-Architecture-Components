package com.ao.roomdatabasemvvm.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.ao.roomdatabasemvvm.R;
import com.ao.roomdatabasemvvm.adapter.RecyclerViewAdapter;
import com.ao.roomdatabasemvvm.model.Player;
import com.ao.roomdatabasemvvm.utils.CreatePlayerDialog;
import com.ao.roomdatabasemvvm.utils.UpdatePlayerDialog;
import com.ao.roomdatabasemvvm.viewModel.PlayerViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CreatePlayerDialog.CreatePlayerListener,
        RecyclerViewAdapter.OnPlayerClickListner,
        UpdatePlayerDialog.UpdatePlayerListener {

    private PlayerViewModel playerViewModel;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private View parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intToolbar();
        intView();


        //Observe Player add
        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        playerViewModel.getAllPlayers().observe(this, new Observer<List<Player>>() {
            @Override
            public void onChanged(List<Player> players) {
                recyclerViewAdapter.setPlayers(players);

            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                playerViewModel.delete(recyclerViewAdapter.getPlayerAt(viewHolder.getAdapterPosition()));
                snackBar("Player Deleted");
            }
        }).attachToRecyclerView(mRecyclerView);

  }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_allPlayer:
                playerViewModel.deleteAllPlayers();
                snackBar("All Player Deleted");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    private void intView() {

        parent = findViewById(android.R.id.content);
        FloatingActionButton fab = findViewById(R.id.fab);
        mRecyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        //Set adapter to RecyclerView
        recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerViewAdapter.setOnPlayerClickListner(this);
        mRecyclerView.setAdapter(recyclerViewAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreatePlayerDialog();
            }
        });
    }




    private void openCreatePlayerDialog() {
        CreatePlayerDialog createPlayerDialog = new CreatePlayerDialog();
        createPlayerDialog.show(getSupportFragmentManager(),"create player");
    }

    private void intToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    public void snackBar(String message) {
        Snackbar.make(parent, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onPlayerClick(Player player) {
        Log.d("MainActivity_Log",""+player.getId());
        openUpdatePlayerDialog(player);

    }
    private void openUpdatePlayerDialog(Player player) {
        UpdatePlayerDialog updatePlayerDialog = new UpdatePlayerDialog();
        updatePlayerDialog.setPlayer(player);
        updatePlayerDialog.show(getSupportFragmentManager(),"update player");
    }



    @Override
    public void saveNewPlayer(Player player) {
        playerViewModel.insert(player);
        snackBar("Player Saved");



    }

    @Override
    public void updateNewPlayer(Player player) {
        playerViewModel.update(player);
        snackBar("Player Updated");
    }
}
