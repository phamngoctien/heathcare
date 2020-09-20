package com.example.health_care.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.health_care.entity.DichVu;
import com.example.health_care.exception.ResourceNotFoundException;
import com.example.health_care.repository.DichVuRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class DichVuController {

	@Autowired
	private DichVuRepository dichvuRepository;

	// get all
	@GetMapping("/dichvu")
	public List<DichVu> getAllDichVu() {
		return dichvuRepository.findAll();
	}

	// get by id
	@GetMapping("/dichvu/{id}")
	public ResponseEntity<DichVu> getLoaiDichVuById(@PathVariable(value = "id") Integer loaiDVId)
			throws ResourceNotFoundException {
		DichVu lDV = dichvuRepository.findById(loaiDVId)
				.orElseThrow(() -> new ResourceNotFoundException("Loai Dich Vu not found :: " + loaiDVId));
		return ResponseEntity.ok().body(lDV);
	}

	// create
	@PostMapping("/dichvu/them")
	public DichVu taoDichVu(@Validated @RequestBody DichVu dichVu) {

		return dichvuRepository.save(dichVu);
	}

	// update
	@PutMapping("/dichvu/capnhat/{id}")
	public ResponseEntity<DichVu> capNhatDichVu(@PathVariable(value = "id") Integer dichVuId,
			@Validated @RequestBody DichVu OrderDetail) throws ResourceNotFoundException {
		DichVu Order = dichvuRepository.findById(dichVuId)
				.orElseThrow(() -> new ResourceNotFoundException("Dich Vu not found on: " + dichVuId));

		Order.setTenDichVu(OrderDetail.getTenDichVu());
		Order.setMaDichVu(OrderDetail.getMaDichVu());
		Order.setGiaDichVu(OrderDetail.getGiaDichVu());

		final DichVu updatedOrder = dichvuRepository.save(Order);

		return ResponseEntity.ok(updatedOrder);
	}

	// delete
	@DeleteMapping("/dichvu/xoa/{id}")
	public Map<String, Boolean> xoaDichVu(@PathVariable(value = "id") Integer loaiDVId)
			throws ResourceNotFoundException {
		DichVu lDV = dichvuRepository.findById(loaiDVId)
				.orElseThrow(() -> new ResourceNotFoundException("Dich Vu not found :: " + loaiDVId));

		dichvuRepository.delete(lDV);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
