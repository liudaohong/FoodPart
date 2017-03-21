package com.liudaohong.foodparty.Bean;

import java.util.List;

/**
 * Created by liuhong on 2017/3/18.
 */

public class FoodTabBean {
    /**
     * categories_count : 4
     * categories : [{"id":1,"name":"首页"},{"id":2,"name":"评测"},{"id":3,"name":"知识"},{"id":4,"name":"美食"}]
     */

    private int categories_count;
    private List<CategoriesBean> categories;

    public int getCategories_count() {
        return categories_count;
    }

    public void setCategories_count(int categories_count) {
        this.categories_count = categories_count;
    }

    public List<CategoriesBean> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesBean> categories) {
        this.categories = categories;
    }

    public static class CategoriesBean {
        /**
         * id : 1
         * name : 首页
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
