package com.project.Spicles;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class LocateAdapter extends RecyclerView.Adapter<LocateHolder> {
    Context context;
    public LocateAdapter(Context c) {
        super();
        context=c;

    }

    @NonNull
    @Override
    public LocateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.activity_hq,parent,false);
        return new LocateHolder(v,context);
    }

    @Override
    public void onBindViewHolder(@NonNull LocateHolder holder, int position) {
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
        {

            if(position==0)
            {
                holder.nearbranch.setVisibility(View.VISIBLE);
            }
            else
            {
                holder.nearbranch.setVisibility(View.INVISIBLE);
            }
        }
        else
        {holder.nearbranch.setVisibility(View.INVISIBLE);

        }

        holder.texttitle.setText(LocateUsActivity.distance.get(position).title);
        holder.textnearaddress.setText(LocateUsActivity.distance.get(position).adress);
        holder.textphone.setText(LocateUsActivity.distance.get(position).phone);
    }

    @Override
    public int getItemCount() {
        return LocateUsActivity.distance.size();
    }
}

