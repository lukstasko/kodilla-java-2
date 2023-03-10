package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DBService {

    private final TaskRepository repository;

    public List<Task> getAllTasks(){
        return repository.findAll();
    }

    public Task save(final Task task){
       return repository.save(task);
    }

    public Task getTask(final Long id)throws TaskNotFoundException {
        return repository.findById(id).orElseThrow(TaskNotFoundException::new);
    }
    public void deleteTask(final Long id) {
         repository.deleteById(id);
    }

}