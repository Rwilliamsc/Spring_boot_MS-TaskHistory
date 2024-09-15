package edu.br.infnet.mstaskhistory;


import edu.br.infnet.mstaskhistory.controller.TaskHistoryController;
import edu.br.infnet.mstaskhistory.model.TaskHistory;
import edu.br.infnet.mstaskhistory.service.TaskHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class TaskHistoryControllerTest {

    @Mock
    private TaskHistoryService taskHistoryService;

    @InjectMocks
    private TaskHistoryController taskHistoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTaskHistory_shouldReturnOkWithTaskHistoryList() {
        List<TaskHistory> taskHistoryList = new ArrayList<>();
        taskHistoryList.add(new TaskHistory());

        when(taskHistoryService.getAllTaskHistory()).thenReturn(taskHistoryList);

        ResponseEntity<List<TaskHistory>> response = taskHistoryController.getAllTaskHistory();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(taskHistoryList, response.getBody());
    }

    @Test
    void getAllTaskHistoryByUserId_shouldReturnOkWithTaskHistoryList() {
        Long userId = 1L;
        List<TaskHistory> taskHistoryList = new ArrayList<>();
        taskHistoryList.add(new TaskHistory());

        when(taskHistoryService.getAllTaskHistoryByUserId(userId)).thenReturn(taskHistoryList);

        ResponseEntity<List<TaskHistory>> response = taskHistoryController.getAllTaskHistory(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(taskHistoryList, response.getBody());
    }
}
