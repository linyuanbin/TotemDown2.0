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
			
			if(u.getState().trim().equals("mark")) System.out.println("��ǩ");
			
			if ((u.getState().trim()).equals("login")) {                 // ��¼
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
				out.write(jsonUser);  //��¼�����û�����User������Ϣ
				//out.println(msg);
				}
				
			} else if ((u.getState().trim()).equals("register")) {     // ע��
				String userID = d.register(u);
				System.out.println("ע�������" + userID);
				if ((!userID.equals(""))) {// ���ע��ɹ�
					//msg = u.getUserID(); //ע��ɹ�����UserId 
					u.setState("true");
					String userfile=CreateJson.getUserJson(u);
					System.out.println("ע��ɹ��û���Ϣ��"+userfile);
					out.write(userfile);
				} else {
					//msg = "false";       //ע��ʧ�ܻ���
					u.setState("false");
					String s=CreateJson.getUserJson(u);
					System.out.println("ע��ʧ���û���Ϣ��"+s); 
					out.write(s);
				}
			} else if ((u.getState().trim()).equals("update")) {        // �����û�
				/*
				 * String UserId=d.getUserID(u.getUserTel());
				 * System.out.println("��ѯ�û�id"+UserId);
				 */
				d.updateUser(u);
				System.out.println("�û����գ�" + u.getUserBirthday().toLocaleString());
			}else if((u.getState().trim()).equals("request".trim())){       //��������
				 String pushPictureJson=PushPicture.getPush(u.getUserID());
				 pushPictureJson="["+pushPictureJson+"]";
				 System.out.println("���ͣ�"+pushPictureJson); 
				 out.write(pushPictureJson);
			}else if((u.getState().trim()).equals("request1".trim())){       //����һ��
				 String pushPictureJson=PushPicture.getSinglePush(u.getUserID());
				 pushPictureJson="["+pushPictureJson+"]";
				 System.out.println("���ͣ�"+pushPictureJson); 
				 out.write(pushPictureJson);
			}else if(u.getState().trim().equals("mark".trim())){ //���ǩ
				System.out.println("���ǩģ��");
				User user=d.showUser(u.getUserID().trim()); 
				System.out.println("���ǩ���Ñ�"+user.getUserName());
				Picture p=pd.selectSinglePictureFID(u.getPID().trim());
				System.out.println("����ǩ��ͼƬ"+p.getPName());
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
//				boolean b=md.insertIntoMark(u.getUserID().trim(),u.getPID().trim(),u.getMarkName().trim()); //�����ǩ
				if(b){//��ǩ�ɹ�
					user.setUserIntegral(user.getUserIntegral()+1); //�ɹ���ǩһ�λ��ּ�һ
					d.updateUser(user);
					User user2=new User();
					user2.setState("true");
					System.out.println("��ǩ�ɹ�");
					String s=CreateJson.getUserJson(user2);
					out.write(s);
				}else{ //��ǩʧ��
					System.out.println("��ǩʧ��");
					User user2=new User();
					user2.setState("false");
					String s=CreateJson.getUserJson(user2);
					out.write(s);
				}//��ǩʧ��
				
			}else{
				System.out.println("û�ҵ�state");
				String str2="500 NotFound the state"; 
				out.write(str2);
			}

		}else{
			System.out.println("���������ʧ��");
			msg = "404 NotFound connect username or password error";       //ע��ʧ�ܻ���
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
