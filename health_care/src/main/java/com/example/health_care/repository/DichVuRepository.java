package com.example.health_care.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.health_care.entity.DichVu;

@Repository
public interface DichVuRepository extends JpaRepository <DichVu, Integer> {
	List<DichVu> findByLoaiDichVuId(Integer loaidichvuId);
	 Optional<DichVu> findByidAndLoaiDichVuId(Integer id, Integer loaidichvuId);

		
}
