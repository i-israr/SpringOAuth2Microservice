package com.hp.loginusingkeycloak.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hp.loginusingkeycloak.R;
import com.hp.loginusingkeycloak.kk.GetDataService;
import com.hp.loginusingkeycloak.kk.AccessToken;
import com.hp.loginusingkeycloak.kk.RetrofitClientInstance;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText etusername;
    private EditText etpassword;
    private Button btnLogin;
    AccessToken result = null;
    JSONObject obj= null;
    private String accessToken2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etusername = findViewById(R.id.username);
        etpassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.button);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAccessToken();
            }
        });
    }

    public void getAccessToken(){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        String username = etusername.getText().toString();
        String password = etpassword.getText().toString();
        System.out.println(username+"---"+password);
        Call<AccessToken> call = service.getAccessToken(Constants.CLIENT_ID,Constants.GRANT_TYPE,Constants.CLIENT_SECRET,Constants.SCOPE,username,password);


        call.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                System.out.println(response);

                if(response.isSuccessful()){
                    result = response.body();
                        if(result.getAccessToken()!=null) {
                            Constants.ACCESS_TOKEN = result.getAccessToken();
                            Log.d("Access token : ", Constants.ACCESS_TOKEN);
                            //reading access token
//                            String[] chunks = Constants.ACCESS_TOKEN.split("\\.");
//                            try {
//                                Log.d("JWT_DECODED", "Header: " + getJson(chunks[0]));
//                                Log.d("JWT_DECODED", "Body: " + getJson(chunks[1]));
//
//                            } catch (UnsupportedEncodingException | JSONException e) {
//                                e.printStackTrace();
//                            }

                        }else{
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

                                }
                            });
                        }

                    Constants.REFRESH_TOKEN = response.body().getRefreshToken();
                    Intent intent = new Intent(MainActivity.this, SuccessLogin.class);
                    intent.putExtra("response",result);
                    MainActivity.this.startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                System.out.println(call.request().toString());
                System.out.println("T value:" + t);
                Toast.makeText(MainActivity.this,"Error Fail",Toast.LENGTH_LONG).show();
            }
        });

    }
    private static JSONObject getJson(String strEncoded) throws UnsupportedEncodingException, JSONException {
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        String data = new String(decodedBytes, "UTF-8");
        JSONObject obj = new JSONObject(data);
       // Log.d("Username",obj.getJSONObject("realm_access").getString("roles"));
        return obj;
    }
}