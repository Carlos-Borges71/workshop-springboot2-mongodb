package com.carlosborges.workshopmongo.resouses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carlosborges.workshopmongo.domain.Post;
import com.carlosborges.workshopmongo.resouses.util.URL;
import com.carlosborges.workshopmongo.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResouce {
	
	@Autowired
	private PostService service;
	
	//@GetMapping
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Post>> findAll(){		
		List<Post> list = service.findAll();		
		return ResponseEntity.ok().body(list); 
	}
	
	//@GetMapping
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id){	
		Post obj = service.findById(id);			
		return ResponseEntity.ok().body(obj); 
	}
	
	//@GetMapping
	@RequestMapping(value="/titleSearch",method=RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="")String text){	
	text = URL.decodeParam(text);
	List<Post> list = service.findByTitle(text);
	return ResponseEntity.ok().body(list);
	
	}
		

}
