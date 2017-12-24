package com.walidhelaoui.gomycode.Controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.walidhelaoui.gomycode.Entity.Data;
import com.walidhelaoui.gomycode.Entity.Food;
import com.walidhelaoui.gomycode.R;

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
    }

    public DataAdapter(@NonNull Context context, @NonNull List<Data> datas) {
        super(context, 0, datas);
        this.ctx = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Data data = getItem(position);
        FoodAdapter.ViewHolder viewHolder;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(ctx);
            convertView = inflater.inflate(R.layout.row_food,parent,false);
            viewHolder = new FoodAdapter.ViewHolder();
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.NameDataTxt);
            viewHolder.txtCity = (TextView) convertView.findViewById(R.id.CityTxt);
            viewHolder.txtPrice = (TextView) convertView.findViewById(R.id.PriceTxt);
            viewHolder.txtPlace = (TextView) convertView.findViewById(R.id.PlaceTxt);
            viewHolder.txtType = (TextView) convertView.findViewById(R.id.typeTxt);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (FoodAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.txtName.setText(data.getName());
        viewHolder.txtCity.setText(data.getCity());
        viewHolder.txtPrice.setText(String.valueOf(data.getPrice()));
        viewHolder.txtPlace.setText(data.getPlace());
        viewHolder.txtType.setText(data.getType());
        return  convertView;
    }
}
