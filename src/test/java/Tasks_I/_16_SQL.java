package Tasks_I;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class _16_SQL extends JDBCParent{

    public static void main(String[] args) throws SQLException {
        DBConnectionOpen();

        ResultSet rs = statement.executeQuery("SELECT departments.dept_name, employees.first_name, employees.last_name, salaries.salary AS peak_salary" +
                "FROM employees" +
                "JOIN salaries ON employees.emp_no = salaries.emp_no" +
                "JOIN dept_emp ON employees.emp_no = dept_emp.emp_no" +
                "JOIN departments ON dept_emp.dept_no = departments.dept_no" +
                "WHERE salaries.salary = (" +
                "SELECT MAX(salary)" +
                "FROM salaries" +
                "WHERE emp_no = employees.emp_no" +
                ")" +
                "ORDER BY salaries.salary DESC");
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
