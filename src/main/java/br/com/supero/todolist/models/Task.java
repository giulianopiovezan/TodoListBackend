package br.com.supero.todolist.models;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.supero.todolist.model.converters.TaskStatusConverter;

@Entity
@Table(name = "task")
public class Task implements Serializable{

	private static final long serialVersionUID = -3030890676034124731L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "{task.title.notnull}")
	@NotEmpty(message = "{task.title.empty}")
	@Size(min = 3, max = 100)
	@Column(nullable = false)
	private String title;
	
	@NotNull(message = "{task.description.notnull}")
	@NotEmpty(message = "{task.description.empty}")
	@Size(max = 300)
	@Column(nullable = false)
	private String description;
	
	@NotNull
	@Convert(converter = TaskStatusConverter.class)
	@Column(nullable = false)
	private TaskStatus status;
	
	@Column(name = "create_time", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createTime;
	
	@Column(name = "update_time", insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar updateTime;
	
	@Column(name = "remove_time", insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar removeTime;
	
	@Column(name = "closed_time", insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar closedTime;
	
	public Task(){
		this.status = TaskStatus.OPEN;
		this.createTime = Calendar.getInstance();
	}
	
	public void close(){
		this.status = TaskStatus.CLOSED;
		this.closedTime = Calendar.getInstance();
		this.updateTime();
	}
	
	public void remove(){
		this.status = TaskStatus.RETIRED;
		this.removeTime = Calendar.getInstance();
		this.updateTime();
	}
	
	public void reopen(){
		this.status = TaskStatus.OPEN;
		this.closedTime = null;
		this.updateTime();
	}
	
	public boolean isRetired(){
		return TaskStatus.RETIRED.equals(this.status);
	}
	
	public void updateTime(){
		this.updateTime = Calendar.getInstance();
	}

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

	public TaskStatus getStatus() {
		return status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getCreateTime() {
		return createTime;
	}

	public Calendar getUpdateTime() {
		return updateTime;
	}

	public Calendar getRemoveTime() {
		return removeTime;
	}

	public Calendar getClosedTime() {
		return closedTime;
	}
}
