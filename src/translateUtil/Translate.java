package translateUtil;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.google.gson.Gson;

public class Translate {
	//在平台申请的APP_ID 详见  http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20170510000047004";
    private static final String SECURITY_KEY = "sntOVNkwNp3e2EjAv0c5";
    
	public static String getTranslate(String content){
		String cn="";
	       TransApi api = new TransApi(APP_ID, SECURITY_KEY);
	        //String query = "高度600米";
	        String query = content; 
	        //String qq="중국";
	       
	        try {
	        	
	        	String s=api.getTransResult(query, "auto", "zh");
				System.out.println(s);   
				Gson gson=new Gson();  
				Baidu b=gson.fromJson(s,Baidu.class); 
				List l=b.getTrans_result();
				Dat d=gson.fromJson(l.get(0).toString(),Dat.class); 
				cn=d.getDst();
				System.out.println("翻译结果?"+cn);
				
				//Data d=gson.fromJson(b.getData().get(0)+"",Data.class);
				//System.out.println(d.getDst());
				return cn;
			} catch (UnsupportedEncodingException e) { 
				e.printStackTrace();
				return "";
			}
		
	}

}
