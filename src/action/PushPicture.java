package action;



import java.util.List;

import org.hibernate.Session;

import hibernateutil.SessionAnnotation;

public class PushPicture {//����ͼƬ
	
	public static String getUserHM(String UserId){
		
		Session session=SessionAnnotation.getSession();
		session.beginTransaction();
		StringBuffer jsonPicture=new StringBuffer();
		jsonPicture.append("");
		String sql="select PID from Mark where USERID ='"+UserId+"'";
		List list=session.createQuery(sql).list();
		if(list.size()==0||list.size()<=5) {  //���Ñ�����������������ͣ��������
			//SELECT * FROM T_USER  ORDER BY  RAND() LIMIT 10  �������10����¼
			String sql2="select PID,PAddress from Picture  ORDER BY RAND()��LIMIT 20"; //�����ݿ������ȡ20��ͼƬ��Ϣ
			list=session.createQuery(sql2).list();
			System.out.println(list.toString());
			
			session.getTransaction().commit();
			SessionAnnotation.closeSession();
		}else{ //����û��������
			
			System.out.println("��������");
			
		}
		
		
		return jsonPicture.toString();
	}
	

}
