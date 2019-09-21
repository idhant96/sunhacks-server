package com.main.sunhacks.pojo;

import org.json.JSONObject;

public class Bus {
	private Long id;
	private String name;
	private String fromDestination;
	private String toDestination;
	private int capacity;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public JSONObject getAsJSONObject() {
		JSONObject busJSONObj = new JSONObject();
		busJSONObj.put("id", id);
		busJSONObj.put("name", name);
		busJSONObj.put("from_destination", fromDestination);
		busJSONObj.put("toDestination", toDestination);
		return busJSONObj;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFromDestination() {
		return fromDestination;
	}
	public void setFromDestination(String fromDestination) {
		this.fromDestination = fromDestination;
	}
	public String getToDestination() {
		return toDestination;
	}
	public void setToDestination(String toDestination) {
		this.toDestination = toDestination;
	}

}
