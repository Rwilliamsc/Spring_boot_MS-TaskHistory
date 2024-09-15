package edu.br.infnet.mstaskhistory;


import edu.br.infnet.mstaskhistory.dto.TaskHistoryDto;
import edu.br.infnet.mstaskhistory.model.ChangeType;
import edu.br.infnet.mstaskhistory.model.TaskHistory;
import edu.br.infnet.mstaskhistory.repository.TaskHistoryRepository;
import edu.br.infnet.mstaskhistory.service.TaskHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TaskHistoryServiceTest {

    @Mock
    private TaskHistoryRepository taskHistoryRepository;

    @InjectMocks
    private TaskHistoryService taskHistoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void logCreateAction_shouldSaveTaskHistory() {
        TaskHistoryDto taskHistoryDto = new TaskHistoryDto(
                1L, 2L, ChangeType.CREATE, LocalDateTime.now(), null, "New Task"
        );

        taskHistoryService.logCreateAction(taskHistoryDto);

        verify(taskHistoryRepository, Mockito.times(1)).save(any(TaskHistory.class));
    }

    @Test
    void logUpdateAction_shouldSaveTaskHistory() {
        TaskHistoryDto taskHistoryDto = new TaskHistoryDto(
                1L, 2L, ChangeType.UPDATE, LocalDateTime.now(), "Old Task", "Updated Task"
        );

        taskHistoryService.logUpdateAction(taskHistoryDto);

        verify(taskHistoryRepository, Mockito.times(1)).save(any(TaskHistory.class));
    }

    @Test
    void logDeleteAction_shouldSaveTaskHistory() {
        TaskHistoryDto taskHistoryDto = new TaskHistoryDto(
                1L, 2L, ChangeType.DELETE, LocalDateTime.now(), "Old Task", null
        );

        taskHistoryService.logDeleteAction(taskHistoryDto);

        verify(taskHistoryRepository, Mockito.times(1)).save(any(TaskHistory.class));
    }

    @Test
    void getAllTaskHistory_shouldReturnListOfTaskHistory() {
        List<TaskHistory> taskHistoryList = new ArrayList<>();
        taskHistoryList.add(new TaskHistory());

        when(taskHistoryRepository.findAll()).thenReturn(taskHistoryList);

        List<TaskHistory> result = taskHistoryService.getAllTaskHistory();

        assertEquals(taskHistoryList, result);
    }

    @Test
    void getAllTaskHistoryByUserId_shouldReturnListOfTaskHistory() {
        Long userId = 1L;
        List<TaskHistory> taskHistoryList = new ArrayList<>();
        taskHistoryList.add(new TaskHistory());

        when(taskHistoryRepository.findByUserId(userId)).thenReturn(taskHistoryList);

        List<TaskHistory> result = taskHistoryService.getAllTaskHistoryByUserId(userId);

        assertEquals(taskHistoryList, result);
    }
}

