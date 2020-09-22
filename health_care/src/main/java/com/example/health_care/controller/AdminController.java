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

import com.example.health_care.entity.Admin;
import com.example.health_care.exception.ResourceNotFoundException;
import com.example.health_care.repository.AdminRepository;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AdminController {
	@Autowired
	private AdminRepository adminRepository;
	
	// get all
		@GetMapping("/admin")
		public List<Admin> getAllAdmin() {
			return adminRepository.findAll();
		}

		// get by id
		@GetMapping("/admin/{id}")
		public ResponseEntity<Admin> getAdminById(@PathVariable(value = "id") Integer loaiDVId)
				throws ResourceNotFoundException {
			Admin lDV = adminRepository.findById(loaiDVId)
					.orElseThrow(() -> new ResourceNotFoundException("Admin not found :: " + loaiDVId));
			return ResponseEntity.ok().body(lDV);
		}

		// create
		@PostMapping("/admin/them")
		public Admin taoAdmin(@Validated @RequestBody Admin dichVu) {

			return adminRepository.save(dichVu);
		}

		// update
		@PutMapping("/admin/capnhat/{id}")
		public ResponseEntity<Admin> capNhatAdmin(@PathVariable(value = "id") Integer dichVuId,
				@Validated @RequestBody Admin OrderDetail) throws ResourceNotFoundException {
			Admin Order = adminRepository.findById(dichVuId)
					.orElseThrow(() -> new ResourceNotFoundException("Admin not found on: " + dichVuId));

			Order.setTenAdmin(OrderDetail.getTenAdmin());
			Order.setIsActive(OrderDetail.getIsActive());
			Order.setMatKhau(OrderDetail.getMatKhau());
			Order.setNgaySinh(OrderDetail.getNgaySinh());
			Order.setEmail(OrderDetail.getEmail());
			Order.setDiaChi(OrderDetail.getDiaChi());
			
			final Admin updatedOrder = adminRepository.save(Order);

			return ResponseEntity.ok(updatedOrder);
		}

		// delete
		@DeleteMapping("/admin/xoa/{id}")
		public Map<String, Boolean> xoaAdmin(@PathVariable(value = "id") Integer loaiDVId)
				throws ResourceNotFoundException {
			Admin lDV = adminRepository.findById(loaiDVId)
					.orElseThrow(() -> new ResourceNotFoundException("Admin not found :: " + loaiDVId));

			adminRepository.delete(lDV);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
	}
}
