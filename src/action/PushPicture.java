package action;



import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import hibernateutil.SessionAnnotation;
import jsonUtil.CreateJson;
import model.Mark;
import model.Picture;
public class PushPicture {//推送图片
	
	public static String getPush(String UserId){ //登录一次推送十张
		
		Session session=SessionAnnotation.getSession();
		session.beginTransaction();
		StringBuffer jsonPicture=new StringBuffer();
		//jsonPicture.append("");
		//String sql="select PID from Mark where USERID ='"+UserId+"'";
		String hql="from Mark where UserId='"+UserId.trim()+"'";//+UserId+"'";
		Query query=session.createQuery(hql);
		//query.setParameter(0,UserId);
		//query.setString(0,UserId);
		List list=query.list();
		System.out.println("历史记录数目："+list.size()+"  "+list.toString());
		//List list=session.createQuery(sql).list();
		if(list.size()<=5) {  //新用戶，不符合针对性推送，随机推送
			//SELECT * FROM T_USER  ORDER BY  RAND() LIMIT 10  随机查找10条记录
			
			//String sql2="select PID,PAddress from Picture  ORDER BY RAND()"; //从数据库随机获取20条图片信息
			String hql2="from Picture where FinalMarkName is null order by rand()";//只有没有确定最终标签的图片需要推送  where FinalMarkName is null
			Query q=session.createQuery(hql2);
			q.setFirstResult(0);  // 从第0条记录开始取 
		      q.setMaxResults(12); // 取20条记录
			List<Picture> pictures=q.list();
			/*for(Picture p:pictures){
				String s=CreateJson.getPictureJson(p);
			
				jsonPicture.append(s+",");
			}*/
			for(int i=0;i<pictures.size();i++){ 
				if(i<(pictures.size()-1)){  
					String s=CreateJson.getPictureJson(pictures.get(i));
					jsonPicture.append(s+",");
				}else{
					String s=CreateJson.getPictureJson(pictures.get(i));
					jsonPicture.append(s);
				}
			}
			session.getTransaction().commit(); 
			SessionAnnotation.closeSession(); 
			return jsonPicture.toString();
		}else{ //针对用户情况推送
			//算法推荐
			
			
			System.out.println("满足针对性条件"); 
			SessionAnnotation.closeSession();
			return jsonPicture.toString();
		}
	}
	
	
public static String getSinglePush(String UserId){ //推送一张
		
		Session session=SessionAnnotation.getSession();
		session.beginTransaction();
		StringBuffer jsonPicture=new StringBuffer();
		//jsonPicture.append("");
		//String sql="select PID from Mark where USERID ='"+UserId+"'";
		String hql="from Mark where UserId=?";//+UserId+"'";
		Query query=session.createQuery(hql);
		//query.setParameter(0,UserId);
		query.setString(0,UserId);
		List list=query.list();
		System.out.println("历史记录数目："+list.size()+"  "+list.toString());
		//List list=session.createQuery(sql).list();
		if(list.size()==0||list.size()<=5) {  //新用戶，不符合针对性推送，随机推送
			//SELECT * FROM T_USER  ORDER BY  RAND() LIMIT 10  随机查找10条记录
			
			//String sql2="select PID,PAddress from Picture  ORDER BY RAND()"; //从数据库随机获取20条图片信息
			String hql2="from Picture where FinalMarkName is null order by rand()";//只有没有确定最终标签的图片需要推送  where FinalMarkName is null
			Query q=session.createQuery(hql2);
			q.setFirstResult(0);  // 从第0条记录开始取
		      q.setMaxResults(1); // 取20条记录
			List<Picture> pictures=q.list();
			/*for(Picture p:pictures){
				String s=CreateJson.getPictureJson(p);
				jsonPicture.append(s+",");
			}*/
			for(int i=0;i<pictures.size();i++){ 
				if(i<(pictures.size()-1)){  
					String s=CreateJson.getPictureJson(pictures.get(i));
					jsonPicture.append(s+",");
				}else{
					String s=CreateJson.getPictureJson(pictures.get(i));
					jsonPicture.append(s);
				}
			}
			session.getTransaction().commit(); 
			SessionAnnotation.closeSession(); 
			return jsonPicture.toString();
		}else{ //针对用户情况推送
			//算法推荐
			
			
			
			System.out.println("满足条件"); 
			SessionAnnotation.closeSession();
			return jsonPicture.toString();
		}
	}


}
