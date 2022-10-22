package com.sarah.wisata.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataWisataResponse{

	@SerializedName("data")
	private List<DataWisataModel> data;

	@SerializedName("message")
	private String message;

	public List<DataWisataModel> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}
}