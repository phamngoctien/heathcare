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
@Table(name = "khuvuc")
public class KhuVuc implements Serializable {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;
	
	@Column(name = "tenKhuVuc", nullable = false, length = 200) 
	private String tenKhuVuc;
	
	@Column(name = "maKhuVuc", nullable = false, length = 200) 
	private String maKhuVuc;
	
	@Column(name = "gia") 
	private Float gia;
	
	@JsonIgnore
	@OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "khuVuc",
			orphanRemoval = true)
	private List<DatLich> DatLich;
	
	@JsonIgnore
	@OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "khuVuc",
			orphanRemoval = true)
	private List<DieuDuong> dieuDuong;
	
	
	@JsonIgnore
	@OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "khuVuc",
			orphanRemoval = true)
	private List<Admin> admin;
}
