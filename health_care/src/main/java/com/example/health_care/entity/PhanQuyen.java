package com.example.health_care.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@EqualsAndHashCode(exclude= {"Id"})
@Builder
@Data
@AllArgsConstructor
@Setter
@Getter
@Table(name = "phanquyen")
public class PhanQuyen implements Serializable  {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer Id;
	
	@Column(name = "tenQuyen", nullable = false, length = 50) 
	private String tenQuyen;
	
//	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "phanquyen", orphanRemoval = true)
//	private TaiKhoan taiKhoan;
}
