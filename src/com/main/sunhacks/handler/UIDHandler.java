package com.main.sunhacks.handler;

public class UIDHandler {
	
	private static String BEACON_UID;

	private static int UID_GENERATION_ATTEMPT_COUNT = 10;
	
	public static String getBeaconUID() throws Exception {

		int uidGenerationAttemptCount = 0;
		String uid = "";
		while(uidGenerationAttemptCount++ < UID_GENERATION_ATTEMPT_COUNT) {
			uid = UIDGenerator.generateUID();
		}
		if(uid.isEmpty()) {
			throw new Exception("Error while generating uid");
		}
		BEACON_UID = uid;
		return BEACON_UID;
	}

	public static boolean verifyBeaconUID(String uid) {
		return BEACON_UID == uid ? true : false;
	}
}
