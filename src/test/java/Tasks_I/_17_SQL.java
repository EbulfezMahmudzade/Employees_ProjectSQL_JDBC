package Tasks_I;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class _17_SQL extends JDBCParent{
    public static void main(String[] args) throws SQLException {
        DBConnectionOpen();

        ResultSet rs = statement.executeQuery("SELECT d.dept_name, e.first_name, e.last_name, AVG(s.salary) AS avg_salary" +
                "FROM employees e" +
                "JOIN salaries s ON e.emp_no = s.emp_no" +
                "JOIN dept_emp de ON e.emp_no = de.emp_no" +
                "JOIN departments d ON de.dept_no = d.dept_no" +
                "GROUP BY d.dept_name, e.emp_no" +
                "HAVING AVG(s.salary) = (" +
                "    SELECT MAX(avg_salary)" +
                "    FROM (" +
                "        SELECT AVG(salary) AS avg_salary" +
                "        FROM salaries s" +
                "        JOIN dept_emp de ON s.emp_no = de.emp_no" +
                "        WHERE de.dept_no = de.dept_no" +
                "        GROUP BY de.emp_no" +
                "    ) AS department_avg_salaries" +
                ")" +
                "ORDER BY avg_salary DESC");
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
