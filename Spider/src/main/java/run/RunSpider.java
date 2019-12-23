package run;

import spider.SpiderPhoto;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

public class RunSpider {
    public static void main(String[] args) throws PropertyVetoException, SQLException, IOException {
        long start = System.currentTimeMillis();
        SpiderPhoto spider = new SpiderPhoto();
        spider.start();
        long end = System.currentTimeMillis();
        System.out.println("抓取完毕并到磁盘用时:" + ((double)(end-start))/1000.00 + "s");
    }
}
