package com.example.health_care.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.health_care.entity.DieuDuong;

@Repository
public interface DieuDuongRepository extends JpaRepository <DieuDuong, Integer> {
	List<DieuDuong> findByChuyenNganhId(Integer dieuDuongId);
	 Optional<DieuDuong> findByidAndChuyenNganhId(Integer id, Integer dieuDuongId);
	 
	 List<DieuDuong> findByKhuVucId(Integer khuVucId);
	 Optional<DieuDuong> findByidAndKhuVucId(Integer id, Integer khuVucId);
}
