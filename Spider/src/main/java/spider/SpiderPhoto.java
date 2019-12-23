package spider;

import bean.UniversityPicture;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.HttpClientUtils;
import utils.JdbcUtils;
import utils.Picture2Mysql;
import java.beans.PropertyVetoException;
import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SpiderPhoto {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();
 public void start() throws IOException, SQLException, PropertyVetoException {
     int x=0;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String sql1 = "select name,cid from university";
        QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
        List<Map<String, Object>> list = queryRunner.query(sql1, new MapListHandler());

        for (Map<String, Object> stringObjectMap : list) {
                   x+=1;
            System.out.println("第"+x+"所学校");
                String name= (String) stringObjectMap.get("name");
                String cid= (String) stringObjectMap.get("cid");

                String url="https://www.baokaodaxue.com/bkdx/college/photos?cid="+cid;

                String html = HttpClientUtils.doGet(url);
                Document document = Jsoup.parse(html);
                Elements elements = document.select(".view-picture-wrap>ul>li");

                    for(int i=1;i<elements.size();i++){
                        UniversityPicture pic=new UniversityPicture();
                            Element li = elements.get(i);
                            String url1 = li.select("a").attr("href");

                            HttpGet PicturehttpGet = new HttpGet(url1);
                            CloseableHttpResponse pictureResponse = httpclient.execute(PicturehttpGet);
                            HttpEntity pictureEntity = pictureResponse.getEntity();
                            InputStream inputStream = pictureEntity.getContent();

                            // 使用 common-io 下载图片到本地，注意图片名不能重复 ✔
                            FileUtils.copyToFile(inputStream, new File("F://img//" +cid+"//"+ cid + "_" + i + ".jpg"));
                            pictureResponse.close(); // pictureResponse关闭
                            pic.setCid(cid);
                            pic.setPicture("F://img//" +cid+"//"+ cid + "_" + i + ".jpg");
                        Picture2Mysql.SaveData(pic);
                        }
                    }
                    httpclient.close(); // httpClient关闭
                }
        }











