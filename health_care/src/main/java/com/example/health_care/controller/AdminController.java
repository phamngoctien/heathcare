package com.example.health_care.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.health_care.entity.Admin;
import com.example.health_care.repository.AdminRepository;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AdminController {
	@Autowired
	private AdminRepository adminRepository;
	
	// get all
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ResponseEntity<List<Admin>> findAllAdmin() {
		List<Admin> admin = adminRepository.findAll();
		if (admin.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(admin, HttpStatus.OK);
	}
	
	// get by id
	@RequestMapping(value = "/admin/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Admin> getAdminById(@PathVariable("id") Integer id) {
		Optional<Admin> admin = adminRepository.findById(id);

		if (!admin.isPresent()) {
			return new ResponseEntity<>(admin.get(), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(admin.get(), HttpStatus.OK);
	}

	// create
	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public ResponseEntity<Admin> taoAdmin(@RequestBody Admin admin, UriComponentsBuilder builder) 
	{
		adminRepository.save(admin);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/admin/{id}").buildAndExpand(admin.getId()).toUri());
		return new ResponseEntity<>(admin, HttpStatus.CREATED);
	}

	// cap nhat
	@RequestMapping(value = "/admin/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Admin> capNhatAdmin(@PathVariable("id") Integer id, @RequestBody Admin admin) {
		Optional<Admin> currentAdmin = adminRepository.findById(id);
		if (!currentAdmin.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		currentAdmin.get().setIsActive(admin.getIsActive());
//		currentAdmin.get().setMaAdmin(admin.getMaAdmin());
		currentAdmin.get().setTenAdmin(admin.getTenAdmin());
		currentAdmin.get().setMatKhau(admin.getMatKhau());
		currentAdmin.get().setNgaySinh(admin.getNgaySinh());
		currentAdmin.get().setDiaChi(admin.getDiaChi());
		currentAdmin.get().setEmail(admin.getEmail());
		
		adminRepository.save(currentAdmin.get());
		return new ResponseEntity<>(currentAdmin.get(), HttpStatus.OK);
	}

	// delete
	@RequestMapping(value = "/admin/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Admin> xoaAdmin(@PathVariable("id") Integer id) {
		Optional<Admin> admin = adminRepository.findById(id);
		if (!admin.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		adminRepository.delete(admin.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
