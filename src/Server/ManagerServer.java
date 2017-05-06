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
		System.out.println("����Ա��¼");
		if(userName!=null &&userName.equals("linyuanbin")&&password!=null&&password.equals("123456")){
			System.out.println("����Ա�ɹ����ӷ�����");
			BufferedReader br=request.getReader();
			String str1="";
			StringBuilder sb=new StringBuilder();
			while((str1=br.readLine())!=null){
				sb.append(str1);
				System.out.println(str1);
			}
			
			Manager m=CreateJson.getManager(br.toString().trim());
			
			if(m.getState().equals("register")){ //ע��
				
				
				
				
			}else if(m.getState().equals("login")){//��¼
			               // ��¼
					String mIdCard = md.login(m.getmName(), m.getmPassword());
					String jsonManager="";
					if(mIdCard.equals("")){
						System.out.println("�������");
						msg=mIdCard;//��ֵ
						Manager m2=new Manager();//����һ���յ�User������ͻ���
						m2.setState("false");
						m2.setmIdCard(" ");
						String Managerfile=CreateJson.getManagerJson(m2);
						System.out.println("�e�`�Ñ���Ϣ��"+Managerfile);
						out.write(Managerfile);
					}else{
					System.out.println("������ȷ �û�ID" + mIdCard);
					Manager m6=md.showManager(mIdCard.trim());  //��ȡ��ӦID��User
					m6.setState("true");
					System.out.println("name"+m6.getmName());
					System.out.println("name"+m6.toString());
					jsonManager=CreateJson.getManagerJson(m6);
//					System.out.println(jsonUser.toString());
//					msg=jsonUser.toString().trim();
					msg=m6.toString();
					out.write(jsonManager);  //��¼�����û�����User������Ϣ
					//out.println(msg);
					}
					
				
			}else if(m.getState().equals("download")){ //������ǩ
				
			}else if(m.getState().equals("upload")){ //�ϴ���Ƭ
				
			}else{
				String str2="500 NotFound";
				out.write(str2);
			}
			
			 
			
		}else{
			msg="404 Not Found"; //���ӷ�����ʧ��
			out.write(msg);
		}
			
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,resp);
	}

}
