package com.example.health_care.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.health_care.entity.KhachHang;
import com.example.health_care.exception.ResourceNotFoundException;
import com.example.health_care.repository.KhachHangRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class KhachHangController {

	@Autowired
	private KhachHangRepository khachHangRepository;

	// get all
	@GetMapping("/khachhang")
	public List<KhachHang> getKhachHang() {
		return khachHangRepository.findAll();
	}

	// get by id
	@GetMapping("/khachhang/{id}")
	public ResponseEntity<KhachHang> getKhachHangById(@PathVariable(value = "id") Integer loaiDVId)
			throws ResourceNotFoundException {
		KhachHang lDV = khachHangRepository.findById(loaiDVId)
				.orElseThrow(() -> new ResourceNotFoundException("Khach Hang  not found :: " + loaiDVId));
		return ResponseEntity.ok().body(lDV);
	}

	// create
	@PostMapping("/khachhang/them")
	public KhachHang taoKhachHang(@Valid @RequestBody KhachHang khachHang) {
		return khachHangRepository.save(khachHang);
	}

	// update
	@PutMapping("/khachhang/capnhat/{id}")
	public ResponseEntity<KhachHang> capNhatKhachHang(@PathVariable(value = "id") Integer loaiDVId,
			@Valid @RequestBody KhachHang loaiDVDetails) throws ResourceNotFoundException {
		KhachHang loaiDV = khachHangRepository.findById(loaiDVId)
				.orElseThrow(() -> new ResourceNotFoundException("Khach Hang not found :: " + loaiDVId));

		loaiDV.setTenKhachHang(loaiDVDetails.getTenKhachHang());
		loaiDV.setEmail(loaiDVDetails.getEmail());
		loaiDV.setDiaChi(loaiDVDetails.getDiaChi());
		loaiDV.setNgaySinh(loaiDVDetails.getNgaySinh());

		final KhachHang updatedUser = khachHangRepository.save(loaiDV);
		return ResponseEntity.ok(updatedUser);
	}

	// delete
	@DeleteMapping("/khachhang/xoa/{id}")
	public Map<String, Boolean> xoaKhachHang(@PathVariable(value = "id") Integer loaiDVId)
			throws ResourceNotFoundException {
		KhachHang lDV = khachHangRepository.findById(loaiDVId)
				.orElseThrow(() -> new ResourceNotFoundException("Khach Hang not found :: " + loaiDVId));

		khachHangRepository.delete(lDV);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
