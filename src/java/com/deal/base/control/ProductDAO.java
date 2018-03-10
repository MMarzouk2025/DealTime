package com.deal.base.control;

/* Marzouk */
import com.deal.base.model.Category;
import com.deal.base.model.Product;
import com.deal.control.DbHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * ********
 */
/* Nagib */
/**
 * ********
 */
/* Mokhtar */
/**
 * ********
 */
/* Ibrahim */
/**
 * ********
 */
/* Yomna */
/**
 * ********
 */
public class ProductDAO {

    /* Marzouk */
    private Connection mConn;
    public static final String SUCCESSFUL_INSERT = "product has been added successfully";
    public static final String SUCCESSFUL_UPDATE = "product info has been updated successfully";
    public static final String SUCCESSFUL_DELETE = "product has been deleted successfully";
    public static final String DELETING_PRODUCT_ERROR = "error while deleting product";
    public static final String EXCEPTION = "exception happened";

    public ProductDAO(Connection mConn) {
        this.mConn = mConn;
    }

    public Product retrieveProduct(long productId) {
        Product product = null;
        try {
            ResultSet results = mConn.createStatement().executeQuery("SELECT PRODUCT_ID, PRODUCT_NAME, PRODUCT_DESCRIPTION,\n"
                    + "PRICE, AVAILABLE_QUANTITY, CATEGORY_ID\n"
                    + "FROM DEALTIME.PRODUCTS\n"
                    + "WHERE PRODUCT_ID = " + productId);
            if (results.next()) {
                product = new Product();
                product.setProductId(productId);
                product.setProductName(results.getString("PRODUCT_NAME"));
                product.setProductDesc(results.getString("PRODUCT_DESCRIPTION"));
                product.setProductPrice(results.getDouble("PRICE"));
                product.setAvailableQuantity(results.getInt("AVAILABLE_QUANTITY"));
                Category category = DbHandler.getCategoryDAO().retrieveCategory(results.getLong("CATEGORY_ID"));
                product.setProductCategory(category);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                mConn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return product;
    }

    public List<Product> retrieveAllProduct() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            ResultSet results = mConn.createStatement().executeQuery("SELECT PRODUCT_ID, PRODUCT_NAME, PRODUCT_DESCRIPTION,\n"
                    + "PRICE, AVAILABLE_QUANTITY, CATEGORY_ID\n"
                    + "FROM DEALTIME.PRODUCTS");
            while (results.next()) {
                Product product = new Product();
                product.setProductId(results.getLong("PRODUCT_ID"));
                product.setProductName(results.getString("PRODUCT_NAME"));
                product.setProductDesc(results.getString("PRODUCT_DESCRIPTION"));
                product.setProductPrice(results.getDouble("PRICE"));
                product.setAvailableQuantity(results.getInt("AVAILABLE_QUANTITY"));
                Category category = DbHandler.getCategoryDAO().retrieveCategory(results.getLong("CATEGORY_ID"));
                product.setProductCategory(category);
                products.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                mConn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return products;
    }

    public String insertProduct(Product product) {
        String result;
        try {
            PreparedStatement stmt = mConn.prepareStatement("INSERT INTO DEALTIME.PRODUCTS (PRODUCT_ID, PRODUCT_NAME,\n"
                    + "PRODUCT_DESCRIPTION, PRICE, AVAILABLE_QUANTITY, CATEGORY_ID)\n"
                    + "VALUES (PRODUCTS_SEQ.NEXTVAL, ?, ?, ?, ?, ?)");
            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getProductDesc());
            stmt.setDouble(3, product.getProductPrice());
            stmt.setInt(4, product.getAvailableQuantity());
            if (product.getProductCategory() == null) {
                stmt.setNull(5, Types.BIGINT);
            } else {
                stmt.setLong(5, product.getProductCategory().getCategoryId());
            }
            stmt.execute();
            result = SUCCESSFUL_INSERT;
        } catch (SQLException ex) {
            ex.printStackTrace();
            result = EXCEPTION;
        } finally {
            try {
                mConn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public String updateProduct(Product product) {
        String result;
        try {
            PreparedStatement stmt = mConn.prepareStatement("UPDATE DEALTIME.PRODUCTS\n"
                    + "SET PRODUCT_NAME = ?, PRODUCT_DESCRIPTION = ?, PRICE = ?,\n"
                    + "AVAILABLE_QUANTITY = ?, CATEGORY_ID = ?\n"
                    + "WHERE PRODUCT_ID = " + product.getProductId());
            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getProductDesc());
            stmt.setDouble(3, product.getProductPrice());
            stmt.setInt(4, product.getAvailableQuantity());
            if (product.getProductCategory() == null) {
                stmt.setNull(5, Types.BIGINT);
            } else {
                stmt.setLong(5, product.getProductCategory().getCategoryId());
            }
            stmt.execute();
            result = SUCCESSFUL_UPDATE;
        } catch (SQLException ex) {
            ex.printStackTrace();
            result = EXCEPTION;
        } finally {
            try {
                mConn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    // find solution for deleting products have orders before deleting them //
    public String deleteProduct(long productId) {
        DbHandler.getOrderDAO().invalidateProductOrders(productId);
        String result;
        try {
            PreparedStatement stmt = mConn.prepareStatement("DELETE DEALTIME.PRODUCTS WHERE PRODUCT_ID = " + productId);
            stmt.execute();
            result = SUCCESSFUL_DELETE;
        } catch (SQLException ex) {
            ex.printStackTrace();
            result = EXCEPTION;
        } finally {
            try {
                mConn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * ********
     */
    /* Nagib */
    /**
     * ********
     */
    /* Mokhtar */
    /**
     * ********
     */
    /* Ibrahim */
    /**
     * ********
     */
    /* Yomna */
    /**
     * ********
     */
}
