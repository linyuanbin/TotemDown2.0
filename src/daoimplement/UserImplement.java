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
	public String register(User u) { // 初次登入添加用户
		Session session = SessionAnnotation.getSession();
		String userId = "";
		if (u.getUserTel() != null) { // 在完成注册之前确定该手机号是否已经注册,一个手机号只能注册一次
			String sql = "select UserID from User where UserTel='" + u.getUserTel() + "'";
			session.beginTransaction();
			List list = session.createQuery(sql).list();
			if (!list.isEmpty()) {
				session.getTransaction().commit();
				SessionAnnotation.closeSession();
				return userId;// 空的id
			} else {
				userId = new Date() + RandomString.getRandomString(5);
				u.setUserID(userId.trim()); // 随机产生用户ID
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
		String sql = "select UserID from User where UserID='" + UserId + "'"; // 擦看是否存在用户
		List list = session.createQuery(sql).list();

		if (list.isEmpty()) { // 如果空则说明不存在
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
	public void updateUser(User u) { // 更新
		if (senseUser(u.getUserID())) { // 如果用户存在则更新
			Session session = SessionAnnotation.getSession();
			session.beginTransaction();
			session.update(u);
			session.getTransaction().commit();
			SessionAnnotation.closeSession();
		}
	}

	@Override
	public User selectUser(String UserName) { // 查找用户 该方法有点bug
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
		if (!list.isEmpty()) { // 如果不空
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
	public boolean senseUser(String UserID) { // 判定用户是否存在
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
