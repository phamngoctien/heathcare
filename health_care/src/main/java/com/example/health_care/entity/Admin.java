package com.example.health_care.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.health_care.utility.Gender;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@EqualsAndHashCode(exclude= {"id"})
@Entity
@Data
@Table(name = "admin")
public class Admin implements Serializable {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;
	
	@Column(name = "tenAdmin", nullable = false, length = 50) 
	private String tenAdmin;
	
	@Column(name = "isActive", nullable = false, length = 50) 
	private Boolean isActive;
	
	@Column(name = "matKhau", nullable = false, length = 50) 
	private String matKhau;
	
	@Column(name = "ngaySinh") 
	private Date ngaySinh;
	
	@Column(name = "email", length = 50) 
	private String email;
	
	@Column(name = "diaChi",  length = 50) 
	private String diaChi;
	
	@ManyToOne
	@JoinColumn(name = "khuVuc_id")
	private KhuVuc khuVuc;
	
}
