package com.udea.skopetest.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udea.skopetest.persistence.entity.Examen;

@Repository
public interface IExamenRepository extends JpaRepository<Examen,Long> {

}
