package com.main.sunhacks.handler;

import java.util.Timer;
import java.util.TimerTask;

public class UIDHandler {
	
	private static String BEACON_UID1 = "";
	private static String BEACON_UID2 = "";

	private static int UID_GENERATION_ATTEMPT_COUNT = 10;
	
	static {
		TimerTask timerTask = new UIDHandler().new MyTimerTask();
        //running timer task as daemon thread
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 15*1000);
	}

	private static void setBeaconUID() throws Exception {

		int uidGenerationAttemptCount = 0;
		String uid = "";
		while(uidGenerationAttemptCount++ < UID_GENERATION_ATTEMPT_COUNT) {
			uid = UIDGenerator.generateUID();
		}
		if(uid.isEmpty()) {
			throw new Exception("Error while generating uid");
		}
		BEACON_UID2 = BEACON_UID1;
		BEACON_UID1 = uid;
	}
	
	public static String getBeaconUID() throws Exception {
		return BEACON_UID1;
	}

	public static boolean verifyBeaconUID(String uid) {
		boolean verified = (BEACON_UID1.equals(uid)) ? true : false;
		if(!verified) {
			verified = (BEACON_UID2.equals(uid)) ? true : false;
			if(!verified) {
				System.out.println("Failed verification: Stored uid value: " + BEACON_UID1 + ", " + BEACON_UID2 + " input uid: " + uid);
			}
		}
		return verified;
	}
	
	
	class MyTimerTask extends TimerTask {

	    @Override
	    public void run() {
	    	try {
				UIDHandler.setBeaconUID();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	}

	
}
