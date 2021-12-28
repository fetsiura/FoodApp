package pl.jaro.dao;

import pl.jaro.model.DayName;
import pl.jaro.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DayNameDao {

    private static final String FIND_ALL_DAYS_QUERY = "SELECT * FROM day_name;";

    public List<DayName> findAll (){
        List<DayName> dayNames = new ArrayList<>();

        try(Connection connection = DbUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_DAYS_QUERY);
            ResultSet resultSet = statement.executeQuery()){

            while (resultSet.next()){
                DayName day = new DayName();
                day.setId(resultSet.getInt("id"));
                day.setName(resultSet.getString("name"));
                dayNames.add(day);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dayNames;
    }

}
