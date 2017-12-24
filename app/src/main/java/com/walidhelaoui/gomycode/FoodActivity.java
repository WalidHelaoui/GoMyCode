package com.walidhelaoui.gomycode;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.walidhelaoui.gomycode.Controller.FoodAdapter;
import com.walidhelaoui.gomycode.utils.FoodDataSource;

public class FoodActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ListView listView;
    private Handler mHandler;
    private final Runnable m_Runnable = new Runnable()
    {
        public void run()

        {
            FoodActivity.this.mHandler.postDelayed(m_Runnable, 3000);

            if (!FoodDataSource.foods.isEmpty()){
                FoodAdapter adapter = new FoodAdapter(FoodActivity.this, FoodDataSource.foods);
                listView.setAdapter(adapter);
                mHandler.removeCallbacksAndMessages(null);
            }
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.mHandler = new Handler();
        this.mHandler.postDelayed(m_Runnable,3000);

        listView = (ListView) findViewById(R.id.listFood);
        FoodDataSource.setFood(this);
        FoodAdapter adapter = new FoodAdapter(this, FoodDataSource.foods);
        listView.setAdapter(adapter);
        //startActivity(new Intent(FoodActivity.this,SearchActivity.class));
        initNavigationDrawer();
    }

    public void initNavigationDrawer() {

        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id){
                    case R.id.home:
                        Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_SHORT).show();
                        //replaceFragment(new SmokeFragment(FoodActivity.this));
                        startActivity(new Intent(FoodActivity.this,SearchActivity.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.food:
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.logout:
                        startActivity(new Intent(FoodActivity.this,LoginActivity.class));

                }
                return true;
            }
        });
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
}
