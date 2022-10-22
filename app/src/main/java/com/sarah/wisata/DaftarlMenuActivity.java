package com.sarah.wisata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sarah.wisata.adapter.DataWisataAdapter;
import com.sarah.wisata.databinding.ActivityDetailMenuBinding;
import com.sarah.wisata.model.DataWisataModel;
import com.sarah.wisata.model.DataWisataResponse;
import com.sarah.wisata.webservice.ApiClient;
import com.sarah.wisata.webservice.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarlMenuActivity extends AppCompatActivity {

    ActivityDetailMenuBinding binding;
    String kategori;
    private DataWisataAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_detail_menu);
        binding.getLifecycleOwner();
        binding.shimmer.startShimmer();

        Intent intent = getIntent();
        kategori = intent.getStringExtra("kategori");

        binding.txtkategori.setText("Daftar" + " "+kategori);
        binding.btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        datawisata(kategori);

    }

    private void datawisata(String kategori) {
        //GET API
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<DataWisataResponse> call = apiInterface.data_wisata_kategori(kategori);
        call.enqueue(new Callback<DataWisataResponse>() {
            @Override
            public void onResponse(Call<DataWisataResponse> call, Response<DataWisataResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        binding.rvwisata.setVisibility(View.VISIBLE);
                        binding.shimmer.setVisibility(View.GONE);
                        binding.shimmer.stopShimmer();
                        List<DataWisataModel> DataWisataModel = response.body().getData();
                        generateDataList(DataWisataModel);
                    } else {
                        Toast.makeText(DaftarlMenuActivity.this,"Response salah", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.d("sarah", "onResponse:  " +e);
                    datawisata(kategori);
                }

            }

            @Override
            public void onFailure(Call<DataWisataResponse> call, Throwable t) {
                Log.d("sarah", "onResponse: " + t.getMessage());
            }
        });
        //END GET API
    }

    private void generateDataList(List<DataWisataModel> dataWisataModels){
        try {
            adapter = new DataWisataAdapter(dataWisataModels);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

            binding.rvwisata.setLayoutManager(layoutManager);

            binding.rvwisata.setAdapter(adapter);

        }catch (Exception e){
            return;
        }

        adapter.setDialog(new DataWisataAdapter.Dialog() {
            @Override
            public void onClick(int position, DataWisataModel dataList) {
                Gson gson = new Gson();
                String noteJson =  gson.toJson(dataList);
                Intent intent = new Intent(DaftarlMenuActivity.this, DetailWisataActivity.class);
                intent.putExtra("model", noteJson );
                startActivity(intent);

            }



        });



    }


}