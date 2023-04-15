package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DBService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskControllerTestSuite {

    @InjectMocks
    TaskController taskController;
    @Mock
    DBService service;
    @Mock
    TaskMapper taskMapper;

    @Test
    public void testGetTasks() {
        //Given
        List<Task> taskList=new ArrayList<>();
        taskList.add(new Task(1L,"Task 1", "Sample task"));
        taskList.add(new Task(2L,"Task 2", "Sample task"));
        List<TaskDto> taskDtoList=new ArrayList<>();
        taskDtoList.add(new TaskDto(1L,"Task 1", "Sample task"));
        taskDtoList.add(new TaskDto(2L,"Task 2", "Sample task"));
        when(service.getAllTasks()).thenReturn(taskList);
        when(taskMapper.maptoTaskDtoList(taskList)).thenReturn(taskDtoList);
        //When
        ResponseEntity<List<TaskDto>> result=taskController.getTasks();
        //Then
         assertEquals(2,result.getBody().size());
         assertEquals("Task 1",result.getBody().get(0).getTitle());
    }

    @Test
    public void testGetTask() throws TaskNotFoundException {
        //Given
        Task task=new Task(1L,"Task 1", "Sample task");
        TaskDto taskDto=new TaskDto(1L,"Task 1", "Sample task");
        when(service.getTask(1L)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        //When
        ResponseEntity<TaskDto> result=taskController.getTask(1L);
        //Then
        assertEquals("Sample task",result.getBody().getContent());
    }

    @Test
    public void testDeleteTask() throws TaskNotFoundException {
        //Given
        Task task=new Task(1L,"Task 1", "Sample task");
        doNothing().when(service).deleteTask(1L);
        when(service.getTask(1L)).thenReturn(task);
        //When
        ResponseEntity result=taskController.deleteTask(1L);
        //Then
        assertEquals("200 OK",result.getStatusCode().toString());
    }
}