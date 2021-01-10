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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<Objects> objectsList;
    Context context;

    public MyAdapter(Context ct, List<Objects> objectsToList) {
        context =ct;
        objectsList =objectsToList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.my_object_row,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.nameObjectText.setText(objectsList.get(position).getName());
        holder.descriptionObjectText.setText(objectsList.get(position).getDescription());
        if(objectsList.get(position).isBag())
        {
            holder.attributeObjectText.setText("Bag capacity:" +objectsList.get(position).getAttribute());
        }
        else
        {
            holder.attributeObjectText.setText("Suspicious decrement: " +objectsList.get(position).getAttribute());
        }
        //Poner la foto
        Picasso.get().load("http://eetacdsa0.upc.es:8080/"+objectsList.get(position).getUrlImage()).into(holder.myImage);

    }

    @Override
    public int getItemCount() {
        return objectsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameObjectText;
        TextView descriptionObjectText;
        TextView attributeObjectText;
        ImageView myImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameObjectText = (TextView) itemView.findViewById(R.id.textNameObject);
            descriptionObjectText = (TextView) itemView.findViewById(R.id.textDescriptionObject);
            attributeObjectText = (TextView) itemView.findViewById(R.id.textAttributeObject);
            myImage = (ImageView) itemView.findViewById(R.id.imageObject);
        }
    }
}
