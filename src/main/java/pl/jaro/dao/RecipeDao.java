package pl.jaro.dao;

import pl.jaro.exception.NotFoundException;
import pl.jaro.model.Book;
import pl.jaro.model.Recipe;
import pl.jaro.model.RecipePlan;
import pl.jaro.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RecipeDao {

    private static final String CREATE_RECIPE_QUERY = "INSERT INTO recipe(name,ingredients,description,preparation_time,preparation,admin_id,created,updated) VALUES (?,?,?,?,?,?,?,?);";
    private static final String DELETE_RECIPE_QUERY = "DELETE FROM recipe where id = ?;";
    private static final String FIND_ALL_RECIPES_QUERY = "select * from recipe where admin_id =?";
    private static final String FIND_ALL_RECIPES = "select * from recipe";
    private static final String READ_RECIPE_QUERY = "SELECT * from recipe where id = ?;";
    private static final String UPDATE_RECIPE_QUERY = "UPDATE recipe SET name = ? , ingredients =?, description = ?, preparation_time = ?, preparation = ?, updated =? WHERE	id = ?;";
    private static final String ADD_RECIPE_PLAN ="INSERT INTO recipe_plan (recipe_id, meal_name, display_order, day_name_id, plan_id) VALUES (?, ?, ?, ?, ?)";

    private static final String FIND_ALL_SORTED_BY_DATE = "select * from recipe order by created desc;";
    private static final String FIND_RECIPE_BY_NAME = "select * from recipe  where name like '?%' order by created desc;";

    public Recipe read(Integer recipeId) {
        Recipe recipe = new Recipe();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPE_QUERY)) {
            statement.setInt(1, recipeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setIngredients(resultSet.getString("ingredients"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setCreated(resultSet.getString("created"));
                    recipe.setUpdated(resultSet.getString("updated"));
                    recipe.setPreparationTime(resultSet.getInt("preparation_time"));
                    recipe.setPreparation(resultSet.getString("preparation"));
                    recipe.setAdminId(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;

    }

    public List<Recipe> findAll(int adminId) {
        List<Recipe> recipeList = new ArrayList<>();
        String query = FIND_ALL_RECIPES_QUERY.replace("?",String.valueOf(adminId));

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
               Recipe recipe =new Recipe();

                recipe.setId(resultSet.getInt("id"));
                recipe.setName(resultSet.getString("name"));
                recipe.setIngredients(resultSet.getString("ingredients"));
                recipe.setDescription(resultSet.getString("description"));
                recipe.setCreated(resultSet.getString("created"));
                recipe.setUpdated(resultSet.getString("updated"));
                recipe.setPreparationTime(resultSet.getInt("preparation_time"));
                recipe.setPreparation(resultSet.getString("preparation"));
                recipe.setAdminId(resultSet.getInt("admin_id"));
                recipeList.add(recipe);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;

    }
    public List<Recipe> findAllNotLogin() {
        List<Recipe> recipeList = new ArrayList<>();

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPES);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
               Recipe recipe =new Recipe();

                recipe.setId(resultSet.getInt("id"));
                recipe.setName(resultSet.getString("name"));
                recipe.setIngredients(resultSet.getString("ingredients"));
                recipe.setDescription(resultSet.getString("description"));
                recipe.setCreated(resultSet.getString("created"));
                recipe.setUpdated(resultSet.getString("updated"));
                recipe.setPreparationTime(resultSet.getInt("preparation_time"));
                recipe.setPreparation(resultSet.getString("preparation"));
                recipe.setAdminId(resultSet.getInt("admin_id"));
                recipeList.add(recipe);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;

    }
    public List<Recipe> findAllSortedByDate() {
        List<Recipe> recipeList = new ArrayList<>();

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_SORTED_BY_DATE);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
               Recipe recipe =new Recipe();

                recipe.setId(resultSet.getInt("id"));
                recipe.setName(resultSet.getString("name"));
                recipe.setIngredients(resultSet.getString("ingredients"));
                recipe.setDescription(resultSet.getString("description"));
                recipe.setCreated(resultSet.getString("created"));
                recipe.setUpdated(resultSet.getString("updated"));
                recipe.setPreparationTime(resultSet.getInt("preparation_time"));
                recipe.setPreparation(resultSet.getString("preparation"));
                recipe.setAdminId(resultSet.getInt("admin_id"));
                recipeList.add(recipe);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;

    }
    public List<Recipe> findAllByName(String name) {
        List<Recipe> recipeList = new ArrayList<>();
        String query = FIND_RECIPE_BY_NAME.replace("?",name);
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
               Recipe recipe =new Recipe();

                recipe.setId(resultSet.getInt("id"));
                recipe.setName(resultSet.getString("name"));
                recipe.setIngredients(resultSet.getString("ingredients"));
                recipe.setDescription(resultSet.getString("description"));
                recipe.setCreated(resultSet.getString("created"));
                recipe.setUpdated(resultSet.getString("updated"));
                recipe.setPreparationTime(resultSet.getInt("preparation_time"));
                recipe.setPreparation(resultSet.getString("preparation"));
                recipe.setAdminId(resultSet.getInt("admin_id"));
                recipeList.add(recipe);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;

    }


    public Recipe create(Recipe recipe,Integer adminId) {

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_RECIPE_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, recipe.getName());
            insertStm.setString(2, recipe.getIngredients());
            insertStm.setString(3, recipe.getDescription());
            insertStm.setInt(4, recipe.getPreparationTime());
            insertStm.setString(5, recipe.getPreparation());
            insertStm.setInt(6, adminId);
            insertStm.setString(7, recipe.getCreated());
            insertStm.setString(8, recipe.getUpdated());

            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipe.setId(generatedKeys.getInt(1));
                    return recipe;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public RecipePlan createRecipePlan(RecipePlan recipePlan) {

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_RECIPE_PLAN,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, recipePlan.getRecipeId());
            statement.setString(2, recipePlan.getMealName());
            statement.setInt(3, recipePlan.getDisplayOrder());
            statement.setInt(4, recipePlan.getDayNameId());
            statement.setInt(5, recipePlan.getPlanId());

            int result = statement.executeUpdate();

            System.out.println(recipePlan);
            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipePlan.setId(generatedKeys.getInt(1));
                    return recipePlan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public void delete(Integer recipeId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_QUERY)) {
            statement.setInt(1, recipeId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void update(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_RECIPE_QUERY)) {

            statement.setString(1, recipe.getName());
            statement.setString(2, recipe.getIngredients());
            statement.setString(3, recipe.getDescription());
            statement.setInt(4, recipe.getPreparationTime());
            statement.setString(5, recipe.getPreparation());
            statement.setString(6,recipe.getUpdated());
            statement.setInt(7, recipe.getId());


            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
