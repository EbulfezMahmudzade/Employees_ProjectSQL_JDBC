package Tasks_I;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class _15_SQL extends JDBCParent{
    public static void main(String[] args) throws SQLException {
        DBConnectionOpen();

        ResultSet rs = statement.executeQuery("SELECT employees.first_name, employees.last_name, AVG(salaries.salary) AS avg_salary" +
                "FROM employees" +
                "JOIN salaries ON employees.emp_no = salaries.emp_no" +
                "JOIN dept_emp ON employees.emp_no = dept_emp.emp_no" +
                "JOIN departments ON dept_emp.dept_no = departments.dept_no" +
                "WHERE departments.dept_name = 'Research'" +
                "GROUP BY employees.emp_no" +
                "ORDER BY avg_salary DESC" +
                "LIMIT 1");
        ResultSetMetaData rsmd=rs.getMetaData();
        // Başlık
        int kolonSayisi= rsmd.getColumnCount();
        for (int i = 1; i <= kolonSayisi; i++)
            System.out.print(rsmd.getColumnName(i)+"\t");

        System.out.println();

        while (rs.next())
        {
            for (int i = 1; i <= kolonSayisi; i++)
                System.out.print(rs.getString(i)+"\t");

            System.out.println();
        }
        DBConnectionClose();
    }
}
