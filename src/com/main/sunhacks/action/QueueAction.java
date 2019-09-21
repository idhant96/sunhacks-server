package com.main.sunhacks.action;

import java.net.URLEncoder;

import com.main.sunhacks.handler.QueueHandler;
import com.opensymphony.xwork2.Action;

public class QueueAction {
	
	private String beaconUID;
	private int busID;
	private String buses;
	private String response;

	
	public String fetchBuses() {
		try {
			System.out.println("Fetching busses");
			buses = URLEncoder.encode(QueueHandler.fetchBuses().toString(), "UTF-8");
			System.out.println("buses " + buses);
		} catch(Exception e) {
			buses = "Standard";
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String addUserToQueue() {
		try {
			response = QueueHandler.addUserToQueue(beaconUID, busID).toString();
		} catch (Exception e) {
			response = "Failed to add user to Queue";
		}
		return Action.SUCCESS;
	}


	public String getBuses() {
		return buses;
	}
	public void setBuses(String buses) {
		this.buses = buses;
	}

	public int getBusID() {
		return busID;
	}

	public void setBusID(int busID) {
		this.busID = busID;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getBeaconUID() {
		return beaconUID;
	}

	public void setBeaconUID(String beaconUID) {
		this.beaconUID = beaconUID;
	}

}
