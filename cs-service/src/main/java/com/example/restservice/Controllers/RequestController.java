package com.example.restservice.Controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.json.simple.JSONObject;

import com.example.restservice.Models.Body;
import com.example.restservice.Database.BodyRepository;

@RestController
class RequestController {

  @Autowired	
  private final BodyRepository repository;
  //This only keeps incrementing as long as the server is running, something would replace this for ID generation in practice
  private final AtomicLong newId = new AtomicLong();
  private final String urlBase = "/callback/"; 

  RequestController(BodyRepository repository) {
    this.repository = repository;
  }
  
  @PostMapping("/request")
  ResponseEntity<String> requestPost(@RequestBody Body newBody) {
	  
	  //This saves the request to an AWS DynamoDB to track the state of the request
	  newBody.setId(newId.incrementAndGet());
	  newBody.setDetail("");
	  newBody.setStatus("CREATED");  
	  repository.save(newBody);
	    
	  /* This would be where we send the third party API request */ 
	  //APIController.SendRequestExternal(newBody.getBody(), urlBase + newBody.getId());
	  
	  return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
  }
  
  @GetMapping("/status/{id}")
  Body getStatusForId(@PathVariable Long id) {
    
    //This could can send a 404 if we do not find it
    return repository.findById(id).get(0);
    }

  @PostMapping("/callback/{id}")
  ResponseEntity<String> postCallback(@RequestBody String status, @PathVariable Long id) {
	  //We have received a callback, use that ID to find the original request and update the detail
	  Body request = repository.findById(id).get(0);
	  request.setStatus(status);
	  repository.save(request);

    return new ResponseEntity<String>("STARTED", HttpStatus.NO_CONTENT);
  }


  @PutMapping("/callback/{id}")
  ResponseEntity<String> putCallback(@RequestBody Body newBody, @PathVariable Long id) {
	  Body updatedBody;
	  try { 
		  updatedBody = repository.findById(id).get(0);
		  updatedBody.setDetail(newBody.getDetail());
		  updatedBody.setStatus(newBody.getStatus());
		  repository.save(updatedBody);
	  } catch(Exception e) {
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);     
	  } finally {
		  //If there was any handling based off of the status of from the put request it could go here
	  }
	  
      return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
  }

}