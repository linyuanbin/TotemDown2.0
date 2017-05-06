package dao;

import model.Manager;
import model.User;

public interface ManagerDao {
	
		//用户注册方法
		public String register(Manager u);
		//用户登录
		public String login(String mName,String mPassword);
		public boolean deleteManager(String mId);
		public boolean updateManager(Manager m);
		public Manager selectManager(String mName);
		//判断是否存在该用户
		public boolean senseManager(String mID); 
		//展现用户
		public Manager showManager(String mID);
		public String getManagerID(String mName);

}
