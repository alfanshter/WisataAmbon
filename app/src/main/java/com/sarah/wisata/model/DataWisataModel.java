package com.sarah.wisata.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataWisataModel {

	@SerializedName("nama")
	private String nama;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("foto")
	private List<FotoModel> foto;

	@SerializedName("latitude")
	private String latitude;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("kategori")
	private String kategori;

	@SerializedName("harga")
	private String harga;

	@SerializedName("id")
	private int id;

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("info")
	private String info;

	@SerializedName("longitude")
	private String longitude;

	public String getNama(){
		return nama;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public List<FotoModel> getFoto(){
		return foto;
	}

	public String getLatitude(){
		return latitude;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public String getKategori(){
		return kategori;
	}

	public String getHarga(){
		return harga;
	}

	public int getId(){
		return id;
	}

	public String getDeskripsi(){
		return deskripsi;
	}

	public String getInfo(){
		return info;
	}

	public String getLongitude(){
		return longitude;
	}
}