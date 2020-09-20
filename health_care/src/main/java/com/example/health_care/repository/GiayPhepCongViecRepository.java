package com.example.health_care.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.health_care.entity.GiayPhepCongViec;

@Repository
public interface GiayPhepCongViecRepository extends JpaRepository <GiayPhepCongViec, Integer> {

}
