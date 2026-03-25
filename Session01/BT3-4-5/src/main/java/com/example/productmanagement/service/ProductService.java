package com.example.productmanagement.service;

import com.example.productmanagement.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Product> productList;

    public ProductService() {
        productList = new ArrayList<>();
        productList.add(new Product(1,"A",100));
        productList.add(new Product(2,"B",200));
        productList.add(new Product(3,"C",300));
    }

    public List<Product> getAllProducts() {
        return productList;
    }

    public String addProduct(Product product) {
        productList.add(product);
        return "Them moi thanh cong";
    }

    public String removeProduct(int id) {
        for (int i=0; i<productList.size();i++){
            if (productList.get(i).getId()==id){
                productList.remove(id);
                return "Xoa thanh cong";
            }
        }

        return "Xoa thanh cong";
    }

    public String updateProduct(Product product) {
       for (int i=0; i< productList.size(); i++) {
           if (productList.get(i).getId() == product.getId()) {
               productList.set(i, product);
               return "Cap nhat thanh cong";
           }
       }

       return "Cap nhat that bai";
    };


}
