package com.carlosborges.workshopmongo.resouses;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.carlosborges.workshopmongo.domain.User;
import com.carlosborges.workshopmongo.dto.UserDTO;
import com.carlosborges.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResouce {
	
	@Autowired
	private UserService service;
	
	//@GetMapping
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll(){		
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto); 
	}

}
