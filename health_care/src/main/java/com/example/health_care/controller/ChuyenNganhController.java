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

import com.example.health_care.entity.ChuyenNganh;
import com.example.health_care.entity.KhuVuc;
import com.example.health_care.exception.ResourceNotFoundException;
import com.example.health_care.repository.ChuyenNganhRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ChuyenNganhController {
	@Autowired
	private ChuyenNganhRepository chuyenNganhRepository;

	// get all
	@GetMapping("/chuyennganh")
	public List<ChuyenNganh> getAllChuyenNganh() {
		return chuyenNganhRepository.findAll();
	}

	// get by id
	@GetMapping("/chuyennganh/{id}")
	public ResponseEntity<ChuyenNganh> getChuyenNganhById(@PathVariable(value = "id") Integer chuyenNganhId)
			throws ResourceNotFoundException {
		ChuyenNganh lDV = chuyenNganhRepository.findById(chuyenNganhId)
				.orElseThrow(() -> new ResourceNotFoundException("Chuyen Nganh not found :: " + chuyenNganhId));
		return ResponseEntity.ok().body(lDV);
	}

	// create
	@PostMapping("/chuyennganh/them")
	public ChuyenNganh taoChuyenNganh(@Valid @RequestBody ChuyenNganh chuyenNganh) {
		return chuyenNganhRepository.save(chuyenNganh);
	}

	// update
	@PutMapping("/chuyennganh/capnhat/{id}")
	public ResponseEntity<ChuyenNganh> capNhatChuyenNganh(@PathVariable(value = "id") Integer chuyenNganhId,
			@Valid @RequestBody ChuyenNganh loaiDVDetails) throws ResourceNotFoundException {
		ChuyenNganh chuyenNganh = chuyenNganhRepository.findById(chuyenNganhId)
				.orElseThrow(() -> new ResourceNotFoundException("Chuyen Nganh not found :: " + chuyenNganhId));

		chuyenNganh.setTenChuyenNganh(loaiDVDetails.getTenChuyenNganh());
		chuyenNganh.setMaChuyenNganh(loaiDVDetails.getMaChuyenNganh());

		final ChuyenNganh updatedUser = chuyenNganhRepository.save(chuyenNganh);
		return ResponseEntity.ok(updatedUser);
	}

	// delete
	@DeleteMapping("/chuyennganh/xoa/{id}")
	public Map<String, Boolean> xoaChuyenNganh(@PathVariable(value = "id") Integer chuyenNganhId)
			throws ResourceNotFoundException {
		ChuyenNganh lDV = chuyenNganhRepository.findById(chuyenNganhId)
				.orElseThrow(() -> new ResourceNotFoundException("Chuyen Nganh not found :: " + chuyenNganhId));

		chuyenNganhRepository.delete(lDV);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
