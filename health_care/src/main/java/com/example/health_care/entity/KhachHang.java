package com.example.health_care.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "khachhang")
public class KhachHang implements Serializable {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;
	
	@Column(name = "tenKhachHang", nullable = false, length = 50) 
	private String tenKhachHang;

	@Column(name = "dienThoai", length = 50) 
	private String dienThoai;
	
	@Column(name = "email", length = 50) 
	private String email;
	
	@Column(name = "diaChi", length = 50)
	private String diaChi;
	
	@Column(name = "ngaySinh")
	private LocalDate ngaySinh;

	@JsonIgnore
	@OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "khachHang", 
			 orphanRemoval=true)
	private List<DatLich> datLich;
	
	@JsonIgnore
	@OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "khachHang", 
			 orphanRemoval=true)
	private List<ThanNhan> thanNhan;

}
