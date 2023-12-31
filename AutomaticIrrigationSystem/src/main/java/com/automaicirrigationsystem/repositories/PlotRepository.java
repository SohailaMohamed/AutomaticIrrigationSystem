package com.automaicirrigationsystem.repositories;

import com.automaicirrigationsystem.model.Plot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PlotRepository extends JpaRepository<Plot, Long> {
}