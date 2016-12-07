import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.kaishengit.entity.User;

public class Test {

	public static void main(String[] args) throws IOException{
		
		/*//�������Է�������Ŀͻ���
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//����һ��Get����ʽ
		HttpGet httpGet = new HttpGet("http://www.kaishengit.com");
		//�������󲢽��ܷ���˵���Ӧ
		HttpResponse response = httpClient.execute(httpGet);
		
		//��ȡHttp��Ӧ��״̬��
		int statusCode = response.getStatusLine().getStatusCode();
		
		if(statusCode==200){
			//��ȡ��Ӧ������
			InputStream inputstream = response.getEntity().getContent();
			
			String result = IOUtils.toString(inputstream,"UTF-8");
			
			inputstream.close();
			
			System.out.println(result);
			
		}else{
			  System.out.println("�������쳣:" + statusCode);
		}
			*/
		
		/*CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpGet httpGet = new HttpGet("http://ww1.sinaimg.cn/mw690/824de770jw1epwcnivby6j20go0p00x4.jpg");
		
		HttpResponse response = httpClient.execute(httpGet);
		if(response.getStatusLine().getStatusCode()==200){
			InputStream inputstream = response.getEntity().getContent();
			OutputStream outputStream = new FileOutputStream("F:/222.jpg");
			
			outputStream.flush();
			outputStream.close();
			inputstream.close();
			
		}
		httpClient.close();*/
		
		/*CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpPost httpPost = new HttpPost("http://localhost:80/save");
		 List<NameValuePair> params = new ArrayList<>();
		 params.add(new BasicNameValuePair("username", "kobe"));
		 params.add(new BasicNameValuePair("address", "USA"));
		 httpPost.setEntity(new UrlEncodedFormEntity(params));
		 
		 for(int i = 0;i < 5;i++){
			 HttpResponse resp = httpClient.execute(httpPost);
			 //System.out.println(resp.getStatusLine());
		 }*/
		 
		User user = new User(1,"�Ʊ�","��ɼ�");

        String json = new Gson().toJson(user);
        System.out.println(json);
		 
		
		
	}
}
