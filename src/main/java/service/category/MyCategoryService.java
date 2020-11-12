package service.category;

import model.Category;

import java.util.List;

public interface MyCategoryService {
    List<Category> findAll();
    Category getCategoryById(int id);
    Category getCategoryByName(String category);

}
