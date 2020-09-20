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

import com.example.health_care.entity.GiayPhepCongViec;
import com.example.health_care.exception.ResourceNotFoundException;
import com.example.health_care.repository.GiayPhepCongViecRepository;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class GiayPhepCongViecController {
	@Autowired
	private GiayPhepCongViecRepository giayPhepCVRepository;

	// get all
		@GetMapping("/giayphepcongviec")
	    public List <GiayPhepCongViec> getGiayPhepCongViec() {
	        return giayPhepCVRepository.findAll();
	    }
		
		// get by id
		@GetMapping("/giayphepcongviec/{id}")
	    public ResponseEntity < GiayPhepCongViec > getGiayPhepCongViecById(@PathVariable(value = "id") Integer giayPhepCVId)
	    		throws ResourceNotFoundException {
			GiayPhepCongViec lDV = giayPhepCVRepository.findById(giayPhepCVId)
	            .orElseThrow(() -> new ResourceNotFoundException("Khong Tim Thay Giay Phep Cong Viec  :: " + giayPhepCVId));
	        return ResponseEntity.ok().body(lDV);
	    }

		// create
		@PostMapping("/giayphepcongviec/them")
	    public GiayPhepCongViec taoGiayPhepCongViec(@Valid @RequestBody GiayPhepCongViec giayPhepCV) {
	        return giayPhepCVRepository.save(giayPhepCV);
	    }

		// cap nhat
		@PutMapping("/giayphepcongviec/capnhat/{id}")
	    public ResponseEntity < GiayPhepCongViec > capNhatGiayPhepCongViec(
	        @PathVariable(value = "id") Integer giayPhepCVId,
	        @Valid @RequestBody GiayPhepCongViec giayPhepCVDetails) throws ResourceNotFoundException {
			GiayPhepCongViec giayPCV = giayPhepCVRepository.findById(giayPhepCVId)
	            .orElseThrow(() -> new ResourceNotFoundException("Khong Tim Thay Giay Phep Cong Viec :: " + giayPhepCVId));
			giayPCV.setXepLoai(giayPhepCVDetails.getXepLoai());
			giayPCV.setDonViCapBang(giayPhepCVDetails.getDonViCapBang());
			giayPCV.setDieuDuong(giayPhepCVDetails.getDieuDuong());
			giayPCV.setNamTotNghiep(giayPhepCVDetails.getNamTotNghiep());
			giayPCV.setDichVu(giayPhepCVDetails.getDichVu());
			
	        final GiayPhepCongViec giayPhepCongViec = giayPhepCVRepository.save(giayPCV);
	        return ResponseEntity.ok(giayPhepCongViec);
	    }

		// delete
		@DeleteMapping("/giayphepcongviec/xoa/{id}")
	    public Map < String, Boolean > xoaGiayPhepCongViec(
	        @PathVariable(value = "id") Integer giayPhepCVId) throws ResourceNotFoundException {
			GiayPhepCongViec lDV = giayPhepCVRepository.findById(giayPhepCVId)
	            .orElseThrow(() -> new ResourceNotFoundException("Khong Tim Thay Giay Phep Cong Viec :: " + giayPhepCVId));

			giayPhepCVRepository.delete(lDV);
	        Map < String, Boolean > response = new HashMap < > ();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }
}
