package utils;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JdbcUtils {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    private static DruidDataSource dataSource = new DruidDataSource();;
    static {
        try {
            InputStream is = new FileInputStream("F:\\spiderWorkspace\\Spider\\src\\main\\resources\\jdbcconfig.properties");
            Properties properties = new Properties();
            properties.load(is);
            driver = properties.getProperty("jdbc.driver");
            url = properties.getProperty("jdbc.url");
            username = properties.getProperty("jdbc.username");
            password = properties.getProperty("jdbc.password");
        } catch (FileNotFoundException e) {
            System.out.println("配置文件不存在");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("配置文件有误");
            System.exit(0);
        }
    }
    public static DataSource getDataSource(){
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
