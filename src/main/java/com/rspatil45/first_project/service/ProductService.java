package com.rspatil45.first_project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;

import com.rspatil45.first_project.entity.ProductEntity;
import com.rspatil45.first_project.entity.ProductRepository;
import com.rspatil45.first_project.shared.dto.ProductsDto;
import com.rspatil45.first_project.ui.model.request.ProductRRModel;


@Service
public class ProductService{
	
	@Autowired
	ProductRepository productRepository;


	public ProductsDto addProduct(ProductsDto product) {
		
		ProductEntity entity = new ProductEntity();
		BeanUtils.copyProperties(product, entity);
		ProductEntity savedProduct = productRepository.save(entity);
		ProductsDto returnValue = new ProductsDto();
		BeanUtils.copyProperties(savedProduct, returnValue);
		return returnValue;
	}
	
	public List<ProductRRModel> getProducts(int page, int limit)
	{
		if(page>0) page = page - 1;
		List<ProductRRModel> returnValue = new ArrayList<>();
		PageRequest pageableRequest = PageRequest.of(page, limit);
		Page<ProductEntity> productPage = productRepository.findAll(pageableRequest);
		List<ProductEntity> products = productPage.getContent();
		for(ProductEntity prd: products)
		{
			ProductRRModel productDto= new ProductRRModel();
			BeanUtils.copyProperties(prd, productDto);
			productDto.setUser(null);
			returnValue.add(productDto);
		}
		return returnValue;
	}
	
	public List<ProductsDto> getSortedProducts(String category, int page, int limit)
	{
		if(page>0)  page = page-1;
		List<ProductsDto> returnValue = new ArrayList<>();
		PageRequest pageableRequest = PageRequest.of(page, limit);
		Page<ProductEntity> productPage = productRepository.findByCategory(category, pageableRequest);
		List<ProductEntity> products = productPage.getContent();
		for(ProductEntity prd: products)
		{
			ProductsDto productDto = new ProductsDto();
			BeanUtils.copyProperties(prd, productDto);
			productDto.setUser(null);
			returnValue.add(productDto);
		}
		return returnValue;
		
	}
	
	

	
	
}
