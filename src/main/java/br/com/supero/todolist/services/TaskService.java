package br.com.supero.todolist.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.supero.todolist.exceptions.TaskNotFoundException;
import br.com.supero.todolist.models.Task;
import br.com.supero.todolist.repositories.TasksRepository;

@Service
@Transactional
public class TaskService {

	private TasksRepository repository;
	
	@Autowired
	public TaskService(TasksRepository repository){
		this.repository = repository;
		
	}

	public List<Task> getAll() {
		Iterable<Task> tasks = this.repository.findAllNotRetired();
		return StreamSupport.stream(tasks.spliterator(), false)
				            .collect(Collectors.toList());
	}
	
	public Task create(Task todo){
		return this.repository.save(todo);
	}
	
	public void update(Task todo, Long id){
		Task taskToUpdate = getTodoListByIdAndValidateIfExists(id);
		taskToUpdate.setDescription(todo.getDescription());
		taskToUpdate.setTitle(todo.getTitle());
		taskToUpdate.updateTime();
	}
	
	public void close(Long id){
		Task todoUpdate = getTodoListByIdAndValidateIfExists(id);
		todoUpdate.close();
	}
	
	public void reopen(Long id){
		Task taskToReopen = getTodoListByIdAndValidateIfExists(id);
		taskToReopen.reopen();
	}
	
	public void remove(Long id){
		Task taskToRemove = getTodoListByIdAndValidateIfExists(id);
		taskToRemove.remove();
	}
	
	private Task getTodoListByIdAndValidateIfExists(Long id){
		return this.repository.findById(id).orElseThrow(() -> new TaskNotFoundException());
	}

}
