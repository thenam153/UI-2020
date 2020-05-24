package com.chenshop.model.db;

import com.chenshop.model.bean.Product;
import com.chenshop.model.bean.Shop;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public final class Database implements Serializable {


    public static final Table<Product> PRODUCT = new Table<>(Product.class, Arrays.asList(
            new Product("SP001", "Chi tiết sản phẩm 1", 4.5, 50L, 100000L, 1, new Date(), new Date()),
            new Product("SP002", "Chi tiết sản phẩm 2", 4.6, 50L, 200000L, 2, new Date(), new Date()),
            new Product("SP003", "Chi tiết sản phẩm 3", 4.7, 50L, 300000L, 3, new Date(), new Date()),
            new Product("SP004", "Chi tiết sản phẩm 4", 4.8, 50L, 400000L, 3, new Date(), new Date()),
            new Product("SP005", "Chi tiết sản phẩm 5", 4.9, 50L, 500000L, 3, new Date(), new Date()),
            new Product("SP006", "Chi tiết sản phẩm 6", 4.4, 50L, 600000L, 1, new Date(), new Date()),
            new Product("SP007", "Chi tiết sản phẩm 7", 4.3, 50L, 700000L, 1, new Date(), new Date()),
            new Product("SP008", "Chi tiết sản phẩm 8", 4.2, 50L, 800000L, 2, new Date(), new Date()),
            new Product("SP009", "Chi tiết sản phẩm 9", 4.1, 50L, 900000L, 1, new Date(), new Date()),
            new Product("SP010", "Chi tiết sản phẩm 10", 4.0, 50L, 1000000L, 1, new Date(), new Date())
    ));

    public static final Table<Shop> SHOP = new Table<>(Shop.class, Arrays.asList(
            new Shop("Shop01", "Description Shop 01"),
            new Shop("Shop02", "Description Shop 02"),
            new Shop("Shop03", "Description Shop 03")
    ));

}
