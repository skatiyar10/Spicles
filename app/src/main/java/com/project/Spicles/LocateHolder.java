package com.project.Spicles;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LocateHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView nearbranch,texttitle,textnearaddress,textphone;
    ImageButton map,call;
    Context c;
    public LocateHolder(@NonNull View itemView, Context x) {
        super(itemView);
        nearbranch=itemView.findViewById(R.id.nearbranch);
        texttitle=itemView.findViewById(R.id.texttitle);
        c=x;
        textnearaddress=itemView.findViewById(R.id.text_near_add);
        textphone=itemView.findViewById(R.id.textphone);
        map=itemView.findViewById(R.id.near_map);
        call=itemView.findViewById(R.id.near_call);
        call.setOnClickListener(this);
        map.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.near_map)
        {
            Uri uri=Uri.parse("google.navigation:q="+ LocateUsActivity.distance.get(getAdapterPosition()).lat+","+ LocateUsActivity.distance.get(getAdapterPosition()).lon);
            Intent mapIntent=new Intent(Intent.ACTION_VIEW,uri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if(mapIntent.resolveActivity(c.getPackageManager())!=null)
                c.startActivity(mapIntent);
        }
        else
        {String phone[]= LocateUsActivity.distance.get(getAdapterPosition()).phone.split(",");
            Uri uri;

            if(phone.length==1)
                uri=Uri.parse("tel:"+phone[0]);
            else
                uri=Uri.parse("tel:"+phone[1]);
            Intent telIntent=new Intent(Intent.ACTION_DIAL,uri);

            if(telIntent.resolveActivity(c.getPackageManager())!=null)
                c.startActivity(telIntent);
        }
    }
}

