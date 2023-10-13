package com.automaicirrigationsystem.repositories;

import com.automaicirrigationsystem.model.IrrigationSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IrrigationSlotRepository extends JpaRepository<IrrigationSlot, Long> {
    Optional<IrrigationSlot> findByIrrigationSlotIdAndPlotId(Long irrigationSlotId, Long plotId);
    List<IrrigationSlot> findAllByPlotId(Long plotId);
}