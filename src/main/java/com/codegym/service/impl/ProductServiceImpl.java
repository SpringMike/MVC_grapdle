package com.codegym.service.impl;

import com.codegym.model.Product;
import com.codegym.repository.IProductRepository;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductServiceImpl implements IProductService {

    //ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
    @Autowired
    IProductRepository productRepository;

    @Override
    public List<Product> findAllHaveBusiness() {

        // code business....

        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void remove(int id) {
        productRepository.remove(id);
    }

    @Override
    public void editProduct(int id, Product product) {
        productRepository.editProduct(id, product);
    }
}
