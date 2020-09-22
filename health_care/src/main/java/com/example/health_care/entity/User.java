package com.example.health_care.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.health_care.utility.Gender;
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
@Table(name = "user")
public class User implements Serializable {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;
	
	@Column(name = "tenUser", nullable = false, length = 50) 
	private String tenUser;
	
	@Column(name = "tenUserDangNhap", unique = true) 
	private String tenUserDangNhap;
	
	@Column(name = "matKhau", nullable = false, length = 50) 
	private String matKhau;
	
	@Column(name = "isActive") 
	private Boolean isActive;

	@Column(name = "email", length = 50) 
	private String email;
	
	@Column(name = "diaChi",  length = 50) 
	private String diaChi;
	
	@Column(name = "gioiTinh")
	@Enumerated(EnumType.STRING)
	private Gender gioiTinh;
	
	@JsonIgnore
	@OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "dieuDuong", 
			orphanRemoval = true)	
	private List<DatLich> DatLich;
	
	@JsonIgnore
	@OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "dieuDuong", 
			orphanRemoval = true)	
	private List<GiayPhepCongViec> giayPhepCongViec;
	
	@ManyToOne
	@JoinColumn(name = "chuyenNganh_id")
	private ChuyenNganh chuyenNganh;
	
	@ManyToOne
	@JoinColumn(name = "khuVuc_id")
	private KhuVuc khuVuc;
	

}
