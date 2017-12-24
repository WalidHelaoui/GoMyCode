package com.walidhelaoui.gomycode;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.walidhelaoui.gomycode.Controller.FoodAdapter;
import com.walidhelaoui.gomycode.utils.FoodDataSource;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Handler mHandler;
    private final Runnable m_Runnable = new Runnable()
    {
        public void run()

        {
            MainActivity.this.mHandler.postDelayed(m_Runnable, 3000);

            if (!FoodDataSource.foods.isEmpty()){
                FoodAdapter adapter = new FoodAdapter(MainActivity.this, FoodDataSource.foods);
                listView.setAdapter(adapter);
                mHandler.removeCallbacksAndMessages(null);
            }
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.mHandler = new Handler();
        this.mHandler.postDelayed(m_Runnable,3000);

        listView = (ListView) findViewById(R.id.listFood);
        FoodDataSource.setFood(this);
        FoodAdapter adapter = new FoodAdapter(this, FoodDataSource.foods);
        listView.setAdapter(adapter);
        startActivity(new Intent(MainActivity.this,SearchActivity.class));
    }
}
