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
			
			Manager m=CreateJson.getManager(sb.toString().trim());
			System.out.println("����Ա״̬"+m.getState());
			System.out.println(m.getState());
			if(m.getState().equals("register")){ //ע��  ����Աע��ֻ�����ݿ��������Ӧ���֤�ŵĲ�����ע��
				
					if(m.getCheckId().equals("gb10086")){ //ע��У������ȷ
						String mIdCard=md.register(m);
						if(mIdCard.equals("")){ //�û�id������
						m.setState("false");
						String managerJson=CreateJson.getManagerJson(m);
						System.out.println("ע�����ʧ���û���Ϣ��"+managerJson);
						out.write(managerJson);
						}else{//ע��ɹ�
							m.setState("true");
							String managerJson=CreateJson.getManagerJson(m);
							System.out.println("ע��ɹ��û���Ϣ"+managerJson);
							out.write(managerJson);
						}
						
					}else{ //ע��У�������
						m.setState("false");
						String managerJson=CreateJson.getManagerJson(m);
						System.out.println("У�������ע��ʧ���û���Ϣ��"+managerJson);
						out.write(managerJson);
					}
				
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
					System.out.println("��¼�ɹ�");
					out.write(jsonManager);  //��¼�����û�����User������Ϣ
					//out.println(msg);
					}
			}else if(m.getState().equals("download")){ //������ǩ
				String finalMark=pd.selectPicturesFFN();
				if(finalMark.equals("")){ //�ޱ�ǩ�����
					out.write("");
				}else{
					finalMark="["+finalMark+"]";
					System.out.println("���ͱ�ǩ�������"+finalMark);
					out.write(finalMark);
				}
				
			}else if(m.getState().equals("upload")){ //�ϴ���Ƭ
				System.out.println("uploadִ��");
				ImageList imagelist=CreateJson.getImageList(sb.toString().trim());
				boolean b=ManagerAction.SaveImage(imagelist);
				if(b){ //ͼƬ����ɹ�
					Manager ma=new Manager();
					ma.setState("true");
					String majson=CreateJson.getManagerJson(ma);
					out.write(majson);
				}else{//ͼƬ����ʧ��
					Manager ma=new Manager();
					ma.setState("false");
					String majson=CreateJson.getManagerJson(ma);
					out.write(majson);
				}
				
			}else if(m.getState().equals("update")){//���¹���Ա���� 
				boolean state=md.updateManager(m); 
				Manager m2=new Manager();
				String ss;
				if(state){
					ss="true";
					System.out.println("����Ա���³ɹ���");
				}else{
					ss="false";
					System.out.println("����Ա����ʧ�ܣ�");
				}
				m2.setState(ss);
				String managerJson=CreateJson.getManagerJson(m2);
				out.write(managerJson);
			}else{ //û�ҵ���Ӧstate��ֵ
				String str2="500 NotFound! Didn't find the state";
				Manager ma=new Manager();
				ma.setState("false");
				String majson=CreateJson.getManagerJson(ma);
				out.write(majson);
				out.write(str2);
			}
			
		}else{ //���ӷ�����ʧ�ܣ�
			msg="404 Not Found! Connection server failed (User name or password error)"; //���ӷ�����ʧ��
			out.write(msg);
		}
			
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,resp);
	}

}
