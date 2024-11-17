package Tasks_I;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class _18_SQL extends JDBCParent{
    public static void main(String[] args) throws SQLException {
        DBConnectionOpen();

        ResultSet rs = statement.executeQuery("SELECT first_name, last_name, hire_date" +
                "FROM employees" +
                "WHERE hire_date < '1990-01-01'" +
                "ORDER BY first_name, last_name");
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
