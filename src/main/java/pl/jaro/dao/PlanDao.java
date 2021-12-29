package pl.jaro.dao;

import pl.jaro.exception.NotFoundException;
import pl.jaro.model.Book;
import pl.jaro.model.LastPlan;
import pl.jaro.model.Plan;
import pl.jaro.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanDao {

    private static final String CREATE_PLAN_QUERY = "INSERT INTO plan(name, description, created, admin_id) VALUES (?,?,?,?);";
    private static final String DELETE_PLAN_QUERY = "DELETE FROM plan where id = ?;";
    private static final String DELETE_RECIPE_FROM_PLAN = "delete recipe_plan from recipe_plan where id = ?";
    private static final String READ_PLAN_QUERY = "SELECT * from plan where id = ?;";
    private static final String FIND_ALL_SORTED_BY_CREATED = "select * from plan where admin_id = ? order by created;";
    private static final String READ_NAME_OF_LAST_PLAN_QUERY = "SELECT * FROM plan where admin_id = ? ORDER BY ID DESC LIMIT 1";
    private static final String UPDATE_PLAN_QUERY = "UPDATE	plan SET name = ? , description = ?, created =? WHERE	id = ?;";
    private static final String FIND_LAST_PLAN_QUERY = "SELECT recipe_id, day_name.name as day_name, meal_name,recipe.name as recipe_name, recipe.description as recipe_description\n" +
            "FROM `recipe_plan` JOIN day_name on day_name.id=day_name_id JOIN recipe on recipe.id=recipe_id WHERE recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE admin_id = ?) ORDER by day_name.display_order, recipe_plan.display_order;";
    private static final String DETAILS_OF_SOME_PLAN = "SELECT recipe_plan.id as id, recipe_id as recipe_id, day_name.name as day_name, meal_name, recipe.name as recipe_name, recipe.description as recipe_description FROM `recipe_plan`\n" +
            "         JOIN day_name on day_name.id=day_name_id\n" +
            "         JOIN recipe on recipe.id=recipe_id WHERE plan_id = ?\n" +
            "ORDER by day_name.display_order, recipe_plan.display_order;";

    private static final String CHECK_IF_PLAN_IS_EMPTY = "select * from recipe_plan where plan_id =?";


    public Plan read(Integer planId) {
        Plan plan = new Plan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_PLAN_QUERY)
        ) {
            statement.setInt(1, planId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    plan.setId(resultSet.getInt("id"));
                    plan.setName(resultSet.getString("name"));
                    plan.setDescription(resultSet.getString("description"));
                    plan.setCreated(resultSet.getString("created"));
                    plan.setAdminId(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plan;

    }

    public Plan readName(int adminId) {
        Plan plan = new Plan();

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_NAME_OF_LAST_PLAN_QUERY)
        ) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    plan.setId(resultSet.getInt("id"));
                    plan.setName(resultSet.getString("name"));
                    plan.setDescription(resultSet.getString("description"));
                    plan.setCreated(resultSet.getString("created"));
                    plan.setAdminId(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plan;

    }





    public Plan create(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_PLAN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, plan.getName());
            statement.setString(2, plan.getDescription());
            statement.setString(3,plan.getCreated());
            statement.setInt(4, plan.getAdminId());
            int result = statement.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    plan.setId(generatedKeys.getInt(1));
                    return plan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PLAN_QUERY)) {
            statement.setString(1, plan.getName());
            statement.setString(2, plan.getDescription());
            statement.setString(3,plan.getCreated());
            statement.setInt(4, plan.getId());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void delete(Integer planId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PLAN_QUERY)) {
            statement.setInt(1, planId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteRecipeFromPlan(Integer planId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_FROM_PLAN)) {
            statement.setInt(1, planId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public List<Plan> findAllSortedByCreated(int adminId) {
        List<Plan> planList = new ArrayList<>();
        String query = FIND_ALL_SORTED_BY_CREATED.replace("?",String.valueOf(adminId));
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(resultSet.getInt("id"));
                planToAdd.setName(resultSet.getString("name"));
                planToAdd.setDescription(resultSet.getString("description"));
                planToAdd.setCreated(resultSet.getString("created"));
                planToAdd.setAdminId(resultSet.getInt("admin_id"));
                planList.add(planToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;

    }
    public List<Plan> checkIfPlanIsEmpty(int planId) {
        List<Plan> planList = new ArrayList<>();
        String query = CHECK_IF_PLAN_IS_EMPTY.replace("?",String.valueOf(planId));
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(resultSet.getInt("id"));
                planList.add(planToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;

    }

    public List<LastPlan> last(int adminId) {

        String query = FIND_LAST_PLAN_QUERY.replace("?",String.valueOf(adminId));
        List<LastPlan> plans = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                LastPlan last = new LastPlan();
                last.setRecipeId(resultSet.getInt("recipe_id"));
                last.setDayName(resultSet.getString("day_name"));
                last.setMealName(resultSet.getString("meal_name"));
                last.setRecipeName(resultSet.getString("recipe_name"));
                last.setRecipeDescription(resultSet.getString("recipe_description"));
                plans.add(last);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plans;
    }
    public List<LastPlan> detailsOfLastPlan(int planId) {

        String query = DETAILS_OF_SOME_PLAN.replace("?",String.valueOf(planId));
        List<LastPlan> plans = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                LastPlan last = new LastPlan();

                last.setId(resultSet.getInt("id"));
                last.setRecipeId(resultSet.getInt("recipe_id"));
                last.setDayName(resultSet.getString("day_name"));
                last.setMealName(resultSet.getString("meal_name"));
                last.setRecipeName(resultSet.getString("recipe_name"));
                last.setRecipeDescription(resultSet.getString("recipe_description"));
                plans.add(last);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plans;
    }

}
