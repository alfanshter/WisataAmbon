package com.sarah.wisata.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class EventResponse{

	@SerializedName("data")
	private List<EventModel> data;

	@SerializedName("message")
	private String message;

	public List<EventModel> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}
}