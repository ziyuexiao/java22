import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lenovo on 2016/12/20.
 */
public class Test2 {
    public static void main(String[] args) throws IOException {

        /*Document document = Jsoup.connect("http://www.topit.me")
                .cookie("is_click","1")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.75 Safari/537.36")
                .get();

        Elements elements = document.select("#content .catalog .e > a");//大于号代表直接子元素
        for (int i = 0;i<elements.size();i++){
            Element elements1 = elements.get(i);
            String href = elements1.attr("href");
            System.out.println(href);

            Document bigDocument = Jsoup.connect(href)
                    .cookie("is_click","1")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.75 Safari/537.36")
                    .get();

            Element img = bigDocument.select("#content #item-tip img").first();
            String imgSrc = img.attr("src");

            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("imgSrc");
            HttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode==200){
                InputStream inputStream = response.getEntity().getContent();
                String imgname = imgSrc.substring(imgSrc.lastIndexOf("/"));
                FileOutputStream outputStream = new FileOutputStream("F:/img/"+imgname);

                IOUtils.copy(inputStream,outputStream);
                outputStream.flush();
                outputStream.close();
                inputStream.close();

            } else {
                System.out.println("ERROR:" + response.getStatusLine().getStatusCode());
            }


        }*/

        /*for (int j = 1;j < 3;j++) {
            Document document = Jsoup
                    .connect("http://www.topit.me/?p="+j)
                    .cookie("is_click","1")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.75 Safari/537.36")
                    .get();

            Elements  elements = document.select("#content .catalog .e > a");

            for(int i = 0;i < elements.size();i++) {
                Element element = elements.get(i);
                String href = element.attr("href");

                System.out.println(href);

                Document bigDocument = Jsoup.connect(href)
                        .cookie("is_click","1")
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.75 Safari/537.36")
                        .get();
                Element img = bigDocument.select("#content #item-tip img").first();
                String imgSrc = img.attr("src");

                CloseableHttpClient httpClient = HttpClients.createDefault();
                HttpGet httpGet = new HttpGet(imgSrc);
                HttpResponse response = httpClient.execute(httpGet);
                if(response.getStatusLine().getStatusCode() == 200) {
                    InputStream inputStream = response.getEntity().getContent();
                    String imageName = imgSrc.substring(imgSrc.lastIndexOf("/"));
                    FileOutputStream outputStream = new FileOutputStream("F:/img/"+imageName);
                    IOUtils.copy(inputStream,outputStream);
                    outputStream.flush();
                    outputStream.close();
                    inputStream.close();
                } else {
                    System.out.println("ERROR:" + response.getStatusLine().getStatusCode());
                }

            }
        }*/




    }
}
