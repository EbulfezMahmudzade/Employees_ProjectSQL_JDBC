package Task_K;

import Tasks_I.JDBCParent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Soru_03_ extends  JDBCParent {
    public static void main(String[] args) throws SQLException {

        DBConnectionOpen();

        String sql = "SELECT AVG(salary) AS average_salary FROM salaries";
        ResultSet resultSet = statement.executeQuery(sql);


        if (resultSet.next()) {

            double averageSalary = resultSet.getDouble("average_salary");
            System.out.println("Average Salary: " + averageSalary);

            DBConnectionClose();


        }
    }
}
