package com.hp.loginusingkeycloak.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hp.loginusingkeycloak.R;
import com.hp.loginusingkeycloak.kk.AccessToken;
import com.hp.loginusingkeycloak.kk.GetDataService;
import com.hp.loginusingkeycloak.kk.RetrofitClientInstance;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuccessLogin extends AppCompatActivity {
    Button btnLogout,getDataFromResource;
    AccessToken value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_login);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = (AccessToken) extras.get("response");
            Log.d("value",value.getAccessToken());
            //The key argument here must match that used in the other activity
        }
        btnLogout = findViewById(R.id.logout);
        getDataFromResource = findViewById(R.id.getDataResource);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        getDataFromResource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }

        });
    }

    private void getData() {
        GetDataService service = RetrofitClientInstance.getRestRetrofitInstance().create(GetDataService.class);
        Call call = service.getAllData("israr","Bearer " + value.getAccessToken());
        //Call call = service.getAllData("israr","Bearer " + "TokenTokenToken");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Toast.makeText(SuccessLogin.this,"Response received",Toast.LENGTH_LONG ).show();

                    System.out.println("in success");
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println(t);
            }
        });

    }
    public void logout(){
        Log.d("InLogout","LogOut");
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseBody> call = service.logout(Constants.CLIENT_ID,Constants.REFRESH_TOKEN,Constants.CLIENT_SECRET);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){

                    System.out.println("in success");
                    Intent intent = new Intent(SuccessLogin.this, MainActivity.class);
                    SuccessLogin.this.startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {


                Toast.makeText(SuccessLogin.this,t.toString(),Toast.LENGTH_LONG ).show();

            }
        });
    }


}