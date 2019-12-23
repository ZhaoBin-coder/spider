package utils;

import bean.UniversityInfo;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.jdbc.core.JdbcTemplate;


import java.beans.PropertyVetoException;
import java.util.List;


public class MysqlUtils {

        public static void SaveData(List<UniversityInfo> universityList) throws PropertyVetoException {
            ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
            comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
            comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/spider?characterEncoding=utf8");
            comboPooledDataSource.setUser("root");
            comboPooledDataSource.setPassword("123456");
            JdbcTemplate jdbcTemplate = new JdbcTemplate(comboPooledDataSource);
            String sql = "insert into universityinfo(cid,year,batch,up,low,avg,difvalue,lowlevel) values(?,?,?,?,?,?,?,?)";

            if (universityList != null && universityList.size() > 0) {
                for (int i = 0; i < universityList.size(); i++) {
                    UniversityInfo info = universityList.get(i);
                    // 保存数据库
                    jdbcTemplate.update(sql,info.getCid(),info.getYear(),info.getUp(),info.getLow(),info.getAvg(),info.getDifvalue(),info.getLowlevel());

                }
            }
        }
    }



