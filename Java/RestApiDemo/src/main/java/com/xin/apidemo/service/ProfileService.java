package com.xin.apidemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xin.apidemo.database.DataBaseClass;
import com.xin.apidemo.model.Profile;

public class ProfileService {
      
	private Map<String, Profile> ps = DataBaseClass.getProfile();
	
	// these hard coded records cannot be deleted with delete method
	public ProfileService(){
		ps.put("p1", new Profile(1L, "p1", "Anna", "Taylor"));
		ps.put("p2", new Profile(2L, "p2", "Bob", "Taylor"));
	}
	
	public List<Profile> getAllProfiles(){
		return new ArrayList<Profile>(ps.values());
	}
	
	public Profile getProfile(String profilename){
		return ps.get(profilename);
	}
	
	public Profile addProfile(Profile prof){
		prof.setId(ps.size() + 1);
		ps.put(prof.getProfilename(), prof);
		return prof;
	}
	
	public Profile updateProfile(Profile prof){
		if(prof.getProfilename().isEmpty()){
			return null;
		}
		else{
			ps.put(prof.getProfilename(), prof);
			return prof;
		}
	}
	
	public Profile deleteProfile(String profilename){
		return ps.remove(profilename);
	}
	
}
