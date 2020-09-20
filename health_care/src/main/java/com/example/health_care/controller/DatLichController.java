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

import com.example.health_care.entity.DatLich;
import com.example.health_care.exception.ResourceNotFoundException;
import com.example.health_care.repository.DatLichRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DatLichController {
	@Autowired
	private DatLichRepository datLichRepository;

	// get all
	@GetMapping("/datlich")
	public List<DatLich> getAllDatLich() {
		return datLichRepository.findAll();
	}

	// get by id
	@GetMapping("/datlich/{id}")
	public ResponseEntity<DatLich> getDatLichById(@PathVariable(value = "id") Integer loaiDVId)
			throws ResourceNotFoundException {
		DatLich lDV = datLichRepository.findById(loaiDVId)
				.orElseThrow(() -> new ResourceNotFoundException("Dat Lich not found :: " + loaiDVId));
		return ResponseEntity.ok().body(lDV);
	}

	// create
	@PostMapping("/datlich/them")
	public DatLich taoDatLich(@Validated @RequestBody DatLich datLich) {

		return datLichRepository.save(datLich);
	}

	// update
	@PutMapping("/datlich/capnhat/{id}")
	public ResponseEntity<DatLich> capNhatDatLich(@PathVariable(value = "id") Integer datLichId,
			@Validated @RequestBody DatLich datLichDetail) throws ResourceNotFoundException {
		DatLich datLich = datLichRepository.findById(datLichId)
				.orElseThrow(() -> new ResourceNotFoundException("Dat Lich not found on: " + datLichId));

		datLich.setTongTien(datLichDetail.getTongTien());
		datLich.setNgayDatLich(datLichDetail.getNgayDatLich());
		datLich.setGioBatDau(datLichDetail.getGioBatDau());
		datLich.setNgayBatDau(datLichDetail.getNgayBatDau());
		datLich.setGhiChu(datLichDetail.getGhiChu());
		datLich.setSoNgay(datLichDetail.getSoNgay());
		datLich.setDichVu(datLichDetail.getDichVu());
		datLich.setTenKhachHang(datLichDetail.getTenKhachHang());
		datLich.setEmail(datLichDetail.getEmail());
		datLich.setDiaChi(datLichDetail.getDiaChi());
		datLich.setNgaySinh(datLichDetail.getNgaySinh());
		
		final DatLich updatedOrder = datLichRepository.save(datLich);

		return ResponseEntity.ok(updatedOrder);
	}

	// delete
	@DeleteMapping("/datlich/xoa/{id}")
	public Map<String, Boolean> xoaDatLich(@PathVariable(value = "id") Integer loaiDVId)
			throws ResourceNotFoundException {
		DatLich lDV = datLichRepository.findById(loaiDVId)
				.orElseThrow(() -> new ResourceNotFoundException("Dat Lich not found :: " + loaiDVId));

		datLichRepository.delete(lDV);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
