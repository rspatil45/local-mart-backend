package com.rspatil45.first_project.ui.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rspatil45.first_project.entity.ProductEntity;
import com.rspatil45.first_project.entity.UserEntity;
import com.rspatil45.first_project.entity.UserRepository;
import com.rspatil45.first_project.service.UserService;
import com.rspatil45.first_project.shared.dto.UserDto;
import com.rspatil45.first_project.ui.model.request.Cart;
import com.rspatil45.first_project.ui.model.request.OrderRequestModel;
import com.rspatil45.first_project.ui.model.request.UserSignupRequestModel;
import com.rspatil45.first_project.ui.model.response.UserLoginResponseModel;
import com.rspatil45.first_project.ui.model.response.UserResponseModel;
import com.rspatil45.first_project.util.AESAlgorithm;
import com.rspatil45.first_project.util.JwtUtils;
import net.bytebuddy.utility.RandomString;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("users") // http://localhost:8080/users
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Autowired
	private UserRepository urd;
	
	JwtUtils jwt = new JwtUtils();
	// @CrossOrigin(origins = "http://localhost:8080")
	@GetMapping()
	public String getUser(@RequestBody UserSignupRequestModel udetails) {
		String token = udetails.getToken();
		jwt.validateToken(token);
		return "get User was called";
	}
	
	
	@GetMapping("/products/{uid}")
	List<ProductEntity> getProducts(@PathVariable String uid)
	{
		UserEntity user = urd.findByPublicUid(uid);
		if(user==null) throw new RuntimeException("user not found");
		if(user.getProducts()==null) throw new RuntimeException("user doesn't have products");
		return user.getProducts();
	}

	@PostMapping(path = "/login", 
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
	)
	public UserLoginResponseModel loginUser(@RequestBody UserSignupRequestModel udetail) throws GeneralSecurityException, IOException {
		String email = udetail.getEmail();
		UserEntity user = urd.findByEmail(email);
		UserLoginResponseModel returnValue = new UserLoginResponseModel();

		if (user == null)
			throw new RuntimeException("User not exists!");
		String enCryptPassword = AESAlgorithm.en(udetail.getPassword());
		if (user.getPassword().equals(enCryptPassword)) {
			//generating jwt token
			String token = jwt.createToken(udetail.getFirstname());
			BeanUtils.copyProperties(user, returnValue);
			returnValue.setToken(token);
			return returnValue;
		} else {
			throw new RuntimeException("Invalid credentials!");
		}

	}
	
	@PostMapping(path = "/addproduct", 
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserResponseModel addProduct(@RequestBody UserResponseModel uresponse) {
		return null;
		
	}

	@PostMapping(path="/signup",consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserResponseModel createUser(@RequestBody UserSignupRequestModel userDetails)
			throws GeneralSecurityException, IOException {

		// check for already existed user
		UserEntity checkUser = urd.findByEmail(userDetails.getEmail());
		if (checkUser != null)
			throw new RuntimeException("User Already Exists!");

		// encrypting password before storing into database
		// AESAlgorithm o = new AESAlgorithm();
		String password = AESAlgorithm.en(userDetails.getPassword());
		userDetails.setPassword(password);

		// saving user
		UserResponseModel returnValue = new UserResponseModel();
		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(userDetails, userDto);

		UserDto createdUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createdUser, returnValue);
		return returnValue;
	}

	@PostMapping(path = "/place-order", 
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public String placeOrder(@RequestBody OrderRequestModel order) throws UnsupportedEncodingException, MessagingException {
		String code = order.getCode();
		UserEntity user = order.getUser();
		user = urd.findByEmail(user.getEmail());
		System.out.println(code + "" + user.getVerifyCode());
		if(code.equals(user.getVerifyCode()))
		{
			for(Cart cart: order.getCart())
			{	
				sendOrderEmail(order.getUser(),cart.getItem(),cart.getAmount());
			}
			return "all orders placed successfully";
		}else
		{
			throw new RuntimeException("Invalid authentication code");
		}
		
	}


	@DeleteMapping
	public String deleteUser() {
		return "delete user was called";
	}
	@PutMapping(path = "/verify-order", 
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public void verifyOrder( @RequestBody UserEntity usr) throws UnsupportedEncodingException, MessagingException {	
		 String randomCode = RandomString.make(4);	
		 UserEntity user = urd.findByEmail(usr.getEmail());
		 if(user==null) throw new RuntimeException("User not found");
		 user.setVerifyCode(randomCode);
		 urd.save(user);
		 sendVerificationEmail(user, randomCode);
		 System.out.println(randomCode);
	}
	
	
	private void sendVerificationEmail(UserEntity user, String code)
	        throws MessagingException, UnsupportedEncodingException {
	    String toAddress = user.getEmail();
	    String fromAddress = "rspatil45@gmail.com";
	    String senderName = "Admin Local mart";
	    String subject = "verification code";
	    String content = "Dear [[name]],<br>"
	            + "Your verification code for local mart is given below:<br><br>"
	            + "[[vcode]]<br>";
	     
	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom(fromAddress, senderName);
	    helper.setTo(toAddress);
	    helper.setSubject(subject);
	     
	    content = content.replace("[[name]]", user.getFirstname());
	     
	    content = content.replace("[[vcode]]", code);
	     
	    helper.setText(content, true);
	     
	    mailSender.send(message);
	     
	}
	private void sendOrderEmail(UserEntity buyer, ProductEntity product, int amount) throws UnsupportedEncodingException, MessagingException {
		String owner_id = product.getPublicUid();
		UserEntity owner = urd.findByPublicUid(owner_id);
		 String toAddress = owner.getEmail();
		    String fromAddress = "rspatil45@gmail.com";
		    String senderName = "Admin Local mart";
		    String subject = "Order Information";
		    String content = "Dear [[name]],<br>"
		            + "Your have received orders Details as below:<br>"
		            + "Customer Name: [[userName]]<br>"
		    		+"Customer email: [[userEmail]]<br>"
		            +"Order Details:<br>"
		    		+"Product Name: [[ordName]]<br>"
		            +"Quantity: [[ordQuantity]]<br>"
		    		+"Category: [[ordCategory]]";
		    
		    MimeMessage message = mailSender.createMimeMessage();
		    MimeMessageHelper helper = new MimeMessageHelper(message);
		     
		    helper.setFrom(fromAddress, senderName);
		    helper.setTo(toAddress);
		    helper.setSubject(subject);
		     
		    content = content.replace("[[name]]", owner.getFirstname());
		     
		    content = content.replace("[[userName]]", buyer.getFirstname()+" "+buyer.getLastname());
		    content = content.replace("[[userEmail]]", buyer.getEmail());
		    content = content.replace("[[ordName]]", product.getName());
		    content = content.replace("[[ordQuantity]]", Integer.toString(amount));
		    content = content.replace("[[ordCategory]]", product.getCategory());
		    
		     
		    helper.setText(content, true);
		     
		    mailSender.send(message);
		
	}

}
