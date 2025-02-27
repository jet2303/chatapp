package com.chat.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseEntity {
	
	@CreatedBy
	private String createdBy;
	
	@CreatedDate
	private LocalDateTime createdAt;

	
	 @PrePersist
	 public void prePersist() {
		 LocalDateTime now = LocalDateTime.now();
		 this.createdAt = now;
		 
	 }
//	 @PreUpdate
//	 public void preUpdate() {
//		 	
//	 }
}
