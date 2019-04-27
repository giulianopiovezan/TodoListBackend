package br.com.supero.todolist.models;

public enum TaskStatus {

	OPEN(1),
	CLOSED(2),
	RETIRED(3);
	
	private Integer code;

	TaskStatus(Integer code){
		this.code = code;
		
	}
	
	public Integer getCode(){
		return this.code;
	}
}
