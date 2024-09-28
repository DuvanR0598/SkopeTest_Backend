package com.udea.skopetest.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udea.skopetest.persistence.entity.RolEntity;

@Repository
public interface IRolRepository extends JpaRepository<RolEntity,Long> {
	
	Optional<RolEntity> findById(Long rolId);

}
