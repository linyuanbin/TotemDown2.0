package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManagerDao;
import daoimplement.ManagerImplement;
import jsonUtil.CreateJson;
import model.Manager;
import model.User;

public class ManagerServer extends HttpServlet{
	
	private ManagerDao md=new ManagerImplement();
	
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
			
			Manager m=CreateJson.getManager(br.toString().trim());
			
			if(m.getState().equals("register")){ //注册
				
				
				
				
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
//					System.out.println(jsonUser.toString());
//					msg=jsonUser.toString().trim();
					msg=m6.toString();
					out.write(jsonManager);  //登录是向用户反馈User基本信息
					//out.println(msg);
					}
					
				
			}else if(m.getState().equals("download")){ //导出标签
				
			}else if(m.getState().equals("upload")){ //上传照片
				
			}else{
				String str2="500 NotFound";
				out.write(str2);
			}
			
			 
			
		}else{
			msg="404 Not Found"; //连接服务器失败
			out.write(msg);
		}
			
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,resp);
	}

}
