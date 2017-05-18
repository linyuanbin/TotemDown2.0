package test;

import java.util.Date;

import org.junit.Test;

import dao.MarkDao;
import dao.PictureDao;
import dao.UserDao;
import daoimplement.MarkImplement;
import daoimplement.PictureImplement;
import daoimplement.UserImplement;
import model.Mark;
import model.Picture;
import model.User;

public class testMark {
	UserDao ud=new UserImplement();
	PictureDao pd=new PictureImplement();
	MarkDao md=new MarkImplement();
	@Test
	public void test1(){
		
		User u=ud.showUser("Thu Apr 27 20:28:09 CST 201731ZDD");
		Picture p=pd.selectSinglePictureFID("Sat Apr 29 15:54:53 CST 20176OXXF");
		Mark m=new Mark();
		m.setUser(u);
		m.setPicture(p);
		//m.setTabId("Thu Apr 27 20:28:09 CST 201731ZDD"+"Sat Apr 29 15:54:53 CST 2017EDEkG");
		m.setTabId(u.getUserID()+p.getPID());
		m.setMarkName("²âÊÔ1");
		m.setMarkDate(new Date());
//		boolean b=md.insertIntoMark("Thu Apr 27 20:28:09 CST 201731ZDD", "Sat Apr 29 15:54:53 CST 2017EDEkG", "atm»ú");
		boolean b=md.insertMark(m); 
		//boolean b=md.updateMark(m);
		if(b){
			u.setUserIntegral(u.getUserIntegral()+1);
			ud.updateUser(u);
			System.out.println("±£´æ³É¹¦£¡");
		}else{
			System.out.println("±£´æÊ§°Ü£¡");
		}
		
		
	}
	
	@Test
	public void GetMark(){
		Mark m=md.getMark("Thu Apr 27 20:28:09 CST 201731ZDD"+"Sat Apr 29 15:54:53 CST 2017EDEkG");
		System.out.println(m.getMarkName());
		
	}
	
	@Test
	public void deleteMark(){
		boolean b=md.deleteOnMark("Thu Apr 27 20:28:09 CST 201731ZDD"+"Sat Apr 29 15:54:53 CST 2017EDEkG");
		if(b){
			System.out.println("É¾³ý³É¹¦");
		}else{
			System.out.println("É¾³ýÊ§°Ü");
		}
	}

}
