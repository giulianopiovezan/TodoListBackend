package br.com.supero.todolist.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.supero.todolist.models.Task;

@Repository
public interface TasksRepository extends CrudRepository<Task, Long>{
	
	@Query("SELECT t FROM Task t WHERE t.status != br.com.supero.todolist.models.TaskStatus.RETIRED")
	public Iterable<Task> findAllNotRetired();

}
