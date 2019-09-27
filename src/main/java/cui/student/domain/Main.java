package cui.student.domain;

import cui.student.util.DBHelper;
import cui.student.util.FileReading;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        DBHelper dbHelper=new DBHelper();
        FileReading fileReading=new FileReading();
        Scanner love=new Scanner(System.in);
        int count=0;
        //录入数据
        Set<String> dates=fileReading.getFileFields(new File(Thread.currentThread().getClass().getResource("/date.txt").getPath()));
        String [] date=new String[dates.size()];
        date=dates.toArray(new String[dates.size()]);
        //后续维护SQL语句
        String [] sqlMaintenance =new String[]{
                "update standard_baseinfogov_miningtype a set a.one_code= ? where a.one_code is Null",
                "update standard_baseinfogov_miningtype a set a.one_name= ? where a.one_code= ? ",
                "update standard_baseinfogov_miningtype a set a.remarks='开采矿种'",
                "update standard_baseinfogov_miningtype a set a.company_id=2 where a.company_id is Null",
                "update standard_baseinfogov_miningtype a set a.created_time=now() where a.created_time is Null",
                "update standard_baseinfogov_miningtype a set a.creator='test' where a.creator is Null",
                "update standard_baseinfogov_miningtype a set a.creator_id=681 where a.creator_id is Null",
                "update standard_baseinfogov_miningtype a set a.creator_name='test' where a.creator_name is NULL",
                "update standard_baseinfogov_miningtype a set a.department_id=139 where a.department_id is Null",
                "update standard_baseinfogov_miningtype a set a.modified_time=created_time where a.modified_time is Null",
                "update standard_baseinfogov_miningtype a set a.modifier='test' where a.modifier is Null",
                "update standard_baseinfogov_miningtype a set a.modifier_name='test' where a.modifier_name is Null",
                "update standard_baseinfogov_miningtype a set a.department_name='dev' where a.department_name is Null",
        };
        PreparedStatement ps=null;
        Connection connection=null;
        try {
            connection=dbHelper.getConnection();
            ps=connection.prepareStatement("insert into standard_baseinfogov_miningtype (two_code,two_name) values (?,?)");
            for(int i=1;i<=date.length;i++){
                if (i<10){
                    ps.setString(1,"01100"+(i));
                    ps.setString(2,date[i-1]);
                    count+=ps.executeUpdate();
                }else if(i>=10&&i<100){
                    ps.setString(1,"0110"+(i));
                    ps.setString(2,date[i-1]);
                    count+=ps.executeUpdate();
                }else if(i>=100&&i<1000){
                    ps.setString(1,"011"+(i));
                    ps.setString(2,date[i-1]);
                    count+=ps.executeUpdate();
                }
            }
            ps.clearBatch();
            ps.clearParameters();
            System.out.println("录入数据:"+count);
            System.out.println("执行表维护......");
            String oneCode="";
            String oneName="";
            for (int i=0;i<sqlMaintenance.length;i++){
                if (i==0){
                    System.out.println("one_code");
                    oneCode=love.nextLine();
                    ps=connection.prepareStatement(sqlMaintenance[i]);
                    ps.setString(1,oneCode);
                    ps.executeUpdate();
                }else if (i==1){
                    System.out.println("one_name");
                    oneName=love.nextLine();
                    ps=connection.prepareStatement(sqlMaintenance[i]);
                    ps.setString(1,oneName);
                    ps.setString(2,oneCode);
                    ps.executeUpdate();
                }
                ps.executeUpdate(sqlMaintenance[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            love.close();
        }
    }
}
