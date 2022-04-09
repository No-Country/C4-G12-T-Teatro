package com.teatro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teatro.entity.Sala;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {

}
