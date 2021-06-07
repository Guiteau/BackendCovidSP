package minik.covid.covidSP.controller;

import java.util.ArrayList;
import java.util.List;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import minik.covid.covidSP.entity.User;
import minik.covid.covidSP.mapper.UserMapper;
import minik.covid.covidSP.response.UserResponse;
import minik.covid.covidSP.service.impl.BcryptService;
import minik.covid.covidSP.service.impl.UserService;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private BcryptService bcryptService;
	
	@GetMapping("getAllUsers")
	public ResponseEntity<List<UserResponse>> getAllUser(){
		
		List<UserResponse> userResponseList = new ArrayList<>();
				
		try {		
			List<User> userList = this.userService.getAllUsers();		
			for(User userElement : userList) {
				userResponseList.add(this.userMapper.getUserResponseFromUser(userElement));
			}		
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(userResponseList, HttpStatus.OK);
	}
	
	@GetMapping("getUserById/{idUser}")
	public ResponseEntity<UserResponse> getUserById(@Validated @PathVariable("idUser") Integer id){
		
		UserResponse userResponse = new UserResponse();
		
		try {
			userResponse = this.userMapper.getUserResponseFromUser(userService.getUserById(id));
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}
	
	@PostMapping("saveUser")
	public ResponseEntity<Integer> saveUser(@RequestBody UserResponse userResponse){
		
		User user = new User();
				
		try {
			if(userResponse.getUserId() != null)
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			user = this.userMapper.getUserFromUserResponse(userResponse);
			user.setPassword(this.bcryptService.encrypt(userResponse.getPassword()));
			this.userService.saveUser(user);
			if(user.getUserId() == null)
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(user.getUserId(), HttpStatus.OK);
	}
	
	@PostMapping("checkUserLogin")
	public ResponseEntity<UserResponse> checkUserLogin(@RequestBody UserResponse userResponse){
		
		UserResponse response = new UserResponse();
				
		try {		
			String userEntityPassword = this.userService.getPasswordByUserEmail(userResponse.getEmail());
			
			if(userEntityPassword == null)
				throw new Exception("El usuario no existe en la base de datos");
			
			if(!this.bcryptService.decrypt(userResponse.getPassword(), userEntityPassword)) throw new AuthenticationException();
			response = this.userMapper.getUserResponseFromUser(this.userService.getUserByEmail(userResponse.getEmail()));
		} catch (AuthenticationException e) {
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("deleteUserById/{id}")
	public ResponseEntity<Boolean> deleteUserById(@Validated @PathVariable ("id") Integer id){
		
		try {
			this.userService.deleteUser(id);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(true, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
