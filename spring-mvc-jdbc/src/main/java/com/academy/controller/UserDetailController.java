package com.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.academy.model.UserDetail;
import com.academy.service.UserDetailService;

/**
 * 
 * Controller Class. The @Controller annotation is used to indicate that this class serves the role of controller. It marks the class as a web request handler.
 *
 */

@Controller
//@RequestMapping Annotation used to map web requests onto specific handler classes and/or handler methods.
@RequestMapping("/")
public class UserDetailController {
	
	@Autowired
	private UserDetailService userDetailService;
	
	/*
	 * This method will display the data of a particular user based on the id of the user that is being passed in the URI in the user.jsp page (Due to @PathVariable annotation being used in the method)
	 */
	@RequestMapping(value = "user/{id}", method = RequestMethod.GET)
	public String getUserDetail(@PathVariable int id, ModelMap userModel) {
		userModel.addAttribute("userDetail", userDetailService.getUserDetail(id));
		return "user";
	}
	
	/*
	 * This method will display the details of all users present in the table in the update.jsp page
	 */
	@RequestMapping(value = "users", method = RequestMethod.GET)
	public String getUsersDetails(ModelMap userModel) {
		userModel.addAttribute("userDetail", userDetailService.getAllUserDetail());
		return "users";
	}
	@RequestMapping(value = "addUser")
	public String addPage() {
		return "add";
	}
	
	
	@RequestMapping(value = "add/user", method = RequestMethod.POST)
	public String addUser(@RequestParam(value = "fname", required = true) String fname,
			@RequestParam(value = "lname", required = true) String lname,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "dob", required = true) String dob, ModelMap userModel) {
		UserDetail userDetail = new UserDetail();
		userDetail.setFirstName(fname);
		userDetail.setLastName(lname);
		userDetail.setEmail(email);
		userDetail.setDob(dob);
		int resp = userDetailService.addUserDetail(userDetail);
		if (resp > 0) {
			userModel.addAttribute("msg", "User with id : " + resp + " added successfully.");
			userModel.addAttribute("userDetail", userDetailService.getAllUserDetail());
			return "users";
		} else {
			userModel.addAttribute("msg", "User addition failed.");
			return "add";
		}
	}
	
	/*
	 * This method will delete a user that is selected based on the user's id. Following deletion, the users.jsp page is displayed and the appropriate msg will be displayed (Whether deletion is successful or not) 
	 */
	@RequestMapping(value = "delete/user/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("id") int id, ModelMap userModel) {
		int resp = userDetailService.deleteUserDetail(id);
		userModel.addAttribute("userDetail", userDetailService.getAllUserDetail());
		if (resp > 0) {
			userModel.addAttribute("msg", "User with id : " + id + " deleted successfully.");
		} else {
			userModel.addAttribute("msg", "User with id : " + id + " deletion failed.");
		}
		return "users";
	}
	
	/*
	 * This method will load the update.jsp page where the details of the user who will be updated is displayed 
	 */
	@RequestMapping(value = "update/user/{id}", method = RequestMethod.GET)
	public String updatePage(@PathVariable("id") int id, ModelMap userModel) {
		userModel.addAttribute("id", id);
		userModel.addAttribute("userDetail", userDetailService.getUserDetail(id));
		return "update";
	}
	
	/*
	 * This method will update the details of the user based on the modification being done on the update.jsp page 
	 * (@RequestParam annotation being used: read the form data and bind it automatically to the parameter present in the provided method.)
	 * If update is successful, the page users.jsp is displayed with a msg saying "updated successfully"
	 * If update failed, the page update.jsp is displayed with a msg saying "update failed"
	 */
	@RequestMapping(value = "update/user", method = RequestMethod.POST)
	public String updateUser(@RequestParam int id, @RequestParam(value = "fname", required = true) String fname,
			@RequestParam(value = "lname", required = true) String lname, @RequestParam("email") String email,
			@RequestParam("dob") String dob, ModelMap userModel) {
		UserDetail userDetail = new UserDetail();
		userDetail.setId(id);
		userDetail.setFirstName(fname);
		userDetail.setLastName(lname);
		userDetail.setEmail(email);
		userDetail.setDob(dob);
		int resp = userDetailService.updateUserDetail(userDetail);
		userModel.addAttribute("id", id);
		if (resp > 0) {
			userModel.addAttribute("msg", "User with id : " + id + " updated successfully.");
			userModel.addAttribute("userDetail", userDetailService.getAllUserDetail());
			return "users";
		} else {
			userModel.addAttribute("msg", "User with id : " + id + " update failed.");
			userModel.addAttribute("userDetail", userDetailService.getUserDetail(id));
			return "update";
		}
	}
}