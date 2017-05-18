package getMarkUtil;
import java.net.MalformedURLException;
import java.util.ArrayList;

//获取初始标签类
public class PictureContent {
	//通过图片的url智能识别图片内容
	public static String getPImage(String url){
		String content="";
		
		try {
			String json=AIMark.getFirstMark(url);
			 String str1="";
			 str1=CreateImageJson.getValue(CreateImageJson.getTag(CreateImageJson.getAttribute(CreateImageJson
		 			.getImage_Scene(CreateImageJson.getResult(json).getResult().toString()).getImage()
					.get(0).toString()).getAttribute().toString()).getTag().get(0).toString()).getValue();
			 String str2="";
			 str2=CreateImageJson.getValue(CreateImageJson.getTag(CreateImageJson.getAttribute(CreateImageJson
						.getImage_Scene(CreateImageJson.getResult(json).getResult().toString()).getScene()
						.get(0).toString()).getAttribute().toString()).getTag().get(0).toString()).getValue();

			System.out.println("Image  "+str1+":"+str2);
			if(str1!=""&str2!=""){
				content=str1+"\n"+str2;
			}else if(str1.equals("")&str2!=""){
				content=str2;
			}else if(str1!=""&str2.equals("")){
				content=str1;
			}else{
			content="";	
			}
		} catch (MalformedURLException e) {  
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		} 
		return content;
	}
	
}
