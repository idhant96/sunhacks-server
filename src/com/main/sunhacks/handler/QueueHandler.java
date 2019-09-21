package com.main.sunhacks.handler;

import org.json.JSONObject;

import com.main.sunhacks.db.DataSource;

public class QueueHandler {

	public static JSONObject fetchBuses() throws Exception {
		return DataSource.getBusDetailsAsJSON();
	}

	public static JSONObject addUserToQueue(String beaconUID, long busID) throws Exception {
		JSONObject responseJSONObj = new JSONObject();
		boolean uidVerified = UIDHandler.verifyBeaconUID(beaconUID);
		if(!uidVerified) {
			responseJSONObj.put("status", "failed");
		}
		// get queue for user. (userID, busID)
		//get qr for user
		return null;
	}
}
