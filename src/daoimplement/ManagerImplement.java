package daoimplement;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import dao.ManagerDao;
import hibernateutil.SessionAnnotation;
import hibernateutil.ValidatorUserNameUtil;
import model.Manager;
import model.User;
import util.RandomString;

public class ManagerImplement implements ManagerDao{

	@Override
	public String register(Manager m) {
		Session session = SessionAnnotation.getSession();
		session.beginTransaction();
		String mIdCard = "";
		if (m.getmIdCard()!= null) { //��֤���֤�Ƿ����
			String sql = "select mIdCard from Manager where mIdCard='" + m.getmIdCard() + "'";
			List list = session.createQuery(sql).list();
			if (!list.isEmpty()) {
				System.out.println("�û����֤�� ע��");
				session.getTransaction().commit();
				SessionAnnotation.closeSession();
				return mIdCard;// �յ�id
			} else {
				try {
					// session.beginTransaction();
					session.save(m);
					session.flush();
					session.getTransaction().commit();
					SessionAnnotation.closeSession();
					return m.getCheckId();

				} catch (Exception e) {
					System.out.println(e);
					SessionAnnotation.closeSession();
					mIdCard="";
					return mIdCard;
				}
			} // else
		}else{ // if
		return mIdCard;
		}
		
	}

	@Override
	public String login(String mName, String mPassword) {
		Session session = SessionAnnotation.getSession();
		String sql;
		String mIdCard = "";

		if (ValidatorUserNameUtil.isEmail(mName)) {
			sql = "select mIdCard from Manager where mEmail='" + mName + "'and mPassword='" + mPassword + "'";
		} else if (ValidatorUserNameUtil.isMobile(mName)) {
			sql = "select mIdCard from Manager where mTel='" + mName + "'and mPassword='" + mPassword + "'";
		} else {
			sql = "select mIdCard from Manager where mName='" + mName + "'and mPassword='" + mPassword + "'";
		}
		session.beginTransaction();
		List list = session.createQuery(sql).list();
		if (list.isEmpty()) {
			// session.getTransaction().commit();
			SessionAnnotation.closeSession();
			mIdCard="";
			System.out.println("��¼ʧ�ܣ�");
			return mIdCard;
		}
		mIdCard = (String) list.iterator().next();
		session.getTransaction().commit();
		SessionAnnotation.closeSession();
		System.out.println("��¼�ɹ���");
		return mIdCard;
	}

	
	@Override
	public boolean deleteManager(String mIdCard) {
		Session session = SessionAnnotation.getSession();
		session.beginTransaction();
		String sql = "select mIdCard from Manager where mIdCard='" + mIdCard + "'"; // �����Ƿ�����û�
		List list = session.createQuery(sql).list();

		if (list.isEmpty()) { // �������˵��������
			SessionAnnotation.closeSession();
			return false;
		}
		// "delete from User where User_ID='"+User_ID+"'";
		sql = "delete from Manager where mIdCard='" + mIdCard + "'"; 
		Query query = session.createQuery(sql);
		query.executeUpdate();
		session.getTransaction().commit();
		SessionAnnotation.closeSession();
		return true;
	}

	@Override
	public boolean updateManager(Manager m) {
		if (senseManager(m.getmIdCard())) { // ����û����������
			try{
				Session session = SessionAnnotation.getSession();
				session.beginTransaction();
				session.update(m);
				session.getTransaction().commit();
				SessionAnnotation.closeSession();
				return true;
			}catch(Exception e){
				System.out.println(e);
				System.out.println("updateManagerʧ��");  
				SessionAnnotation.closeSession();  
				return false;
			}
		}else{
			System.out.println("�û����ڣ�");
			return false;
		}
	}  

	@Override
	public Manager selectManager(String mName) {
		Session session = SessionAnnotation.getSession();
		String sql;
		if (ValidatorUserNameUtil.isEmail(mName)) {
			sql = "select mIdCard from Manager where mEmail='" + mName + "'";
		} else if (ValidatorUserNameUtil.isMobile(mName)) {
			sql = "select mIdCard from Manager where mTel='" + mName + "'";

		} else {
			sql = "select mIdCard from Manager where mName='" + mName + "'";
		}
		session.beginTransaction();
		List list = session.createQuery(sql).list();
		System.out.println(list);
		if (!list.isEmpty()) { // �������
			// User user=(User)list.iterator().next();
			String uid = list.toString();
			Manager m = (Manager) session.get(Manager.class, uid.trim());
			System.out.println("selectManager" + m.getmName());
			session.getTransaction().commit();
			SessionAnnotation.closeSession();
			return m;
		}
		// session.getTransaction().commit();
		SessionAnnotation.closeSession();
		return null;
	}

	@Override
	public boolean senseManager(String mID) {
		Session session = SessionAnnotation.getSession();
		String sql;
		sql = "select mIdCard from Manager where mIdCard='" + mID + "'";
		session.beginTransaction();
		List list = session.createQuery(sql).list();
		if (!list.isEmpty()) {
			session.getTransaction().commit();
			SessionAnnotation.closeSession();
			return true;
		}
		return false;
	}

	@Override
	public Manager showManager(String mID) {
		Session session = SessionAnnotation.getSession();
		session.beginTransaction();
		Manager manager = (Manager) session.get(Manager.class, mID);
		session.getTransaction().commit();
		// session.flush();
		SessionAnnotation.closeSession();
		return manager;
	}

	@Override
	public String getManagerID(String mName) {
		Session session = SessionAnnotation.getSession();
		session.beginTransaction();
		String sql;
		if (ValidatorUserNameUtil.isEmail(mName)) {
			sql = "select mIdCard from Manager where mEmail='" + mName + "'";
		} else if (ValidatorUserNameUtil.isMobile(mName)) {
			sql = "select mIdCard from Manager where mTel='" + mName + "'";

		} else {
			sql = "select mIdCard from Manager where mName='" + mName + "'";
		}
		List list = session.createQuery(sql).list();
		String mID = (String) list.iterator().next();
		// String userid=session.createQuery(sql).toString();
		session.getTransaction().commit();
		if (mID.isEmpty()) {
			SessionAnnotation.closeSession();
			return null;
		} else {
			return mID;
		}
	}
 

	
}
