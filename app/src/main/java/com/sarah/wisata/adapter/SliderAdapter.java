package com.sarah.wisata.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sarah.wisata.R;
import com.sarah.wisata.Utils.Constant;
import com.sarah.wisata.model.FotoModel;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends
        SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder> {

    private List<FotoModel> mFotoModels;

    public SliderAdapter(Context context, List<FotoModel> fotoarrayList) {
        this.mFotoModels = fotoarrayList;
    }



    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, null);
        return new SliderAdapterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, final int position) {

        final FotoModel sliderItem = mFotoModels.get(position);

        // Glide is use to load image
        // from url in your imageview.
        Picasso.get().load(new Constant().storage+sliderItem.getFoto()).fit().centerCrop().into(viewHolder.imageViewBackground);
//        Glide.with(viewHolder.itemView)
//                .load(sliderItem.getFoto())
//                .fitCenter()
//                .into(viewHolder.imageViewBackground);
    }

    @Override
    public int getCount() {
        return mFotoModels.size();
    }

    static class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {
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