package com.carlosborges.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.carlosborges.workshopmongo.domain.Post;
import com.carlosborges.workshopmongo.domain.User;
import com.carlosborges.workshopmongo.dto.AuthorDTO;
import com.carlosborges.workshopmongo.dto.CommentDTO;
import com.carlosborges.workshopmongo.repository.PostRepository;
import com.carlosborges.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();	
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		postRepository.deleteAll();
		Post post1 = new Post(null,sdf.parse("21/03/2018"), "Partiu viagem","Vou viajar para São Paulo. Abraços!" , new AuthorDTO(maria));
		Post post2 = new Post(null,sdf.parse("23/03/2018"), "Bom dia","Acordei feliz hoje!", new AuthorDTO(maria));
		Post post3 = new Post(null,sdf.parse("30/06/2022"), "Partiu praia","Vou viajar para Rio. Abraços!" , new AuthorDTO(alex));
		Post post4 = new Post(null,sdf.parse("23/05/2018"), "Bom dia","Feliz hoje!", new AuthorDTO(bob));
		
		CommentDTO c1 = new CommentDTO("Boa viagem mano", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("23/03/2018"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia", sdf.parse("23/03/2018"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2,post3, post4));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		alex.getPosts().addAll(Arrays.asList(post3));
		bob.getPosts().addAll(Arrays.asList(post4));
		userRepository.save(maria);
		userRepository.save(alex);
		userRepository.save(bob);
		
		
	}
	
	

}
