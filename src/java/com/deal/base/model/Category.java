package com.deal.base.model;

import java.io.Serializable;

public class Category implements Serializable {

    private long categoryId;
    private String categoryName;

//    nagib
//
//   
//    marzok
//
//   
//    mokhtar
//
//   
//    yomna
//
//
    public Category() {
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getCategoryId() {
        return categoryId;
    }
    
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category{" + "categoryId=" + categoryId + ", categoryName=" + categoryName + '}';
    }
    
}
