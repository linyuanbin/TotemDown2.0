package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import daoimplement.UserImplement;
import jsonUtil.CreateJson;
import model.Picture;
import model.User;

public class LoginServe extends HttpServlet {

	private UserDao d = new UserImplement();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ��ȡ��http�� //��ȡ������ //��ȡ�˿ں�
		String str = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "";
		System.out.println(str);

		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		// request.setCharacterEncoding("utf-8");
		// response.setContentType("text/html");
		// response.setContentType("text/html;charset=utf8"); //�����������
		// ����д�ڵõ���֮ǰgbk
		String username = new String(request.getParameter("username").getBytes("iso-8859-1"), "utf-8");
		// String username = request.getParameter("username");
		// username = new String(username.getBytes("iso-8859-1"),"utf-8��);
		String password = request.getParameter("password");
		System.out.println(username + ":" + password);

		PrintWriter out = response.getWriter(); // ͨ��response�õ��ֽ������
		String msg = null;
		if (username != null && username.equals("linyuanbin") && password != null && password.equals("123456")) {
			
			System.out.print("��¼�������ɹ�l ��");
//			msg = "http://imgstore04.cdn.sogou.com/app/a/100520024/877e990117d6a7ebc68f46c5e76fc47a";
//			out.write(msg);
			BufferedReader br = request.getReader();
			
			String str1 = null;
			StringBuilder resource = new StringBuilder();
			while ((str1 = br.readLine()) != null) // �ж����һ�в����ڣ�Ϊ�ս���ѭ��
			{
				resource.append(str1);
				System.out.println(str1);// ԭ���������������
			}

			User u = CreateJson.getUser(resource.toString());
			if (u.getState().equals("login")) {                 // ��¼
				String UserId = d.login(u.getUserName(), u.getUserPassword());
				String jsonUser="";
				if(UserId.equals("")){
					System.out.println("�������");
					msg=UserId;//��ֵ
					User u2=new User();//����һ���յ�User������ͻ���
					u2.setState("false");
					u2.setUserID(" ");
					String userfile=CreateJson.getUserJson(u2);
					System.out.println("�e�`�Ñ���Ϣ��"+userfile);
					out.write(userfile);
				}else{
				System.out.println("������ȷ �û�ID" + UserId);
				User u6=d.showUser(UserId.trim());  //��ȡ��ӦID��User
				u6.setState("true");
				System.out.println("name"+u6.getUserName());
				System.out.println("name"+u6.toString());
				 jsonUser=CreateJson.getUserJson(u6);
//				System.out.println(jsonUser.toString());
//				msg=jsonUser.toString().trim();
				msg=u6.toString();
				out.write(msg);  //��¼�����û�����User������Ϣ
				//out.println(msg);
				}
				
			} else if (u.getState().equals("register")) {     // ע��
				String userID = d.register(u);
				System.out.println("ע�������" + userID);
				if (!userID.equals("")) {// ���ע��ɹ�
					//msg = u.getUserID(); //ע��ɹ�����UserId 
					u.setState("true");
					String userfile=CreateJson.getUserJson(u);
					out.write(userfile);
				} else {
					msg = "false";       //ע��ʧ�ܻ���
					out.write(msg);
				}
			} else if (u.getState().equals("update")) {        // �����û�
				/*
				 * String UserId=d.getUserID(u.getUserTel());
				 * System.out.println("��ѯ�û�id"+UserId);
				 */
				d.updateUser(u);
				System.out.println("�û����գ�" + u.getUserBirthday().toLocaleString());
			}else if(u.getState().equals("request  ")){       //��������
				 
				
			}else if(u.getState().equals("mark")){             //���ǩ
				User user=d.showUser(u.getUserID());
				Picture p=CreateJson.getPicture(resource.toString());
				user.getPictures().add(p);
			}

		}else{
			System.out.println("���������ʧ��");
			msg = "";       //ע��ʧ�ܻ���
			out.write(msg);
		}
		// out.print(msg); //���͸��ͻ�������

		out.flush();
		out.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}