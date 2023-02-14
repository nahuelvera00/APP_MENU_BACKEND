package com.nahuel.apirest.repository;

import com.nahuel.apirest.entities.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusinessRepository extends CrudRepository<Business, Long> {
    List<Business> findByUser_Id(Long id);
}
