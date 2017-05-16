package daoimplement;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import dao.PictureDao;
import hibernateutil.SessionAnnotation;
import jsonUtil.CreateJson;
import model.Picture;
import util.RandomString;

public class PictureImplement implements PictureDao {

	@Override
	public boolean AddPicture(Picture p) {  //添加图片资源
		Session session=SessionAnnotation.getSession();
		p.setPID(new Date()+RandomString.getRandomString(5));
		try{
		session.beginTransaction();
		session.save(p);
		session.getTransaction().commit();
		SessionAnnotation.closeSession();
		return true;
		}catch(Exception e){
			System.out.println(e);
			SessionAnnotation.closeSession(); 
			return false;
		}
	}

	@Override
	public boolean updatePicture(Picture p) {
		Session session=SessionAnnotation.getSession();
		try{
		session.beginTransaction();
		session.update(p);
		session.getTransaction().commit();
		SessionAnnotation.closeSession();
		return true;
		}catch(Exception e){
			System.out.println(e);
			SessionAnnotation.closeSession();
			return false;
		}
	}

	@Override
	public boolean deletePicture(String PID) {  //删除图片
		Session session=SessionAnnotation.getSession();
		session.beginTransaction();
		String sql="select PID from Picture where PID='"+PID+"'";  //擦看是否存在该图片
		List list=session.createQuery(sql).list();
		
		if(list.isEmpty()){ //如果空则说明不存在
			SessionAnnotation.closeSession();
			return false;
		}
		//"delete from User where User_ID='"+User_ID+"'";
		sql="delete from Picture where UserID='"+PID+"'";
		Query query=session.createQuery(sql);
		query.executeUpdate();
		session.getTransaction().commit(); 
		SessionAnnotation.closeSession(); 
		return true; 
	}

	@Override
	public Set<Picture> selectAllPicture() {  //返回数据库中所有的图片对象     测试成功
		Session session=SessionAnnotation.getSession();
		session.beginTransaction();
		Set<Picture> pictures=new HashSet<Picture>();
		String sql="select PID from Picture";
		List<String> list=new ArrayList<>();
		list=(List<String>) session.createQuery(sql).list();
		System.out.println("list"+list);
		for(String li:list){
			System.out.println("Pid  "+li);
			Picture p=(Picture) session.get(Picture.class,li);
			pictures.add(p);
		}
		session.getTransaction().commit();
		SessionAnnotation.closeSession();
		return pictures;
	}

	@Override
	public Picture selectSinglePictureFID(String PID) {  //ID查询图片
		Session session=SessionAnnotation.getSession();
		session.beginTransaction();
		try{
			
			Picture p=(Picture) session.get(Picture.class,PID);
			session.getTransaction().commit();
			SessionAnnotation.closeSession();
			return p;
		}catch(Exception e){
			System.out.println(e);
			SessionAnnotation.closeSession();
			return null;
		}
	}

	@Override
	public Set<Picture> selectPicturesFN(String PName) {  //查找名字匹配相似图片
		Session session=SessionAnnotation.getSession();
		session.beginTransaction();
		Set<Picture> pictures=new HashSet<Picture>();
		String sql="select PID from Picture where PNAME like '%"+PName+"%'";
		List<String> list=session.createQuery(sql).list();
		for(String li:list){
			Picture p=(Picture)session.get(Picture.class,li); 
			pictures.add(p); 
		}
		session.getTransaction().commit(); 
		SessionAnnotation.closeSession(); 
		return pictures;
	}

	@Override
	public Set<Picture> selectPicturesFM(String pMark) {  
		Session session=SessionAnnotation.getSession();
		session.beginTransaction();
		Set<Picture> pictures=new HashSet<Picture>(); 
		String sql="select PID from Picture where MarkName like '%"+pMark+"%'"; 
		List<String> list=session.createQuery(sql).list(); 
		for(String li:list){ 
			Picture p=(Picture)session.get(Picture.class,li); 
			pictures.add(p); 
		}
		session.getTransaction().commit(); 
		SessionAnnotation.closeSession(); 
		return pictures; 
	}

	@Override
	public Picture selectSinglePictureFN(String Pname) { 
		Session session=SessionAnnotation.getSession();
		session.beginTransaction();
		try{
			String sql="select PID from picture where Pname='"+Pname+"'"; 
			List list=session.createQuery(sql).list(); 
			Picture p=(Picture)session.get(Picture.class,list.toString()); 
			session.getTransaction().commit(); 
			SessionAnnotation.closeSession(); 
			return p; 
		}catch(Exception e){
			session.getTransaction().commit();
			SessionAnnotation.closeSession();
			return null;
		}
	
	}

	@Override
	public String selectPicturesFFN() {  //导出最终标签化结果
		Session session=SessionAnnotation.getSession();
		session.beginTransaction();
		//Set<Picture> pictures=new HashSet<Picture>();
		List<Picture> pictures=new ArrayList<>();
		try{
			String hql="from Picture where FinalMarkName is not null";
			pictures=session.createQuery(hql).list();
			StringBuilder sb2=new StringBuilder();
			if(pictures.size()!=0){
			for(int i=0;i<pictures.size();i++){ 
				if(i<(pictures.size()-1)){  
					String s=CreateJson.getPictureJson(pictures.get(i));
					sb2.append(s+",");
				}else{
					String s=CreateJson.getPictureJson(pictures.get(i));
					sb2.append(s);
				}
			}
			System.out.println("标签结果："+sb2);
			session.getTransaction().commit();
			SessionAnnotation.closeSession();
			return sb2.toString();
			}else{
				System.out.println("无标签化结果！");
				SessionAnnotation.closeSession();
				return "";
			}
		}catch(Exception e){
			SessionAnnotation.closeSession();
			System.out.println("导出标签结果异常："+e);
			return "";
		}
	}
	
	
	

}
