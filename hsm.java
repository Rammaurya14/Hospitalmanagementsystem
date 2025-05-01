package jdbcjava;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class doctor {
    private Connection connection;
    private Scanner scanner;

    public doctor(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void addDoctor() {
        System.out.println("Enter Name of Doctor:");
        String name = scanner.nextLine();
        System.out.println("Enter Specialty:");
        String specialty = scanner.nextLine();

        try {
            String query = "INSERT INTO doctors(name, specialty) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, specialty);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Doctor added successfully");
            } else {
                System.out.println("Doctor not added");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewDoctors() {
        String query = "SELECT * FROM doctors";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Doctors: ");
            System.out.println("+--------------------+--------------+--------------------+");
            System.out.println("| Doctor Id          | Name         | Specialty          |");
            System.out.println("+--------------------+--------------+--------------------+");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialty = resultSet.getString("specialty");
                System.out.printf("| %-20d | %-14s | %-20s |\n", id, name, specialty);
            }
            System.out.println("+--------------------+--------------+--------------------+");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDoctor(int id) {
        String query = "DELETE FROM doctors WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Doctor deleted successfully");
            } else {
                System.out.println("Doctor not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDoctor(int id) {
        System.out.println("Enter new Name of Doctor:");
        String name = scanner.nextLine();
        System.out.println("Enter new Specialty:");
        String specialty = scanner.nextLine();

        String query = "UPDATE doctors SET name=?, specialty=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, specialty);
            preparedStatement.setInt(3, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Doctor updated successfully");
            } else {
                System.out.println("Doctor not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


//patient class

package jdbcjava;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class patient {
    private Connection connection;
    private Scanner scanner;

    public patient(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void addPatient() {
        System.out.println("Enter Name of Patient:");
        String name = scanner.nextLine();
        System.out.println("Enter Patient age:");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.println("Enter Gender:");
        String gender = scanner.nextLine();

        try {
            String query = "INSERT INTO patients(name, age, gender) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Patient added successfully");
            } else {
                System.out.println("Patient not added");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewPatients() {
        String query = "SELECT * FROM patients";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Patients: ");
            System.out.println("+--------------------+--------------+--------------------+--------+");
            System.out.println("| Patient Id         | Name         | Age                | Gender |");
            System.out.println("+--------------------+--------------+--------------------+--------+");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                System.out.printf("| %-20d | %-14s | %-20d | %-8s |\n", id, name, age, gender);
            }
            System.out.println("+--------------------+--------------+--------------------+--------+");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getPatientById(int id) {
        String query = "SELECT * FROM patients WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); // Returns true if a record is found
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deletePatient(int id) {
        String query = "DELETE FROM patients WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Patient deleted successfully");
            } else {
                System.out.println("Patient not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePatient(int id) {
        System.out.println("Enter new Name of Patient:");
        String name = scanner.nextLine();
        System.out.println("Enter new Patient age:");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.println("Enter new Gender:");
        String gender = scanner.nextLine();

        String query = "UPDATE patients SET name=?, age=?, gender=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);
            preparedStatement.setInt(4, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Patient updated successfully");
            } else {
                System.out.println("Patient not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchPatient(int id) {
        String query = "SELECT * FROM patients WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Patient found:");
                System.out.println("id:");
                resultSet.getInt("id");
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Age: " + resultSet.getInt("age"));
                System.out.println("Gender: " + resultSet.getString("gender"));
            } else {
                System.out.println("Patient not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


//main class

package jdbcjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class hospitalmanagementsystem {
    private static final String URL = "jdbc:mysql://localhost:3306/hospital";
    private static final String USERNAME = "root"; // Replace with your MySQL username
    private static final String PASSWORD = "Ram4321@"; // Replace with your MySQL password

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Scanner scanner = new Scanner(System.in)) {

            patient patient = new patient(connection, scanner);
            doctor doctor = new doctor(connection, scanner);
            int choice;

            do {
                System.out.println("Hospital Management System");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patients");
                System.out.println("3. Delete Patient");
                System.out.println("4. Update Patient");
                System.out.println("5. Search Patient");
                System.out.println("6. Add Doctor");
                System.out.println("7. View Doctors");
                System.out.println("8. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        patient.addPatient(); // Call the method to add a patient
                        break;
                    case 2:
                        patient.viewPatients(); // Call the method to view patients
                        break;
                    case 3:
                        System.out.print("Enter Patient ID to delete: ");
                        int deleteId = scanner.nextInt();
                        patient.deletePatient(deleteId); // Call the method to delete a patient
                        break;
                    case 4:
                        System.out.print("Enter Patient ID to update: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                        patient.updatePatient(updateId); // Call the method to update a patient
                        break;
                    case 5:
                        System.out.print("Enter Patient ID to search: ");
                        int searchId = scanner.nextInt();
                        patient.searchPatient(searchId); // Call the method to search for a patient
                        break;
                    case 6:
                        doctor.addDoctor(); // Call the method to add a doctor
                        break;
                    case 7:
                        doctor.viewDoctors(); // Call the method to view doctors
                        break;
                    case 8:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } while (choice != 8); // Continue until the user chooses to exit

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}