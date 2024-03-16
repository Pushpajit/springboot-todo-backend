package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.Todos;
import com.example.demo.Entity.Users;
import com.example.demo.repository.TodoRepository;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

@CrossOrigin
@RestController
@RequestMapping("/v0/api")
public class TodoController {

	@Autowired
	private UserRepository repository;

	@Autowired
	private TodoRepository todoRepository;


	// Return all the users with their todos
	@GetMapping("/users")
	public List<Users> getFunction() {
		var result = this.repository.findAll();
		result.forEach(data -> System.out.println(data));
		return result;
	}


	@CrossOrigin
	@GetMapping("/user")
	public Users getUserByID(@RequestParam Long userId) {
		var res = this.repository.findById(userId);
		if (res.isPresent()) {
			return res.get();
		}
		return new Users();
	}


	// Get a particular user's all todo.
	@GetMapping("todo")
	public ResponseEntity<List<Todos>> getAllTodoList(@RequestParam Long userId) {
		Optional<Users> optionalUser = repository.findById(userId);

		if (optionalUser.isPresent()) {
			List<Todos> allTodos = optionalUser.get().getTodos();
			return new ResponseEntity<List<Todos>>(allTodos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}



	// Create todo for a particular user.
	@CrossOrigin
	@PostMapping("/todo")
	public ResponseEntity<Users> addTodo(@RequestBody Todos todo, @RequestParam Long userId) {
		Optional<Users> optionalUser = repository.findById(userId);

		if (optionalUser.isPresent()) {
			Users user = optionalUser.get();
			// ADD BOTH END.
			todo.setUser(user); // ADD user into todo.
			user.setTodos(List.of(todo)); // Add todo into user.

			try {
				Todos savedTodo = todoRepository.save(todo);
				return new ResponseEntity<Users>(user, HttpStatus.CREATED);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}


	// Delete a particular user's todo.
	@CrossOrigin
	@DeleteMapping("/todo")
	public ResponseEntity<Users> deleteTodo(@RequestParam Long userId, @RequestParam Long todoId) {
		var resUser = repository.findById(userId);
		var resTodo = todoRepository.findById(todoId);

		if (resUser.isPresent() && resTodo.isPresent()) {

			try {
				Users user = resUser.get();
				Todos todo = resTodo.get();
				// CYCLIC DELETION: Need to delete from both the end to make it work.
				user.getTodos().remove(todo); // Delete from user todo arrayList.
				todoRepository.delete(todo); // Delete from the todos database.

				return new ResponseEntity<Users>(user, HttpStatus.OK);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	// To modify a particular todo of an user
	@CrossOrigin
	@PutMapping("/todo")
	@Transactional
	public ResponseEntity<Todos> modifyTodo(@RequestParam Long userId, @RequestParam Long todoId,
			@RequestBody Todos updateTodo) {

		var resUser = repository.findById(userId);
		var resTodo = todoRepository.findById(todoId);


		if (resUser.isPresent() && resTodo.isPresent()) {
			try {
				Todos todo = resTodo.get();
				// If the title or description is missing then set default.
				if (updateTodo.getTitle() == null) {
					updateTodo.setTitle(todo.getTitle());
				}

				if (updateTodo.getDescription() == null) {
					updateTodo.setDescription(todo.getDescription());
				}

				if(updateTodo.getPriority() == null){
					updateTodo.setPriority(todo.getPriority());
				}

				if(updateTodo.getStatus() == null){
					updateTodo.setStatus(todo.getStatus());
				}

				System.out.println(updateTodo);

				// Customized JPQL function to update data transactionally.
				this.todoRepository.updateTodos(todoId, updateTodo.getTitle(), updateTodo.getDescription(), updateTodo.getPriority(), updateTodo.getStatus());
				return new ResponseEntity<Todos>(todo, HttpStatus.OK);
			
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

}
