package daoimplement;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import dao.PictureDao;
import dao.UserDao;
import hibernateutil.SessionAnnotation;
import model.Mark;
import model.Picture;
import model.User;

public class MarkImplement {
	
	public boolean insertIntoMark(String UserId,String Pid,String markName){
		Session session=SessionAnnotation.getSession();
		session.beginTransaction();
		try{
			UserDao d=new UserImplement();
			PictureDao pd=new PictureImplement();
			Mark m=new Mark();
			m.setTabId(UserId+Pid);
			User u=d.showUser(UserId);
			Picture p= pd.selectSinglePictureFID(Pid);
			m.setUser(u);
			m.setPicture(p);
			m.setMarkName(markName);
			session.save(m);
			session.getTransaction().commit();
			SessionAnnotation.closeSession();
			return true;
		}catch(Exception e){
			System.out.println(e);
			System.out.println("����mark��ʧ��");
			SessionAnnotation.closeSession();
			return false;
		}
	}
	
	public boolean insertMark(Mark m){
		Session session=SessionAnnotation.getSession();
		session.beginTransaction();
		try{
			session.save(m);
			session.getTransaction().commit();
			SessionAnnotation.closeSession();
			return true;
		}catch(Exception e){
			System.out.println(e);
			System.out.println("����mark��ʧ�ܣ�");
			SessionAnnotation.closeSession();
			return false;
		}
	}
	
	public boolean deleteOnMark(String TabId){  //����idɾ��mark����¼
		Session session=SessionAnnotation.getSession();
		session.beginTransaction();
		try{
			String sql="select TabId from Mark where TabId='"+TabId+"'";
			List list=session.createQuery(sql).list();
			if(list.isEmpty()){ //��������ھ�ɾ��ʧ��
				SessionAnnotation.closeSession();
				return false;
			}
			sql="delete from Mark where TabId='"+TabId+"'";
			Query query=session.createQuery(sql);
			query.executeUpdate();
			session.getTransaction().commit();
			SessionAnnotation.closeSession();
			return true;
		}catch(Exception e){
			System.out.println(e);
			System.out.println("ɾ��mark��ʧ�ܣ�");
			SessionAnnotation.closeSession();
			return false;
		}
	}
	
	public boolean updateMark(Mark m){
		Session session=SessionAnnotation.getSession();
		session.beginTransaction();
		try{
			
			
			return true;
		}catch(Exception e){
			System.out.println(e);
			System.out.println("����mark��ʧ��");
			SessionAnnotation.closeSession();
			return false;
		}
	}
	

}