package com.sarah.wisata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.sarah.wisata.adapter.SliderAdapter;
import com.sarah.wisata.databinding.ActivityDetailWisataBinding;
import com.sarah.wisata.model.DataWisataModel;
import com.sarah.wisata.model.FotoModel;

import java.util.ArrayList;

public class DetailWisataActivity extends AppCompatActivity {

    ActivityDetailWisataBinding binding;
    DataWisataModel dataWisataModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_detail_wisata);
        binding.getLifecycleOwner();

        Gson gson = new Gson();
        dataWisataModel = gson.fromJson(getIntent().getStringExtra("model"),DataWisataModel.class);

        // we are creating array list for storing our image urls.
        ArrayList<FotoModel> fotoModels = new ArrayList<>();

// passing this array list inside our adapter class.
        SliderAdapter adapter = new SliderAdapter(DetailWisataActivity.this,dataWisataModel.getFoto());

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
        binding.btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.txtkategori.setText(dataWisataModel.getKategori());
        binding.txtdeskripsi.setText(dataWisataModel.getDeskripsi());

        if (dataWisataModel.getHarga()!=null){
            binding.txtharga.setVisibility(View.VISIBLE);
            binding.txtharga.setText("Rp. "+dataWisataModel.getHarga());
        }
        if (dataWisataModel.getLatitude()!=null){
            binding.btnarah.setVisibility(View.VISIBLE);
            binding.btnarah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Gson gson = new Gson();
                    String noteJson =  gson.toJson(dataWisataModel);
                    Intent intent = new Intent(DetailWisataActivity.this, MapsActivity.class);
                    intent.putExtra("model", noteJson );
                    startActivity(intent);

                }
            });
        }

    }
}