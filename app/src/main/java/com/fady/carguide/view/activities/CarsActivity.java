package com.fady.carguide.view.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fady.carguide.R;
import com.fady.carguide.utilis.Constants;
import com.fady.carguide.view.adapters.CarsAdapter;
import com.fady.carguide.viewmodel.CarsViewModel;

public class CarsActivity extends AppCompatActivity {
    private CarsViewModel mViewModel;
    private CarsAdapter mAdapter;
    private RecyclerView carsRV;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);
        carsRV = findViewById(R.id.cars_RV);
        progressBar = findViewById(R.id.progress_circular);
        mViewModel = ViewModelProviders.of(this).get(CarsViewModel.class);
        loadCars();
    }

    public void loadCars() {
        mViewModel.getCarsList(1).observe(this, cars -> {
            if (cars != null) {
                    successView();
                    mAdapter = new CarsAdapter(cars.getData());
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    carsRV.setLayoutManager(mLayoutManager);
                    carsRV.setAdapter(mAdapter);

                }
        });
    }

    private void successView() {
        progressBar.setVisibility(View.GONE);
        carsRV.setVisibility(View.VISIBLE);
    }

}
