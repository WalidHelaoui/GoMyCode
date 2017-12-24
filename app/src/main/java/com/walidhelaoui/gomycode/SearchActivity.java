package com.walidhelaoui.gomycode;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.walidhelaoui.gomycode.Controller.DataAdapter;
import com.walidhelaoui.gomycode.Controller.FoodAdapter;
import com.walidhelaoui.gomycode.Entity.Data;
import com.walidhelaoui.gomycode.Entity.Food;
import com.walidhelaoui.gomycode.utils.DataSource;
import com.walidhelaoui.gomycode.utils.FoodDataSource;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private ListView listView;
    private Handler mHandler;
    CheckBox all,food,hotels,events;
    EditText editText;
    Button search;
    List<Data> searchList = new ArrayList<>();
    private final Runnable m_Runnable = new Runnable()
    {
        public void run()

        {
            SearchActivity.this.mHandler.postDelayed(m_Runnable, 3000);

            if (!DataSource.datas.isEmpty()){
                DataAdapter adapter = new DataAdapter(SearchActivity.this, DataSource.datas);
                listView.setAdapter(adapter);
                mHandler.removeCallbacksAndMessages(null);
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        this.mHandler = new Handler();
        this.mHandler.postDelayed(m_Runnable,3000);

        all = findViewById(R.id.AllcheckBox);
        food = findViewById(R.id.FoodcheckBox);
        hotels = findViewById(R.id.HotelscheckBox);
        events = findViewById(R.id.EventscheckBox);

        editText = findViewById(R.id.editText);
        search = findViewById(R.id.button2);
        listView = (ListView) findViewById(R.id.SearchListView);
        DataSource.setData(this);
        DataAdapter adapter = new DataAdapter(this, DataSource.datas);
        listView.setAdapter(adapter);

    }

    public void searchAction(View view) {
       Toast.makeText(this,editText.getText().toString(),Toast.LENGTH_SHORT).show();
       Float budget = 0f;
       try {
           budget = Float.valueOf(editText.getText().toString().trim());
       }catch (Exception ex){
          Toast.makeText(SearchActivity.this,"Enter a Budget",Toast.LENGTH_SHORT).show();
       }
       List<Data> data = DataSource.datas;

       if(all.isChecked()){
           searchList.clear();
           for (Data item : data){
               if ((item.getPrice())<=budget){
                   searchList.add(item);
                   Log.e("loop","aa");
               }
           }
       }
       if(food.isChecked()){
           for (Data item : data){
               if (((item.getPrice())<=budget)&&(item.getType().equals("Food"))){
                   searchList.add(item);
                   Log.e("loop","aa");
               }
           }
       }
       if(hotels.isChecked()){
           for (Data item : data){
               if (((item.getPrice())<=budget)&&(item.getType().equals("Hotels"))){
                   searchList.add(item);
                   Log.e("loop","aa");
               }
           }
       }
       if(events.isChecked()){
           for (Data item : data){
               if (((item.getPrice())<=budget)&&(item.getType().equals("Event"))){
                   searchList.add(item);
                   Log.e("loop","aa");
               }
           }
       }

       if((!all.isChecked())&&(!food.isChecked())&&(!hotels.isChecked())&&(!events.isChecked())){
           searchList.clear();
       }

       DataAdapter adapter = new DataAdapter(SearchActivity.this,searchList);
       listView.setAdapter(adapter);


    }
    public void clearAction(View view) {
        searchList.clear();
        DataAdapter adapter = new DataAdapter(SearchActivity.this,searchList);
        listView.setAdapter(adapter);
        editText.setText("");
    }
}
