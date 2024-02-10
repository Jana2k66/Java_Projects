package GCT_Hostel;
import java.util.Scanner;
import java.sql.*;

public class hostelsDAO {
    public void displayParticularHostelInfo(int id) throws SQLException {
        Scanner scan = new Scanner(System.in);
        StudentDAO info = new StudentDAO();
        hostelsDAO call=new hostelsDAO();
        String query = "Select * from hostels where student_year=?";
        Connection con = DbConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        try {
            rs.next();
            System.out.println("           " + rs.getString(1) + "  Hostel");
            System.out.println("Warden Name        : " + rs.getString(2));
            System.out.println("Mess Incharge Name : " + rs.getString(3));
            System.out.println("Watchman Name      : " + rs.getString(4));
            if (rs.getInt(5) == 1)
                System.out.println("Year               : 1st Year Hostel ");
            else if (rs.getInt(5) == 2)
                System.out.println("Year               : 2nd Year Hostel ");
            else if (rs.getInt(5) == 3)
                System.out.println("Year               : 3rd Year Hostel ");
            else
                System.out.println("Year               : 4th Year Hostel ");
            System.out.println("Total Rooms        : " + rs.getInt(6));
            System.out.println("Room Capacity      : " + rs.getInt(7));
            System.out.println("");
            System.out.println("------------------------------------------------------------");
            System.out.println("Press 1 for Room Information of " + rs.getString(1) + " hostel else 0 to exit : ");
            int op = scan.nextInt();
            if (op == 1) {
                System.out.println("Enter room number : ");
                int roomnum = scan.nextInt();
                int capacity=call.getCapacity(rs.getInt(5),roomnum);
                int available=call.getAvailable(rs.getInt(5),roomnum);

                info.displayRoomInfo(rs.getString(1), roomnum, capacity, available);


            }
            else
            {
                System.out.println("Okay . . . ");
            }
        }
        catch(Exception i) {
            System.out.println("No one is present in the room ");
        }
    }
    public void displayHostelInfo() throws SQLException {
        String query = "Select * from hostels";
        Connection con = DbConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        try {
            while (rs.next()) {
                System.out.println("           " + rs.getString(1) + "  Hostel");
                System.out.println("Warden Name        : " + rs.getString(2));
                System.out.println("Mess Incharge Name : " + rs.getString(3));
                System.out.println("Watchman Name      : " + rs.getString(4));
                if (rs.getInt(5) == 1)
                    System.out.println("Year               : 1st Year Hostel ");
                else if (rs.getInt(5) == 2)
                    System.out.println("Year               : 2nd Year Hostel ");
                else if (rs.getInt(5) == 3)
                    System.out.println("Year               : 3rd Year Hostel ");
                else
                    System.out.println("Year               : 4th Year Hostel ");
                System.out.println("Total Rooms        : " + rs.getInt(6));
                System.out.println("Room Capacity      : " + rs.getInt(7));
                System.out.println("");
            }

            System.out.println("------------------------------------------------------------");
        }
        catch (Exception o)
        {
            System.out.println("Hostels are found . . .");
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
    public int getCapacity(int year,int room_no) throws SQLException {
        String query = "Select capacity from room where hostel_year=? and room_no=?";
        Connection con = DbConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, year);
        pst.setInt(2, room_no);
        ResultSet rs = pst.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
    public int getAvailable(int year,int room_no) throws SQLException {
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