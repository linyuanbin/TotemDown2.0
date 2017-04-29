package daoimplement;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import dao.UserDao;
import hibernateutil.SessionAnnotation;
import hibernateutil.ValidatorUserNameUtil;
import model.User;
import util.RandomString;

public class UserImplement implements UserDao {

	@Override
	public String register(User u) { // ���ε�������û�
		Session session = SessionAnnotation.getSession();
		String userId = "";
		if (u.getUserTel() != null) { // �����ע��֮ǰȷ�����ֻ����Ƿ��Ѿ�ע��,һ���ֻ���ֻ��ע��һ��
			String sql = "select UserID from User where UserTel='" + u.getUserTel() + "'";
			session.beginTransaction();
			List list = session.createQuery(sql).list();
			if (!list.isEmpty()) {
				session.getTransaction().commit();
				SessionAnnotation.closeSession();
				return userId;// �յ�id
			} else {
				userId = new Date() + RandomString.getRandomString(5);
				u.setUserID(userId.trim()); // ��������û�ID
				try {
					// session.beginTransaction();
					session.save(u);
					session.flush();
					session.getTransaction().commit();
					SessionAnnotation.closeSession();
					return userId;

				} catch (Exception e) {
					System.out.println(e);
					SessionAnnotation.closeSession();
					userId="";
					return userId;
				}
			} // else
		}else{ // if
		return userId;
		}
	}

	@Override
	public String login(String UserName, String Password) {
		Session session = SessionAnnotation.getSession();
		String sql;
		String userId = "";

		if (ValidatorUserNameUtil.isEmail(UserName)) {
			sql = "select UserID from User where UserEmail='" + UserName + "'and UserPassword='" + Password + "'";
		} else if (ValidatorUserNameUtil.isMobile(UserName)) {
			sql = "select UserID from User where UserTel='" + UserName + "'and UserPassword='" + Password + "'";
		} else {
			sql = "select UserID from User where UserName='" + UserName + "'and UserPassword='" + Password + "'";
		}
		session.beginTransaction();
		List list = session.createQuery(sql).list();
		if (list.isEmpty()) {
			// session.getTransaction().commit();
			SessionAnnotation.closeSession();
			userId="";
			return userId;
		}
		userId = (String) list.iterator().next();
		session.getTransaction().commit();
		SessionAnnotation.closeSession();
		return userId;
	}

	@Override
	public boolean deleteUser(String UserId) {
		Session session = SessionAnnotation.getSession();
		session.beginTransaction();
		String sql = "select UserID from User where UserID='" + UserId + "'"; // �����Ƿ�����û�
		List list = session.createQuery(sql).list();

		if (list.isEmpty()) { // �������˵��������
			SessionAnnotation.closeSession();
			return false;
		}
		// "delete from User where User_ID='"+User_ID+"'";
		sql = "delete from User where UserID='" + UserId + "'";
		Query query = session.createQuery(sql);
		query.executeUpdate();
		session.getTransaction().commit();
		SessionAnnotation.closeSession();
		return true;
	}

	@Override
	public void updateUser(User u) { // ����
		if (senseUser(u.getUserID())) { // ����û����������
			Session session = SessionAnnotation.getSession();
			session.beginTransaction();
			session.update(u);
			session.getTransaction().commit();
			SessionAnnotation.closeSession();
		}
	}

	@Override
	public User selectUser(String UserName) { // �����û� �÷����е�bug
		Session session = SessionAnnotation.getSession();
		String sql;
		if (ValidatorUserNameUtil.isEmail(UserName)) {
			sql = "select UserID from User where UserEmail='" + UserName + "'";
		} else if (ValidatorUserNameUtil.isMobile(UserName)) {
			sql = "select UserID from User where UserTel='" + UserName + "'";

		} else {
			sql = "select UserID from User where UserName='" + UserName + "'";
		}
		session.beginTransaction();
		List list = session.createQuery(sql).list();
		System.out.println(list);
		if (!list.isEmpty()) { // �������
			// User user=(User)list.iterator().next();
			String uid = list.toString();
			User user = (User) session.get(User.class, uid);
			System.out.println("selectUser" + user.getUserName());
			session.getTransaction().commit();
			SessionAnnotation.closeSession();
			return user;
		}
		// session.getTransaction().commit();
		SessionAnnotation.closeSession();
		return null;
	}

	@Override
	public boolean senseUser(String UserID) { // �ж��û��Ƿ����
		Session session = SessionAnnotation.getSession();
		String sql;
		sql = "select UserID from User where UserID='" + UserID + "'";
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
	public User showUser(String UserID) {

		Session session = SessionAnnotation.getSession();
		session.beginTransaction();
		User user = (User) session.get(User.class, UserID);
		session.getTransaction().commit();
		// session.flush();
		SessionAnnotation.closeSession();
		return user;

	}

	@Override
	public String getUserID(String UserName) {
		Session session = SessionAnnotation.getSession();
		session.beginTransaction();
		String sql;
		if (ValidatorUserNameUtil.isEmail(UserName)) {
			sql = "select UserID from User where UserEmail='" + UserName + "'";
		} else if (ValidatorUserNameUtil.isMobile(UserName)) {
			sql = "select UserID from User where UserTel='" + UserName + "'";

		} else {
			sql = "select UserID from User where UserName='" + UserName + "'";
		}
		List list = session.createQuery(sql).list();
		String userid = (String) list.iterator().next();
		// String userid=session.createQuery(sql).toString();
		session.getTransaction().commit();
		if (userid.isEmpty()) {
			SessionAnnotation.closeSession();
			return null;
		} else {
			return userid;
		}
	}

}
