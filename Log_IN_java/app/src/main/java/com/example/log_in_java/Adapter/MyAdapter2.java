package com.example.log_in_java.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.log_in_java.models.Objects;
import com.example.log_in_java.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder>{

    List<Objects> objectsList;
    List<Objects> selectedObjectsList;
    Context context;

    public MyAdapter2(Context ct, List<Objects> objectsToList) {
        context =ct;
        objectsList =objectsToList;
    }

    @NonNull
    @Override
    public MyAdapter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.objects_to_choose,parent,false);
        return new MyAdapter2.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter2.MyViewHolder holder, int position) {

        Picasso.get().load("http://eetacdsa0.upc.es:8080/"+objectsList.get(position).getUrlImage()).into(holder.myImage);

    }

    @Override
    public int getItemCount() {
        return objectsList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView myImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myImage = (ImageView) itemView.findViewById(R.id.objectToChoose);
        }
    }




}
