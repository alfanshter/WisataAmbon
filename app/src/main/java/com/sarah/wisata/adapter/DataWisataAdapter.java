package com.sarah.wisata.adapter;


import android.transition.Slide;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sarah.wisata.R;
import com.sarah.wisata.Utils.Constant;
import com.sarah.wisata.model.DataWisataModel;
import com.squareup.picasso.Picasso;

import java.util.List;


public class DataWisataAdapter extends RecyclerView.Adapter<DataWisataAdapter.FoodViewHolder> {


    private List<DataWisataModel> dataList;
    private Dialog dialog;


    public interface Dialog {
        void onClick(int position, DataWisataModel dataList);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }


    public DataWisataAdapter(List<DataWisataModel> dataList) {
        this.dataList = dataList;
    }

    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_wisata, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getNama());
        Picasso.get().load(new Constant().storage +dataList.get(position).getFoto().get(0).getFoto()).fit().centerCrop().into(holder.foto);
        holder.txtDeskripsi.setText(dataList.get(position).getDeskripsi());

        //jika penginapan
        if (dataList.get(position).getKategori().equals("Penginapan")){
            holder.txtharga.setVisibility(View.VISIBLE);
            holder.txtharga.setText("Rp. "+dataList.get(position).getHarga());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null) {
                    dialog.onClick(holder.getLayoutPosition(),dataList.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNama, txtDeskripsi,txtharga;
        private ImageView foto;

        public FoodViewHolder(View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.txtnama);
            txtDeskripsi = (TextView) itemView.findViewById(R.id.txtdeskripsi);
            txtharga = (TextView) itemView.findViewById(R.id.txtharga);
            foto = (ImageView) itemView.findViewById(R.id.imgfoto);



        }
    }
}