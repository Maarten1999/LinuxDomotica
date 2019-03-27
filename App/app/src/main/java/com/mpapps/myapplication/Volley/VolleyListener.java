package com.mpapps.myapplication.Volley;

import com.mpapps.myapplication.Models.LightModel;

import java.util.List;

public interface VolleyListener
{
    void getLightsReceived(List<LightModel> lights);
    void getLightsError();
    void changeLightSuccess();
    void changeLightError();
}
