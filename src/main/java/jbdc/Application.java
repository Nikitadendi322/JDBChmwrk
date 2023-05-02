package jbdc;

import model.City;
import model.Employee;
import service.EmployeeDao;
import service.EmployeeDaoImpl;

import java.sql.*;

public class Application {
    public static void main(String[] args) throws SQLException {
        final String user = "postgres";
        final String password = "13971397";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("" +
                     "SELECT * FROM book WHERE id = (?)")) {

            statement.setInt(1, 1);
            final ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = "Name:" + resultSet.getString("first_name");
                String surname = "Surname:" + resultSet.getString("last_name");
                String gender = "Gender:" + resultSet.getString(4);
                int age = resultSet.getInt(5);

                System.out.println(name);
                System.out.println(surname);
                System.out.println(gender);
                System.out.println("Age:" + age);
            }
        }
        EmployeeDao employeeDao=new EmployeeDaoImpl();
        System.out.println(employeeDao.getAllEmployee());
        Employee employee=new Employee(1,"Nikita","Pavlov","m",45,new City(1,"Omsk"));
        employeeDao.add(employee);
        System.out.println(employeeDao.getAllEmployee());
        System.out.println();
        employee.setLast_name("Yudin");
        employeeDao.updateEmployee(12,employee);
        System.out.println(employeeDao.getById(12));
        employeeDao.deleteEmployee(1);
        System.out.println();
        System.out.println(employeeDao.getAllEmployee());
    }


}
