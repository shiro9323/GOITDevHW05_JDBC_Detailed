import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabasePopulateService {

    public static final String POPULATE_BD_SQL = "sql/populate_db.sql";
    private static PreparedStatement preparedStatement;
    private final Connection connection = Database.getInstance().getConnection();

   public static void main(String[] args) throws SQLException {
        /*Connection connection = Database.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = Files.readString(Path.of(POPULATE_BD_SQL));
            statement.executeUpdate(sql);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }*/
       DatabasePopulateService databasePopulateService = new DatabasePopulateService();

       databasePopulateService.insertClient();
       databasePopulateService.insertProject();
       databasePopulateService.insertWorker();
       databasePopulateService.insertProject_Worker();
    }

    public void insertClient() throws SQLException{

        preparedStatement = connection.prepareStatement("INSERT INTO client (name) VALUES (?)");
        try {

            batchUpdate( "Name1");
            batchUpdate( "Name2");

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        preparedStatement.executeBatch();
        //preparedStatement.getConnection().close();

    }

    public void insertProject() throws SQLException{

        preparedStatement = connection.prepareStatement("INSERT INTO project (client_id, start_date, finish_date) VALUES (?, ?, ?)");
        try {

            batchUpdate( 1, "2022-01-01", "2022-12-31");
            batchUpdate( 2, "2023-01-01", "2023-12-31");

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        preparedStatement.executeBatch();
        //preparedStatement.getConnection().close();

    }

    public void insertWorker() throws SQLException{

        preparedStatement = connection.prepareStatement("INSERT INTO worker (name, birthday, level, salary) VALUES (?, ?, ?, ?)");
        try {

            batchUpdate( "Andrii","1993-01-01", "Junior", 1000);
            batchUpdate( "Ina","1988-02-02", "Middle", 2000);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        preparedStatement.executeBatch();
        //preparedStatement.getConnection().close();

    }

    public void insertProject_Worker() throws SQLException{

        preparedStatement = connection.prepareStatement("INSERT INTO project_worker (project_id , worker_id) VALUES (?, ?)");
        try {

            batchUpdate( 1, 9);
            batchUpdate( 2, 10);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        preparedStatement.executeBatch();
        //preparedStatement.getConnection().close();

    }


    private static void batchUpdate(String name) throws SQLException {
        preparedStatement.setString(1, name);
        preparedStatement.addBatch();
    }

    private static void batchUpdate(int project_id, int worker_id) throws SQLException {
        preparedStatement.setInt(1, project_id);
        preparedStatement.setInt(2, worker_id);
        preparedStatement.addBatch();
    }
    private static void batchUpdate(String name, String birthday, String level,  int salary) throws SQLException {
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, birthday);
        preparedStatement.setString(3, level);
        preparedStatement.setInt(4, salary);
        preparedStatement.addBatch();
    }

    private static void batchUpdate(int id, String startDate, String finishDate) throws SQLException {

       preparedStatement.setInt(1, id);
       preparedStatement.setString(2, startDate);
       preparedStatement.setString(3, finishDate);
       preparedStatement.addBatch();
    }
}
