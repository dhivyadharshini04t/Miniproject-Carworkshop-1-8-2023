package demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class Appointment extends Entity {
    private int customerId;
    private String appointmentDate;
    private String serviceType;
    private String status;

    public Appointment(int customerId, String appointmentDate, String serviceType, String status) {
        this.customerId = customerId;
        this.appointmentDate = appointmentDate;
        this.serviceType = serviceType;
        this.status = status;
    }

    @Override
    public void insertEntity() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO appointments (customer_id, appointment_date, service_type, status) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, customerId);
            preparedStatement.setString(2, appointmentDate);
            preparedStatement.setString(3, serviceType);
            preparedStatement.setString(4, status);
            preparedStatement.executeUpdate();
            System.out.println("Appointment added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM appointments";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int appointmentId = resultSet.getInt("appointment_id");
                int customerId = resultSet.getInt("customer_id");
                String appointmentDate = resultSet.getString("appointment_date");
                String serviceType = resultSet.getString("service_type");
                String status = resultSet.getString("status");

                Appointment appointment = new Appointment(customerId, appointmentDate, serviceType, status);
                appointment.setId(appointmentId);
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    @Override
    public String toString() {
        return "Appointment ID: " + getId() +
                "\nCustomer ID: " + customerId +
                "\nAppointment Date: " + appointmentDate +
                "\nService Type: " + serviceType +
                "\nStatus: " + status +
                "\n---------------------";
    }

    @Override
    public void viewAllEntities() {
        System.out.println("View Appointments:");
        List<Appointment> appointments = getAllAppointments();
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }
}
