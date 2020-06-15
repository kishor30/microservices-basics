package com.amdocs.media.assignement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.amdocs.media.assignement.dao.UserLoginRepository;
import com.amdocs.media.assignement.model.UserLoginDAO;
import com.amdocs.media.assignement.model.UserPrincipal;
import com.amdocs.media.assignement.model.UserProfileDTO;

@Service
public class UserLoginService implements UserDetailsService{
	
	@Autowired
	UserLoginRepository userLoginRepository;
	


	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loading User......");
	     UserLoginDAO user = userLoginRepository.findByUsername(username);
	 	System.out.println("found loading User......"+user);
	        if (user == null) {
	        	System.out.println("throwing User......"+user);
	        	throw new UsernameNotFoundException(username);
	        }

	    	System.out.println("loading User......"+user);
	        return new UserPrincipal(user);
	 
	}
	
	public void saveUsers(UserLoginDAO user) {
		userLoginRepository.save(user);
		
	}
	
	@Autowired
	private KafkaTemplate<String, byte[]> kafkaTemplate;
	


	//String kafkaTopic = "profileOperationsforUpdate";
	
	public void send(UserProfileDTO message,String kafkaTopic) {
		String jsonString = new com.google.gson.Gson().toJson(message);
		byte[] inputasByte = jsonString.getBytes();
	    System.out.println("topic is:"+kafkaTopic+"and username is:"+message);
	    kafkaTemplate.send(kafkaTopic, inputasByte);
	    //kafkaTemplate.getProducerFactory().getConfigurationProperties().put("spring.kafka.producer.value-serializer", "org.apache.kafka.common.serialization.StringSerializer");
	    
	}
	


}
