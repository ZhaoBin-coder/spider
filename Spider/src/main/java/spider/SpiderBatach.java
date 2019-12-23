package spider;

import bean.UniversityInfo;
import com.google.gson.Gson;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import utils.HttpClientUtils;
import utils.JdbcUtils;
import utils.MysqlUtils;


import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SpiderBatach{
    private static final ExecutorService executorService = Executors.newCachedThreadPool();
    private static final BlockingQueue<UniversityInfo> queuePhone = new ArrayBlockingQueue<UniversityInfo>(100);
    public void start() throws IOException, SQLException, PropertyVetoException {

        String sql1 = "select cid from university";
        QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
        List<Map<String, Object>> list = queryRunner.query(sql1, new MapListHandler());
        for (Map<String, Object> stringObjectMap : list) {

            String res = stringObjectMap.toString();
            int start = res.indexOf("=");
            int end=res.indexOf("}");
            String result = res.substring(start + 1, end);

            String url1="https://www.baokaodaxue.com/bkdx/college/fenshu?wl=1&cid="+result+"&type=0&special=0&limit=3";

            String json = HttpClientUtils.doGet(url1);
            parseJson(json,result);
        }
    }
    public void parseJson(String json,String result) throws PropertyVetoException {


        List<UniversityInfo> universityList = new ArrayList<>();
        Gson gson = new Gson();
        Map map = gson.fromJson(json, Map.class);
        ArrayList<Map> dataList = (ArrayList<Map>) map.get("extraData");
        for (Map obj : dataList) {
            String cid = result;
            String year = (String) obj.get("year");
            String batch = (String) obj.get("pici");
            String up = (String) obj.get("gaofen");
            String low = (String) obj.get("difen");
            String avg = (String) obj.get("pjfen");
            Double difvalue = (Double) obj.get("xc");
            String lowlevel = (String) obj.get("zdwc");
            UniversityInfo universityInfo = new UniversityInfo();
            universityInfo.setCid(cid);
            universityInfo.setYear(year);
            universityInfo.setBatch(batch);
            universityInfo.setUp(up);
            universityInfo.setLow(low);
            universityInfo.setAvg(avg);
            universityInfo.setDifvalue(difvalue);
            universityInfo.setLowlevel(lowlevel);
            universityList.add(universityInfo);

        }
        MysqlUtils.SaveData(universityList);
    }

}