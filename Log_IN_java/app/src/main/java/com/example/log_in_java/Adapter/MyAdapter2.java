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

import com.example.log_in_java.NewGameActivity;
import com.example.log_in_java.models.Objects;
import com.example.log_in_java.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder>{

    List<Objects> objectsList;
    List<Objects> selectedObjectsList = new ArrayList<Objects>();;
    Context context;
    int posicionMarcada = 0;
    int atributoObjeto;
    //Boolean chosen = false;

    private int maleta = 0;


    public MyAdapter2(Context ct, List<Objects> objectsToList) {
        context =ct;
        objectsList =objectsToList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.objects_to_choose,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Picasso.get().load("http://eetacdsa0.upc.es:8080/"+objectsList.get(position).getUrlImage()).into(holder.myImage);
        final int pos = position;
        holder.myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (objectsList.get(position).chosen == false) {
                    //chosen = true;
                    objectsList.get(position).setChosen(true);
                    if(!objectsList.get(position).isBag() || (maleta < 1)){
                        if(objectsList.get(position).isBag()){
                            maleta = 1;
                        }
                        holder.barraSeleccion.setBackgroundColor(Color.RED);
                        notifyDataSetChanged();
                        selectedObjectsList.add(objectsList.get(position));
                        notifyDataSetChanged();
                        System.out.println("seleccionado");
                    }
                    /*holder.barraSeleccion.setBackgroundColor(Color.RED);

                    selectedObjectsList.add(objectsList.get(position));*/

                }
                else{
                    holder.barraSeleccion.setBackgroundColor(Color.BLACK);
                    notifyDataSetChanged();
                    objectsList.get(position).setChosen(false);
                    //chosen = false;
                    maleta = 0;

                    selectedObjectsList.remove(objectsList.get(position));
                    notifyDataSetChanged();
                    System.out.println("deseleccionado");
                }
                System.out.println("click");
            }
        });


    }

    @Override
    public int getItemCount() {
        return objectsList.size();
    }


    public List<Objects> getSelectedObjectsList() {
        return selectedObjectsList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardObject;
        ImageView myImage;
        TextView barraSeleccion;
        ProgressBar progressbar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardObject = (CardView) itemView.findViewById(R.id.cardObject);
            myImage = (ImageView) itemView.findViewById(R.id.objectToChoose);
            barraSeleccion = (TextView) itemView.findViewById(R.id.barraSeleccion);
            progressbar = (ProgressBar) itemView.findViewById(R.id.progressBarSuspicious);

        }
    }




}
