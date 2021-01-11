package com.example.log_in_java.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.log_in_java.NewGameActivity;
import com.example.log_in_java.models.Objects;
import com.example.log_in_java.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder>{

    List<Objects> objectsList;
    List<Objects> selectedObjectsList;
    Context context;
    private MyAdapter objectsSelectedAdapter;

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
    public void onBindViewHolder(@NonNull MyAdapter2.MyViewHolder holder, int position) {

        Picasso.get().load("http://eetacdsa0.upc.es:8080/"+objectsList.get(position).getUrlImage()).into(holder.myImage);

        holder.myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedObjectsList.add(objectsList.get(position));
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


        ImageView myImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myImage = (ImageView) itemView.findViewById(R.id.objectToChoose);


        }


    }




}
