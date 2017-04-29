package action;



import java.util.List;

import org.hibernate.Session;

import hibernateutil.SessionAnnotation;

public class PushPicture {//推送图片
	
	public static String getUserHM(String UserId){
		
		Session session=SessionAnnotation.getSession();
		session.beginTransaction();
		StringBuffer jsonPicture=new StringBuffer();
		jsonPicture.append("");
		String sql="select PID from Mark where USERID ='"+UserId+"'";
		List list=session.createQuery(sql).list();
		if(list.size()==0||list.size()<=5) {  //新用戶，不符合针对性推送，随机推送
			//SELECT * FROM T_USER  ORDER BY  RAND() LIMIT 10  随机查找10条记录
			String sql2="select PID,PAddress from Picture  ORDER BY RAND()　LIMIT 20"; //从数据库随机获取20条图片信息
			list=session.createQuery(sql2).list();
			System.out.println(list.toString());
			
			session.getTransaction().commit();
			SessionAnnotation.closeSession();
		}else{ //针对用户情况推送
			
			System.out.println("满足条件");
			
		}
		
		
		return jsonPicture.toString();
	}
	

}
