package edu.br.infnet.mstaskhistory;


import edu.br.infnet.mstaskhistory.model.ChangeType;
import edu.br.infnet.mstaskhistory.model.TaskHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskHistoryTest {

    private TaskHistory taskHistory;

    @BeforeEach
    void setUp() {
        taskHistory = new TaskHistory();
    }

    @Test
    void testSetAndGetId() {
        Long id = 1L;
        taskHistory.setId(id);
        assertEquals(id, taskHistory.getId());
    }

    @Test
    void testSetAndGetTaskId() {
        Long taskId = 100L;
        taskHistory.setTaskId(taskId);
        assertEquals(taskId, taskHistory.getTaskId());
    }

    @Test
    void testSetAndGetUserId() {
        Long userId = 200L;
        taskHistory.setUserId(userId);
        assertEquals(userId, taskHistory.getUserId());
    }

    @Test
    void testSetAndGetChangeType() {
        ChangeType changeType = ChangeType.CREATE;
        taskHistory.setChangeType(changeType);
        assertEquals(changeType, taskHistory.getChangeType());
    }

    @Test
    void testSetAndGetChangeTimestamp() {
        LocalDateTime timestamp = LocalDateTime.now();
        taskHistory.setChangeTimestamp(timestamp);
        assertEquals(timestamp, taskHistory.getChangeTimestamp());
    }

    @Test
    void testSetAndGetOldValue() {
        String oldValue = "Old Value";
        taskHistory.setOldValue(oldValue);
        assertEquals(oldValue, taskHistory.getOldValue());
    }

    @Test
    void testSetAndGetNewValue() {
        String newValue = "New Value";
        taskHistory.setNewValue(newValue);
        assertEquals(newValue, taskHistory.getNewValue());
    }
}
