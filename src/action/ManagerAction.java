package action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import jsonUtil.CreateJson;
import model.Image;
import model.ImageList;

public class ManagerAction {
	public static boolean SaveImage(ImageList imagelist){
		List files=imagelist.getList();//�õ�ͼƬ�ļ��Ķ�����
		if(files.size()==0){
			return false;
		}else{
			for(int i=0;i<files.size();i++){//ȡ������ͼƬ�������ļ����浽������
				Image image=CreateJson.getImage(files.get(i).toString());
				System.out.println(image.getImagefile());
				//ִ�б���
				int a=saveToImgByStr(image.getImagefile(),"C:\\nginx\\html",new Date()+""+i+".jpg");
				if(a==1){ continue;
				}else{ return false;}//�����쳣
			}
			return true;
		}
		
	}
	
	
    /**
     * �����յ��ַ���ת����ͼƬ����
     * @param imgStr ��������ת�����ַ���
     * @param imgPath ͼƬ�ı���·��
     * @param imgName ͼƬ������
     * @return 
     *      1����������
     *      0������ʧ��
     */
    public static int saveToImgByStr(String imgStr,String imgPath,String imgName){
try {
    System.out.println("===imgStr.length()====>" + imgStr.length()
            + "=====imgStr=====>" + imgStr);
} catch (Exception e) {
    e.printStackTrace();
}
        int stateInt = 1;
        if(imgStr != null && imgStr.length() > 0){
            try {
                 
                // ���ַ���ת���ɶ����ƣ�������ʾͼƬ  
                // ���������ɵ�ͼƬ��ʽ�ַ��� imgStr����ԭ��ͼƬ��ʾ  
                byte[] imgByte = hex2byte( imgStr );  
     
                InputStream in = new ByteArrayInputStream(imgByte);
     
                File file=new File(imgPath,imgName);//�������κ�ͼƬ��ʽ.jpg,.png��
                FileOutputStream fos=new FileOutputStream(file);
                   
                byte[] b = new byte[1024];
                int nRead = 0;
                while ((nRead = in.read(b)) != -1) {
                    fos.write(b, 0, nRead);
                }
                fos.flush();
                fos.close();
                in.close();
     
            } catch (Exception e) {
                stateInt = 0;
                e.printStackTrace();
            } finally {
            }
        }
        return stateInt;
    }
     
    /**
     * ��������ת����ͼƬ����
     * @param imgStr ��������ת�����ַ���
     * @param imgPath ͼƬ�ı���·��
     * @param imgName ͼƬ������
     * @return 
     *      1����������
     *      0������ʧ��
     */
    public static int saveToImgByBytes(File imgFile,String imgPath,String imgName){
 
        int stateInt = 1;
        if(imgFile.length() > 0){  
            try {
                File file=new File(imgPath,imgName);//�������κ�ͼƬ��ʽ.jpg,.png��
                FileOutputStream fos=new FileOutputStream(file);
                 
                FileInputStream fis = new FileInputStream(imgFile);
                   
                byte[] b = new byte[1024];
                int nRead = 0;
                while ((nRead = fis.read(b)) != -1) {
                    fos.write(b, 0, nRead);
                }
                fos.flush();
                fos.close();
                fis.close();
     
            } catch (Exception e) {
                stateInt = 0;
                e.printStackTrace();
            } finally {
            }
        }
        return stateInt;
    }
 
    /**
     * ������ת�ַ���
     * @param b
     * @return
     */
    public static String byte2hex(byte[] b) // ������ת�ַ���
    {
        StringBuffer sb = new StringBuffer();
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1) {
                sb.append("0" + stmp);
            } else {
                sb.append(stmp);
            }
 
        }
        return sb.toString();
    }
 
    /**
     * �ַ���ת������
     * @param str Ҫת�����ַ���
     * @return  ת����Ķ���������
     */
    public static byte[] hex2byte(String str) { // �ַ���ת������
        if (str == null)
            return null;
        str = str.trim();
        int len = str.length();
        if (len == 0 || len % 2 == 1)
            return null;
        byte[] b = new byte[len / 2];
        try {
            for (int i = 0; i < str.length(); i += 2) {
                b[i / 2] = (byte) Integer
                        .decode("0X" + str.substring(i, i + 2)).intValue();
            }
            return b;
        } catch (Exception e) {
            return null;
        }
    }

}
