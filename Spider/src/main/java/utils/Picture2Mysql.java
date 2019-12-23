package utils;


import bean.UniversityPicture;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import java.beans.PropertyVetoException;

public class Picture2Mysql {
    public static void SaveData(UniversityPicture pic) throws PropertyVetoException {

    ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
            comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
            comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/spider?characterEncoding=utf8");
            comboPooledDataSource.setUser("root");
            comboPooledDataSource.setPassword("123456");
    JdbcTemplate jdbcTemplate = new JdbcTemplate(comboPooledDataSource);
    String sql = "insert into picture(cid,picture) values(?,?)";

            // 保存数据库
            jdbcTemplate.update(sql,pic.getCid(),pic.getPicture());
        comboPooledDataSource.close();
        }

}


