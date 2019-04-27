package br.com.supero.todolist.exceptions;

public class TaskNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = -7198177809292196719L;

	public TaskNotFoundException(){
		super("{todolist.update.tasknotfound}");
	}

}
