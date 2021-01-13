package com.rspatil45.first_project.ui.controller;

import java.util.Date;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rspatil45.first_project.entity.ProductEntity;
import com.rspatil45.first_project.entity.ProductRepository;
import com.rspatil45.first_project.service.ProductService;
import com.rspatil45.first_project.shared.dto.ProductsDto;
import com.rspatil45.first_project.ui.model.request.ProductRequestModel;
import com.rspatil45.first_project.ui.model.request.ProductResponseModel;
import com.rspatil45.first_project.ui.model.response.ProductCreateResponseModel;
import com.rspatil45.first_project.util.JwtUtils;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
//@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("products") // http://localhost:8080/users
public class productsController {
	@Autowired
	ProductService productService;

	@Autowired
	private ProductRepository prd;
	
	JwtUtils jwt = new JwtUtils();

	@PostMapping("/new")
	public ProductCreateResponseModel addProduct(@RequestBody ProductRequestModel product) {
		jwt.validateToken(product.getToken());
		Date d = new Date();
		ProductCreateResponseModel returnValue = new ProductCreateResponseModel();
		ProductsDto productDto = new ProductsDto();
		BeanUtils.copyProperties(product, productDto);
		productDto.setDate(d);
		ProductsDto createdProuct = productService.addProduct(productDto);
		BeanUtils.copyProperties(createdProuct, returnValue);
		return returnValue;
	}

	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<ProductsDto> getProducts(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "2") int limit) {
		
		List<ProductsDto> products = productService.getProducts(page, limit);
		return products;
	}

	@GetMapping(path = "/category")
	public List<ProductsDto> getCategoryProducts(@RequestParam(value = "category") String category,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "2") int limit) {
		List<ProductsDto> products = productService.getSortedProducts(category, page, limit);
		return products;
	}

	@GetMapping(path = "/{id}")
	public ProductResponseModel getProductById(@PathVariable long id) {
		ProductResponseModel product = new ProductResponseModel();
		ProductEntity item = prd.findById(id);
		BeanUtils.copyProperties(item, product);
		return product;
	}
	
	@DeleteMapping(path="/{id}/{token}")
	public boolean deleteProduct(@PathVariable long id, @PathVariable String token)
	{
		
		jwt.validateToken(token);
		try {
			prd.deleteById(id);
			return true;
		}catch(Exception e) {
			return false;
		}
		
	}
	
	@GetMapping(path = "/count")
	long getCount() {
		
		long count = prd.count();

		return count;
	}

	@PutMapping("/update")
	public ProductResponseModel updateProduct(@RequestBody ProductsDto product) {
		jwt.validateToken(product.getToken());
		ProductResponseModel returnValue = new ProductResponseModel();
		ProductEntity currentProduct = prd.findById(product.getId());
		if (currentProduct == null) {
			return null;
		}			
		currentProduct.setName(product.getName());
		currentProduct.setImage(product.getImage());
		currentProduct.setDescription(product.getDescription());
		currentProduct.setPrice(product.getPrice());
		currentProduct.setCategory(product.getCategory());
		currentProduct.setQuantity(product.getQuantity());
		
		prd.save(currentProduct);
		
		BeanUtils.copyProperties(currentProduct, returnValue);
		return returnValue;

	}

}
