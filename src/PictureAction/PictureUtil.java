package PictureAction;

import java.nio.file.Path;
import java.util.Date;

import org.hibernate.Session;

import hibernateutil.SessionAnnotation;
import model.Picture;
import util.RandomString;

public class PictureUtil {  //保存图片到数据库

	public void savePicture2DB(Path p){
		Session session=SessionAnnotation.getSession();
		session.beginTransaction();
		Picture picture=new Picture();
		picture.setPID(new Date()+RandomString.getRandomString(5));
		picture.setPName(p.getFileName().toString());
		picture.setPAddress(p.toString());
		session.getTransaction().commit();
		SessionAnnotation.closeSession();
	}
	
}
