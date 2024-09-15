package edu.br.infnet.mstaskhistory;

import edu.br.infnet.mstaskhistory.consumers.TaskHistoryConsumer;
import edu.br.infnet.mstaskhistory.dto.TaskHistoryDto;
import edu.br.infnet.mstaskhistory.model.ChangeType;
import edu.br.infnet.mstaskhistory.service.TaskHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.handler.annotation.Payload;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class TaskHistoryConsumerTest {

    @Mock
    private TaskHistoryService taskHistoryService;

    @InjectMocks
    private TaskHistoryConsumer taskHistoryConsumer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listenHistoryQueue_whenCreate_shouldCallLogCreateAction() {
        TaskHistoryDto taskHistoryDto = Mockito.mock(TaskHistoryDto.class);
        Mockito.when(taskHistoryDto.changeType()).thenReturn(ChangeType.CREATE);

        taskHistoryConsumer.listenHistoryQueue(taskHistoryDto);

        verify(taskHistoryService, times(1)).logCreateAction(taskHistoryDto);
    }

    @Test
    void listenHistoryQueue_whenUpdate_shouldCallLogUpdateAction() {
        TaskHistoryDto taskHistoryDto = Mockito.mock(TaskHistoryDto.class);
        Mockito.when(taskHistoryDto.changeType()).thenReturn(ChangeType.UPDATE);

        taskHistoryConsumer.listenHistoryQueue(taskHistoryDto);

        verify(taskHistoryService, times(1)).logUpdateAction(taskHistoryDto);
    }

    @Test
    void listenHistoryQueue_whenDelete_shouldCallLogDeleteAction() {
        TaskHistoryDto taskHistoryDto = Mockito.mock(TaskHistoryDto.class);
        Mockito.when(taskHistoryDto.changeType()).thenReturn(ChangeType.DELETE);

        taskHistoryConsumer.listenHistoryQueue(taskHistoryDto);

        verify(taskHistoryService, times(1)).logDeleteAction(taskHistoryDto);
    }
}
