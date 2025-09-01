package com.uniq.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniq.com.config.service.JwtUtil;
import com.uniq.com.entity.Employee;
import com.uniq.com.repo.EmpRepository;

@RestController
@RequestMapping("/api")
public class EmpController {
	
	@Autowired
	private EmpRepository repo;
	
	@Autowired
	private AuthenticationManager am;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
    private JwtUtil jwtUtil;
	
	@PostMapping("/register")
	public ResponseEntity<String> save(@RequestBody Employee req){
		String pwd = req.getPwd();
		String encodedPWD = encoder.encode(pwd);
		req.setPwd(encodedPWD);
		repo.save(req);
		return new ResponseEntity<String>("Saved Sucessfully", HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> signUp(@RequestBody Employee e){
		
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(e.getEmail(), e.getPwd());
			Authentication authenticate = am.authenticate(token);
			if(authenticate.isAuthenticated()){
				Employee emp = repo.findByEmail(e.getEmail()).get();
				//System.out.println(emp.getEmail());
				//System.out.println(emp.getRole());
				String jwt = jwtUtil.generateToken(e.getEmail(), emp.getRole());
	            return ResponseEntity.ok(jwt);
			}
		}
		catch(AuthenticationException ee) {
			//return new ResponseEntity<String>("Wrong credentials", HttpStatus.BAD_REQUEST);
		}
				
		return new ResponseEntity<>("Wrong credentials", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/home")
	public String home() {
		return "Welcome back";
	}
	
	@GetMapping("/user")
	//@PreAuthorize("hasRole('USER')")
    public String userPage() {
        return "User Page";
    }

    @GetMapping("/admin")
    //@PreAuthorize("hasRole('ADMIN')")
    public String adminPage() {
        return "Admin Page";
    }
    
    
    /*
	/api/login
	/api/user
	/api/admin
	 * 
	 Authorization: Bearer <>
	*/
}
