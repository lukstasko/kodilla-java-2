package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskMapperTestSuite {

    @Autowired
    TaskMapper taskMapper;

    @Test
    void testMapToTask() {
        //Given
        TaskDto taskDto=new TaskDto(2L,"TaskDto","Sample taskDto");
        //When
        Task resultTask=taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(2L,resultTask.getId());
        assertEquals("TaskDto",resultTask.getTitle());
        assertEquals("Sample taskDto",resultTask.getContent());
    }

    @Test
    void testMapToTaskDto() {
        //Given
        Task task=new Task(1L,"Task","Sample task");
        //When
        TaskDto resultTask=taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(1L,resultTask.getId());
        assertEquals("Task",resultTask.getTitle());
        assertEquals("Sample task",resultTask.getContent());
    }

    @Test
    void testMapToTaskDtoList() {
        //Given
        List<Task> taskLists =
                List.of(new Task(1L,"Task","Sample task"));
        //When
        List<TaskDto> resultList=taskMapper.maptoTaskDtoList(taskLists);
        //Then
        assertEquals(1,resultList.size());
        assertEquals("Sample task",resultList.get(0).getContent());
    }
}