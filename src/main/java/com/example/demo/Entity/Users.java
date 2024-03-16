package com.example.demo.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String username;
	
	private String password;

	@CreationTimestamp
	private LocalDateTime creation_time;

	@UpdateTimestamp
	private LocalDateTime update_time;

	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Todos> todos = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public List<Todos> getTodos() {
		return todos;
	}

	public void setTodos(List<Todos> todos) {
		this.todos.addAll(todos);
	}

	public Users(Long id, String username, String password, LocalDateTime creation_time, LocalDateTime update_time) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.creation_time = creation_time;
		this.update_time = update_time;
	}

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", creation_time="
				+ creation_time + ", update_time=" + update_time + ", todos=" + todos + "]";
	}

	

}
