package com.kignorchan.milesremaining;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomeFragment.OnFragmentInteractionListener, CarListFragment.OnFragmentInteractionListener, SettingFragment.OnFragmentInteractionListener, AboutFragment.OnFragmentInteractionListener {

    //private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        try{
            String json = DataController.readJsonFile(Information.FILENAME, MainActivity.this);
            if(json.equals("null")){
                DataController.storeJsonToLocal("[]",Information.FILENAME,MainActivity.this);
            }
            Log.i("JSONHOME", json);

            if(!Information.carLeases.isEmpty()){
                Information.carLeases.clear();
            }

            JSONArray jsonarray = new JSONArray(json);
            for(int i=0; i<jsonarray.length(); i++){
                Information.carLeases.add(DataController.parseJsonToCarleaseObject(jsonarray.getJSONObject(i).toString()));
            }



        }catch (Exception e){
            e.printStackTrace();
        }


        Fragment fragment=null;
        Class fragmentClass = null;
        fragmentClass = HomeFragment.class;
        try
        {
            fragment = (Fragment)fragmentClass.newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.commit();


        getIntent().setAction("Already created");
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_car) {
            Toast.makeText(getApplicationContext(), "add car", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, AddNewLeaseActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        Class fragmentClass = null;

        if (id == R.id.nav_home) {
            //Toast.makeText(getApplicationContext(), "home", Toast.LENGTH_SHORT).show();
            fragmentClass = HomeFragment.class;
        } else if (id == R.id.nav_car_list) {
            //Toast.makeText(getApplicationContext(), "car list", Toast.LENGTH_SHORT).show();
            fragmentClass = CarListFragment.class;
        } else if (id == R.id.nav_setting) {
            //Toast.makeText(getApplicationContext(), "setting", Toast.LENGTH_SHORT).show();
            fragmentClass = SettingFragment.class;
        } else if (id == R.id.nav_about) {
            //Toast.makeText(getApplicationContext(), "about", Toast.LENGTH_SHORT).show();
            fragmentClass = AboutFragment.class;
        }

        try
        {
            fragment = (Fragment)fragmentClass.newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onContentChanged() {

        super.onContentChanged();
    }

    //    @Override
//    protected void onResume() {
//        String action = getIntent().getAction();
//        // Prevent endless loop by adding a unique action, don't restart if action is present
//        if(action == null || !action.equals("Already created")) {
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        }
//        // Remove the unique action so the next time onResume is called it will restart
//        else
//            getIntent().setAction(null);
//
//        super.onResume();
//
//    }
//    @Override
//    protected void onResume(){
//        Log.i("MAINONRESUME","hsdgf");
//        super.onResume();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//    }
}
