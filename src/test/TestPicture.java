package test;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.hibernate.Session;
import org.junit.Test;

import PictureAction.SearchPicture;
import dao.PictureDao;
import daoimplement.PictureImplement;
import hibernateutil.SessionAnnotation;
import model.Picture;

public class TestPicture {

	private static PictureDao pd = new PictureImplement();
	//private String mPath = "F:\\picture\\photo";// \\����
	private String mPath="C:\\nginx\\html\\photo";

	@Test
	public void testPicture() { // �����ļ���������
		// +"Pictures");
		File file = new File("D:\\Mypicture");
		String writePath = "D:\\Mypicture\\";
		String readPath = "C:\\Users\\lin\\Pictures\\";
	/*	Vector<String> pictures = SearchPicture.SearchPName("F:\\picture\\photo");// ("c:"+System.getProperty("file.separator")
		for (String fileName : pictures) { // �����fileName��ʵ�Ǿ���·��
			File f = new File(fileName.toString());
			System.out.println(fileName);
			// save(readPath + fileName, writePath + fileName);
		}*/
		
		/*Vector<String> picturesPath = SearchPicture.SearchPPath("F:\\picture\\photo");
		for (String fileName : picturesPath) { // �����fileName��ʵ�Ǿ���·��
			File f = new File(fileName.toString());
			System.out.println(fileName);
			// save(readPath + fileName, writePath + fileName);
		}*/
	}

	@Test   //������ͼƬ���ص����ݿ�
	public void testAdd() throws IOException { // ���Ի�ȡ��������

		DirectoryStream<Path> paths = SearchPicture.GetPath(mPath);
		
		Picture picture=new Picture();
		for (Path p : paths) {
			System.out.println(p.toString() + "-----" + p.getFileName().toString());
			picture.setPName(p.getFileName().toString());
			picture.setPAddress("http://192.168.0.18/photo/"+p.getFileName());
			try{
			pd.AddPicture(picture);
			
			}catch(Exception e){
				System.out.println(e);
			}
		}
	}

	/*@Test
	public void testGetAllFile() throws IOException { // �ӷ�������ȡ

		DirectoryStream<Path> paths = SearchPicture.getAllfileFList(mPath);
		System.out.println("��ʾͼƬscssccscs��");

		for (Path p : paths) {
			System.out.println(p.toString() + "-----" + p.getFileName().toString());

		}
	}
*/
	@Test
	public void testGetAPicture() { // �����ݿ���

		/*Set<Picture> Pictures = new HashSet<Picture>();
		System.out.println("����");
		Pictures = pd.selectAllPicture();
		System.out.println("------picture" + Pictures);
		for (Picture p : Pictures) {
			System.out.println(p.getPName());
		}*/
		 
//		Picture p=pd.selectSinglePictureFN("����");
//		System.out.println(p.getPAddress());

		/*Set<Picture> Pictures = new HashSet<Picture>(); //�������Ʋ��� �ɹ�
		System.out.println("����");
		Pictures = pd.selectPicturesFN("����");
		System.out.println("------picture" + Pictures);
		for (Picture p : Pictures) {
			System.out.println(p.getPName());
		}*/
		
		/*
		 * Session s = SessionAnnotation.getSession(); s.beginTransaction();
		 * String sql = "select PID from Picture"; List<String> list =
		 * (List<String>) s.createQuery(sql).list();
		 * s.getTransaction().commit(); for (String li : list) {
		 * s.beginTransaction(); Picture p = (Picture) s.get(Picture.class, li);
		 * s.getTransaction().commit(); System.out.println(p.getPName());
		 * System.out.println(li); }
		 */

	}
	
	@Test
	public void selectAllPictureOnDB(){
		Set<Picture> pictures= pd.selectPicturesFN("��"); //pd.selectAllPicture();
		for(Picture p:pictures){
			System.out.println(p.getPAddress());
		}
	}
	

}