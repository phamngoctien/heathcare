package com.example.health_care.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

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
@EqualsAndHashCode(exclude = { "id" })
@Builder
@Data
@AllArgsConstructor
@Setter
@Getter
@Table(name = "datlich")
public class DatLich implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "tongTien")
	private Float tongTien;

	@Column(name = "ngayDatLich")
	@CreationTimestamp
	private LocalDateTime ngayDatLich;

	@Column(name = "tenKhachHang", nullable = false, length = 50)
	private String tenKhachHang;

	@Column(name = "dienThoai", length = 50)
	private String dienThoai;

	@Column(name = "email", length = 50)
	private String email;

	@Column(name = "diaChi", length = 50)
	private String diaChi;


	@Column(name = "gioBatDau")
	private LocalTime gioBatDau;

	@Column(name = "ngayBatDau")
	private LocalDate ngayBatDau;

	@Column(name = "ghiChu")
	private Float ghiChu;

	@Column(name = "soNgay")
	private Integer soNgay;

	@ManyToOne
	@JoinColumn(name = "dieuDuong_id")
	private DieuDuong dieuDuong;

	@ManyToOne
	@JoinColumn(name = "khuVuc_id")
	private KhuVuc khuVuc;

	@ManyToOne
	@JoinColumn(name = "dichVu_id")
	private DichVu dichVu;

	@ManyToOne
	@JoinColumn(name = "khachHang_id")
	private KhachHang khachHang;

//	@JsonIgnore
//	@OneToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "datLich", orphanRemoval = true)
//	private ChiTietDatLich chiTietDatLich;

}
