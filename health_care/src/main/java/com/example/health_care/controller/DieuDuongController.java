package com.example.health_care.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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

import com.example.health_care.entity.ChuyenNganh;
import com.example.health_care.entity.DichVu;
import com.example.health_care.entity.DieuDuong;
import com.example.health_care.entity.KhuVuc;
import com.example.health_care.entity.LoaiDichVu;
import com.example.health_care.exception.ResourceNotFoundException;
import com.example.health_care.repository.ChuyenNganhRepository;
import com.example.health_care.repository.DieuDuongRepository;
import com.example.health_care.repository.KhuVucRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DieuDuongController {

	@Autowired
	private DieuDuongRepository dieuDuongRepository;

//	@Autowired
//	private KhuVucRepository khuVucRepository;
//	
//	@Autowired
//	private ChuyenNganhRepository chuyenNganhRepository;
//	

	// get all
	@GetMapping("/dieuduong")
	public List<DieuDuong> getAllDieuDuong() {
		return dieuDuongRepository.findAll();
	}

	// get by id
	@GetMapping("/dieuduong/{id}")
	public ResponseEntity<DieuDuong> getDieuDuongById(@PathVariable(value = "id") Integer dieuDuongId)
			throws ResourceNotFoundException {
		DieuDuong lDV = dieuDuongRepository.findById(dieuDuongId)
				.orElseThrow(() -> new ResourceNotFoundException("Dieu Duong not found :: " + dieuDuongId));
		return ResponseEntity.ok().body(lDV);
	}

//		// create
//		@PostMapping("/khuvuc/{khuvucId}/dieuduong")
//		public DieuDuong taoDichVuByKV(@PathVariable(value = "khuvucId") Integer loaiDichVuId,
//				@Valid @RequestBody DieuDuong dichVu) throws ResourceNotFoundException {
//			return khuVucRepository.findById(loaiDichVuId).map(loaiDV -> {
//				dichVu.setKhuVuc(loaiDV);
//				return dieuDuongRepository.save(dichVu);
//			}
//			).orElseThrow(() -> new ResourceNotFoundException("Khong Tim Thay Loai Dich Vu"));
//		}

	// create
	@PostMapping("/dieuduong/them")
	public DieuDuong createDieuDuong(@Validated @RequestBody DieuDuong dieuDuong) {
		return dieuDuongRepository.save(dieuDuong);
	}

	// update
	@PutMapping("/dieuduong/capnhat/{id}")
	public ResponseEntity<DieuDuong> capNhatDieuDuong(@PathVariable(value = "id") Integer OrderId,
			@Validated @RequestBody DieuDuong dDDetail) throws ResourceNotFoundException {
		DieuDuong dichvu = dieuDuongRepository.findById(OrderId)
				.orElseThrow(() -> new ResourceNotFoundException("Dieu Duong not found on: " + OrderId));

		dichvu.setTenDieuDuong(dDDetail.getTenDieuDuong());
		dichvu.setTenDangNhap(dDDetail.getTenDangNhap());
		dichvu.setEmail(dDDetail.getEmail());
		dichvu.setNgaySinh(dDDetail.getNgaySinh());
		dichvu.setMatKhau(dDDetail.getMatKhau());
		dichvu.setIsActive(dDDetail.getIsActive());
		dichvu.setGioiTinh(dDDetail.getGioiTinh());
		dichvu.setDiaChi(dDDetail.getDiaChi());

		final DieuDuong updatedOrder = dieuDuongRepository.save(dichvu);

		return ResponseEntity.ok(updatedOrder);
	}
	
	@DeleteMapping("/dieuduong/xoa//{id}")
    public Map < String, Boolean > xoaDieuDuong(
        @PathVariable(value = "id") Integer DieuDuongId) throws ResourceNotFoundException {
		DieuDuong lDV = dieuDuongRepository.findById(DieuDuongId)
            .orElseThrow(() -> new ResourceNotFoundException("Dieu Duong not found :: " + DieuDuongId));

		dieuDuongRepository.delete(lDV);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

//		@PostMapping("/chuyennganh/{chuyennganhId}/dieuduong")
//		public DieuDuong taoDichVuByCN(@PathVariable(value = "chuyennganhId") Integer loaiDichVuId,
//				@Valid @RequestBody DieuDuong dichVu) throws ResourceNotFoundException {
//			return chuyenNganhRepository.findById(loaiDichVuId).map(loaiDV -> {
//				dichVu.setChuyenNganh(loaiDV);
//				return dieuDuongRepository.save(dichVu);
//			}).orElseThrow(() -> new ResourceNotFoundException("Khong Tim Thay Loai Dich Vu"));
//		}
//		

	// add new product

//		// update
//		@PutMapping("/loaidichvu/{loaidichvuId}/dichvu/{dichvuId}")
//		public DichVu updateCourse(@PathVariable(value = "loaidichvuId") Integer instructorId,
//				@PathVariable(value = "dichvuId") Integer loaiDichVuId, @Valid @RequestBody DichVu courseRequest)
//				throws ResourceNotFoundException {
//			if (!loaiDichvuRepository.existsById(instructorId)) {
//				throw new ResourceNotFoundException("Khong Tim Thay Loai Dich Vu");
//			}
//
//			return dichvuRepository.findById(loaiDichVuId).map(dichvu -> {
//				dichvu.setMaDichVu(courseRequest.getMaDichVu());
//				dichvu.setTenDichVu(courseRequest.getTenDichVu());
//				dichvu.setGiaDichVu(courseRequest.getGiaDichVu());
//
//				return dichvuRepository.save(dichvu);
//			}).orElseThrow(() -> new ResourceNotFoundException("Dich Vu Khong Tim Thay"));
//		}
//
//		// delete
//		@DeleteMapping("/loaidichvu/{loaidichvuId}/dichvu/{dichvuId}")
//		public ResponseEntity<?> deleteDichVu(@PathVariable(value = "loaidichvuId") Integer loaiDichVuId,
//				@PathVariable(value = "dichvuId") Integer dichVuId) throws ResourceNotFoundException {
//			return dichvuRepository.findByidAndLoaiDichVuId(dichVuId, loaiDichVuId).map(course -> {
//				dichvuRepository.delete(course);
//				return ResponseEntity.ok().build();
//			}).orElseThrow(() -> new ResourceNotFoundException(
//					"Dich Vu Khong Tim Thay Voi Id " + dichVuId + " and Loai Dich Vu " + loaiDichVuId));

}
