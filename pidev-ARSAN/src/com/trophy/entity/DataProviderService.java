/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.entity;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author rihab bns
 */
public class DataProviderService {
    public void getData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.gamerpower.com/api/giveaways/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
     giveawysApi giveawy=retrofit.create(giveawysApi.class);
     giveawy.getGlobalData() 
             .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        System.out.println(response.isSuccessful());
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                       System.out.println(call.isExecuted());
                    } 
                });
    }
}
