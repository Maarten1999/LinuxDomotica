package com.mpapps.myapplication.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.mpapps.myapplication.Models.LightModel;
import com.mpapps.myapplication.Volley.VolleyListener;
import com.mpapps.myapplication.Volley.VolleyService;

import java.util.ArrayList;
import java.util.List;

public class MainActivityVM extends AndroidViewModel implements VolleyListener
{
    private MutableLiveData<List<LightModel>> lights;
    private VolleyService volleyService;

    public MainActivityVM(@NonNull Application application)
    {
        super(application);

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
    public void refreshLights(String ip){
        String url = "http:// + " + ip + "/lights";
        volleyService.getLightsRequest(url);
    }

    public void changeLightState(String ip, int lightId, boolean state){
        String url = "http:// + " + ip + "/lights/" + lightId + "/on=" + state;
        volleyService.changeLight(url);
    }

    public void changeLightName(String ip, int lightId, String name){
        String url = "http:// + " + ip + "/lights/" + lightId + "/name=" + name;
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
