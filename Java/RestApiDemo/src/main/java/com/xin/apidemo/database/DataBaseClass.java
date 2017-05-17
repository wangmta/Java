package com.xin.apidemo.database;

import java.util.HashMap;
import java.util.Map;

import com.xin.apidemo.model.Message;
import com.xin.apidemo.model.Profile;

public class DataBaseClass {
	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<String, Profile> profile = new HashMap<>();

	public static Map<Long, Message> getMessages() {
		return messages;
	}

	public static Map<String, Profile> getProfile() {
		return profile;
	}

}
