package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerServer extends HttpServlet{
	
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
