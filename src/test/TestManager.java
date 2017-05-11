package test;

import org.junit.Test;

import com.google.gson.Gson;

import jsonUtil.CreateJson;
import model.Manager;

public class TestManager {

	@Test
	public void testmanager(){
		
		Manager m=new Manager();
		/*m.setmIdCard("12212");
		m.setmName("scjs");
		m.setmEmail("sdsd");
		String s=CreateJson.getManagerJson(m);
		System.out.println(s);*/
		String s="{\"mIdCard\":\"12212\",\"mName\":\"scjs\",\"mEmail\":\"sdsd\",\"mAge\":0}";
		String sd="{\"mTel\":\"13244235890\",\"mHeadPort\":\"scs\",\"mWorkNum\":\"wang15201\",\"mSex\":\"Ů\",\"mAge\":\"21\",\"mEmail\":\"206666@qq.com\",\"mName\":\"zhong\",\"mIdCard\":\"2252\",\"state\":\"register\",\"mPassword\":\"666666\"}";
		String sdsds="{\"state\":\"register\",\"mName\":\"aaa\",\"mPassword\":\"maomao1314\",\"mTel\":\"13244234908\",\"mSex\":\"Ů\",\"checkId\":\"gb10086\",\"mIdCard\":\"500225\"}";
		m=CreateJson.getManager(sdsds);
		System.out.println("managerName:"+m.getmName());
	}
}
