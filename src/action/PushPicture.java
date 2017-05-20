package action;



import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import hibernateutil.SessionAnnotation;
import jsonUtil.CreateJson;
import model.Mark;
import model.Picture;
public class PushPicture {//����ͼƬ
	
	public static String getPush(String UserId){ //��¼һ������ʮ��
		
		Session session=SessionAnnotation.getSession();
		session.beginTransaction();
		StringBuffer jsonPicture=new StringBuffer();
		//jsonPicture.append("");
		//String sql="select PID from Mark where USERID ='"+UserId+"'";
		String hql="from Mark where UserId='"+UserId.trim()+"'";//+UserId+"'";
		Query query=session.createQuery(hql);
		//query.setParameter(0,UserId);
		//query.setString(0,UserId);
		List list=query.list();
		System.out.println("��ʷ��¼��Ŀ��"+list.size()+"  "+list.toString());
		//List list=session.createQuery(sql).list();
		if(list.size()<=5) {  //���Ñ�����������������ͣ��������
			//SELECT * FROM T_USER  ORDER BY  RAND() LIMIT 10  �������10����¼
			
			//String sql2="select PID,PAddress from Picture  ORDER BY RAND()"; //�����ݿ������ȡ20��ͼƬ��Ϣ
			String hql2="from Picture where FinalMarkName is null order by rand()";//ֻ��û��ȷ�����ձ�ǩ��ͼƬ��Ҫ����  where FinalMarkName is null
			Query q=session.createQuery(hql2);
			q.setFirstResult(0);  // �ӵ�0����¼��ʼȡ 
		      q.setMaxResults(12); // ȡ20����¼
			List<Picture> pictures=q.list();
			/*for(Picture p:pictures){
				String s=CreateJson.getPictureJson(p);
			
				jsonPicture.append(s+",");
			}*/
			for(int i=0;i<pictures.size();i++){ 
				if(i<(pictures.size()-1)){  
					String s=CreateJson.getPictureJson(pictures.get(i));
					jsonPicture.append(s+",");
				}else{
					String s=CreateJson.getPictureJson(pictures.get(i));
					jsonPicture.append(s);
				}
			}
			session.getTransaction().commit(); 
			SessionAnnotation.closeSession(); 
			return jsonPicture.toString();
		}else{ //����û��������
			//�㷨�Ƽ�
			
			
			System.out.println("�������������"); 
			SessionAnnotation.closeSession();
			return jsonPicture.toString();
		}
	}
	
	
public static String getSinglePush(String UserId){ //����һ��
		
		Session session=SessionAnnotation.getSession();
		session.beginTransaction();
		StringBuffer jsonPicture=new StringBuffer();
		//jsonPicture.append("");
		//String sql="select PID from Mark where USERID ='"+UserId+"'";
		String hql="from Mark where UserId=?";//+UserId+"'";
		Query query=session.createQuery(hql);
		//query.setParameter(0,UserId);
		query.setString(0,UserId);
		List list=query.list();
		System.out.println("��ʷ��¼��Ŀ��"+list.size()+"  "+list.toString());
		//List list=session.createQuery(sql).list();
		if(list.size()==0||list.size()<=5) {  //���Ñ�����������������ͣ��������
			//SELECT * FROM T_USER  ORDER BY  RAND() LIMIT 10  �������10����¼
			
			//String sql2="select PID,PAddress from Picture  ORDER BY RAND()"; //�����ݿ������ȡ20��ͼƬ��Ϣ
			String hql2="from Picture where FinalMarkName is null order by rand()";//ֻ��û��ȷ�����ձ�ǩ��ͼƬ��Ҫ����  where FinalMarkName is null
			Query q=session.createQuery(hql2);
			q.setFirstResult(0);  // �ӵ�0����¼��ʼȡ
		      q.setMaxResults(1); // ȡ20����¼
			List<Picture> pictures=q.list();
			/*for(Picture p:pictures){
				String s=CreateJson.getPictureJson(p);
				jsonPicture.append(s+",");
			}*/
			for(int i=0;i<pictures.size();i++){ 
				if(i<(pictures.size()-1)){  
					String s=CreateJson.getPictureJson(pictures.get(i));
					jsonPicture.append(s+",");
				}else{
					String s=CreateJson.getPictureJson(pictures.get(i));
					jsonPicture.append(s);
				}
			}
			session.getTransaction().commit(); 
			SessionAnnotation.closeSession(); 
			return jsonPicture.toString();
		}else{ //����û��������
			//�㷨�Ƽ�
			
			
			
			System.out.println("��������"); 
			SessionAnnotation.closeSession();
			return jsonPicture.toString();
		}
	}


}
