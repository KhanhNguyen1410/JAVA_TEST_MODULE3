package service.category;

import model.Category;
import service.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceIPL implements MyCategoryService{
    Connection connection = ConnectDB.getConnect();

    private static final String FIND_ALL_CATEGORY = "{call findAllCategory()}";
    private static final String FIND_CATEGORY_BY_ID = "{call findById(?)}";
    private static final String FIND_CATEGORY_BY_NAME = "{call findCategoryByName(?)}";



    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        try {
            CallableStatement statement = connection.prepareCall(FIND_ALL_CATEGORY);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    categories.add(new Category(id,name));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return categories;
    }


    @Override
    public Category getCategoryById(int id) {
        Category category = null;
        try {
            CallableStatement statement = connection.prepareCall(FIND_CATEGORY_BY_ID);
            statement.setInt(1,id);
            ResultSet rs= statement.executeQuery();
            while (rs.next()){
                String name = rs.getString("name");
                category = new Category(id,name);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return category;
    }

    @Override
    public Category getCategoryByName(String namec) {
        Category category = null;
        try {
            CallableStatement statement = connection.prepareCall(FIND_CATEGORY_BY_NAME);
            statement.setString(1,namec);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                category = new Category(id,name);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return category;
    }
}
