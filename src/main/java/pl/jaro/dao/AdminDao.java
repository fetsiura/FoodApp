package pl.jaro.dao;

import org.mindrot.jbcrypt.BCrypt;
import pl.jaro.model.Admins;
import pl.jaro.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

    private static final String CREATE_ADMIN_QUERY = "INSERT INTO admins(first_name, last_name, email, password, superadmin, enable) VALUES(?, ?, ?, ?, ?, ?);";
    private static final String DELETE_ADMIN_QUERY = "DELETE FROM admins where id = ?;";
    private static final String FIND_ALL_ADMINS_QUERY = "SELECT * FROM admins;";
    private static final String READ_ADMIN_QUERY = "SELECT * from admins where id = ?;";
    private static final String UPDATE_ADMIN_QUERY = "UPDATE admins SET first_name = ? , last_name = ?, email = ? , password = ?, superadmin = ? WHERE	id = ?;";

    private Admins read(Integer adminId){
        Admins admin = new Admins();

        try(Connection connection = DbUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(READ_ADMIN_QUERY)){

            statement.setInt(1,adminId);
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    admin.setId(resultSet.getInt("id"));
                    admin.setFirstName(resultSet.getString("firstName"));
                    admin.setLastName(resultSet.getString("lastName"));
                    admin.setEmail(resultSet.getString("email"));
                    admin.setPassword(resultSet.getString("password"));
                    admin.setSuperadmin(resultSet.getInt("superadmin"));
                    admin.setEnable(resultSet.getInt("enable"));
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return admin;
    }

     public List<Admins> findAll(){
        List<Admins> admins = new ArrayList<>();

        try(Connection connection = DbUtil.getConnection();
        PreparedStatement statement =connection.prepareStatement(FIND_ALL_ADMINS_QUERY);
        ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()){
                Admins adminsToAdd = new Admins();
                adminsToAdd.setId(resultSet.getInt("id"));
                adminsToAdd.setFirstName(resultSet.getString("firstName"));
                adminsToAdd.setLastName(resultSet.getString("lastName"));
                adminsToAdd.setEmail(resultSet.getString("email"));
                adminsToAdd.setPassword(resultSet.getString("password"));
                adminsToAdd.setSuperadmin(resultSet.getInt("superadmin"));
                adminsToAdd.setEnable(resultSet.getInt("enable"));
                admins.add(adminsToAdd);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return admins;
     }

     public Admins create(Admins admins){
        try(Connection connection = DbUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(CREATE_ADMIN_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)){
            statement.setString(1,admins.getFirstName());
            statement.setString(2,admins.getLastName());
            statement.setString(3, admins.getEmail());
            String hashed = BCrypt.hashpw(admins.getPassword(),BCrypt.gensalt());
            statement.setString(4,hashed);
            statement.setInt(5,admins.getSuperadmin());
            statement.setInt(6,admins.getEnable());
            int result = statement.executeUpdate();

            if(result != 1){
                throw  new RuntimeException("Execute update returned "+ result);
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()){
                if(generatedKeys.first()){
                    admins.setId(generatedKeys.getInt(1));
                    return admins;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
     }

     public void delete (Integer adminId){
        try(Connection connection = DbUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_ADMIN_QUERY)){
            statement.setInt(1,adminId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if(!deleted){
                throw new RuntimeException("Product not found");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
     }

     public void update (Admins admins){
        try(Connection connection = DbUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_ADMIN_QUERY)){
            statement.setString(1,admins.getFirstName());
            statement.setString(2,admins.getLastName());
            statement.setString(3,admins.getEmail());
            statement.setString(4,BCrypt.hashpw(admins.getPassword(),BCrypt.gensalt()));
            statement.setInt(5,admins.getSuperadmin());
            statement.setInt(6,admins.getEnable());
            statement.setInt(7,admins.getId());
            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
     }
}
