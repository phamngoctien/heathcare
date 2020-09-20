package com.example.health_care.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "loaidichvu")
public class LoaiDichVu implements Serializable {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;
	
	@Column(name = "maLoaiDV", nullable = false, length = 50) 
	private String maLoaiDV;
	
	@Column(name = "tenLoaiDV", nullable = false, length = 50) 
	private String tenLoaiDV;
	
	@JsonIgnore
	@OneToMany ( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "loaiDichVu", 
			 orphanRemoval=true)
	private List<DichVu> dichVu;
 
}
