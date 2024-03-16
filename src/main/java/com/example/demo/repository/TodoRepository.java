package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Todos;

@Repository
public interface TodoRepository extends JpaRepository<Todos, Long> {

    @Modifying
    @Query("UPDATE Todos T SET T.title = :title, T.description = :desc, T.priority = :priority, T.status = :status WHERE T.id = :id")
    public void updateTodos(@Param("id") Long id, @Param("title") String title, @Param("desc") String desc, @Param("priority") String priority, @Param("status") String status);
	
}
