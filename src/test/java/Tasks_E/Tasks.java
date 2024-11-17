package Tasks_E;

import Tasks_I.JDBCParent;

import java.sql.ResultSet;

    public class Tasks extends JDBCParent {

        public static void main(String[] args) {
            DBConnectionOpen();

            try {
                findMaleEmployeeCount();
                findFemaleEmployeeCount();
                findGroupedEmployeeCount();
                findTotalEmployeeCount();
                findUniqueFirstNamesCount();
                findDistinctDepartmentCount();
                listEmployeeCountByDepartment();
                listEmployeesHiredWithinLastFiveYears();
                listEmployeeInfoByName("Annemarie", "Redmiles");
                listFullEmployeeInfoByName("Annemarie", "Redmiles");
                listEmployeesAndManagersByDepartment("D005");
                listEmployeesHiredAfterWithHighSalary("1994-02-24", 50000);
                listSalesManagers();
                findLongestWorkingDepartmentForEmployee(10102);
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            } finally {
                DBConnectionClose();
            }

        }


        public static void findMaleEmployeeCount() {
            try {
                String query = "SELECT COUNT(*) AS male_count FROM employees WHERE gender = 'M';";
                ResultSet rs = statement.executeQuery(query);
                if (rs.next()) {
                    int maleCount = rs.getInt("male_count");
                    System.out.println("Erkek Çalışan Sayısı: " + maleCount);
                }
            } catch (Exception ex) {
                System.out.println("Erkek çalışan sayısı alınırken hata: " + ex.getMessage());
            }
        }
        public static void findFemaleEmployeeCount() {
            try {
                String query = "SELECT COUNT(*) AS female_count FROM employees WHERE gender = 'F';";
                ResultSet rs = statement.executeQuery(query);
                if (rs.next()) {
                    int femaleCount = rs.getInt("female_count");
                    System.out.println("Kadın Çalışan Sayısı: " + femaleCount);
                }
            } catch (Exception ex) {
                System.out.println("Kadın çalışan sayısı alınırken hata: " + ex.getMessage());
            }
        }
        public static void findGroupedEmployeeCount() {
            try {
                String query = "SELECT gender, COUNT(*) AS employee_count FROM employees GROUP BY gender;";
                ResultSet rs = statement.executeQuery(query);
                System.out.println("Cinsiyete Göre Çalışan Sayıları:");
                while (rs.next()) {
                    String gender = rs.getString("gender");
                    int count = rs.getInt("employee_count");
                    System.out.println(gender + ": " + count + " çalışan");
                }
            } catch (Exception ex) {
                System.out.println("Gruplandırılmış çalışan sayısı alınırken hata: " + ex.getMessage());
            }
        }
        public static void findTotalEmployeeCount() {
            try {
                String query = "SELECT COUNT(*) AS total_employees FROM employees;";
                ResultSet rs = statement.executeQuery(query);
                if (rs.next()) {
                    int totalEmployees = rs.getInt("total_employees");
                    System.out.println("Toplam Çalışan Sayısı: " + totalEmployees);
                }
            } catch (Exception ex) {
                System.out.println("Toplam çalışan sayısı alınırken hata: " + ex.getMessage());
            }
        }
        public static void findUniqueFirstNamesCount() {
            try {
                String query = "SELECT COUNT(DISTINCT first_name) AS unique_first_names FROM employees;";
                ResultSet rs = statement.executeQuery(query);
                if (rs.next()) {
                    int uniqueFirstNames = rs.getInt("unique_first_names");
                    System.out.println("Benzersiz İlk İsim Sayısı: " + uniqueFirstNames);
                }
            } catch (Exception ex) {
                System.out.println("Benzersiz ilk ad sayısı alınırken hata: " + ex.getMessage());
            }
        }

        public static void findDistinctDepartmentCount() {
            try {
                String query = "SELECT COUNT(DISTINCT department_name) AS distinct_departments FROM departments;";
                ResultSet rs = statement.executeQuery(query);
                if (rs.next()) {
                    int distinctDepartments = rs.getInt("distinct_departments");
                    System.out.println("Farklı Departman Sayısı: " + distinctDepartments);
                }
            } catch (Exception ex) {
                System.out.println("Farklı departman sayısı alınırken hata: " + ex.getMessage());
            }
        }
        public static void listEmployeeCountByDepartment() {
            try {
                String query = """
                SELECT d.department_name, COUNT(e.employee_id) AS employee_count
                FROM departments d
                LEFT JOIN employees e ON d.department_id = e.department_id
                GROUP BY d.department_name
                ORDER BY employee_count DESC;
            """;

                ResultSet rs = statement.executeQuery(query);

                System.out.println("Departmanlardaki Çalışan Sayıları:");
                while (rs.next()) {
                    String departmentName = rs.getString("department_name");
                    int employeeCount = rs.getInt("employee_count");
                    System.out.println(departmentName + ": " + employeeCount + " çalışan");
                }
            } catch (Exception ex) {
                System.out.println("Departmanlara göre çalışan sayısı alınırken hata: " + ex.getMessage());
            }
        }
        public static void listEmployeesHiredWithinLastFiveYears() {
            try {
                String query = """
                SELECT employee_id, first_name, last_name, hire_date
                FROM employees
                WHERE hire_date BETWEEN '1990-02-20' AND DATE_ADD('1990-02-20', INTERVAL 5 YEAR)
                ORDER BY hire_date ASC;
            """;

                ResultSet rs = statement.executeQuery(query);

                System.out.println("Son 5 yıl içinde işe alınan çalışanlar (1990-02-20'den itibaren):");
                while (rs.next()) {
                    int employeeId = rs.getInt("employee_id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String hireDate = rs.getString("hire_date");

                    System.out.println("ID: " + employeeId + ", İsim: " + firstName + " " + lastName + ", İşe Alım Tarihi: " + hireDate);
                }
            } catch (Exception ex) {
                System.out.println("Son 5 yıl içinde işe alınan çalışanlar listelenirken hata: " + ex.getMessage());
            }
        }
        public static void listEmployeeInfoByName(String firstName, String lastName) {
            try {
                String query = """
                SELECT employee_id, birth_date, first_name, last_name, gender, hire_date
                FROM employees
                WHERE first_name = ? AND last_name = ?;
            """;

                var preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);

                ResultSet rs = preparedStatement.executeQuery();

                System.out.println("Çalışan Bilgileri (" + firstName + " " + lastName + "):");
                if (rs.next()) {
                    int employeeId = rs.getInt("employee_id");
                    String birthDate = rs.getString("birth_date");
                    String fName = rs.getString("first_name");
                    String lName = rs.getString("last_name");
                    String gender = rs.getString("gender");
                    String hireDate = rs.getString("hire_date");

                    System.out.println("Çalışan Numarası: " + employeeId);
                    System.out.println("Doğum Tarihi: " + birthDate);
                    System.out.println("İsim: " + fName + " " + lName);
                    System.out.println("Cinsiyet: " + gender);
                    System.out.println("İşe Alım Tarihi: " + hireDate);
                } else {
                    System.out.println("Çalışan bulunamadı.");
                }
            } catch (Exception ex) {
                System.out.println("Çalışan bilgileri alınırken hata: " + ex.getMessage());
            }
        }
        public static void listFullEmployeeInfoByName(String firstName, String lastName) {
            try {
                String query = """
                SELECT e.employee_id, e.birth_date, e.first_name, e.last_name, e.gender, e.hire_date,
                       s.salary, d.department_name, t.title
                FROM employees e
                LEFT JOIN salaries s ON e.employee_id = s.employee_id
                LEFT JOIN dept_emp de ON e.employee_id = de.employee_id
                LEFT JOIN departments d ON de.department_id = d.department_id
                LEFT JOIN titles t ON e.employee_id = t.employee_id
                WHERE e.first_name = ? AND e.last_name = ?;
            """;

                var preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);

                ResultSet rs = preparedStatement.executeQuery();

                System.out.println("Çalışan Bilgileri (" + firstName + " " + lastName + "):");
                if (rs.next()) {
                    int employeeId = rs.getInt("employee_id");
                    String birthDate = rs.getString("birth_date");
                    String fName = rs.getString("first_name");
                    String lName = rs.getString("last_name");
                    String gender = rs.getString("gender");
                    String hireDate = rs.getString("hire_date");
                    double salary = rs.getDouble("salary");
                    String department = rs.getString("department_name");
                    String title = rs.getString("title");

                    System.out.println("Çalışan Numarası: " + employeeId);
                    System.out.println("Doğum Tarihi: " + birthDate);
                    System.out.println("İsim: " + fName + " " + lName);
                    System.out.println("Cinsiyet: " + gender);
                    System.out.println("İşe Alım Tarihi: " + hireDate);
                    System.out.println("Maaş: " + salary);
                    System.out.println("Departman: " + department);
                    System.out.println("Unvan: " + title);
                } else {
                    System.out.println("Çalışan bulunamadı.");
                }
            } catch (Exception ex) {
                System.out.println("Çalışan bilgileri alınırken hata: " + ex.getMessage());
            }
        }
        public static void listEmployeesAndManagersByDepartment(String departmentId) {
            try {
                String query = """
                SELECT e.employee_id, e.first_name, e.last_name, e.gender, e.hire_date,
                       d.department_name, ""
                       CASE WHEN dm.employee_id IS NOT NULL THEN 'Manager' ELSE 'Employee' END AS role
                FROM employees e
                LEFT JOIN dept_emp de ON e.employee_id = de.employee_id
                LEFT JOIN departments d ON de.department_id = d.department_id
                LEFT JOIN dept_manager dm ON e.employee_id = dm.employee_id AND dm.department_id = d.department_id
                WHERE d.department_id = ?;
            """;

                var preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, departmentId);

                ResultSet rs = preparedStatement.executeQuery();

                System.out.println("D005 Departmanındaki Çalışanlar ve Yöneticiler:");
                System.out.println("------------------------------------------------");
                while (rs.next()) {
                    int employeeId = rs.getInt("employee_id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String gender = rs.getString("gender");
                    String hireDate = rs.getString("hire_date");
                    String departmentName = rs.getString("department_name");
                    String role = rs.getString("role");

                    System.out.printf("ID: %d, İsim: %s %s, Cinsiyet: %s, İşe Alım Tarihi: %s, Departman: %s, Rol: %s%n",
                            employeeId, firstName, lastName, gender, hireDate, departmentName, role);
                }
            } catch (Exception ex) {
                System.out.println("Çalışanlar ve yöneticiler alınırken hata: " + ex.getMessage());
            }
        }
        public static void listEmployeesHiredAfterWithHighSalary(String hireDate, double minSalary) {
            try {
                String query = """
                SELECT e.employee_id, e.first_name, e.last_name, e.gender, e.hire_date, s.salary
                FROM employees e
                INNER JOIN salaries s ON e.employee_id = s.employee_id
                WHERE e.hire_date > ? AND s.salary > ?
                ORDER BY e.hire_date ASC;
            """;

                var preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, hireDate);
                preparedStatement.setDouble(2, minSalary);

                ResultSet rs = preparedStatement.executeQuery();

                System.out.println("1994-02-24 tarihinden sonra işe alınan ve 50,000'den fazla kazanan çalışanlar:");
                System.out.println("----------------------------------------------------------------------------");
                while (rs.next()) {
                    int employeeId = rs.getInt("employee_id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String gender = rs.getString("gender");
                    String hireDateResult = rs.getString("hire_date");
                    double salary = rs.getDouble("salary");

                    System.out.printf("ID: %d, İsim: %s %s, Cinsiyet: %s, İşe Alım Tarihi: %s, Maaş: %.2f%n",
                            employeeId, firstName, lastName, gender, hireDateResult, salary);
                }
            } catch (Exception ex) {
                System.out.println("Çalışan bilgileri alınırken hata: " + ex.getMessage());
            }
        }
        public static void listSalesManagers() {
            try {
                String query = """
                SELECT e.employee_id, e.first_name, e.last_name, e.gender, e.hire_date, d.department_name, dm.employee_id AS manager_id
                FROM employees e
                INNER JOIN dept_emp de ON e.employee_id = de.employee_id
                INNER JOIN departments d ON de.department_id = d.department_id
                INNER JOIN dept_manager dm ON e.employee_id = dm.employee_id
                WHERE d.department_name = 'Sales' AND dm.department_id = d.department_id;
            """;

                var preparedStatement = connection.prepareStatement(query);

                ResultSet rs = preparedStatement.executeQuery();

                System.out.println("Sales departmanındaki Yöneticiler:");
                System.out.println("------------------------------------");
                while (rs.next()) {
                    int employeeId = rs.getInt("employee_id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String gender = rs.getString("gender");
                    String hireDate = rs.getString("hire_date");
                    String departmentName = rs.getString("department_name");
                    int managerId = rs.getInt("manager_id");

                    System.out.printf("ID: %d, İsim: %s %s, Cinsiyet: %s, İşe Alım Tarihi: %s, Departman: %s, Manager ID: %d%n",
                            employeeId, firstName, lastName, gender, hireDate, departmentName, managerId);
                }
            } catch (Exception ex) {
                System.out.println("Yöneticiler alınırken hata: " + ex.getMessage());
            }
        }
        public static void findLongestWorkingDepartmentForEmployee(int employeeId) {
            try {
                String query = """
                SELECT d.department_name, de.from_date, de.to_date,
                       DATEDIFF(de.to_date, de.from_date) AS duration_in_days
                FROM dept_emp de
                INNER JOIN departments d ON de.department_id = d.department_id
                WHERE de.employee_id = ?
                ORDER BY duration_in_days DESC
                LIMIT 1;
            """;

                var preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, employeeId);

                ResultSet rs = preparedStatement.executeQuery();

                if (rs.next()) {
                    String departmentName = rs.getString("department_name");
                    String fromDate = rs.getString("from_date");
                    String toDate = rs.getString("to_date");
                    int durationInDays = rs.getInt("duration_in_days");

                    System.out.printf("Çalışan %d'nin en uzun süre çalıştığı departman: %s%n", employeeId, departmentName);
                    System.out.printf("Başlangıç Tarihi: %s, Bitiş Tarihi: %s, Süre: %d gün%n", fromDate, toDate, durationInDays);
                } else {
                    System.out.println("Çalışan bilgisi bulunamadı.");
                }
            } catch (Exception ex) {
                System.out.println("Departman bilgileri alınırken hata: " + ex.getMessage());
            }
        }

    }
