//package com.example.health_care.controller;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.health_care.entity.ChiTietDatLich;
//import com.example.health_care.exception.ResourceNotFoundException;
//import com.example.health_care.repository.ChiTietDatLichRepository;
//
//@CrossOrigin(origins = "http://localhost:3000")
//@RestController
//public class ChiTietDatLichController {
//	@Autowired
//	private ChiTietDatLichRepository chiTietDLRepository;
//
//	// get all
//	@GetMapping("/chitietdatlich")
//	public List<ChiTietDatLich> getTatCatChiTietDatLich() {
//		return chiTietDLRepository.findAll();
//	}
//
//	// get by id
//	@GetMapping("/chitietdatlich/{id}")
//	public ResponseEntity<ChiTietDatLich> getChiTietDatLichById(@PathVariable(value = "id") Integer datLichId)
//			throws ResourceNotFoundException {
//		ChiTietDatLich datLich = chiTietDLRepository.findById(datLichId)
//				.orElseThrow(() -> new ResourceNotFoundException("Chi Tiet Dat Lich Khong Tim Thay: " + datLichId));
//
//		return ResponseEntity.ok().body(datLich);
//	}
//
//	// create
//	@PostMapping("chitietdatlich/them")
//	public ChiTietDatLich taoChiTietDL(@Validated @RequestBody ChiTietDatLich datLich) {
//		return chiTietDLRepository.save(datLich);
//	}
//
//	// update_dat_lich
//	@PutMapping("/chitietdatlich/capnhat/{id}")
//	public ResponseEntity<ChiTietDatLich> capNhatDatLich(@PathVariable(value = "id") Integer ItemId,
//			@Validated @RequestBody ChiTietDatLich datLichDetail) throws ResourceNotFoundException {
//		ChiTietDatLich datLich = chiTietDLRepository.findById(ItemId)
//				.orElseThrow(() -> new ResourceNotFoundException("Khong Tim Thay Chi Tiet Dat Lich: " + ItemId));
//
//		datLich.setGioBatDau(datLichDetail.getGioBatDau());
//		datLich.setNgayBatDau(datLichDetail.getNgayBatDau());
//		datLich.setGhiChu(datLichDetail.getGhiChu());
//		datLich.setSoNgay(datLichDetail.getSoNgay());
//		datLich.setSoNgay(datLichDetail.getSoNgay());
//		datLich.setDatLich(datLichDetail.getDatLich());
//		datLich.setDichVu(datLichDetail.getDichVu());
//
//		final ChiTietDatLich capNhatCTDL = chiTietDLRepository.save(datLich);
//		return ResponseEntity.ok(capNhatCTDL);
//	}
//
//	// delete all
//	@DeleteMapping("/chitietdatlich/xoa/{id}")
//	public Map<String, Boolean> xoaChiTietDL(@PathVariable(value = "id") Integer dichVuId) throws Exception {
//		ChiTietDatLich chiTietDL = chiTietDLRepository.findById(dichVuId)
//				.orElseThrow(() -> new ResourceNotFoundException("Khong Tim Thay Chi Tiet Dich Vu: " + dichVuId));
//		chiTietDLRepository.delete(chiTietDL);
//		Map<String, Boolean> response = new HashMap<>();
//		response.put("deleted", Boolean.TRUE);
//		return response;
//	}
//
//}
