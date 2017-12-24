package com.walidhelaoui.gomycode.Controller;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.walidhelaoui.gomycode.Entity.Data;
import com.walidhelaoui.gomycode.Entity.Food;
import com.walidhelaoui.gomycode.R;
import com.walidhelaoui.gomycode.ShowActivity;

import java.util.List;

/**
 * Created by walid on 24/12/2017.
 */

public class DataAdapter extends ArrayAdapter<Data> {

    Context ctx;

    public static class ViewHolder{
        TextView txtName;
        TextView txtPrice;
        TextView txtPlace;
        TextView txtCity;
        TextView txtType;
        ImageView imageView;
    }

    public DataAdapter(@NonNull Context context, @NonNull List<Data> datas) {
        super(context, 0, datas);
        this.ctx = context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Data data = getItem(position);
        DataAdapter.ViewHolder viewHolder;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(ctx);
            convertView = inflater.inflate(R.layout.row_food,parent,false);
            viewHolder = new DataAdapter.ViewHolder();
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.NameDataTxt);
            viewHolder.txtCity = (TextView) convertView.findViewById(R.id.CityTxt);
            viewHolder.txtPrice = (TextView) convertView.findViewById(R.id.PriceTxt);
            viewHolder.txtPlace = (TextView) convertView.findViewById(R.id.PlaceTxt);
            viewHolder.txtType = (TextView) convertView.findViewById(R.id.typeTxt);
            viewHolder.imageView = convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (DataAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.txtName.setText(data.getName());
        viewHolder.txtCity.setText(data.getCity());
        viewHolder.txtPrice.setText(String.valueOf(data.getPrice()));
        viewHolder.txtPlace.setText(data.getPlace());
        viewHolder.txtType.setText(data.getType());
        if (data.getType().equals("Event")){
            viewHolder.imageView.setImageResource(R.drawable.event);
        }
        if (data.getType().equals("Food")){
            viewHolder.imageView.setImageResource(R.drawable.food);
        }
        if (data.getType().equals("Hotel")){
            viewHolder.imageView.setImageResource(R.drawable.hotel);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(String.valueOf(position),data.getName());
                Intent intent = new Intent(getContext(), ShowActivity.class);
                intent.putExtra("name",data.getName());
                intent.putExtra("price",String.valueOf(data.getPrice()));
                intent.putExtra("place",data.getPlace());
                getContext().startActivity(intent);
            }
        });
        return  convertView;
    }
}
