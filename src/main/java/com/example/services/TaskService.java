package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.dto.CountType;
import com.example.model.Task;
import com.example.repository.TaskRespository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class TaskService {
	private TaskRespository taskRespository;
	 
	public TaskService(TaskRespository taskRespository) {
		super();
		this.taskRespository = taskRespository;
	}
	public List<CountType> getPercentageGroupByType() {
		return taskRespository.getPercentageGroupByType();
		
	}

	public List<Task> getalltask(){
		return taskRespository.getAllTaskDueDateDesc();
		
	}
	
	public Task createtask(Task task) {
		return taskRespository.saveAndFlush(task);
	}
	
	public boolean exist(Long id) {
		return taskRespository.existsById(id);
	}
	 public Optional<Task> getById(long id) {
		 return taskRespository.findById(id);
	 }

	public void delete(Long id) {
		
		taskRespository.deleteById(id);
	}

}
