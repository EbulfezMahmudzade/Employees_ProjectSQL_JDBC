package Tasks_I;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class _20_SQL extends JDBCPare{
    public static void main(String[] args) throws SQLException {
        DBConnectionOpen();

        ResultSet rs = statement.executeQuery("SELECT e.first_name, e.last_name, e.hire_date, s.salary" +
                "FROM employees e" +
                "JOIN salaries s ON e.emp_no = s.emp_no" +
                "JOIN dept_emp de ON e.emp_no = de.emp_no" +
                "JOIN departments d ON de.dept_no = d.dept_no" +
                "WHERE d.dept_name = 'Sales'" +
                "  AND e.hire_date BETWEEN '1985-01-01' AND '1989-12-31'" +
                "ORDER BY s.salary DESC");
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
