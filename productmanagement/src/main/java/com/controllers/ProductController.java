package com.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.Constant;
import com.entities.Attribute;
import com.entities.Category;
import com.entities.Product;
import com.services.ProductService;

@Controller
@RequestMapping(value="product")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@RequestMapping(value="view", method = RequestMethod.GET)
	public ModelAndView getPage() {
		ModelAndView view = new ModelAndView("Product");
		return view;
	}

	
	@RequestMapping(value = "/productlist", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAllProduct(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Product> productList = productService.getAllProduct();
		if(productList != null) {
			resultMap.put(Constant.AjaxResult.Status, "200");
			resultMap.put(Constant.AjaxResult.Message, "Data found");
			resultMap.put(Constant.AjaxResult.Data, productList);
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> getDeleted(Product product) {
        Map<String, Object> map = new HashMap<String, Object>();
        //ResponseEntity res = new ResponseEntity(200);
        try {
        	if (productService.deleteProduct(product)) {
            	//productCategoryService.deleteProductCategory(category);
            	//productCategoryService.addProductCategory(categoryProduct.getCategory(), categoryProduct.getProductList());
                map.put(Constant.AjaxResult.Status, "200");
                map.put(Constant.AjaxResult.Message, "Your record have been deleted successfully");
            }
        }catch(Exception ex) {
        	map.put(Constant.AjaxResult.Status, "Error");
            map.put(Constant.AjaxResult.Message, "Delete error");
        }
        map.put("status", 200);
 
        return map;
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> getSaved(Product product) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
        	if (productService.saveOrUdpateProduct(product)) {
            	//productCategoryService.deleteProductCategory(category, productList);
            	//productCategoryService.addProductCategory(category, productList);
                map.put(Constant.AjaxResult.Status, "200");
                map.put(Constant.AjaxResult.Message, "Your record have been saved successfully");
            }	
        }catch(Exception ex) {
        	map.put(Constant.AjaxResult.Status, "Error");
            map.put(Constant.AjaxResult.Message, "Add error");
        }
        return map;
    }
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> getUpdated(Product product) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
        	if (productService.saveOrUdpateProduct(product)) {
            	//productCategoryService.deleteProductCategory(category, productList);
            	//productCategoryService.addProductCategory(category, productList);
                map.put(Constant.AjaxResult.Status, "200");
                map.put(Constant.AjaxResult.Message, "Your record have been saved successfully");
            }	
        }catch(Exception ex) {
        	map.put(Constant.AjaxResult.Status, "Error");
            map.put(Constant.AjaxResult.Message, "Add error");
        }
        return map;
    }
 
 
}
