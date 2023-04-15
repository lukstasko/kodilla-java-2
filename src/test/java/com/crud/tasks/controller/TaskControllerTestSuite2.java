package com.crud.tasks.controller;

import com.crud.tasks.controller.TaskController;
import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskControllerTestSuite2 {

   @Autowired
    private TaskController taskController;

    @Test
    public void shouldErrorHandlerGetTask() throws TaskNotFoundException {

        try {
            ResponseEntity<TaskDto> result = taskController.getTask(1L);
            fail();

        } catch (TaskNotFoundException e) {

            assertNotEquals(null,e.toString());
        }
    }

}
