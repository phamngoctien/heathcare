package com.example.health_care.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.health_care.entity.ThanNhan;

@Repository
public interface ThanNhanRepository extends JpaRepository <ThanNhan, Integer>{
	List<ThanNhan> findByKhachHangId(Integer khachHangId);
	 Optional<ThanNhan> findByidAndKhachHangId(Integer id, Integer khachHangId);
}
