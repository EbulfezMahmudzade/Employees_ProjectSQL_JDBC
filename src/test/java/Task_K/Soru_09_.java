package Task_K;

import Tasks_I.JDBCParent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Soru_09_ extends JDBCParent {
    public static void main(String[] args) throws SQLException {
        DBConnectionOpen();
        String sql = "SELECT AVG(salaries.salary) AS AverageSalary, departments.dept_name AS DepartmentName " +
                "FROM departments " +
                "LEFT JOIN dept_emp ON departments.dept_no = dept_emp.dept_no " +
                "LEFT JOIN salaries ON dept_emp.emp_no = salaries.emp_no " +
                "GROUP BY departments.dept_no";

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            double averageSalary = rs.getDouble("AverageSalary");
            String departmentName = rs.getString("DepartmentName");


            System.out.println("Department Name: " + departmentName + ", Average Salary: " + averageSalary);
        }
        DBConnectionClose();
    }
}
