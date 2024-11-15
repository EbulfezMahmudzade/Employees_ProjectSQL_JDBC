package Task_K;

import Tasks_I.JDBCParent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Soru_10_ extends JDBCParent {
    public static void main(String[] args) throws SQLException {
        DBConnectionOpen();
        String sql = "SELECT * FROM employees.salaries WHERE emp_no LIKE '10102'";

        ResultSet rs = statement.executeQuery(sql);


        while (rs.next()) {
            int empNo = rs.getInt("emp_no");
            int salary = rs.getInt("salary");
            String fromDate = rs.getString("from_date");
            String toDate = rs.getString("to_date");


            System.out.println("Emp No: " + empNo + ", Salary: " + salary +
                    ", From Date: " + fromDate + ", To Date: " + toDate);
        }
        DBConnectionClose();
    }
}
