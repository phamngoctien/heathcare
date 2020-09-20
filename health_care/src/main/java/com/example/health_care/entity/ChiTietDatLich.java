//package com.example.health_care.entity;
//
//import java.io.Serializable;
//import java.time.LocalDate;
//import java.time.LocalTime;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@NoArgsConstructor
//@SuppressWarnings("serial")
//@EqualsAndHashCode(exclude= {"id"})
//@Builder
//@Data
//@AllArgsConstructor
//@Setter
//@Getter
//@Table(name = "chitietdatlich")
//public class ChiTietDatLich implements Serializable {
//	@Id 
//	@GeneratedValue(strategy = GenerationType.IDENTITY) 
//	private Integer id;
//	
//	@Column(name = "gioBatDau") 
//	private LocalTime gioBatDau;
//	
//	@Column(name = "ngayBatDau") 
//	private LocalDate ngayBatDau;
//	
//	@Column(name = "ghiChu")
//	private Float ghiChu;
//	
//	@Column(name = "soNgay") 
//	private  Integer soNgay;
//	
//	@JsonIgnore
//	@OneToOne
//	@JoinColumn(name = "datLich_id")
//	private DatLich datLich;
//	
//	@ManyToOne
//	@JoinColumn(name = "dichVu_id")
//	private DichVu dichVu;
//	
//}
