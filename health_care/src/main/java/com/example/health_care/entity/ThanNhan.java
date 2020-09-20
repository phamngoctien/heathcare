package com.example.health_care.entity;

import java.io.Serializable;
import java.time.LocalDate;

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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@SuppressWarnings("serial")
@EqualsAndHashCode(exclude= {"id"})
@Builder
@Data
@AllArgsConstructor
@Setter
@Getter
@Table(name = "thannhan")
public class ThanNhan implements Serializable {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;
	
	@Column(name = "tenThanNhan", nullable = false, length = 50) 
	private String tenThanNhan;
	
	@Column(name = "dienThoai", length = 20) 
	private String dienThoai;
	
	@Column(name = "diaChi", length = 50)
	private String DiaChi;
	
	@Column(name = "ngaySinh")
	private LocalDate ngaySinh;
	
	
	@ManyToOne
	@JoinColumn(name = "khachHang_id")
	private KhachHang khachHang;
}
