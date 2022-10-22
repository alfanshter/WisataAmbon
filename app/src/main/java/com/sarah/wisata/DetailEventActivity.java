package com.sarah.wisata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.sarah.wisata.Utils.Constant;
import com.sarah.wisata.databinding.ActivityDetailEventBinding;
import com.sarah.wisata.model.DataWisataModel;
import com.sarah.wisata.model.EventModel;
import com.squareup.picasso.Picasso;

public class DetailEventActivity extends AppCompatActivity {

    ActivityDetailEventBinding binding;
    EventModel eventModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_detail_event);
        binding.getLifecycleOwner();

        binding.btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Gson gson = new Gson();
        eventModel = gson.fromJson(getIntent().getStringExtra("model"),EventModel.class);

        binding.txtkategori.setText(eventModel.getNama());
        Picasso.get().load(new Constant().storage+eventModel.getFoto()).fit().centerCrop().into(binding.imgfoto);
        binding.txtdeskripsi.setText(eventModel.getDeskripsi());


    }
}