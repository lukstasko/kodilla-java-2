package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;


@SpringJUnitWebConfig
@WebMvcTest(TaskControllerTest.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskController taskController;

    @Test
    void shouldGetTasks() throws Exception {
        //Given
        List<TaskDto> taskList=List.of(new TaskDto(1L,"Test","Sample content"));
        when(taskController.getTasks()).thenReturn(ResponseEntity.of(Optional.of(taskList)));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title",Matchers.is("Test")));
    }

    @Test
    void shouldGetTaskById() throws Exception{
        //Given
        TaskDto taskDto=new TaskDto(1L,"Test","Sample content");
        when(taskController.getTask(1L)).thenReturn(ResponseEntity.of(Optional.of(taskDto)));
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks/{taskId}","1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title",Matchers.is("Test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content",Matchers.is("Sample content")));
    }

    @Test
    void shouldDeleteTask() throws Exception {
        //Given
        when(taskController.deleteTask(1L)).thenReturn(ResponseEntity.ok().build());
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete ("/v1/tasks/{taskId}","1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldUpdateTask() throws Exception{
        //Given
        TaskDto taskDto=new TaskDto(1L,"Test","Sample content");
        when(taskController.updateTask(taskDto)).thenReturn(ResponseEntity.ok(taskDto));
        Gson gson=new Gson();
        String jsonContent=gson.toJson(taskDto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UCF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldCreateTask() throws Exception{
        //Given
        TaskDto taskDto=new TaskDto(1L,"Test","Sample content");
        when(taskController.createTask(taskDto)).thenReturn(ResponseEntity.ok().build());
        Gson gson=new Gson();
        String jsonContent=gson.toJson(taskDto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UCF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}