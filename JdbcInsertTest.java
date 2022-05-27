import java.sql.*;

public class JdbcInsertTest {
    public static void main(String[] args) {
        try (
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true",
                    "root", ""
            );
            Statement statement = connection.createStatement();
        ){
//            Delete a record
            String sqlDelete = "delete from books where id >= 3000 and id < 4000";
            System.out.println("The SQL statement is: "+ sqlDelete +"\n");
            int countDeleted = statement.executeUpdate(sqlDelete);
            System.out.println(countDeleted+"record deleted.\n");
//            Insert a record
            String sqlInsert = "insert into books value(3001,'Gone Fishing','Kumar',11.11,11)";
            System.out.println("The SQL statement is: "+ sqlInsert +"\n");
            int countInserted = statement.executeUpdate(sqlInsert);
            System.out.println(countInserted+"record insert");
//            Insert multiple records
            sqlInsert = "insert into books values" +
                    "(3002,'Gone Fishing 2','Kumar',22.22,22)," +
                    "(3003,'Gone Fishing 3','Kumar',33.33,33)";
            System.out.println("The SQL statement is: "+ sqlInsert +"\n");
            countInserted = statement.executeUpdate(sqlInsert);
            System.out.println(countInserted+"record insert");
//            Issue a SELECT to check change
            String strSelect = "select * from books";
            System.out.println("The SQL statement is: "+ strSelect +"\n");
            ResultSet resultSet = statement.executeQuery(strSelect);
            while (resultSet.next()){
                System.out.println(resultSet.getInt("id") + ", "
                        + resultSet.getString("author") + ", "
                        + resultSet.getString("title") + ", "
                        + resultSet.getDouble("price") + ", "
                        + resultSet.getInt("qty")
                );
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
