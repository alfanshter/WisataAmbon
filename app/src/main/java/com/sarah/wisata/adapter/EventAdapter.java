package com.sarah.wisata.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.sarah.wisata.DaftarlMenuActivity;
import com.sarah.wisata.DetailEventActivity;
import com.sarah.wisata.DetailWisataActivity;
import com.sarah.wisata.R;
import com.sarah.wisata.Utils.Constant;
import com.sarah.wisata.model.DataWisataModel;
import com.sarah.wisata.model.EventModel;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EventAdapter extends
        SliderViewAdapter<EventAdapter.SliderAdapterViewHolder> {

    private List<EventModel> mEventModels;
    private EventAdapter.Dialog dialog;
    public EventAdapter(Context context, List<EventModel> fotoarrayList) {
        this.mEventModels = fotoarrayList;
    }

    public interface Dialog {
        void onClick(int position, EventModel dataList);
    }

    public void setDialog(EventAdapter.Dialog dialog) {
        this.dialog = dialog;
    }


    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, null);
        return new SliderAdapterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, final int position) {

        final EventModel sliderItem = mEventModels.get(position);

        // Glide is use to load image
        // from url in your imageview.
        Picasso.get().load(new Constant().storage+sliderItem.getFoto()).fit().centerCrop().into(viewHolder.imageViewBackground);
//        Glide.with(viewHolder.itemView)
//                .load(sliderItem.getFoto())
//                .fitCenter()
//                .into(viewHolder.imageViewBackground);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog != null) {
                    dialog.onClick(position ,sliderItem);
                }
            }
        });
    }

    @Override
    public int getCount() {
        return mEventModels.size();
    }

    static class SliderAdapterViewHolder extends ViewHolder {
        // Adapter class for initializing 
        // the views of our slider view.
        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.myimage);
            this.itemView = itemView;
        }
    }

}