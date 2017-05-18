package getMarkUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



public class CreateImageJson {
	
	static {
		try{		
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		}catch (Exception e) {
		System.out.println(e);
		}
	}
	
	public static final ThreadLocal<Gson> gsons =new ThreadLocal<Gson>();

	public static Result getResult(String json) {
		try{
			Result u = new Result();
			Gson gson=gsons.get();
			if(gson==null){
				gson=new Gson();
				gsons.set(gson);
			}
			u = gson.fromJson(json, Result.class);
			return u;
		}catch(Exception e){
			System.out.println("getResult"+"  "+e);
			return null;
		}
	}
	
	public static Image_Scene getImage_Scene(String json) {
		try{
			Image_Scene u = new Image_Scene();
			Gson gson=gsons.get();
			if(gson==null){
				gson=new Gson();
				gsons.set(gson);
			}
			u = gson.fromJson(json, Image_Scene.class);
			return u;
		}catch(Exception e){
			System.out.println("getImage_Scene"+"  "+e);
			return null;
		}
	}
	
	public static Attribute getAttribute(String json) {
		try{
			Attribute u = new Attribute();
			Gson gson=gsons.get();
			if(gson==null){
				gson=new Gson();
				gsons.set(gson);
			}
			u = gson.fromJson(json, Attribute.class);
			return u;
		}catch(Exception e){
			System.out.println("getAttribute"+"  "+e);
			return null;
		}
	}
	
	public static Tag getTag(String json) {
		try{
			Tag u = new Tag();
			Gson gson=gsons.get();
			if(gson==null){
				gson=new Gson();
				gsons.set(gson);
			}
			u = gson.fromJson(json, Tag.class);
			return u;
		}catch(Exception e){
			System.out.println("getTag"+"  "+e);
			return null;
		}
		
	}
	
	public static Value getValue(String json) {
		try{
			Value u = new Value();
			Gson gson=gsons.get();
			if(gson==null){
				gson=new Gson();
				gsons.set(gson);
			}
			u = gson.fromJson(json, Value.class);
			return u;
		}catch(Exception e){
			System.out.println("getTag"+"  "+e);
			return null;
		}
	}
	
	//Content
	public static Content getContent(String json) {
		try{
			Content u = new Content(); 
			Gson gson=gsons.get();
			if(gson==null){ 
				gson=new Gson();
				gsons.set(gson);
			}
			u = gson.fromJson(json, Content.class); 
			return u;
		}catch(Exception e){
			System.out.println("getContent"+"  "+e);
			return null;
		}
	}
	
}