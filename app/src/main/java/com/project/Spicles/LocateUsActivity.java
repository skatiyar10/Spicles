package com.project.Spicles;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class LocateUsActivity extends AppCompatActivity  {
    RecyclerView recycler;
    Toolbar toolbar;
    Location loc;
    LocateAdapter adapter;
    static ArrayList<Distance> distance=null;
    TextView branch;
    private FusedLocationProviderClient fusedLocationProviderClient;
    RecyclerView.LayoutManager manager;
    Context c;
    ConstraintLayout layout;

    public LocateUsActivity() {
        loc = null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate_us);
        c=this;
        layout=findViewById(R.id.layout);
        recycler=findViewById(R.id.contactrecycler);
        toolbar=findViewById(R.id.toolbar_locate);
        toolbar.setTitle("Locate Us");
        toolbar.setTitleTextColor(getResources().getColor(R.color.gen_white));
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        //progress=findViewById(R.id.progress);
        manager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        branch=findViewById(R.id.ourbranch);
        branch.setVisibility(View.GONE);
        distance=new ArrayList<>();

        distance.add(new Distance("Spicles Jaipur","Civil Defence Colony, Parivahan Nagar, Khatipura, Jaipur\n" +
                "Pincode: 302012\n" +
                "09783597835",26.9354564, 75.7289776,"09783597835" ));
        distance.add(new Distance("Spicles Warehouse and sort centre","Plot 138, Jhotwara Industrial Area, Jaipur,\n" +
                //"near Ahinsa Circle,C-Scheme, Jaipur\n" +
                "Pincode: 302012\n" +
                //"+91-0141-4032221\n" +
                "8003099217",26.949713, 75.7466984,"8003099217"));
        distance.add(new Distance("Spicles Seller Services","Mansarovar Plaza, 403 4th floor, Mansarovar, Jaipur\n" +
                "Pincode: 302020    \n" +
                //"+91-0141-4002221\n" +
                "0141 239 7322",26.9024084, 75.7177009, "0141 239 7322"));

        // toolbar.setTitleTextColor(getResources().getColor(R.color.status_welcome));


        branch.setVisibility(View.VISIBLE);
        adapter=new LocateAdapter(c);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(adapter);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M)
        {
            if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
            {


                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},148);

            }
            else
            {
                checknearby();
            }

        }
        else
        {
            checknearby();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==148)
        {if(ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
            checknearby();
        }
    }

    public void checknearby()
    {
        getLocation();
        adapter=new LocateAdapter(c);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(adapter);

    }


    public void getAll(Location l)
    {float maxdis=3000000;int pos=-1;
        ArrayList<Distance> mycontact=new ArrayList<>();
        Location branch=new Location("");
        for(int i=0;i<distance.size();i++)
        {
            branch.setLatitude(distance.get(i).lat);

            branch.setLongitude(distance.get(i).lon);

            if(maxdis>=(l.distanceTo(branch)/1000))
            {
                maxdis=(l.distanceTo(branch)/1000);

                pos=i;
            }

        }

        mycontact.add(distance.get(pos));
        distance.set(pos,distance.get(0));
        distance.set(0,mycontact.get(0));
        updateui();
    }
    public void buildGpsMessage()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("Goto Settings Page To Enable GPS",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();

    }
    public void getLocation()
    {

        final LocationManager locationManager=(LocationManager) getSystemService( Context.LOCATION_SERVICE );
        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            buildGpsMessage();
        }

        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {


            fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
            LocationRequest locationRequest= LocationRequest.create();
            locationRequest.setFastestInterval(2000);
            locationRequest.setInterval(5000);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationRequest);

            SettingsClient client = LocationServices.getSettingsClient(this);
            Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());
            task.addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
                @Override
                public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                    if(ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
                        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if(location!=null)
                                {
                                    Log.e("location ",location.getLatitude()+"   "+location.getLongitude());
                                    getAll(location);



                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Something Went Wrong",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                }

            });

            Task<LocationSettingsResponse> locationSettingsResponseTask = task.addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if (e instanceof ResolvableApiException) {
                        // Location settings are not satisfied, but this can be fixed
                        // by showing the user a dialog.
                        try {

                            // Show the dialog by calling startResolutionForResult(),
                            //
                        } catch (Exception sendEx) {
                            // Ignore the error.
                        }
                    }
                }
            });
        }
    }

    public void updateui()
    {
        adapter=new LocateAdapter(c);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(adapter);
    }
}




