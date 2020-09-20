package com.example.health_care.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "giayphepcongviec")
public class GiayPhepCongViec implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "xepLoai", nullable = false, length = 50) 
	private String xepLoai;
	
	@Column(name = "namTotNghiep")
	private LocalDate namTotNghiep;
	
	@Column(name = "donViCapBang")
	private String donViCapBang;
	
	@ManyToOne
	@JoinColumn(name = "dieuDuong_id")
	private DieuDuong dieuDuong;
	
	@ManyToOne
	@JoinColumn(name = "dichVu_id")
	private DichVu dichVu;
	
	
}
