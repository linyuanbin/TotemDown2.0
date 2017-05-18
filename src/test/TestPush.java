package test;

import org.junit.Test;

import action.PushPicture;

public class TestPush {
	
	@Test
	public void testPush(){
		//Thu Apr 27 20:28:09 CST 201731ZDD
		String str=PushPicture.getPush("Thu Apr 27 20:28:09 CST 201731ZDD");
		System.out.println("history:"+str);
		
		
	}
	
	
}
