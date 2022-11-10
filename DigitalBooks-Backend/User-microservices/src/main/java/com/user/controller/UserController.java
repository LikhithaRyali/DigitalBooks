package com.user.controller;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.user.entity.ERole;
import com.user.entity.Role;
import com.user.entity.User;
import com.user.jwt.util.JwtUtils;
import com.user.payload.request.LoginRequest;
import com.user.payload.request.SignupRequest;
import com.user.payload.response.LoginResponse;
import com.user.payload.response.MessageResponse;
import com.user.repository.IRoleRepo;
import com.user.repository.IUserRepo;
import com.user.service.implementation.UserDetailsImpl;

@RestController
@CrossOrigin(origins= "*", maxAge = 4800, allowCredentials = "false")
@RequestMapping("/api/user")
public class UserController {

	
	@Autowired
	IUserRepo userRepo;
	
	@Autowired
	IRoleRepo roleRepo;	
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	PasswordEncoder encoder;
	
	@PostMapping("/sign-in")
	public ResponseEntity<?> userLoginAuthenticate(@Valid @RequestBody LoginRequest loginRequest) {
		
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		String jwt = jwtUtils.genreatingTokenForJwt(authenticate);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authenticate.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map((item) -> item.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(new LoginResponse(jwt,
				userDetails.getId(),
		userDetails.getUsername(),
		userDetails.getEmail(),
		roles));
	}
	
	@PostMapping("/sign-up")
	public ResponseEntity<?> newUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if(userRepo.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Username is already taken"));
		}
		if(userRepo.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Email already exists"));
		}
		
		User user = new User(signUpRequest.getUsername(),signUpRequest.getEmail(),encoder.encode(signUpRequest.getPassword()));
		Set<String> strRoles = signUpRequest.getRole();
		
		Set<Role> roles = new HashSet<>();
		
		if (strRoles == null) {
			Role userRole = roleRepo.findByName(ERole.ROLE_GUEST)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "author":
					Role authorRole = roleRepo.findByName(ERole.ROLE_AUTHOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(authorRole);

					break;
				case "reader":
					Role readerRole = roleRepo.findByName(ERole.ROLE_READER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(readerRole);

					break;
				default:
					Role guestRole = roleRepo.findByName(ERole.ROLE_GUEST)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(guestRole);
				}
			});
		}

		user.setRoles(roles);
		userRepo.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	
	
}
