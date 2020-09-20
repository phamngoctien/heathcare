package com.example.health_care.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "dichvu")
public class DichVu implements Serializable
{
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;
	
	@Column(name = "tenDichVu", nullable = false, length = 50) 
	private String tenDichVu;
	
	@Column(name = "maDichVu", nullable = false, length = 50) 
	private String maDichVu;
	
	@Column(name = "gia") 
	private Float giaDichVu;
	
	

	@JsonIgnore
	@OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "dichVu", 
			orphanRemoval = true)	
	private List<GiayPhepCongViec> giayPhepCongViec;
	
	@JsonIgnore
	@OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "dichVu", 
			orphanRemoval = true)	
	private List<DatLich> datLich;
	
	@ManyToOne
	@JoinColumn(name = "loaiDichVu_id",nullable = false)
	private LoaiDichVu loaiDichVu;
	
	
	 
}
