package com.example.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.api.model.Camera;

@Repository
public interface CameraRepository extends CrudRepository<Camera, Long>{

}