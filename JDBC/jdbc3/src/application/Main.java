package application;

import db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {


        // inserir dados
        Connection conn = null;
        PreparedStatement st = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("INSERT INTO seller  "
                            + "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
                            + "VALUES "
                            + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, "Kim Minji");
            st.setString(2, "minji@gmail.com");
            st.setDate(3, new Date(sdf.parse("07/05/2004").getTime()));
            st.setDouble(4, 3000.0);
            st.setInt(5, 4);

            // st = conn.prepareStatement("insert into department (Name) values ('D1'), ('D2')", Statement.RETURN_GENERATED_KEYS);   => inserir novos valores no department

            int rowsAffected = st.executeUpdate();
            if(rowsAffected > 0) {
                ResultSet rs2 = st.getGeneratedKeys();
                while (rs2.next()) {
                    int id = rs2.getInt(1);
                    System.out.println("Done, id = " + id);
                }
            }
            else {
                System.out.println("No rows affected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
        
    }
}