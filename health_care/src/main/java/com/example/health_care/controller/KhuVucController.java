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

import com.example.health_care.entity.KhuVuc;
import com.example.health_care.entity.LoaiDichVu;
import com.example.health_care.exception.ResourceNotFoundException;
import com.example.health_care.repository.KhuVucRepository;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class KhuVucController {
	
	@Autowired
	private KhuVucRepository khuVucRepository;

	// get all
		@GetMapping("/khuvuc")
	    public List <KhuVuc> getKhuVuc() {
	        return khuVucRepository.findAll();
	    }
		
		// get by id
		@GetMapping("/khuvuc/{id}")
	    public ResponseEntity < KhuVuc > getKhuVucById(@PathVariable(value = "id") Integer loaiDVId)
	    		throws ResourceNotFoundException {
			KhuVuc lDV = khuVucRepository.findById(loaiDVId)
	            .orElseThrow(() -> new ResourceNotFoundException("Khu Vuc not found :: " + loaiDVId));
	        return ResponseEntity.ok().body(lDV);
	    }

		// create
		@PostMapping("/khuvuc")
	    public KhuVuc taoKhuVuc (@Valid @RequestBody KhuVuc khuVuc) {
	        return khuVucRepository.save(khuVuc);
	    }

		// update
		@PutMapping("/khuvuc/{id}")
	    public ResponseEntity < KhuVuc > capNhatKhuVuc(
	        @PathVariable(value = "id") Integer loaiDVId,
	        @Valid @RequestBody KhuVuc loaiDVDetails) throws ResourceNotFoundException {
			KhuVuc loaiDV = khuVucRepository.findById(loaiDVId)
	            .orElseThrow(() -> new ResourceNotFoundException("Khu Vuc not found :: " + loaiDVId));
			
			loaiDV.setTenKhuVuc(loaiDVDetails.getTenKhuVuc());
			loaiDV.setMaKhuVuc(loaiDVDetails.getMaKhuVuc());
	 
	        final KhuVuc updatedUser = khuVucRepository.save(loaiDV);
	        return ResponseEntity.ok(updatedUser);
	    }

		// delete
		@DeleteMapping("/khuvuc/{id}")
	    public Map < String, Boolean > xoaKhuVuc(
	        @PathVariable(value = "id") Integer loaiDVId) throws ResourceNotFoundException {
			KhuVuc lDV = khuVucRepository.findById(loaiDVId)
	            .orElseThrow(() -> new ResourceNotFoundException("Khu Vuc not found :: " + loaiDVId));

			khuVucRepository.delete(lDV);
	        Map < String, Boolean > response = new HashMap < > ();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	}
}
