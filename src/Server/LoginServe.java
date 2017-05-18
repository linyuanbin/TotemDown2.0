package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.PushPicture;
import dao.MarkDao;
import dao.PictureDao;
import dao.UserDao;
import daoimplement.MarkImplement;
import daoimplement.PictureImplement;
import daoimplement.UserImplement;
import jsonUtil.CreateJson;
import model.Mark;
import model.Picture;
import model.User;

public class LoginServe extends HttpServlet {

	private UserDao d = new UserImplement();
	MarkDao md=new MarkImplement();
	private PictureDao pd=new PictureImplement();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 获取“http” //获取服务名 //获取端口号
		String str = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "";
		System.out.println(str);

		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		// request.setCharacterEncoding("utf-8");
		// response.setContentType("text/html");
		// response.setContentType("text/html;charset=utf8"); //解决中文乱码
		// 必须写在得到流之前gbk
		String username = new String(request.getParameter("username").getBytes("iso-8859-1"), "utf-8");
		// String username = request.getParameter("username");
		// username = new String(username.getBytes("iso-8859-1"),"utf-8”);
		String password = request.getParameter("password");
		System.out.println(username + ":" + password);

		PrintWriter out = response.getWriter(); // 通过response得到字节输出流
		String msg = null;
		if (username != null && username.equals("linyuanbin") && password != null && password.equals("123456")) {
			
			System.out.print("登录服务器成功l ！");
//			msg = "http://imgstore04.cdn.sogou.com/app/a/100520024/877e990117d6a7ebc68f46c5e76fc47a";
//			out.write(msg);
			BufferedReader br = request.getReader(); 
			
			String str1 = null;
			StringBuilder resource = new StringBuilder();
			while ((str1 = br.readLine()) != null) // 判断最后一行不存在，为空结束循环
			{
				resource.append(str1);
				System.out.println(str1);// 原样输出读到的内容
			}

			User u = CreateJson.getUser(resource.toString());
			
			if(u.getState().trim().equals("mark")) System.out.println("标签");
			
			if ((u.getState().trim()).equals("login")) {                 // 登录
				String UserId = d.login(u.getUserName(), u.getUserPassword());
				String jsonUser="";
				if(UserId.equals("")){
					System.out.println("密码错误！");
					msg=UserId;//空值
					User u2=new User();//创建一个空的User对象给客户端
					u2.setState("false");
					u2.setUserID(" ");
					String userfile=CreateJson.getUserJson(u2);
					System.out.println("錯誤用戶信息："+userfile);
					out.write(userfile);
				}else{
				System.out.println("密码正确 用户ID" + UserId);
				User u6=d.showUser(UserId.trim());  //获取对应ID的User
				u6.setState("true");
				System.out.println("name"+u6.getUserName());
				System.out.println("name"+u6.toString());
				 jsonUser=CreateJson.getUserJson(u6);
//				System.out.println(jsonUser.toString());
//				msg=jsonUser.toString().trim();
				msg=u6.toString();
				out.write(jsonUser);  //登录是向用户反馈User基本信息
				//out.println(msg);
				}
				
			} else if ((u.getState().trim()).equals("register")) {     // 注册
				String userID = d.register(u);
				System.out.println("注册情况：" + userID);
				if ((!userID.equals(""))) {// 如果注册成功
					//msg = u.getUserID(); //注册成功反馈UserId 
					u.setState("true");
					String userfile=CreateJson.getUserJson(u);
					System.out.println("注册成功用户信息："+userfile);
					out.write(userfile);
				} else {
					//msg = "false";       //注册失败回馈
					u.setState("false");
					String s=CreateJson.getUserJson(u);
					System.out.println("注册失败用户信息："+s); 
					out.write(s);
				}
			} else if ((u.getState().trim()).equals("update")) {        // 更新用户
				/*
				 * String UserId=d.getUserID(u.getUserTel());
				 * System.out.println("查询用户id"+UserId);
				 */
				d.updateUser(u);
				System.out.println("用户生日：" + u.getUserBirthday().toLocaleString());
			}else if((u.getState().trim()).equals("request".trim())){       //推送请求
				 String pushPictureJson=PushPicture.getPush(u.getUserID());
				 pushPictureJson="["+pushPictureJson+"]";
				 System.out.println("推送："+pushPictureJson); 
				 out.write(pushPictureJson);
			}else if((u.getState().trim()).equals("request1".trim())){       //推送一张
				 String pushPictureJson=PushPicture.getSinglePush(u.getUserID());
				 pushPictureJson="["+pushPictureJson+"]";
				 System.out.println("推送："+pushPictureJson); 
				 out.write(pushPictureJson);
			}else if(u.getState().trim().equals("mark".trim())){ //打标签
				System.out.println("打标签模块");
				User user=d.showUser(u.getUserID().trim()); 
				System.out.println("打标签的用戶"+user.getUserName());
				Picture p=pd.selectSinglePictureFID(u.getPID().trim());
				System.out.println("被标签的图片"+p.getPName());
				Mark m=new Mark();
				m.setMarkName(u.getMarkName().trim());
				m.setUser(user);
				m.setPicture(p);
				m.setMarkDate(new Date());
				m.setTabId(user.getUserID().trim()+p.getPID());
				boolean a=false;
//				if((!(m.getMarkName().trim().equals("")))||(!(m.getMarkName().trim()==null))){
//					a=true;
//				}
//				boolean b=false;
//				if(a){
				boolean b=md.insertMark(m);
//				}
//				boolean b=md.insertIntoMark(u.getUserID().trim(),u.getPID().trim(),u.getMarkName().trim()); //存入标签
				if(b){//标签成功
					user.setUserIntegral(user.getUserIntegral()+1); //成功标签一次积分加一
					d.updateUser(user);
					User user2=new User();
					user2.setState("true");
					System.out.println("标签成功");
					String s=CreateJson.getUserJson(user2);
					out.write(s);
				}else{ //标签失败
					System.out.println("标签失败");
					User user2=new User();
					user2.setState("false");
					String s=CreateJson.getUserJson(user2);
					out.write(s);
				}//标签失败
				
			}else{
				System.out.println("没找到state");
				String str2="500 NotFound the state"; 
				out.write(str2);
			}

		}else{
			System.out.println("登入服务器失败");
			msg = "404 NotFound connect username or password error";       //注册失败回馈
			out.write(msg);
		}
		// out.print(msg); //传送给客户端数据

		out.flush();
		out.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
