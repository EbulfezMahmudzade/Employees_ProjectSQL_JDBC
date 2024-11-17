package Tasks_I;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class _11_SQL extends JDBCParent {
    public static void main(String[] args) throws SQLException {
        DBConnectionOpen();

        ResultSet rs = statement.executeQuery("SELECT emp_no, salary, from_date, to_date FROM salaries WHERE emp_no = '10102' ORDER BY from_date");
        ResultSetMetaData rsmd=rs.getMetaData();

        int kolonSayisi= rsmd.getColumnCount();
        // Başlık
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
