package com.mpapps.myapplication.Activities;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mpapps.myapplication.R;
import com.mpapps.myapplication.ViewModels.MainActivityVM;

public class MainActivity extends AppCompatActivity implements LightRecyclerviewAdapter.ItemOnClickListener
{
    MainActivityVM viewModel;
    RecyclerView recyclerView;
    private SwipeRefreshLayout swipeContainer;
    private LightRecyclerviewAdapter adapter;
    public static final String SHARED_PREF_IP = "SHARED_PREF_IP";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();

        if(!sharedPreferences.contains(SHARED_PREF_IP))
            sharedPrefEditor.putString(SHARED_PREF_IP, "192.168.178.1").apply();

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
            viewModel.refreshLights();

            Handler mHandler = new Handler();
            mHandler.postDelayed(()->
                    swipeContainer.setRefreshing(false), 5000);
        });
        LayoutInflater inflater = getLayoutInflater();
        final View customView = inflater.inflate(R.layout.ip_dialog_layout, null);
        AlertDialog dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.myDialog))
                .setTitle("Change IP Address")
                .setIcon(R.drawable.network_icon)
                .setView(customView)
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    EditText editText = customView.findViewById(R.id.ip_dialog_editText);
                    sharedPrefEditor.putString(SHARED_PREF_IP, editText.getText().toString()).apply();
                    dialogInterface.dismiss();
                })
                .setNegativeButton("Cancel", ((dialogInterface, i) -> {
                    dialogInterface.cancel();
                }))
                .create();

        FloatingActionButton fab = findViewById(R.id.mainactivity_fab);
        fab.setOnClickListener((view -> {
           dialog.show();
            TextView textView = customView.findViewById(R.id.ip_dialog_textView);
            String text = "Current IP-Address: " + sharedPreferences.getString(SHARED_PREF_IP, "Not Available");
            if(textView != null)
                textView.setText(text);
        }));
    }

    @Override
    public void onItemClick()
    {
        //todo show fragment
    }
}
