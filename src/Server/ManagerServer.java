package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.ManagerAction;
import dao.ManagerDao;
import dao.PictureDao;
import daoimplement.ManagerImplement;
import daoimplement.PictureImplement;
import jsonUtil.CreateJson;
import model.ImageList;
import model.Manager;
import model.Picture;
import model.User;

public class ManagerServer extends HttpServlet{
	
	private ManagerDao md=new ManagerImplement();
	private PictureDao pd=new PictureImplement();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

		String str = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "";
		System.out.println(str);

		String userName=request.getParameter("username");
		String password=request.getParameter("password");
		System.out.println(userName+":"  +password);
		PrintWriter out=resp.getWriter();
		String msg="";
		System.out.println("管理员登录");
		if(userName!=null &&userName.equals("linyuanbin")&&password!=null&&password.equals("123456")){
			System.out.println("管理员成功连接服务器");
			BufferedReader br=request.getReader();
			String str1="";
			StringBuilder sb=new StringBuilder();
			while((str1=br.readLine())!=null){
				sb.append(str1);
				System.out.println(str1);
			}
			
			Manager m=CreateJson.getManager(sb.toString().trim());
			System.out.println("管理员状态"+m.getState());
			System.out.println(m.getState());
			if(m.getState().equals("register")){ //注册  管理员注册只有数据库中有其对应身份证号的才允许注册
				
					if(m.getCheckId().equals("gb10086")){ //注册校验码正确
						String mIdCard=md.register(m);
						if(mIdCard.equals("")){ //用户id不存在
						m.setState("false");
						String managerJson=CreateJson.getManagerJson(m);
						System.out.println("注册存入失败用户信息："+managerJson);
						out.write(managerJson);
						}else{//注册成功
							m.setState("true");
							String managerJson=CreateJson.getManagerJson(m);
							System.out.println("注册成功用户信息"+managerJson);
							out.write(managerJson);
						}
						
					}else{ //注册校验码错误
						m.setState("false");
						String managerJson=CreateJson.getManagerJson(m);
						System.out.println("校验码错误注册失败用户信息："+managerJson);
						out.write(managerJson);
					}
				
			}else if(m.getState().equals("login")){//登录
			               // 登录
					String mIdCard = md.login(m.getmName(), m.getmPassword());
					String jsonManager="";
					if(mIdCard.equals("")){
						System.out.println("密码错误！");
						msg=mIdCard;//空值
						Manager m2=new Manager();//创建一个空的User对象给客户端
						m2.setState("false");
						m2.setmIdCard(" ");
						String Managerfile=CreateJson.getManagerJson(m2);
						System.out.println("錯誤用戶信息："+Managerfile);
						out.write(Managerfile);
					}else{
					System.out.println("密码正确 用户ID" + mIdCard);
					Manager m6=md.showManager(mIdCard.trim());  //获取对应ID的User
					m6.setState("true");
					System.out.println("name"+m6.getmName());
					System.out.println("name"+m6.toString());
					jsonManager=CreateJson.getManagerJson(m6);
					System.out.println("登录成功");
					out.write(jsonManager);  //登录是向用户反馈User基本信息
					//out.println(msg);
					}
			}else if(m.getState().equals("download")){ //导出标签
				String finalMark=pd.selectPicturesFFN();
				if(finalMark.equals("")){ //无标签化结果
					out.write("");
				}else{
					finalMark="["+finalMark+"]";
					System.out.println("发送标签化结果："+finalMark);
					out.write(finalMark);
				}
				
			}else if(m.getState().equals("upload")){ //上传照片
				System.out.println("upload执行");
				ImageList imagelist=CreateJson.getImageList(sb.toString().trim());
				boolean b=ManagerAction.SaveImage(imagelist);
				if(b){ //图片保存成功
					Manager ma=new Manager();
					ma.setState("true");
					String majson=CreateJson.getManagerJson(ma);
					out.write(majson);
				}else{//图片保存失败
					Manager ma=new Manager();
					ma.setState("false");
					String majson=CreateJson.getManagerJson(ma);
					out.write(majson);
				}
				
			}else if(m.getState().equals("update")){//更新管理员资料 
				boolean state=md.updateManager(m); 
				Manager m2=new Manager();
				String ss;
				if(state){
					ss="true";
					System.out.println("管理员更新成功！");
				}else{
					ss="false";
					System.out.println("管理员更新失败！");
				}
				m2.setState(ss);
				String managerJson=CreateJson.getManagerJson(m2);
				out.write(managerJson);
			}else{ //没找到对应state的值
				String str2="500 NotFound! Didn't find the state";
				Manager ma=new Manager();
				ma.setState("false");
				String majson=CreateJson.getManagerJson(ma);
				out.write(majson);
				out.write(str2);
			}
			
		}else{ //连接服务器失败！
			msg="404 Not Found! Connection server failed (User name or password error)"; //连接服务器失败
			out.write(msg);
		}
			
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,resp);
	}

}
