package com.example.health_care.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

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
@Table(name = "chuyennganh")
public class ChuyenNganh implements Serializable  {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;

	@Column(name = "maChuyenNganh", nullable = false, length = 50) 
	private String maChuyenNganh;
	
	@Column(name = "tenChuyenNganh", nullable = false, length = 50) 
	private String tenChuyenNganh;

	@JsonIgnore
	@OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "chuyenNganh", 
			orphanRemoval = true)	
	private List<DieuDuong> dieuDuong;
	
//	@OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "chuyenNganh", 
//			orphanRemoval = true)	
//	private Set<DieuDuong> dieuDuong;
	
}
