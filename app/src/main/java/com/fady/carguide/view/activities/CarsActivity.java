package com.fady.carguide.view.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.fady.carguide.R;
import com.fady.carguide.datamodel.models.Car;
import com.fady.carguide.view.adapters.CarsAdapter;
import com.fady.carguide.viewmodel.CarsViewModel;

import java.util.ArrayList;
import java.util.List;

public class CarsActivity extends AppCompatActivity {
    private CarsViewModel mViewModel;
    private CarsAdapter mAdapter;
    private RecyclerView carsRV;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeContainer;
    private List<Car> cars =  new ArrayList<>();
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    int pageNumber = 1;
    LinearLayoutManager mLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);
        mLayoutManager = new LinearLayoutManager(this);
        carsRV = findViewById(R.id.cars_RV);
        progressBar = findViewById(R.id.progress_circular);
        swipeContainer = findViewById(R.id.swipe_container);
        mViewModel = ViewModelProviders.of(this).get(CarsViewModel.class);
        loadCars(pageNumber);
        loadingView();
        swipeContainer.setOnRefreshListener(() -> loadCars(pageNumber));
        initEndlessScroll();

    }

    private void initEndlessScroll(){
        carsRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = carsRV.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount)
                        <= (firstVisibleItem + visibleThreshold)) {
                    loading = true;
                    pageNumber++;
                    loadCars(pageNumber);
                }
            }
        });

    }

    public void loadCars(Integer pageNumber) {
        mViewModel.getCarsList(pageNumber).observe(this, carsList -> {
            swipeContainer.setRefreshing(false);
            if (carsList.getData() != null) {
                    successView();
                    cars.addAll(carsList.getData());
                    mAdapter = new CarsAdapter(cars);
                    mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    carsRV.setLayoutManager(mLayoutManager);
                    carsRV.setAdapter(mAdapter);

                }
        });
    }

    private void loadingView() {
        progressBar.setVisibility(View.VISIBLE);
        carsRV.setVisibility(View.GONE);
    }


    private void successView() {
        progressBar.setVisibility(View.GONE);
        carsRV.setVisibility(View.VISIBLE);

    }

}
