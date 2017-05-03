package jsonUtil;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import model.Mark;
import model.Picture;
import model.User;

public class CreateJson {
	
	static {
		try{		
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		}catch (Exception e) {
		System.out.println(e);
		}
	}
	
	public static final ThreadLocal<Gson> gsons =new ThreadLocal<Gson>();

	public static User getUser(String json) {
		User u = new User();
		Gson gson=gsons.get();
		if(gson==null){
			gson=new Gson();
			gsons.set(gson);
		}
		u = gson.fromJson(json, User.class);
		return u;
	}
	
	public static String getUserJson(User u){
		
		try{
		//String str="";
		Gson gson=gsons.get();
		if(gson==null){
			gson=new Gson();
			gsons.set(gson);
		}  
		
		String str=gson.toJson(u);
		System.out.println("createJson"+str);
		return str;
		}catch(Exception e){
			System.out.println("getUserJson“Ï≥£");
			System.out.println(e);
			return "";
		}
	}
	
	public static Picture getPicture(String json){
		Picture p=new Picture();
		Gson gson=gsons.get();
		if(gson==null){
			gson=new Gson();
			
			gsons.set(gson);
		}
		
		p=gson.fromJson(json,Picture.class);
		return p;
	}
	
	public static String getPictureJson(Picture p){
		try{
		String pictureJson="";
		Gson gson=gsons.get();
		if(gson==null){
			gson=new Gson();
		}
		pictureJson=gson.toJson(p);
		return pictureJson;
		}catch(Exception e){
			System.out.println(e);
			System.out.println("getPictureJson“Ï≥££°");
			return "";
		}
	}
	
	public static String getmarkJson(Mark m){
		
		try{
			Gson gson=gsons.get();
			if(gson==null){
				gson=new Gson();
				gsons.set(gson);
			}
			String markJson=gson.toJson(m);
			System.out.println(markJson);
			return markJson;
		}catch(Exception e){
			System.out.println(e);
			System.out.println("getmarkJson“Ï≥£");
			return "";
		}
	}

}
