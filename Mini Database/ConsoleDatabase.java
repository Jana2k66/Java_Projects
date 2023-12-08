package miniDB;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleDatabase {
    private static Map<String, Map<String, ArrayList<ArrayList<String>>>> database= new HashMap<>();
    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            ConsoleDatabase cd = new ConsoleDatabase();
            Need nd = new Need();
            System.out.println("Database Console . . .");
            System.out.println("Command ((create database/table)/insert/select/exit)");
            System.out.println("Rules : ");
            System.out.println("* Don't use ';' after every command and don't need to give single or double quotation for string");
            System.out.println("* Don't give space while creating table inside the bracket");
            System.out.println("* Don't give space before and after to '=' in delete command");
            System.out.println();
            String command = scan.nextLine();
            if (command.startsWith("create database"))
                    cd.createDatabase(command);
            else
                System.out.println("First Initialize the database");
            nd.operation();
        } catch(Exception e){
            System.out.println("Error found");
            System.out.println();
        }
    }
    public void createDatabase (String command){
        try{
            String[] parts = command.split(" ");
            String dbname=parts[2];
            database.put(dbname, new HashMap<>());
            System.out.println("Database " + dbname + " is created.");
            System.out.println();
        } catch (Exception e){
            System.out.println("Error found");
            System.out.println();
        }
    }
    public String usedatabase(String command){
        try{
        String dbname = command.substring(4,command.length());
        return dbname;
        }catch (Exception e){
            System.out.println("Error found");
            System.out.println();
            return "";
        }
    }
    public void createTable(String dbname,String command){
        try {
            int index = command.indexOf("(");
            String subcommand = command.substring(0, index);
            String rem = command.substring(index + 1, command.length() - 1);
            ArrayList<String> elements = new ArrayList<>();
            ArrayList<String> datatype = new ArrayList<>();
            subcommand.trim();
            String[] parts = subcommand.split(" ");
            String tableName = parts[2];
            String[] attributes = rem.split(",");
            index = 0;
            for (String attribute : attributes) {
                index = attribute.indexOf(" ");
                String element = attribute.substring(0, index);
                String dtype = attribute.substring(index + 1, attribute.length());
                elements.add(element);
                datatype.add(dtype);
            }
            database.get(dbname).put(tableName.trim(), new ArrayList<>());
            database.get(dbname).get(tableName).add(elements);
            database.get(dbname).get(tableName).add(datatype);
            System.out.println("Table '" + tableName + "' is created in database " + dbname);
            System.out.println();
        }catch (Exception e){
            System.out.println("Error found");
            System.out.println();
        }
    }
    public void tableStructure(String dbname,String command){
        try {
            String[] parts = command.split(" ");
            String tablename = parts[1];
            ArrayList<String> list1 = new ArrayList<>(database.get(dbname).get(tablename).get(0));
            ArrayList<String> list2 = new ArrayList<>(database.get(dbname).get(tablename).get(1));
            System.out.println(list1);
            System.out.println(list2);
            System.out.println("+------------------------------+------------------------------+");
            System.out.println("|  Field                       |  Type                        |");
            System.out.println("+------------------------------+------------------------------+");
            for (int i = 0; i < list1.size(); i++) {
                System.out.print("|  " + list1.get(i));
                int space1 = 28 - list1.get(i).length();
                for (int j = 0; j < space1; j++) {
                    System.out.print(" ");
                }
                System.out.print("|  " + list2.get(i));
                int space2 = 28 - list2.get(i).length();
                for (int j = 0; j < space2; j++) {
                    System.out.print(" ");
                }
                System.out.print("|");
                System.out.println();
            }
            System.out.println("+------------------------------+------------------------------+");
            System.out.println();
        }catch (Exception e){
            System.out.println("Error found");
            System.out.println();
        }
    }
    public void insertIntoTable (String dbname,String command){
        try {
            int index = command.indexOf("(");
            String subcommand = command.substring(0, index);
            String rem = command.substring(index + 1, command.length() - 1);
            subcommand.trim();
            String[] parts = subcommand.split(" ");
            String tablename = parts[2];
            ArrayList<String> check = new ArrayList<>(database.get(dbname).get(tablename).get(0));
            String[] str = rem.split(",");
            ArrayList<String> values = new ArrayList<>();
            for (String s : str) {
                values.add(s);
            }
            if (values.size() == check.size()) {
                database.get(dbname).get(tablename).add(values);
                System.out.println("values inserted into the table " + tablename);
                System.out.println();
            } else {
                System.out.println("some value is missing in the command . . .");
                System.out.println();
            }
        }catch (Exception e){
            System.out.println("Error found");
            System.out.println();
        }
    }
    public void deleteFromTable(String dbname,String command){
        try{
            int index=command.indexOf("where");
            String str=command.substring(0,index);
            String s=command.substring(index,command.length());
            s.trim();
            str.trim();
            String[] parts=command.split(" ");
            String tablename=parts[2];
            index=s.indexOf("=");
            int space=s.indexOf(" ");
            String element=s.substring(space+1,index);
            String value=s.substring(index+1,s.length());
            ArrayList<ArrayList<String>> list = database.get(dbname).get(tablename);
            ArrayList<String> keys=new ArrayList<>(list.get(0));
            index=keys.indexOf(element);
            int count=0;
            for (int i = 2; i < list.size(); i++) {
                if(list.get(i).get(index).equals(value)){
                    database.get(dbname).get(tablename).remove(i);
                    count++;
                }
            }
            System.out.println(count +" rows is affected ");
            System.out.println();
        }catch (Exception e){
            System.out.println("Error found");
            System.out.println();
        }
    }
    public void selectFromTable (String dbname,String command) {
        try {
            int index = command.indexOf("from");
            String tablename = command.substring(index + 5, command.length());
            if (database.get(dbname).get(tablename).size() < 3) {
                System.out.println("Empty set");
                System.out.println();
            } else {
                ArrayList<ArrayList<String>> list = database.get(dbname).get(tablename);

                for (int i = 0; i < list.size(); i++) {
                    if (i == 0 || i == 2) {
                        for (int j = 0; j < list.get(i).size(); j++) {
                            System.out.print("+------------------------------");
                        }
                        System.out.print("+");
                        System.out.println();
                    }
                    for (int j = 0; j < list.get(i).size(); j++) {
                        if (i == 1) break;
                        System.out.print("|  " + list.get(i).get(j));
                        int space = 28 - list.get(i).get(j).length();
                        for (int k = 0; k < space; k++) {
                            System.out.print(" ");
                        }
                    }
                    if (i != 1) {
                        System.out.print("|");
                        System.out.println();
                    }
                }
                for (int j = 0; j < list.get(0).size(); j++) {
                    System.out.print("+------------------------------");
                }
                System.out.print("+");
                System.out.println();
                System.out.println();
            }
        }catch (Exception e){
            System.out.println("Error found");
            System.out.println();
        }
    }
}
class Need {
    String dbname;
    public void operation() {
        try {
            Scanner scan = new Scanner(System.in);
            ConsoleDatabase cd = new ConsoleDatabase();
            String command;
            int flag = 0;
            while (true) {
                command = scan.nextLine();
                if (command.equalsIgnoreCase("exit")) {
                    flag = 0;
                    break;
                } else if (command.startsWith("create database")) {
                    cd.createDatabase(command);
                    flag = 0;
                } else if (command.startsWith("use")) {
                    flag = 1;
                    String name = cd.usedatabase(command);
                    this.dbname = name;
                    System.out.println("selected database is " + this.dbname);
                    System.out.println();
                } else if (command.startsWith("create table")) {
                    if (flag == 1)
                        cd.createTable(this.dbname, command);
                    else {
                        System.out.println("Database is not selected");
                        System.out.println();
                    }
                } else if (command.startsWith("insert into")) {
                    if (flag == 1)
                        cd.insertIntoTable(this.dbname, command);
                    else {
                        System.out.println("Database is not selected");
                        System.out.println();
                    }
                }else if (command.startsWith("delete from")) {
                    if (flag == 1)
                        cd.deleteFromTable(this.dbname, command);
                    else {
                        System.out.println("Database is not selected");
                        System.out.println();
                    }
                } else if (command.startsWith("select * from")) {
                    if (flag == 1)
                        cd.selectFromTable(this.dbname, command);
                    else {
                        System.out.println("Database is not selected ");
                        System.out.println();
                    }
                } else if (command.startsWith("desc")) {
                    if (flag == 1)
                        cd.tableStructure(this.dbname, command);
                    else {
                        System.out.println("Database is not selected ");
                        System.out.println();
                    }
                } else {
                    System.out.println("Invalid command. Try again.");
                    System.out.println();
                }
            }
        }catch (Exception e){
            System.out.println("Error found");
            System.out.println();
        }
    }
}