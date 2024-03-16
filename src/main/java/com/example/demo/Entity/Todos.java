package com.example.demo.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Todos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String description;
	private String priority;
	private String status;
	
	@CreationTimestamp
	private LocalDateTime creation_time;
	
	@UpdateTimestamp
	private LocalDateTime update_time;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreation_time() {
		return creation_time;
	}

	public void setCreation_time(LocalDateTime creation_time) {
		this.creation_time = creation_time;
	}

	public LocalDateTime getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(LocalDateTime update_time) {
		this.update_time = update_time;
	}

	public Long getUser() {
		return user.getId();
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Todos(Long id, String title, String description, String priority, String status, LocalDateTime creation_time,
			LocalDateTime update_time, Users user) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;

		if(priority == null){
			this.priority = "Low";
		}else{
			this.priority = priority;
		}
		if(priority == null){
			this.status = "Pending";
		}else {
			this.status = status;
		}

		this.creation_time = creation_time;
		this.update_time = update_time;
		this.user = user;
	}

	public Todos() {
		this.priority = "Low";
		this.status = "Pending";
	}

	@Override
	public String toString() {
		return "Todos [id=" + id + ", title=" + title + ", description=" + description + ", priority=" + priority
				+ ", status=" + status + ", creation_time=" + creation_time + ", update_time=" + update_time;
	}

}
