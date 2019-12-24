package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.model.ProductForm;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@PropertySource("classpath:global_config_app.properties")
@RequestMapping("product/")
public class ProductController {

    @Autowired
    Environment env;

    //ProductServiceImpl productService = nebooksw ProductServiceImpl();
    @Autowired
    IProductService productService;

    @RequestMapping(value = "list*", method = RequestMethod.GET)
    public ModelAndView listProducts() {

        List<Product> listProducts = productService.findAllHaveBusiness();

        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", listProducts);

        return modelAndView;
    }


    @RequestMapping(value = "search")
    public ModelAndView searchProduct(@RequestParam("txtSearch") String txtSearch) {
        Product product = productService.findByName(txtSearch);
        ModelAndView modelAndView = new ModelAndView("/product/search");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @RequestMapping("create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("productform", new ProductForm());
        return modelAndView;
    }

    @RequestMapping("save-product")
    public ModelAndView saveProduct(@ModelAttribute("productform") ProductForm productform, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("Result Error Occured" + result.getAllErrors());
        }
        MultipartFile multipartFile = productform.getImage();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();
        try {

            FileCopyUtils.copy(productform.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Product productObject = new Product(10, productform.getName(), productform.getPrice(), fileName);
        productService.save(productObject);
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }


    @RequestMapping(value = "/edit-product")
    public ModelAndView editProduct(@ModelAttribute("productform") ProductForm productform, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("Result Error Occured" + result.getAllErrors());
        }
        MultipartFile multipartFile = productform.getImage();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();

        try {
            FileCopyUtils.copy(productform.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Product productObject = new Product(productform.getId(), productform.getName(),
                productform.getPrice(), fileName);
        productService.editProduct(productObject.getId(),productObject);
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        modelAndView.addObject("productform", productObject);
        modelAndView.addObject("message", "Da sua xong san pham");
        return modelAndView;
    }


    @GetMapping("/edit-product/{id}")
    public ModelAndView showEditForm(@PathVariable long id) {
        Product product = productService.findById(id);

        ProductForm productForm = new ProductForm(product.getId(), product.getName(),
                product.getPrice(),null);
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        modelAndView.addObject("productform", productForm);
        return modelAndView;

    }




    @RequestMapping(value = "product-delete/{id}", method = RequestMethod.GET)
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Product product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/product/delete");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @RequestMapping("/delete-product")
    public String deleteReceptionist(@ModelAttribute("product") Product product) {
        productService.remove(product.getId());
        return "redirect:list";
    }

    @RequestMapping(value = "product-detail/{id}", method = RequestMethod.GET)
    public ModelAndView productDetail(@PathVariable Long id) {
        Product product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/product/detail");
        modelAndView.addObject("product", product);
        return modelAndView;
    }





}

