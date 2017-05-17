package com.xin.apidemo.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.xin.apidemo.model.Profile;
import com.xin.apidemo.service.ProfileService;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {
   
	ProfileService pserv = new ProfileService();
	
	@GET
	public List<Profile> getAllProfile(){
		return pserv.getAllProfiles();
	}
	
	@GET
	@Path("/{profilename}")
	public Profile getProfile(@PathParam("profilename")String pfn){
		return pserv.getProfile(pfn);
	}
	
	@POST
	public Profile addProfile(Profile prof){
		return pserv.addProfile(prof);
	}
	
	@PUT
	@Path("/{profilename}")
	public Profile updateProfile(@PathParam("profilename")String pfn,Profile prof){
		prof.setProfilename(pfn);
		return pserv.updateProfile(prof);
	}
	
	@DELETE
	@Path("/{profilename}")
	public Profile deteleProfile(@PathParam("profilename")String pfn){
		return pserv.deleteProfile(pfn);
	}
	
	
}
