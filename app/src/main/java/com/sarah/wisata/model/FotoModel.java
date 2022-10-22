package com.sarah.wisata.model;

import com.google.gson.annotations.SerializedName;

public class FotoModel {

	@SerializedName("foto")
	private String foto;

	@SerializedName("id_wisata")
	private int idWisata;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	public String getFoto(){
		return foto;
	}

	public int getIdWisata(){
		return idWisata;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}
}