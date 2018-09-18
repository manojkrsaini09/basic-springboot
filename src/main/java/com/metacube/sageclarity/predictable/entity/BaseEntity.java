package com.metacube.sageclarity.predictable.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity<U> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@LastModifiedDate
	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	private LocalDateTime  updatedDate;

	@CreatedDate
	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", updatable = false, nullable = false)
	private LocalDateTime createdDate;

	@CreatedBy
	@Column(name = "created_by", updatable = false, nullable = false)
	protected U createdBy;

	@LastModifiedBy
	protected U lastModifiedBy;

	protected BaseEntity(){}

	protected BaseEntity(Long id){
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime  updatedDate) {
		this.updatedDate = updatedDate;
	}

	public LocalDateTime  getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime  createdDate) {
		this.createdDate = createdDate;
	}

	public U getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(U createdBy) {
		this.createdBy = createdBy;
	}

	public U getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(U lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
}
