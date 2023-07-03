import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseQueryService databaseQueryService = new DatabaseQueryService();

        //test

        System.out.println("findLongestProject:" + "\n" + databaseQueryService.findLongestProject());
        System.out.println("findMaxProjectClient:" + "\n" + databaseQueryService.findMaxProjectClient());
        System.out.println("findMaxSalaryWorker:" + "\n" + databaseQueryService.findMaxSalaryWorker());
        System.out.println("findProjectPrices:" + "\n" + databaseQueryService.findProjectPrices());
        System.out.println("findYoungestEldestWorkers:" + "\n" + databaseQueryService.findYoungestEldestWorkers());
    }
}
