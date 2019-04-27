package br.com.supero.todolist.model.converters;

import java.util.Arrays;
import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.supero.todolist.models.TaskStatus;

@Converter
public class TaskStatusConverter implements AttributeConverter<TaskStatus, Integer>{

	public Integer convertToDatabaseColumn(TaskStatus attribute) {
		return attribute.getCode();
	}

	public TaskStatus convertToEntityAttribute(Integer dbCode) {
		final Optional<TaskStatus> value = Arrays.asList(TaskStatus.values())
				                                     .stream().filter(todoStatus -> todoStatus.getCode().equals(dbCode)).findAny();
		
		if(value.isPresent()){
			return value.get();
		}
		
		return null;
	}

}
