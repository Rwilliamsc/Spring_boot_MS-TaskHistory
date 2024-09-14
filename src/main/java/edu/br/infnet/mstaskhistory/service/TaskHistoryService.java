package edu.br.infnet.mstaskhistory.service;

import edu.br.infnet.mstaskhistory.model.ChangeType;
import edu.br.infnet.mstaskhistory.model.TaskHistory;
import edu.br.infnet.mstaskhistory.repository.TaskHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskHistoryService {

    @Autowired
    private TaskHistoryRepository taskHistoryRepository;

    public void logCreateAction(Long taskId, String changedBy, String newValue) {
        TaskHistory history = new TaskHistory();
        history.setTaskId(taskId);
        history.setChangedBy(changedBy);
        history.setChangeType(ChangeType.valueOf("CREATE"));
        history.setChangeTimestamp(LocalDateTime.now());
        history.setNewValue(newValue);

        taskHistoryRepository.save(history);
    }

    public void logUpdateAction(Long taskId, String changedBy, String oldValue, String newValue) {
        TaskHistory history = new TaskHistory();
        history.setTaskId(taskId);
        history.setChangedBy(changedBy);
        history.setChangeType(ChangeType.valueOf("UPDATE"));
        history.setChangeTimestamp(LocalDateTime.now());
        history.setOldValue(oldValue);
        history.setNewValue(newValue);

        taskHistoryRepository.save(history);
    }

    public void logDeleteAction(Long taskId, String changedBy, String oldValue) {
        TaskHistory history = new TaskHistory();
        history.setTaskId(taskId);
        history.setChangedBy(changedBy);
        history.setChangeType(ChangeType.valueOf("DELETE"));
        history.setChangeTimestamp(LocalDateTime.now());
        history.setOldValue(oldValue);

        taskHistoryRepository.save(history);
    }

    public List<TaskHistory> getAllTaskHistory() {
        return taskHistoryRepository.findAll();
    }

    public List<TaskHistory> getAllTaskHistoryByUserId(Long userId) {
        return taskHistoryRepository.findByUserId(userId);
    }
}
