package com.mpapps.myapplication.Volley;

import com.mpapps.myapplication.LightModel;

import java.util.List;

interface VolleyListener
{
    void getLightsReceived(List<LightModel> lights);
    void getLightsError();
    void changeLightState(boolean state);
    void changeLightName(String name);
    void changeLightError();
}
