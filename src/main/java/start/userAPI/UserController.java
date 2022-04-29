package start.userAPI;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import start.logic.UsersService;



@RestController
public class UserController {
	
	
	private UsersService userService;
	@Autowired
	public void setUserService(UsersService userService) {
		this.userService = userService;
	}

	
	
	@RequestMapping(path = "/blockchain/users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary createUser(@RequestBody NewUserDetails userDeatalis) {
		System.out.println(userDeatalis.toString());
		UserBoundary user = this.userService.converNewtUserDeatailsToBoundary(userDeatalis);
		return this.userService.createUser(user);
	}

	@RequestMapping(path = "/blockchain/users/login/{userSpace}/{userEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary getUserSpace(@PathVariable("userSpace") String userSpace,
			@PathVariable("userEmail") String userEmail) {
		return this.userService.login(userSpace, userEmail);
	}

	@RequestMapping(path = "/blockchain/users/{userSpace}/{userEmail}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateUser(@PathVariable("userSpace") String userSpace, @PathVariable("userEmail") String userEmail,
			@RequestBody UserBoundary user_boundry) {
		this.userService.updateUser(userSpace, userEmail, user_boundry);
	}
}
