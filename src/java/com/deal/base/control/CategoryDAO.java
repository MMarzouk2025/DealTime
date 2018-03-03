package com.deal.base.control;

/* Marzouk */
import com.deal.base.model.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
public class CategoryDAO {

    private Connection mConn;
    public static final String SUCCESSFUL_INSERT = "new category has been created successfully";
    public static final String SUCCESSFUL_UPDATE = "category has been updated successfully";
    public static final String SUCCESSFUL_DELETE = "category has been deleted successfully";
    public static final String EXISTING_CATEGORY = "This category already exists";
    public static final String DELETING_CATEGORY_ERROR = "error while deleting category";
    public static final String EXCEPTION = "exception happened";

    /* Marzouk */
    public CategoryDAO(Connection mConn) {
        this.mConn = mConn;
    }

    public List<Category> retrieveAllCategories() {
        ArrayList<Category> categories = new ArrayList();
        try {
            ResultSet results = mConn.createStatement().executeQuery("SELECT CATEGORY_ID, CATEGORY_NAME\n"
                    + "FROM DEALTIME.CATEGORIES");
            while (results.next()) {
                Category category = new Category();
                category.setCategoryId(results.getLong("CATEGORY_ID"));
                category.setCategoryName(results.getString("CATEGORY_NAME"));
                categories.add(category);
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
        return categories;
    }

    public String insertCategory(Category category) {
        String result;
        boolean isCategoryExisting = checkCategoryExistance(category.getCategoryName());
        try {
            if (isCategoryExisting) {
                result = EXISTING_CATEGORY;
            } else {
                PreparedStatement stmt = mConn.prepareStatement("INSERT INTO DEALTIME.CATEGORIES (CATEGORY_ID, CATEGORY_NAME)\n"
                        + "VALUES (?, ?)");
                stmt.setLong(1, category.getCategoryId());
                stmt.setString(2, category.getCategoryName());
                stmt.execute();
                result = SUCCESSFUL_INSERT;
            }
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

    private boolean checkCategoryExistance(String categoryName) {
        boolean result = false;
        try {
            ResultSet rSet = mConn.createStatement().executeQuery("SELECT COUNT(*) FROM DEALTIME.CATEGORIES\n"
                    + "WHERE UPPER(CATEGORY_NAME) = UPPER('" + categoryName + "')");
            if (rSet.next()) {
                if (rSet.getInt(1) > 0) {
                    result = true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            result = false;
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
