package com.project.Spicles.startup;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.Spicles.AccountActivity;
import com.project.Spicles.ContactActivity;
import com.project.Spicles.LocateUsActivity;
import com.project.Spicles.MyBroadcast;
import com.project.Spicles.OrderActivity;
import com.project.Spicles.R;
import com.project.Spicles.SignUpActivity;
import com.project.Spicles.Terms_Conditions;
import com.project.Spicles.fragments.ImageFragment;
import com.project.Spicles.miscellaneous.EmptyActivity;
import com.project.Spicles.notification.NotificationCountSetClass;
import com.project.Spicles.options.CartActivity;
import com.project.Spicles.options.SearchActivity;
import com.project.Spicles.options.WishlistActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static int notificationCountCart = 0;
    static ViewPager viewPager;
    static TabLayout tabLayout;
    MyBroadcast broadcast;
    TextView profile_name;
    ImageView profile_image;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    //MenuItem logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_locate);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        SharedPreferences sharedPref = this.getSharedPreferences("FromsignIn", Context.MODE_PRIVATE);
        String name = sharedPref.getString("keyname","default");
        String image=sharedPref.getString("keyimage",null);
        profile_image=navigationView.getHeaderView(0).findViewById(R.id.profileimg);
        profile_name=navigationView.getHeaderView(0).findViewById(R.id.name);
        profile_name.setText(name);
        profile_image.setImageResource(R.drawable.spicles);
        // userPhoto.setImageURI(Uri.parse(image));
        Picasso.get().load(image).into(profile_image);
        /*profile_image=navigationView.getHeaderView(0).findViewById(R.id.profileimg);
        profile_name=navigationView.getHeaderView(0).findViewById(R.id.name);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Personal Data").child(firebaseUser.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                profile_name.setText(Objects.requireNonNull(snapshot.child("UserName").getValue()).toString());
                String link = Objects.requireNonNull(snapshot.child("UserPhotoUri").getValue()).toString();
                Picasso.get().load(link).into(profile_image);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

         */

         viewPager = (ViewPager) findViewById(R.id.viewpager);
         tabLayout = (TabLayout) findViewById(R.id.tabs);

        if (viewPager != null) {
            setupViewPager(viewPager);
            tabLayout.setupWithViewPager(viewPager);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        invalidateOptionsMenu();
        broadcast=new MyBroadcast();
        IntentFilter filter=new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(broadcast,filter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // Get the notifications MenuItem and
        MenuItem item = menu.findItem(R.id.action_cart);
        //logout = menu.findItem(R.id.action_logout);
        NotificationCountSetClass.setAddToCart(MainActivity.this, item,notificationCountCart);
        // onCreateOptionsMenu(Menu) will be called again.
        invalidateOptionsMenu();
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_search) {
            startActivity(new Intent(MainActivity.this, SearchActivity.class));
            return true;
        }else if (id == R.id.action_cart) {

           /* NotificationCountSetClass.setAddToCart(MainActivity.this, item, notificationCount);
            invalidateOptionsMenu();*/
            startActivity(new Intent(MainActivity.this, CartActivity.class));

           /* notificationCount=0;//clear notification count
            invalidateOptionsMenu();*/
            return true;
        }else {
            startActivity(new Intent(MainActivity.this, EmptyActivity.class));

        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        ImageFragment fragment = new ImageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", 1);
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, "Offers");
        fragment = new ImageFragment();
        bundle = new Bundle();
        bundle.putInt("type", 2);
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, "Snacks");
        fragment = new ImageFragment();
        bundle = new Bundle();
        bundle.putInt("type", 3);
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, "Pickles");
        fragment = new ImageFragment();
        bundle = new Bundle();
        bundle.putInt("type", 4);
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, "Spices");
       /* fragment = new ImageFragment();
        bundle = new Bundle();
        bundle.putInt("type", 5);
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, getString(R.string.item_5));

        */
        fragment = new ImageFragment();
        bundle = new Bundle();
        bundle.putInt("type", 5);
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, getString(R.string.item_6));
        viewPager.setAdapter(adapter);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_item1) {
            viewPager.setCurrentItem(0);
        } else if (id == R.id.nav_item2) {
            viewPager.setCurrentItem(1);
        } else if (id == R.id.nav_item3) {
            viewPager.setCurrentItem(2);
        } else if (id == R.id.nav_item4) {
            viewPager.setCurrentItem(3);
        } else if (id == R.id.nav_item5) {
            viewPager.setCurrentItem(4);
        }
        //else if (id == R.id.nav_item5) {
           // viewPager.setCurrentItem(5);
        //}
        else if (id == R.id.my_wishlist) {
            startActivity(new Intent(MainActivity.this, WishlistActivity.class));
        }else if (id == R.id.my_cart) {
            startActivity(new Intent(MainActivity.this, CartActivity.class));
        }else if (id == R.id.my_account) {
            startActivity(new Intent(MainActivity.this, AccountActivity.class));
        }else if (id == R.id.contact_us) {
            startActivity(new Intent(MainActivity.this, ContactActivity.class));
        }else if (id == R.id.terms_conditions) {
            startActivity(new Intent(MainActivity.this, Terms_Conditions.class));
        }else if (id == R.id.locate_us) {
            startActivity(new Intent(MainActivity.this, LocateUsActivity.class));

        //}else if (id == R.id.logout) {

            //    startActivity(new Intent(MainActivity.this, SignUpActivity.class));
        } else {
            startActivity(new Intent(MainActivity.this, EmptyActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

   /** @Override
    protected void onResume() {
        super.onResume();
        broadcast=new MyBroadcast();
        IntentFilter filter=new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(broadcast,filter);

    }
    **/

    @Override
    protected void onStop() {
        super.onStop();

        //unregisterReceiver(broadCast);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcast);
    }
}


//E91E63 colorprimary
//C2185B colorprimarydark
//651FFF colorAccent