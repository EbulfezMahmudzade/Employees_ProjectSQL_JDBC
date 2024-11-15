package Task_K;

import Tasks_I.JDBCParent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Soru_08_ extends JDBCParent {
    public static void main(String[] args) throws SQLException {
        DBConnectionOpen();

        String sql = "SELECT AVG(salary) AS AverageSalary, dept_emp.dept_no AS DepartmentNo " +
                "FROM salaries " +
                "LEFT JOIN dept_emp ON dept_emp.emp_no = salaries.emp_no " +
                "GROUP BY dept_no";

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            double averageSalary = rs.getDouble("AverageSalary");
            String departmentNo = rs.getString("DepartmentNo");


            System.out.println("Department No: " + departmentNo + ", Average Salary: " + averageSalary);
        }

        DBConnectionClose();
    }
}
