package com.mpapps.myapplication.Activities;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mpapps.myapplication.R;
import com.mpapps.myapplication.ViewModels.MainActivityVM;

public class MainActivity extends AppCompatActivity implements LightRecyclerviewAdapter.ItemOnClickListener
{

    MainActivityVM viewModel;
    RecyclerView recyclerView;
    private SwipeRefreshLayout swipeContainer;
    private LightRecyclerviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MainActivityVM.class);

        viewModel.getLights().observe(this, lightModels -> {
            assert lightModels != null;

            adapter.notifyDataSetChanged();
            swipeContainer.setRefreshing(false);
        });

        recyclerView = findViewById(R.id.mainactivity_recyclerview);
        adapter = new LightRecyclerviewAdapter(getApplicationContext(), viewModel);
        adapter.setListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);


        swipeContainer = findViewById(R.id.mainactivity_swipecontainer);
        swipeContainer.setOnRefreshListener(() ->
        {
            viewModel.getLights();

            Handler mHandler = new Handler();
            mHandler.postDelayed(()->
                    swipeContainer.setRefreshing(false), 5000);
        });
    }

    @Override
    public void onItemClick()
    {
        //todo show fragment
    }
}
