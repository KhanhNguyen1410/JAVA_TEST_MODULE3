package controller;

import model.Category;
import model.Product;
import service.category.CategoryServiceIPL;
import service.category.MyCategoryService;
import service.product.MyProductService;
import service.product.ProductServiceIPL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "productServlet", urlPatterns = "/products")
public class productServlet extends HttpServlet {
    MyProductService productService = new ProductServiceIPL();
    MyCategoryService categoryService = new CategoryServiceIPL();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                insertNewProduct(request,response);
                break;
            case "edit":
                updateProduct(request,response);
                break;
            case "delete":
                deleteProduct(request,response);
                break;
            case "search":
                searchProduct(request,response);
                break;
            default:
                showListProduct(request,response);
        }
    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreatForm(request,response);
                break;
            case "edit":
                showEditForm(request,response);
                break;
            case "delete":
                showDeleteForm(request,response);
                break;
            default:
                showListProduct(request,response);
        }
    }



    private void showCreatForm(HttpServletRequest request, HttpServletResponse response) {
        List<Category> categories=categoryService.findAll();
        request.setAttribute("categorys",categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/create.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void insertNewProduct(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int category_id = Integer.parseInt(request.getParameter("category"));
        Category category = categoryService.getCategoryById(category_id);
//        String category_name = request.getParameter("category");
//        Category category =categoryService.getCategoryByName(category_name);

        Product product = new Product(name,price,quantity,color,description,category);
        productService.insert(product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/create.jsp");
        request.setAttribute("products","product has created");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void showListProduct(HttpServletRequest request, HttpServletResponse response) {
        List<Product> productList=productService.findAll();

        request.setAttribute("products",productList);
        RequestDispatcher dispatcher=request.getRequestDispatcher("product/listProduct.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        List<Category> categories=categoryService.findAll();
        request.setAttribute("categorys",categories);
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        request.setAttribute("products",product);

        RequestDispatcher dispatcher = request.getRequestDispatcher("product/edit.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void updateProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int category_id = Integer.parseInt(request.getParameter("category"));
        Category category = categoryService.getCategoryById(category_id);
        Product product = new Product(id,name,price,quantity,color,description,category);
//        request.setAttribute("products",product);
        productService.update(product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/edit.jsp");
        request.setAttribute("products","product has created");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/delete.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        request.setAttribute("products",product);
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        RequestDispatcher dispatcher;
        productService.delete(id);
        dispatcher = request.getRequestDispatcher("product/delete.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void searchProduct(HttpServletRequest request, HttpServletResponse response) {
        String name= request.getParameter("search");
        List<Product> products= productService.searchByName(name);
        RequestDispatcher dispatcher= request.getRequestDispatcher("product/listProduct.jsp");
        request.setAttribute("products", products);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
