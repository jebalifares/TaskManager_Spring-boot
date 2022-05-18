package com.example.controller;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dto.CountType;
import com.example.model.Task;
import com.example.services.TaskService;



@RestController
@CrossOrigin(origins = "http://localhost:4200") 
@RequestMapping("/api")



public class TaskController {
	
	private TaskService taskservice;
	
	public TaskController(TaskService taskservice) {
		super();
		this.taskservice = taskservice;
	}
	//get all task

	@GetMapping("/tasks")
	public List<Task> gettask(){
		return taskservice.getalltask();
		
	}
	

	@GetMapping("/task/vData/percentcounttype")
	public List<CountType> getPercentageGroupByType(){
		return taskservice.getPercentageGroupByType();
	}
	//create a task
	
	@PostMapping("/task")
	public Task addtask (@RequestBody Task task){
		
		return taskservice.createtask(task);
		
		
	}

	@GetMapping("/task/{id}")
	public Task getById(@PathVariable Long id) {
		return taskservice.getById(id).orElseThrow(()->new EntityNotFoundException("Requested Task not found"));
	}
	@PutMapping("/task/{id}")
	public ResponseEntity<?> addTask(@RequestBody Task taskPara, @PathVariable Long id)
	{
		if(taskservice.exist(id)) {
			Task task=taskservice.getById(id).orElseThrow(()->new EntityNotFoundException("Requested Task not found"));
			task.setTitle(taskPara.getTitle());
			task.setDueDate(taskPara.getDueDate());
			task.setType(taskPara.getType());
			task.setDescription(taskPara.getDescription());
			taskservice.createtask(task);
			return ResponseEntity.ok().body(task);
		}else {
			HashMap<String, String>message= new HashMap<>();
			message.put("message", id + " task not found or matched");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}
		
	}
	@DeleteMapping("/task/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable Long id) {
		if(taskservice.exist(id)) {
			taskservice.delete(id);
			HashMap<String, String>message= new HashMap<>();
			message.put("message", id + " task removed");
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}else {
			HashMap<String, String>message= new HashMap<>();
			message.put("message", id + " task not found or matched");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}
	}

}
