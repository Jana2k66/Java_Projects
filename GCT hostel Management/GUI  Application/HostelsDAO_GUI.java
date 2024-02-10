package GCT_Hostel_Management;

import javax.swing.*;
import java.sql.*;

public class HostelsDAO_GUI {
    public void displayParticularHostelInfoGUI(int id) throws SQLException {
        StudentDAO_GUI info = new StudentDAO_GUI();
        HostelsDAO_GUI call = new HostelsDAO_GUI();
        String query = "Select * from hostels where student_year=?";
        Connection con = DbConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();

        try {
            if (rs.next()) {
                String hostelName = rs.getString(1);
                String wardenName = rs.getString(2);
                String messInchargeName = rs.getString(3);
                String watchmanName = rs.getString(4);
                String year = "";
                int yearValue = rs.getInt(5);
                if (yearValue == 1)
                    year = "1st Year Hostel";
                else if (yearValue == 2)
                    year = "2nd Year Hostel";
                else if (yearValue == 3)
                    year = "3rd Year Hostel";
                else
                    year = "4th Year Hostel";

                int totalRooms = rs.getInt(6);
                int roomCapacity = rs.getInt(7);

                String hostelInfo = "           " + hostelName + "  Hostel\n" +
                        "Warden Name        : " + wardenName + "\n" +
                        "Mess Incharge Name : " + messInchargeName + "\n" +
                        "Watchman Name      : " + watchmanName + "\n" +
                        "Year               : " + year + "\n" +
                        "Total Rooms        : " + totalRooms + "\n" +
                        "Room Capacity      : " + roomCapacity + "\n";

                int option = JOptionPane.showConfirmDialog(null, hostelInfo + "\nPress 'OK' for Room Information or 'Cancel' to exit: ", "Hostel Information", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    int roomNum = Integer.parseInt(JOptionPane.showInputDialog("Enter room number: "));
                    int capacity = call.getCapacity(yearValue, roomNum);
                    int available = call.getAvailable(yearValue, roomNum);
                    info.displayRoomInfoGUI(hostelName,roomNum, capacity, available);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No one is present in the room.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void displayHostelInfoGUI() throws SQLException {
        String query = "Select * from hostels";
        Connection con = DbConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        try {
            StringBuilder hostelInfo = new StringBuilder();
            while (rs.next()) {
                hostelInfo.append("           ").append(rs.getString(1)).append("  Hostel\n");
                hostelInfo.append("Warden Name        : ").append(rs.getString(2)).append("\n");
                hostelInfo.append("Mess Incharge Name : ").append(rs.getString(3)).append("\n");
                hostelInfo.append("Watchman Name      : ").append(rs.getString(4)).append("\n");
                int yearValue = rs.getInt(5);
                String year = "";
                if (yearValue == 1)
                    year = "1st Year Hostel";
                else if (yearValue == 2)
                    year = "2nd Year Hostel";
                else if (yearValue == 3)
                    year = "3rd Year Hostel";
                else
                    year = "4th Year Hostel";

                hostelInfo.append("Year               : ").append(year).append("\n");
                hostelInfo.append("Total Rooms        : ").append(rs.getInt(6)).append("\n");
                hostelInfo.append("Room Capacity      : ").append(rs.getInt(7)).append("\n\n");
            }

            JOptionPane.showMessageDialog(null, hostelInfo.toString(), "Hostel Information", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public String getHostelName(int y) throws SQLException {
        String query = "Select hostel_name from hostels where student_year=?";
        Connection con = DbConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, y);
        ResultSet rs = pst.executeQuery();
        rs.next();
        return rs.getString(1);
    }

    public int getCapacity(int year, int room_no) throws SQLException {
        String query = "Select capacity from room where hostel_year=? and room_no=?";
        Connection con = DbConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, year);
        pst.setInt(2, room_no);
        ResultSet rs = pst.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public int getAvailable(int year, int room_no) throws SQLException {
        String query = "Select student_available from room where hostel_year=? and room_no=?";
        Connection con = DbConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, year);
        pst.setInt(2, room_no);
        ResultSet rs = pst.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
}
