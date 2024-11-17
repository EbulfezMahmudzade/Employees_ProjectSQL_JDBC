package Tasks_Y;

import Tasks_I.JDBCParent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Tasks extends JDBCParent {
    public static void main(String[] args) {
        DBConnectionOpen();
        try {

            testTask31();
            testTask32();
            testTask33();
            testTask34();
            testTask35();
            testTask36();
            testTask37();
            testTask38();
            testTask39();
            testTask40();
            testTask41();

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            DBConnectionClose();
        }
    }

    public static void testTask31() throws SQLException {
        System.out.println("Executing Task 31: Find the highest paid employee in department 'D004'");
        ResultSet sonuc = statement.executeQuery("SELECT e.emp_no, e.first_name, e.last_name, s.salary " +
                "FROM employees.salaries s " +
                "JOIN employees.dept_emp de ON s.emp_no = de.emp_no " +
                "JOIN employees.employees e ON e.emp_no = s.emp_no " +
                "WHERE de.dept_no = 'd004' " +
                "ORDER BY s.salary DESC " +
                "LIMIT 1");

        if (sonuc.next()) {
            int empNo = sonuc.getInt("emp_no");
            String firstName = sonuc.getString("first_name");
            String lastName = sonuc.getString("last_name");
            int salary = sonuc.getInt("salary");
            System.out.println("Highest Paid Employee - Emp No: " + empNo + ", First Name: " + firstName + ", Last Name: " + lastName + ", Salary: " + salary);
        }
    }

    public static void testTask32() throws SQLException {
        System.out.println("Executing Task 32: Find the entire position history for employee '10102'");
        ResultSet sonuc = statement.executeQuery("SELECT emp_no, title, from_date, to_date " +
                "FROM employees.titles " +
                "WHERE emp_no = '10102' " +
                "ORDER BY from_date ASC");

        while (sonuc.next()) {
            String title = sonuc.getString("title");
            String fromDate = sonuc.getString("from_date");
            String toDate = sonuc.getString("to_date");
            System.out.println("Position History - Title: " + title + ", From Date: " + fromDate + ", To Date: " + toDate);
        }
    }

    public static void testTask33() throws SQLException {
        System.out.println("Executing Task 33: Find the average 'employee age'");
        ResultSet sonuc = statement.executeQuery("SELECT AVG(DATEDIFF(CURDATE(), birth_date) / 365) AS avg_age " +
                "FROM employees.employees");

        if (sonuc.next()) {
            double avgAge = sonuc.getDouble("avg_age");
            System.out.println("Average Employee Age: " + avgAge + " years");
        }
    }

    public static void testTask34() throws SQLException {
        System.out.println("Executing Task 34: Find the number of employees per department");
        ResultSet sonuc = statement.executeQuery("SELECT d.dept_name, COUNT(de.emp_no) AS num_employees " +
                "FROM employees.dept_emp de " +
                "JOIN employees.departments d ON de.dept_no = d.dept_no " +
                "GROUP BY d.dept_name");

        while (sonuc.next()) {
            String deptName = sonuc.getString("dept_name");
            int numEmployees = sonuc.getInt("num_employees");
            System.out.println("Department: " + deptName + ", Number of Employees: " + numEmployees);
        }
    }

    public static void testTask35() throws SQLException {
        System.out.println("Executing Task 35: Find the managerial history of employee '110022'");
        ResultSet sonuc = statement.executeQuery("SELECT dept_no, from_date, to_date " +
                "FROM employees.dept_manager " +
                "WHERE emp_no = '110022' " +
                "ORDER BY from_date ASC");

        while (sonuc.next()) {
            String deptNo = sonuc.getString("dept_no");
            String fromDate = sonuc.getString("from_date");
            String toDate = sonuc.getString("to_date");
            System.out.println("Managerial History - Dept No: " + deptNo + ", From Date: " + fromDate + ", To Date: " + toDate);
        }
    }

    public static void testTask36() throws SQLException {
        System.out.println("Executing Task 36: Find the duration of employment for each employee");
        ResultSet sonuc = statement.executeQuery("SELECT emp_no, first_name, last_name, DATEDIFF(CURDATE(), hire_date) AS employment_duration " +
                "FROM employees.employees " +
                "ORDER BY employment_duration DESC");

        int rowCount = 0;
        while (sonuc.next() && rowCount < 10) {
            rowCount++;
            int empNo = sonuc.getInt("emp_no");
            String firstName = sonuc.getString("first_name");
            String lastName = sonuc.getString("last_name");
            int duration = sonuc.getInt("employment_duration");
            System.out.println("Employee - Emp No: " + empNo + ", Name: " + firstName + " " + lastName + ", Duration: " + duration + " days");
        }
    }

    public static void testTask37() throws SQLException {
        System.out.println("Executing Task 37:Find the latest title information for each employee.");
        ResultSet sonuc = statement.executeQuery("SELECT emp_no, title, from_date " +
                "FROM employees.titles " +
                "WHERE to_date = '9999-01-01'");

        int rowCount = 0;
        while (sonuc.next() && rowCount < 10) {
            rowCount++;
            int empNo = sonuc.getInt("emp_no");
            String title = sonuc.getString("title");
            String fromDate = sonuc.getString("from_date");
            System.out.println("Employee Details - Emp No: " + empNo + ", Title: " + title + ", From Date: " + fromDate);
        }
    }

    public static void testTask38() throws SQLException {
        System.out.println("Executing Task 38: Find the first and last names of managers in department 'D005'");
        ResultSet sonuc = statement.executeQuery("SELECT e.first_name, e.last_name " +
                "FROM employees.employees e " +
                "JOIN employees.dept_manager dm ON e.emp_no = dm.emp_no " +
                "WHERE dm.dept_no = 'd005'");

        int rowCount = 0;
        while (sonuc.next() && rowCount < 10) {
            rowCount++;
            String firstName = sonuc.getString("first_name");
            String lastName = sonuc.getString("last_name");
            System.out.println("Manager Details - First Name: " + firstName + ", Last Name: " + lastName);
        }
    }

    public static void testTask39() throws SQLException {
        System.out.println("Executing Task 39: Sort employees by their birth dates");
        ResultSet sonuc = statement.executeQuery("SELECT * " +
                "FROM employees.employees " +
                "ORDER BY birth_date");

        int rowCount = 0;
        while (sonuc.next() && rowCount < 10) {
            rowCount++;
            int empNo = sonuc.getInt("emp_no");
            String firstName = sonuc.getString("first_name");
            String lastName = sonuc.getString("last_name");
            String birthDate = sonuc.getString("birth_date");
            System.out.println("Employee Details - Emp No: " + empNo + ", First Name: " + firstName + ", Last Name: "
                    + lastName + ", Birth Date: " + birthDate);
        }
    }

    public static void testTask40() throws SQLException {
        System.out.println("Executing Task 40: List employees hired in April 1992.");
        ResultSet sonuc = statement.executeQuery("SELECT emp_no, first_name, last_name, gender, hire_date " +
                "FROM employees.employees " +
                "WHERE hire_date BETWEEN '1992-04-01' AND '1992-04-30' " +
                "ORDER BY hire_date ASC");

        int rowCount = 0;
        while (sonuc.next() && rowCount < 10) {
            rowCount++;
            int empNo = sonuc.getInt("emp_no");
            String firstName = sonuc.getString("first_name");
            String lastName = sonuc.getString("last_name");
            String gender = sonuc.getString("gender");
            String hireDate = sonuc.getString("hire_date");
            System.out.println("Employee Details - Emp No: " + empNo + ", First Name: "
                    + firstName + ", Last Name: " + lastName + ", Gender: " + gender + ", Hire Date: " + hireDate);
        }
    }

    public static void testTask41() throws SQLException {
        System.out.println("Executing Task 41: Find all departments that employee '10102' has worked in.");
        ResultSet sonuc = statement.executeQuery("SELECT DISTINCT e.first_name, e.last_name, d.dept_name " +
                "FROM employees.dept_emp de " +
                "JOIN employees.employees e ON de.emp_no = e.emp_no " +
                "JOIN employees.departments d ON de.dept_no = d.dept_no " +
                "WHERE de.emp_no = '10102'");

        int rowCount = 0;
        while (sonuc.next() && rowCount < 10) {
            rowCount++;
            String firstName = sonuc.getString("first_name");
            String lastName = sonuc.getString("last_name");
            String deptName = sonuc.getString("dept_name");
            System.out.println("Department Details - First Name: " + firstName + ", Last Name: "
                    + lastName + ", Department Name: " + deptName);
        }
    }
}
