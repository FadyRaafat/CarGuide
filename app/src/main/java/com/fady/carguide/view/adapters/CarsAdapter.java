package com.fady.carguide.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fady.carguide.R;
import com.fady.carguide.datamodel.models.Car;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.CarsViewHolder> {

    private List<Car> carsList;
    private Context context;

    public CarsAdapter(List<Car> carsList) {
        this.carsList = carsList;

    }

    @Override
    public CarsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_car, parent, false);
        context = parent.getContext();
        return new CarsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CarsViewHolder holder, int position) {
        Car car = carsList.get(position);
        Picasso.get()
                .load(car.getImageUrl())
                .centerCrop()
                .fit()
                .into(holder.carImage);
        if (car.getIsUsed()) {
            holder.carStatus.setText("used");
        } else {
            holder.carStatus.setText("new");
        }
        holder.carBrand.setText(car.getBrand());
        holder.carConstructionYear.setText(car.getConstractionYear());


    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return carsList.size();
    }

    static class CarsViewHolder extends RecyclerView.ViewHolder {
        ImageView carImage;
        TextView carBrand, carStatus, carConstructionYear;

        CarsViewHolder(View view) {
            super(view);
            carImage = view.findViewById(R.id.carImage_IV);
            carBrand = view.findViewById(R.id.carBrand_TV);
            carStatus = view.findViewById(R.id.carStatus_TV);
            carConstructionYear = view.findViewById(R.id.carConstructionYear_TV);
        }

    }


}