package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.junit.Test;

import dao.PictureDao;
import dao.UserDao;
import daoimplement.PictureImplement;
import daoimplement.UserImplement;
import hibernateutil.SessionAnnotation;
import hibernateutil.SimpleDateFormatUtil;
import jsonUtil.CreateJson;
import model.Picture;
import model.User;
import util.RandomString;

public class TestUser {
	
	UserDao d=new UserImplement(); 
		
	@Test
	public void testuser() throws ParseException{
		
		
		 //ģ��ע��
	/*	User u=new User();
		u.setUserName("����");
		u.setUserTel("13244237897");
		u.setUserBirthday(SimpleDateFormatUtil.getSimpleDateFormat("1996-10-1"));
		u.setUserPassword("666666");
		u.setUserHeadPortr("D:picture\\jiu.jpg");
		u.setUserEmail("888888@qq.com"); 
		if(d.register(u)){
			System.out.println("YES");
		}else{
			System.out.println("NO");
		}*/
		
		//System.out.println("��¼���:"+d.login("13244237695", "666666")); //ģ�µ�¼
		
		/*
		User u=new User();  //���²���
		//u.setUserID("Thu Apr 06 20:25:03 CST 2017GsPbI");
		u.setUserName("�����"); 
		u.setUserHeadPortr("���ľͺ�");
		u.setUserIntegral(5);
		u.setUserMajor("Android����ʦ");
		u.setUserPassword("666666"); 
		//SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		//String day="2015-5-1";
		//u.setUserBirthday(sf.parse(day)); 
		u.setUserBirthday(SimpleDateFormatUtil.getSimpleDateFormat("1996-10-1"));
		u.setUserTel("13244236656");  
		d.updateUser(u); */
		
		
		
		/*User u=d.selectUser("13244237695");
		System.out.println(u.getUserName());
		
		u=d.selectUser("����");
		System.out.println(u.toString());
		
		u=d.selectUser("666666@qq.com");
		System.out.println(u.toString());
		*/
		
		
	/*	Session session=SessionAnnotation.getSession(); //������ʾ�û�����
		String sql="select UserID from User";
		List list=session.createQuery(sql).list();
		for(int i=0;i<list.size();i++){
			User u=d.showUser(list.get(i).toString());
			System.out.println(u.toString());
		}*/
		
		
		
		//���Թ�����ϵ
		User u1=new User();
		u1.setUserName("������"); 
		u1.setUserHeadPortr("������������");
		u1.setUserIntegral(6);
		u1.setUserMajor("JAVA����ʦ");
		u1.setUserPassword("666666"); 
		u1.setUserBirthday(SimpleDateFormatUtil.getSimpleDateFormat("1998-9-1"));
		u1.setUserTel("13244236811");  
		User u2=new User();
		u2.setUserName("�Ĺ���"); 
		u2.setUserHeadPortr("�����");
		u2.setUserIntegral(6);
		u2.setUserMajor("web����ʦ");
		u2.setUserPassword("666666"); 
		u2.setUserBirthday(SimpleDateFormatUtil.getSimpleDateFormat("1998-9-2"));
		u2.setUserTel("13244236877");  
		
		Picture p1=new Picture();
		p1.setPID(new Date()+RandomString.getRandomString(5));
	    p1.setPName("");	
	    p1.setPAddress("D:\\picture\\two.jpg");
	    
		Picture p2=new Picture();
		p2.setPID(new Date()+RandomString.getRandomString(5));
	    p2.setPName("��");	
	    p2.setPAddress("D:\\picture\\wwpp.jpg");
		
	   /* u1.getPictures().add(p1);
	    u1.getPictures().add(p2);
	    u2.getPictures().add(p1);*/
	    d.register(u1); //ע��
	    d.register(u2);//ע��
	    
	    
		
//		Picture p2=new Picture();
//		p2.setPID(new Date()+RandomString.getRandomString(5));
//	    p2.setPName("shijia");	
//	    p2.setPAddress("D:\\picture\\wwpp.jpg"); 
//		User u2=d.showUser("Mon Apr 10 21:27:04 CST 2017F596m"); 
//		if(u2.getPictures()==null){
//			System.out.println("yes");
//		}else{
//			System.out.println("no");
//		}
//		System.out.println(u2.getUserName());
//		System.out.println(u2.getPictures());
//		u2.getPictures().add(p2);
//	    System.out.println(u2.getUserEmail()+"�ʼ�");
//	    d.updateUser(u2);
//	    System.out.println(u2.getUserMajor()+"����");
	    
//	    
		
	
		
		//System.out.println(d.deleteUser("Mon Apr 10 21:27:04 CST 2017EkBVm")); //����ɾ��user
		
		/*User u=d.showUser("Mon Apr 10 21:27:04 CST 2017EkBVm");  //���Ը����û�
		u.setUserNickName("��ֶ�ħ�Ǹ�");
		d.updateUser(u);*/
		
		
/*		try{
			 Session session=SessionAnnotation.getSession();
			    String sql="select RUSERID,RPID from Mark";
//				String sql="select UserID from User";
			    session.beginTransaction();
			    System.out.println("aaaaaaaaaaaa");
			    List list=session.createQuery(sql).list();
			    System.out.println("aaaaaaaaaaaa");
			    System.out.println(list);
			    session.getTransaction().commit();
			    SessionAnnotation.closeSession();
		}catch(Exception e){
			System.out.println(e);
			
		}*/
	    
	}

	@Test
	public void testUserPictures(){
		System.out.println("picture1");     //�������ѯ  
	    //String uid=d.getUserID("13244236888");  
	    User u3=d.showUser("Thu Apr 13 19:24:52 CST 2017b3H2V");   
		//User u3=d.selectUser("������"); 
	    
	    System.out.println(u3.getUserName());   
	    Set<Picture> pictures=u3.getPictures(); 
	    System.out.println("pictures�ǿ�"+pictures); 
		System.out.println("picture2"+" "+pictures.size());  
	    for (Picture picture : pictures) { 
			System.out.println(picture+"pic"); 
			System.out.println(picture.getPAddress()+" "+picture.getPName()); 
		}
	    
//		Session session=SessionAnnotation.getSession();
//		User user=(User) session.get(User.class, "Thu Apr 13 19:24:52 CST 2017b3H2V");
//		Set<Picture> pictures=user.getPictures();  
//		 for (Picture picture : pictures) { 
//				System.out.println(picture+"pic"); 
//				System.out.println(picture.getPAddress()+" "+picture.getPName()); 
//			}
	}
	

	 
	 @Test 
	 public void TestMarkTable(){
		 Session session=SessionAnnotation.getSession();
		 String sql="insert into mark values ("+"'Thu Apr 20 19:00:32 CST 2017Qti3c',"+"'Thu Apr 20 19:05:32 CST 2017m71lZ')";
		 session.beginTransaction();
		 session.createQuery(sql);
		 session.getTransaction().commit();
		 SessionAnnotation.closeSession();
	 }
	 
	 @Test
	 public void TestCreateJson(){
		 User u=d.showUser("Thu Apr 27 20:28:09 CST 201731ZDD");
		 User u2=new User();
		 u2.setUserName("scns");
		 u2.setUserID("scsic");
		 String s=CreateJson.getUserJson(u);  
		 System.out.println(s);
		 
	 }

}
