package com.example.restservice.Database;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.example.restservice.Models.Body;

@EnableScan
public interface PagingBodyRepository extends CrudRepository<Body, String> {
  Page<Body> findById(Long id, Pageable pageable);
  Page<Body> findByStatus(String status, Pageable pageable);
  Page<Body> findByDetail(String detail, Pageable pageable);
  Page<Body> findByBody(String body, Pageable pageable);

  @EnableScan
  @EnableScanCount
  Page<Body> findAll(Pageable pageable);
}