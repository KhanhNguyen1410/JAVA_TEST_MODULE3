package service.product;

import javafx.scene.chart.CategoryAxis;
import model.Category;
import model.Product;
import service.ConnectDB;
import service.category.CategoryServiceIPL;
import service.category.MyCategoryService;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductServiceIPL implements MyProductService {
    Connection connection = ConnectDB.getConnect();
    MyCategoryService categoryService = new CategoryServiceIPL();

    private static final String INSERT_PRODUCT_SQL = "{call insertNewProduct(?,?,?,?,?,?)}";

    private static final String FIND_ALL_PRODUCT = "call findAllProduct()";
    private static final String UPDATE_PRODUCT = "call updateProduct(?,?,?,?,?,?,?)";
    private static final String DELETE_PRODUCT = "call deleteProduct(?)";
    private static final String FIND_PRODUCT_BY_ID = "call findProductById(?)";
    private static final String SEARCH = "call searchByName(?)";

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try {
            CallableStatement statement = connection.prepareCall(FIND_ALL_PRODUCT);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String description = rs.getString("description");
                int category_id = rs.getInt("category_id");
                Category category = categoryService.getCategoryById(category_id);
                products.add(new Product(id, name, price, quantity, color, description, category));

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return products;
    }

    @Override
    public void insert(Product product) {
        try {
            CallableStatement statement = connection.prepareCall(INSERT_PRODUCT_SQL);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getQuantity());
            statement.setString(4, product.getColor());
            statement.setString(5, product.getDescription());
            statement.setInt(6, product.getCategory().getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(Product product) {

        CallableStatement statement = null;
        try {
            statement = connection.prepareCall(UPDATE_PRODUCT);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getQuantity());
            statement.setString(4, product.getColor());
            statement.setString(5, product.getDescription());
            statement.setInt(6, product.getCategory().getId());
            statement.setInt(7, product.getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }


    }

    @Override
    public void delete(int id) {
        try {
            CallableStatement statement = connection.prepareCall(DELETE_PRODUCT);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Product findById(int id) {
        Product product = null;
        try {
            CallableStatement statement = connection.prepareCall(FIND_PRODUCT_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String description = rs.getString("description");
                int category_id = rs.getInt("category_id");
                Category category = categoryService.getCategoryById(category_id);
                product = new Product(id,name,price,quantity,color,description,category);
            }
            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> searchByName(String name) {
            List<Product> list= new ArrayList<>();
            try {
                CallableStatement callableStatement= connection.prepareCall(SEARCH);
                callableStatement.setString(1, name);
                ResultSet resultSet= callableStatement.executeQuery();
                while (resultSet.next()){
                    int id= resultSet.getInt("id");
//                   String nameproduct= resultSet.getString("name");
                    double price = resultSet.getDouble("price");
                    int quantity= resultSet.getInt("quantity");
                    String color= resultSet.getString("color");
                    String desc= resultSet.getString("description");
                    int category= resultSet.getInt("category_id");
                    Category category1= categoryService.getCategoryById(category);
                    list.add(new Product(id, name, price, quantity, color, desc,category1));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return list;
    }
}
