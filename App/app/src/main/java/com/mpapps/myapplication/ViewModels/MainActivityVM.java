package com.mpapps.myapplication.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Patterns;
import android.widget.Toast;

import com.mpapps.myapplication.Activities.MainActivity;
import com.mpapps.myapplication.Models.LightModel;
import com.mpapps.myapplication.Volley.VolleyListener;
import com.mpapps.myapplication.Volley.VolleyService;

import java.util.ArrayList;
import java.util.List;

public class MainActivityVM extends AndroidViewModel implements VolleyListener
{
    private MutableLiveData<List<LightModel>> lights;
    private VolleyService volleyService;
    //todo instellen van ip
    private static final String IP_ADDRESS = "192.168.178.1";
    SharedPreferences sharedPreferences;

    public MainActivityVM(@NonNull Application application)
    {
        super(application);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application);

        lights = new MutableLiveData<>();
        List<LightModel> lightModels = new ArrayList<>();
        lightModels.add(new LightModel(1, "1", "RED", false));
        lightModels.add(new LightModel(2, "2", "GREEN", false));
        lightModels.add(new LightModel(3, "3", "YELLOW", true));
        lightModels.add(new LightModel(4, "4", "RED", false));
        lights.postValue(lightModels);
        volleyService = VolleyService.getInstance(application, this);
    }

    //region Volley request methods
    public void refreshLights(){
        String restoredIP = sharedPreferences.getString(MainActivity.SHARED_PREF_IP, null);
        String url = "http:// + " + restoredIP + "/lights";
        if(restoredIP != null) {
            if(Patterns.IP_ADDRESS.matcher(restoredIP).matches())
                volleyService.getLightsRequest(url);
            else
                Toast.makeText(getApplication(), "Not a valid IP", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(getApplication(), "Unable to make request, ip address not available", Toast.LENGTH_SHORT).show();
    }

    public void changeLightState(int lightId, boolean state){
        String restoredIP = sharedPreferences.getString(MainActivity.SHARED_PREF_IP, null);
        String url = "http:// + " + restoredIP + "/lights/" + lightId + "/on=" + state;
        if(restoredIP != null){
            if(Patterns.IP_ADDRESS.matcher(restoredIP).matches())
                volleyService.changeLight(url);
            else
                Toast.makeText(getApplication(), "Not a valid IP", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(getApplication(), "Unable to make request, ip address not available", Toast.LENGTH_SHORT).show();
    }

    public void changeLightName(int lightId, String name){
        String url = "http:// + " + IP_ADDRESS + "/lights/" + lightId + "/name=" + name;
        volleyService.changeLight(url);
    }
    //endregion

    //region Volley Response methods
    @Override
    public void getLightsReceived(List<LightModel> lights)
    {
        this.lights.postValue(lights);
    }

    @Override
    public void getLightsError()
    {
        //TODO handle error
    }

    @Override
    public void changeLightSuccess()
    {
        //TODO handle error
    }

    @Override
    public void changeLightError()
    {
        //TODO handle error
    }
    //endregion


    public MutableLiveData<List<LightModel>> getLights()
    {
        return lights;
    }
}
