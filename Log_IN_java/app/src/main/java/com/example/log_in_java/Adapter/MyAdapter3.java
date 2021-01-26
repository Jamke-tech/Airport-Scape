package com.example.log_in_java.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.log_in_java.MyGame;
import com.example.log_in_java.MyMaps;
import com.example.log_in_java.NewGameActivity;
import com.example.log_in_java.models.Game;
import com.example.log_in_java.models.Objects;
import com.example.log_in_java.R;
import com.squareup.picasso.Picasso;
import com.unity3d.player.UnityPlayerActivity;

import java.util.ArrayList;
import java.util.List;


public class MyAdapter3  extends RecyclerView.Adapter<MyAdapter3.MyViewHolder>{
    List<Game>  listGames;
    Game selectedGame;
    Context context;
    String nickname;
    int posicionMarcada = 0;
    int atributoObjeto;//prueba jaume
    //Boolean chosen = false;

    private int maleta = 0;


    public MyAdapter3(Context ct, List<Game> gamesToList) {
        context =ct;
        listGames =gamesToList;
    }

    @NonNull
    @Override
    public MyAdapter3.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.games_saved,parent,false);
        return new MyAdapter3.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter3.MyViewHolder holder, int position) {
        holder.gameName.setText(listGames.get(position).getName());
        final int pos = position;
        holder.gameName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newintent = new Intent(context, UnityPlayerActivity.class); //PASAR DATOS!!!!

                selectedGame = listGames.get(position);

                MyGame startedGame = MyGame.getInstance();
                startedGame.setLevel(selectedGame.getIdMap());
                startedGame.setMoney(selectedGame.getMoney());
                startedGame.setNameUser(selectedGame.getUserName());
                startedGame.setSuspicious(selectedGame.getSuspicious());
                startedGame.setNamePartida(selectedGame.getName());
                MyMaps mapas = MyMaps.getInstance();
                mapas.setMaps();

                context.startActivity(newintent);


                System.out.println("click");
            }
        });


    }


    @Override
    public int getItemCount() {
        return listGames.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardGame;
        TextView gameName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardGame = (CardView) itemView.findViewById(R.id.cardGame);
            gameName = (TextView) itemView.findViewById(R.id.gameNameTextView);


        }
    }



}



