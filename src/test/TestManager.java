package test;

import org.junit.Test;

import com.google.gson.Gson;

import action.ManagerAction;
import jsonUtil.CreateJson;
import model.Image;
import model.ImageList;
import model.Manager;

public class TestManager {

/*	@Test
	public void testmanager(){
		
		Manager m=new Manager();
		m.setmIdCard("12212");
		m.setmName("scjs");
		m.setmEmail("sdsd");
		String s=CreateJson.getManagerJson(m);
		System.out.println(s);
		String s="{\"mIdCard\":\"12212\",\"mName\":\"scjs\",\"mEmail\":\"sdsd\",\"mAge\":0}";
		String sd="{\"mTel\":\"13244235890\",\"mHeadPort\":\"scs\",\"mWorkNum\":\"wang15201\",\"mSex\":\"Ů\",\"mAge\":\"21\",\"mEmail\":\"206666@qq.com\",\"mName\":\"zhong\",\"mIdCard\":\"2252\",\"state\":\"register\",\"mPassword\":\"666666\"}";
		String sdsds="{\"state\":\"register\",\"mName\":\"aaa\",\"mPassword\":\"maomao1314\",\"mTel\":\"13244234908\",\"mSex\":\"Ů\",\"checkId\":\"gb10086\",\"mIdCard\":\"500225\"}";
		m=CreateJson.getManager(sdsds);
		System.out.println("managerName:"+m.getmName());
	}*/
	
	@Test
	public void testUpload(){
		String sss="{\"state\":\"upload\",\"files\":[{\"imagefile\":\"imagefilessss\"},{\"imagefile\":\"imagefiless22\"}]}";
		String ss="{\"state\":\"upload\",\"files\":\"sss\"}";
		String s="{\"state\":\"upload\",\"mEmail\":\"dsds\",\"mAge\":0}";
		Manager m=CreateJson.getManager(sss); 
		System.out.println(m.getState()); 
		Gson gson=new Gson();
		ImageList imagelist=gson.fromJson(sss,ImageList.class);
		System.out.println(imagelist.getList().size()+" "+imagelist.getList().get(0).toString());
		
		for(int i=0;i<imagelist.getList().size();i++){
			Image image=gson.fromJson(imagelist.getList().get(i).toString(), Image.class);
			System.out.println("iamgefile"+image.getImagefile());
		}
		
		boolean b=ManagerAction.SaveImage(imagelist); 
		System.out.println("zhuang  "+b); 
		
//		Manager m=new Manager(); 
//		m.setmEmail("dsds"); 
//		m.setmName("ssdssd"); 
//		System.out.println(CreateJson.getManagerJson(m));
		
		
	}
}
