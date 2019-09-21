package com.main.sunhacks.action;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.main.sunhacks.handler.UIDHandler;
import com.opensymphony.xwork2.Action;

public class BeaconHelp {
	
	private Logger LOGGER = Logger.getLogger(BeaconHelp.class.getName());

	private String uid;
	
	
	public String getBeaconUID() {
		try {
			uid = UIDHandler.getBeaconUID();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Failed to create Beacon UID" , e);
		}
		return Action.SUCCESS;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

}
