import Resources.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    private static final String FIND_LONGEST_PROJECT_SQL = "sql/find_longest_project.sql";
    private static final String FIND_MAX_PROJECTS_CLIENT_SQL = "sql/find_max_projects_client.sql";
    private static final String FIND_MAX_SALARY_WORKER_SQL = "sql/find_max_salary_worker.sql";
    private static final String FIND_YOUNGEST_ELDEST_WORKERS_SQL = "sql/find_youngest_eldest_workers.sql";
    private static final String FIND_PROJECT_PRICES_SQL = "sql/print_project_prices.sql";
    private final Connection connection = Database.getInstance().getConnection();

    public List<LongestProject> findLongestProject() {
        List<LongestProject> longestProjects = new ArrayList<>();
        try {

            String sql = Files.readString(Path.of(FIND_LONGEST_PROJECT_SQL));
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                LongestProject longestProject = new LongestProject();
                longestProject.setId(resultSet.getInt("id"));
                longestProject.setMonthCount(resultSet.getInt("month_count"));
                longestProjects.add(longestProject);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return longestProjects;
    }

    public List<MaxProjectsClient> findMaxProjectClient() {
        List<MaxProjectsClient> maxProjectsClients = new ArrayList<>();
        try {

            String sql = Files.readString(Path.of(FIND_MAX_PROJECTS_CLIENT_SQL));
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                MaxProjectsClient maxProjectsClient = new MaxProjectsClient();
                maxProjectsClient.setName(resultSet.getString("name"));
                maxProjectsClient.setProjectCount(resultSet.getInt("count_project"));
                maxProjectsClients.add(maxProjectsClient);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return maxProjectsClients;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() {
        List<MaxSalaryWorker> maxSalaryWorkers = new ArrayList<>();
        try {

            String sql = Files.readString(Path.of(FIND_MAX_SALARY_WORKER_SQL));
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                MaxSalaryWorker maxSalaryWorker = new MaxSalaryWorker();
                maxSalaryWorker.setName(resultSet.getString("name"));
                maxSalaryWorker.setSalary(resultSet.getInt("salary"));
                maxSalaryWorkers.add(maxSalaryWorker);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return maxSalaryWorkers;
    }

    public List<YoungestEldestWorker> findYoungestEldestWorkers() {
        List<YoungestEldestWorker> youngestEldestWorkers = new ArrayList<>();
        try {

            String sql = Files.readString(Path.of(FIND_YOUNGEST_ELDEST_WORKERS_SQL));
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                YoungestEldestWorker youngestEldestWorker = new YoungestEldestWorker();
                youngestEldestWorker.setType(resultSet.getString("type"));
                youngestEldestWorker.setName(resultSet.getString("name"));
                youngestEldestWorker.setBirthday(resultSet.getString("birthday"));
                youngestEldestWorkers.add(youngestEldestWorker);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return youngestEldestWorkers;
    }

    public List<ProjectPrice> findProjectPrices() {
        List<ProjectPrice> projectPrices = new ArrayList<>();
        try {

            String sql = Files.readString(Path.of(FIND_PROJECT_PRICES_SQL));
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ProjectPrice projectPrice = new ProjectPrice();
                projectPrice.setId(resultSet.getString("id"));
                projectPrice.setPrice(resultSet.getInt("overall_price"));
                projectPrices.add(projectPrice);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return projectPrices;
    }
}
