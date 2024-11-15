package Task_K;

import Tasks_I.JDBCParent;

import java.sql.*;

public class Soru_01_ extends JDBCParent {

    public static void main(String[] args) throws SQLException {
        DBConnectionOpen();
        String sql = "SELECT e.emp_no, e.first_name, e.last_name, de.dept_no " +
                "FROM dept_emp de " +
                "JOIN employees e ON de.emp_no = e.emp_no " +
                "WHERE de.dept_no = 'd001'";




            ResultSet resultSet = statement.executeQuery(sql);


            while (resultSet.next()) {
                int empNo = resultSet.getInt("emp_no");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String deptNo = resultSet.getString("dept_no");

                System.out.println("Employee No: " + empNo + ", First Name: " + firstName +
                        ", Last Name: " + lastName + ", Dept No: " + deptNo);
            }
        

            DBConnectionClose();








    }
}
