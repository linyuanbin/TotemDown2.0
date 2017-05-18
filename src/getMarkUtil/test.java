package getMarkUtil;
import java.net.MalformedURLException;
import java.util.Scanner;

import org.junit.Test;
import org.omg.Messaging.SyncScopeHelper;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class test {
	  String ur="http://apicn.imageplusplus.com/analyze?"
			+ "api_key=5955858fb07cbd8a9df3824f973aad8a&"
			+ "api_secret=2063b10602539e44bf31b91ae7f04998&"
			+ "url=https://timgsa.baidu.com/timg?image&quality=80&size=b9999_1"
			+ "0000&sec=1495196414&di=7235cd8"
			+ "83befa162b31689047af18395&imgtype=jpg&er=1&src=http%3"
			+ "A%2F%2Fd.hiphotos.baidu.com%2Flv"
			+ "pics%2Fh%3D800%2Fsign%3D9931b79f1dd5ad6eb5f969eab1ca39a3%2"
			+ "Fa8773912b31bb051b3333f73307adab44aede052.jpg";
	  String s="http://a1.qpic.cn/psb?/V10SiQSV1CXjlp/MihG4xwDMaYZP.s8Atag0d.HVMYJyGPMmuVclwIUEk4!/b/dBcBAAAAAAAA&bo=jQOAAgAAAAARBzw!&rf=viewer_4";
	  String ss="http://pic23.nipic.com/20120817/10748806_181900551145_2.jpg";
	  String sss="http://pic11.nipic.com/20101214/213291_155243023914_2.jpg";
	@Test
	public void test() 
	{
		try {
			String json=AIMark.getFirstMark(sss);   
			/*String json=Mark.getFirstMark(ss);    
			System.out.println("初始标签："+json);  
			Result result=CreateJson.getResult(json); 
			String resu=result.getResult().toString();
			System.out.println("图片信息 ："+resu); 
			Image_Scene IS=CreateJson.getImage_Scene(resu);
			System.out.println("image1: "+IS.getImage().toString());
			
			String attribute=IS.getImage().get(0).toString();
			
		
			Attribute AT=CreateJson.getAttribute(attribute);
			String at=AT.getAttribute().toString();
			System.out.println("Attribute:"+at);
			
			Tag tag=CreateJson.getTag(at);
			System.out.println("tag0"+tag.getTag().get(0));
			
			Value v=CreateJson.getValue(tag.getTag().get(0).toString());
			System.out.println("value  "+v.getValue());
			*/
			/*
			String json=Mark.getFirstMark(ss);    
			System.out.println("初始标签："+json);  
			Result result=CreateJson.getResult(json); 
			String resu=result.getResult().toString(); 
			System.out.println("resu图片信息 ："+resu); 
			Image_Scene IS=CreateJson.getImage_Scene(resu);
			System.out.println("image1: "+IS.getImage().toString());
			
			//String attribute=IS.getImage().get(0).toString();
			
			String attribute=IS.getText().get(0).toString();
			System.out.println("image2: "+attribute);
			
			Attribute AT=CreateJson.getAttribute(attribute);
			String at=AT.getContent().toString();
//			Content c=AT.getContent();
			System.out.println("Content对象:"+at); 
			
//			Tag tag=CreateJson.getTag(at);
//			System.out.println("tag0"+tag.getTag().get(0));
			
			Content content=CreateJson.getContent(at); 
			System.out.println("content:" +content.getValue());
			
//			Value v=CreateJson.getValue(tag.getTag().get(0).toString());
//			System.out.println("value  "+v.getValue());
*/			
			String s=CreateImageJson.getValue(CreateImageJson.getTag(CreateImageJson.getAttribute(CreateImageJson
					.getImage_Scene(CreateImageJson.getResult(json).getResult().toString()).getImage()
					.get(0).toString()).getAttribute().toString()).getTag().get(0).toString()).getValue();
			System.out.println("Image  "+s); 
			
			String s2=CreateImageJson.getValue(CreateImageJson.getTag(CreateImageJson.getAttribute(CreateImageJson
					.getImage_Scene(CreateImageJson.getResult(json).getResult().toString()).getScene()
					.get(0).toString()).getAttribute().toString()).getTag().get(0).toString()).getValue();
			System.out.println("Scene  "+s2);
			
		
			
		/*	String s3=CreateImageJson.getValue(CreateImageJson.getContent(CreateImageJson.getAttribute(CreateImageJson
					.getImage_Scene(CreateImageJson.getResult(json).getResult().toString()).getText()
					.get(0).toString()).getAttribute().toString()).getValue().toString()).getValue();
			System.out.println("Pedestrian"+s3);
			
			String s4=CreateJson.getValue(CreateJson.getContent(CreateJson.getAttribute(CreateJson
					.getImage_Scene(CreateJson.getResult(json).getResult().toString()).getText()
					.get(0).toString()).getAttribute().toString()).getTag().get(0).toString()).getValue();
			System.out.println("text  "+s4);*/
//			System.out.println(o.hashCode());
//			String value=o.getAsJsonObject("result").getAsJsonArray("image").get(0).getAsJsonObject().getAsJsonObject("attribute").getAsJsonArray("tag").get(0).toString();//getAsJsonObject("attribute").getAsJsonObject("tag").getAsJsonObject("value").getAsString();
//			System.out.println("初始标签："); 
//			System.out.println("初始标签value："+value); 
		} catch (MalformedURLException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace();
			System.out.println("yicang");
		}
	}
	
	@Test
	public void testPc(){
		String image=PictureContent.getPImage(sss);
		
		Scanner scanner=new Scanner(image);
		//scanner.useDelimiter(":");
		while(scanner.hasNext()){
			System.out.println(scanner.nextLine());
		}
		
		
	}


}
