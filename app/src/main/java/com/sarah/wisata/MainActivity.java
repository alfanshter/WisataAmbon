package com.sarah.wisata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sarah.wisata.adapter.EventAdapter;
import com.sarah.wisata.databinding.ActivityMainBinding;
import com.sarah.wisata.model.DataWisataModel;
import com.sarah.wisata.model.EventResponse;
import com.sarah.wisata.model.EventModel;
import com.sarah.wisata.webservice.ApiClient;
import com.sarah.wisata.webservice.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.getLifecycleOwner();


        get_slider();

        binding.btnwisatabahari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DaftarlMenuActivity.class);
                intent.putExtra("kategori", "Wisata Bahari");
                startActivity(intent);

            }
        });

        binding.btnwisatasejarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DaftarlMenuActivity.class);
                intent.putExtra("kategori", "Wisata Sejarah");
                startActivity(intent);

            }
        });

        binding.btnwisataalam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DaftarlMenuActivity.class);
                intent.putExtra("kategori", "Wisata Alam");
                startActivity(intent);

            }
        });

        binding.btncafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DaftarlMenuActivity.class);
                intent.putExtra("kategori", "Cafe");
                startActivity(intent);

            }
        });

        binding.btnpenginapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DaftarlMenuActivity.class);
                intent.putExtra("kategori", "Penginapan");
                startActivity(intent);

            }
        });

        binding.btntransportasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DaftarlMenuActivity.class);
                intent.putExtra("kategori", "Transportasi");
                startActivity(intent);


            }
        });

    }

    void get_slider() {
        //GET API
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<EventResponse> call = apiInterface.data_event();
        call.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        EventAdapter adapter = new EventAdapter(MainActivity.this, response.body().getData());

                        binding.slider.setAutoCycleDirection(View.LAYOUT_DIRECTION_LTR);
                        binding.slider.setScrollTimeInSec(3);

                        // to set it scrollable automatically
                        // we use below method.
                        binding.slider.setAutoCycle(true);

                        // to start autocycle below method is used.
                        binding.slider.startAutoCycle();
                        // below method is used to
                        // setadapter to binding.slider.
                        binding.slider.setSliderAdapter(adapter);
                        adapter.setDialog(new EventAdapter.Dialog() {
                            @Override
                            public void onClick(int position, EventModel dataList) {
                                Gson gson = new Gson();
                                String noteJson =  gson.toJson(dataList);
                                Intent intent = new Intent(MainActivity.this, DetailEventActivity.class);
                                intent.putExtra("model", noteJson );
                                startActivity(intent);
                            }
                        });
                    } else {
                        Toast.makeText(MainActivity.this, "Response salah", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.d("sarah", "onResponse:  " + e);
                    get_slider();
                }

            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                Log.d("sarah", "onResponse: " + t.getMessage());
            }
        });
        //END GET API
    }
}