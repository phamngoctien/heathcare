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

import com.example.health_care.entity.ThanNhan;
import com.example.health_care.exception.ResourceNotFoundException;
import com.example.health_care.repository.ThanNhanRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ThanNhanController {
	@Autowired
	private ThanNhanRepository thanNhanRepository;


	// get all
	@GetMapping("/thannhan")
	public List<ThanNhan> getAllThanNhan() {
		return thanNhanRepository.findAll();
	}

	// get by id
	@GetMapping("/thannhan/{id}")
	public ResponseEntity<ThanNhan> getThanNhanById(@PathVariable(value = "id") Integer loaiDVId)
			throws ResourceNotFoundException {
		ThanNhan lDV = thanNhanRepository.findById(loaiDVId)
				.orElseThrow(() -> new ResourceNotFoundException("Than Nhan not found :: " + loaiDVId));
		return ResponseEntity.ok().body(lDV);
	}

	// create
	@PostMapping("/thannhan/them")
	public ThanNhan taoThanNhan(@Validated @RequestBody ThanNhan thanNhan) {

		return thanNhanRepository.save(thanNhan);
	}

	// update
	@PutMapping("/thannhan/capnhat/{id}")
	public ResponseEntity<ThanNhan> capNhatThanNhan(@PathVariable(value = "id") Integer dichVuId,
			@Validated @RequestBody ThanNhan OrderDetail) throws ResourceNotFoundException {
		ThanNhan Order = thanNhanRepository.findById(dichVuId)
				.orElseThrow(() -> new ResourceNotFoundException("Than Nhan not found on: " + dichVuId));

		Order.setTenThanNhan(OrderDetail.getTenThanNhan());
		Order.setDienThoai(OrderDetail.getDienThoai());
		Order.setDiaChi(OrderDetail.getDiaChi());
		Order.setNgaySinh(OrderDetail.getNgaySinh());
		
		final ThanNhan updatedOrder = thanNhanRepository.save(Order);

		return ResponseEntity.ok(updatedOrder);
	}

	// delete
	@DeleteMapping("/thannhan/xoa/{id}")
	public Map<String, Boolean> xoaThanNhan(@PathVariable(value = "id") Integer loaiDVId)
			throws ResourceNotFoundException {
		ThanNhan lDV = thanNhanRepository.findById(loaiDVId)
				.orElseThrow(() -> new ResourceNotFoundException("Than Nhan not found :: " + loaiDVId));

		thanNhanRepository.delete(lDV);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;

	}
}
