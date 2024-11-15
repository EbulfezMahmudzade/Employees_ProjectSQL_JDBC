package Task_K;

import Tasks_I.JDBCParent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Soru_05_ extends JDBCParent {

    public static void main(String[] args) throws SQLException {
        DBConnectionOpen();
        String sql="SELECT AVG(s.salary) AS OdemeOrtalamasi " +
                "FROM employees " +
                "JOIN salaries s ON employees.emp_no = s.emp_no " +
                "WHERE employees.gender = 'F'";

        ResultSet rs= statement.executeQuery(sql);
        if (rs.next()) {

            double averageSalary = rs.getDouble("OdemeOrtalamasi");
            System.out.println("Ortalama Kadın Çalışan Maaşı " + averageSalary);




        }


        DBConnectionClose();
    }
}
