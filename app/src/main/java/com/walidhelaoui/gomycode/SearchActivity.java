package com.walidhelaoui.gomycode;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    EditText editText;
    Button search;
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

        editText = findViewById(R.id.editText);
        search = findViewById(R.id.button2);
        listView = (ListView) findViewById(R.id.SearchListView);
        DataSource.setData(this);
        DataAdapter adapter = new DataAdapter(this, DataSource.datas);
        listView.setAdapter(adapter);
    }

    public void searchAction(View view) {
       Toast.makeText(this,editText.getText().toString(),Toast.LENGTH_SHORT).show();
       Float budget = Float.valueOf(editText.getText().toString().trim());
       List<Data> data = DataSource.datas;
       List<Data> search = new ArrayList<>();

        for (Data item : data){
           if ((item.getPrice())<=budget){
               search.add(item);
               Log.e("loop","aa");
           }
       }

       DataAdapter adapter = new DataAdapter(SearchActivity.this,search);
       listView.setAdapter(adapter);


    }
}
