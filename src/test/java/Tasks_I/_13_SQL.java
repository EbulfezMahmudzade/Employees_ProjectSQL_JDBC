package Tasks_I;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class _13_SQL extends JDBCParent{
    public static void main(String[] args) throws SQLException {

        DBConnectionOpen();

        ResultSet rs = statement.executeQuery(" SELECT employees.emp_no, employees.first_name, employees.last_name, salaries.salary" +
                "        FROM employees" +
                "        JOIN salaries ON employees.emp_no = salaries.emp_no" +
                "        WHERE (employees.emp_no, salaries.from_date) IN (" +
                "                SELECT emp_no, MAX(from_date)" +
                "                FROM salaries" +
                "                GROUP BY emp_no" +
                "        )" +
                "        ORDER BY employees.emp_no");
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
