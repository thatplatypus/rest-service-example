package com.example.restservice.Database;

import java.util.List;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.example.restservice.Models.Body;

@EnableScan
public interface BodyRepository extends CrudRepository<Body, String> {
  List<Body> findById(Long id);
  List<Body> findByStatus(String status);
  List<Body> findByDetail(String detail);
  List<Body> findByBody(String body);
}