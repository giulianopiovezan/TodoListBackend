package br.com.supero.todolist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.supero.todolist.models.Task;
import br.com.supero.todolist.services.TaskService;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {
	
	private TaskService service;
	private static final String APPLICATION_JSON = "application/json";

	@Autowired
	public TaskController(TaskService service){
		this.service = service;
	}

	@GetMapping
	public List<Task> getTasks(){
		return this.service.getAll();
	}
	
	@PostMapping(consumes = APPLICATION_JSON)
	public ResponseEntity<Task> create(@RequestBody Task todo){
		return new ResponseEntity<Task>(this.service.create(todo), HttpStatus.CREATED);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping(path = "/{id}", consumes = APPLICATION_JSON)
	public ResponseEntity update(@RequestBody Task todo, @PathVariable("id") Long id){
		this.service.update(todo, id);
		return ResponseEntity.ok().build();
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping(path = "/{id}")
	public ResponseEntity remove(@PathVariable("id") Long id){
		this.service.remove(id);
		return ResponseEntity.noContent().build();
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(path = "/{id}/close")
	public ResponseEntity close(@PathVariable("id") Long id){
		this.service.close(id);
		return ResponseEntity.ok().build();
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(path = "/{id}/reopen")
	public ResponseEntity reopen(@PathVariable("id") Long id){
		this.service.reopen(id);
		return ResponseEntity.ok().build();
	}
}
