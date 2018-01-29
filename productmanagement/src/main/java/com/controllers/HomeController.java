package com.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.Constant;
import com.dao.CategoryDao;
import com.entities.*;
import com.models.CategoryProductModel;
import com.services.CategoryService;
import com.services.ProductCategoryService;
import com.services.ProductService;



@Controller
@RequestMapping(value="home")
public class HomeController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductCategoryService productCategoryService;
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value="/category", method = RequestMethod.GET)
	public ModelAndView getPage() {
		ModelAndView view = new ModelAndView("Home");
		return view;
	}
	
	@RequestMapping(value = "/categorylist", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAllCategory(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Category> categoryList = categoryService.getAllCategory(); 
		if(categoryList != null) {
			resultMap.put(Constant.AjaxResult.Status, "200");
			resultMap.put(Constant.AjaxResult.Message, "Data found");
			resultMap.put(Constant.AjaxResult.Data, categoryList);
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/productlist", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getProductCategory(Category category, String categoryMode){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List productList;
		if(categoryMode.equals("Add")) {
			productList = productService.getAllProduct();
		}else {
			productList = productCategoryService.getAllProduct(category);
		}
		if(productList != null) {
			resultMap.put(Constant.AjaxResult.Status, "200");
			resultMap.put(Constant.AjaxResult.Message, "Data found");
			resultMap.put(Constant.AjaxResult.Data, productList);
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> getDeleted(Category category) {
        Map<String, Object> map = new HashMap<String, Object>();
        //ResponseEntity res = new ResponseEntity(200);
        try {
        	if (categoryService.deleteCategory(category)) {
            	productCategoryService.deleteProductCategory(category);
            	//productCategoryService.addProductCategory(categoryProduct.getCategory(), categoryProduct.getProductList());
                map.put(Constant.AjaxResult.Status, "200");
                map.put(Constant.AjaxResult.Message, "Your record have been deleted successfully");
            }
        }catch(Exception ex) {
        	map.put(Constant.AjaxResult.Status, "Error");
            map.put(Constant.AjaxResult.Message, "Delete error");
        }
        map.put("statys", 200);
 
        return map;
    }
	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> getSaved(@RequestBody CategoryProductModel categoryProduct) {
        Map<String, Object> map = new HashMap<String, Object>();
        //ResponseEntity res = new ResponseEntity(200);
        try {
        	if (categoryService.saveOrUdpateCategory(categoryProduct.getCategory())) {
            	//productCategoryService.deleteProductCategory(categoryProduct.getCategory(), categoryProduct.getProductList());
            	productCategoryService.addProductCategory(categoryProduct.getCategory(), categoryProduct.getProductList());
                map.put(Constant.AjaxResult.Status, "200");
                map.put(Constant.AjaxResult.Message, "Your record have been saved successfully");
            }
        }catch(Exception ex) {
        	map.put(Constant.AjaxResult.Status, "Error");
            map.put(Constant.AjaxResult.Message, "Add error");
        }
        map.put("statys", 200);
 
        return map;
    }
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> getUpdated(@RequestBody CategoryProductModel categoryProduct) {
        Map<String, Object> map = new HashMap<String, Object>();
        //ResponseEntity res = new ResponseEntity(200);
        try {
        	if (categoryService.saveOrUdpateCategory(categoryProduct.getCategory())) {
            	productCategoryService.deleteProductCategory(categoryProduct.getCategory());
            	productCategoryService.addProductCategory(categoryProduct.getCategory(), categoryProduct.getProductList());
                map.put(Constant.AjaxResult.Status, "200");
                map.put(Constant.AjaxResult.Message, "Your record have been saved successfully");
            }
        }catch(Exception ex) {
        	map.put(Constant.AjaxResult.Status, "Error");
            map.put(Constant.AjaxResult.Message, "Add error");
        }
        map.put("statys", 200);
 
        return map;
    }
 
	
}
