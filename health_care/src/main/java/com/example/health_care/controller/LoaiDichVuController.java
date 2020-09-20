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

import com.example.health_care.entity.LoaiDichVu;
import com.example.health_care.exception.ResourceNotFoundException;
import com.example.health_care.repository.LoaiDichVuRepository;
@CrossOrigin(origins = "http://localhost:3000")

@RestController
public class LoaiDichVuController {

	@Autowired
	private LoaiDichVuRepository loaiDichVuRepository;
	
	// get all
	@GetMapping("/loaidichvu")
    public List <LoaiDichVu> getLoaiDichVu() {
        return loaiDichVuRepository.findAll();
    }
	
	// get by id
	@GetMapping("/loaidichvu/{id}")
    public ResponseEntity < LoaiDichVu > getLoaiDichVuById(@PathVariable(value = "id") Integer loaiDVId)
    		throws ResourceNotFoundException {
		LoaiDichVu lDV = loaiDichVuRepository.findById(loaiDVId)
            .orElseThrow(() -> new ResourceNotFoundException("Loai Dich Vu not found :: " + loaiDVId));
        return ResponseEntity.ok().body(lDV);
    }

	// create
	@PostMapping("/loaidichvu")
    public LoaiDichVu taoLoaiDichVu(@Valid @RequestBody LoaiDichVu loaiDV) {
        return loaiDichVuRepository.save(loaiDV);
    }

	// update
	@PutMapping("/loaidichvu/{id}")
    public ResponseEntity < LoaiDichVu > capNhatLoaiDichVu(@PathVariable(value = "id") Integer loaiDVId,
        @Valid @RequestBody LoaiDichVu loaiDVDetails) throws ResourceNotFoundException {
		LoaiDichVu loaiDV = loaiDichVuRepository.findById(loaiDVId)
            .orElseThrow(() -> new ResourceNotFoundException("Loai Dich Vu not found :: " + loaiDVId));
		loaiDV.setMaLoaiDV(loaiDVDetails.getMaLoaiDV());
		loaiDV.setTenLoaiDV(loaiDVDetails.getTenLoaiDV());
 
        final LoaiDichVu updatedUser = loaiDichVuRepository.save(loaiDV);
        return ResponseEntity.ok(updatedUser);
    }

	// delete
	@DeleteMapping("/loaidichvu/{id}")
    public Map < String, Boolean > xoaLoaiDichVu(
        @PathVariable(value = "id") Integer loaiDVId) throws ResourceNotFoundException {
		LoaiDichVu lDV = loaiDichVuRepository.findById(loaiDVId)
            .orElseThrow(() -> new ResourceNotFoundException("Loai Dich Vu not found :: " + loaiDVId));

		loaiDichVuRepository.delete(lDV);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
