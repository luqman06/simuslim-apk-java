package com.example.sinergimuslim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sinergimuslim.api.BaseApiService;
import com.example.sinergimuslim.api.UntilsApi;
import com.example.sinergimuslim.pojo.login_si.LoginSi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    ImageButton imageButton;
    EditText et_username, et_password;
    Context mContext;
    BaseApiService mApiInterface;
    private ProgressDialog loadingBar;
    Config config;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loadingBar = new ProgressDialog(this);
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }

        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        int screenWidth = getResources().getDisplayMetrics().widthPixels;

        if (screenWidth >= 720){
            ImageView imageView = findViewById(R.id.imageViewLogin);


        }else {


        }

        mApiInterface = UntilsApi.getApiService();
        initComponents();

    }

    private void initComponents() {
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        imageButton = findViewById(R.id.buttonLogin);

        mContext = this;


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et_username.getText().toString())){
                    et_username.setError("Mohon di isi");
                }else if (TextUtils.isEmpty(et_password.getText().toString())){
                    et_password.setError("Mohon di isi");
                }else {
                    loadingBar.setTitle("Login proses");
                    loadingBar.setMessage("Tunggu sebentar.........");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.show();

                    requestLogin();
                }

            }
        });
    }

    private void requestLogin() {
            String user = et_username.getText().toString();
            String pass = et_password.getText().toString();

            if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pass)) {


                mApiInterface.loginRequest(et_username.getText().toString().trim(), et_password.getText().toString().trim())
                        .

                                enqueue(new Callback<LoginSi>() {


                            @Override
                            public void onResponse(Call<LoginSi> call, Response<LoginSi> response) {

                                if (response.isSuccessful()) {

                                    String username = response.body().getData().getUsername();
                                    String role = response.body().getData().getRole();
                                    String idAnggota = response.body().getData().getIdAnggota();
                                    String lokasi = response.body().getData().getKota();
//                                    String pekerjaan = response.body().getData().get
                                    String namaAnggota = response.body().getData().getNamaAnggota();
                                    String no_wa = response.body().getData().getNoWa();
                                    if (role.equals("admin")){
                                        namaAnggota = "Admin";
                                    }


                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("username", username);
                                    intent.putExtra("role", role);
                                    intent.putExtra("idAnggota", idAnggota);
                                    //intent.putExtra("updated_at", update_at);
                                    startActivity(intent);
                                    loadingBar.dismiss();
                                 new Config(getApplicationContext()).setId(idAnggota);
                                 new Config(getApplicationContext()).setUsername(username);
                                 new Config(getApplicationContext()).setLokasi(lokasi);
                                 new Config(getApplicationContext()).setNamaAnggota(namaAnggota);
                                 new Config(getApplicationContext()).setNoWa(no_wa);
                                 new Config(getApplicationContext()).setPekerjaan(role);
                                 new Config(getApplicationContext()).setRole(role);

                                    finish();
//                                    Toast.makeText(LoginActivity.this, "Sukses", Toast.LENGTH_SHORT).show();

                                } else {
                                    //loading.dismiss();
                                    loadingBar.dismiss();
                                    Toast.makeText(mContext, "Ulangi lagi", Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<LoginSi> call, Throwable t) {
                                loadingBar.dismiss();
                                Log.e("debuug", "onFailure: ERROR >" + t.toString());
                                Toast.makeText(mContext, t.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }
    }

