package Task_K;

import Tasks_I.JDBCParent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Soru_6_ extends JDBCParent {
    public static void main(String[] args) throws SQLException {
        DBConnectionOpen();
        String sql = "SELECT * " +
                "FROM employees " +
                "JOIN salaries ON employees.emp_no = salaries.emp_no " +
                "JOIN dept_emp ON employees.emp_no = dept_emp.emp_no " +
                "WHERE dept_emp.dept_no = 'd007' " +
                "AND salaries.salary > 70000";

        ResultSet rs = statement.executeQuery(sql);


        while (rs.next()) {
            int empNo = rs.getInt("emp_no");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String deptNo = rs.getString("dept_no");
            int salary = rs.getInt("salary");


            System.out.println("Emp No: " + empNo + ", Name: " + firstName + " " + lastName +
                    ", Dept No: " + deptNo + ", Salary: " + salary);
        }



        DBConnectionClose();
    }
}
