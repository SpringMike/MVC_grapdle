package com.codegym.repository.impl;

import com.codegym.model.Product;
import com.codegym.repository.IProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements IProductRepository {

    List<Product> productList = new ArrayList<>();

    {
        productList.add(new Product(1, "Samsung", 300,"img1"));
        productList.add(new Product(2, "Iphone", 500,"img2"));
        productList.add(new Product(3, "Nokia", 700,"img3"));
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public Product findById(Long id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                return productList.get(i);
            }
        }
        return null;
    }

    @Override
    public Product findByName(String name) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getName().equals(name)) {
                return productList.get(i);
            }
        }
        return null;
    }

    @Override
    public void save(Product product) {
        productList.add(product);
    }

    @Override
    public void remove(int id) {
        for (int i =0 ; i<productList.size();i++){
            if (productList.get(i).getId() == id){
                productList.remove(i);
                return;
            }
    }

}

    @Override
    public void editProduct(int id, Product product) {
        int index = -1;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                index = i;
            }
        }
        productList.set(index,product);
    }

}
