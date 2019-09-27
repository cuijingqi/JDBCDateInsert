package cui.student.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBHelper {
    private static String driver="com.mysql.jdbc.Driver";//数据库驱动
    private static String url="jdbc:mysql://localhost:3306/safetydb652";
    private static String username="root";//数据库名
    private static String password="123456";//数据库密码

    private static Connection conn=null;
    //资源文件
    static Properties pros = null;

    //静态初始化  当加载JDBCUtil类时调用
    static {
        pros = new Properties();
        try {
            //加载资源文件
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
            if(in == null) {
                throw new FileNotFoundException("配置文件未找到");
            }
            pros.load(in);
            driver=pros.getProperty("driver");
            url=pros.getProperty("url");
            username=pros.getProperty("username");
            password=pros.getProperty("password");
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //单例模式返回数据库对象
    public static Connection getConnection() throws Exception{
        if(conn==null){
            conn= DriverManager.getConnection(url, username, password);
            return conn;
        }
        return conn;
    }

}
