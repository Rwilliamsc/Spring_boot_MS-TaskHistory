package edu.br.infnet.mstaskhistory.service;

import edu.br.infnet.mstaskhistory.dto.TaskHistoryDto;
import edu.br.infnet.mstaskhistory.model.ChangeType;
import edu.br.infnet.mstaskhistory.model.TaskHistory;
import edu.br.infnet.mstaskhistory.repository.TaskHistoryRepository;
import edu.br.infnet.mstaskhistory.util.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskHistoryService {

    @Autowired
    private TaskHistoryRepository taskHistoryRepository;

    Logger logger= LogManager.getLogger(TaskHistoryService.class);

    public void logCreateAction(TaskHistoryDto taskHistoryDto) {
        try{
            TaskHistory history = new TaskHistory();
            history.setTaskId(taskHistoryDto.taskId());
            history.setUserId(taskHistoryDto.userId());
            history.setChangeType(ChangeType.valueOf("CREATE"));
            history.setChangeTimestamp(LocalDateTime.now());
            history.setNewValue(taskHistoryDto.newValue());

            taskHistoryRepository.save(history);
            logger.info("TaskHistoryService:logCreateAction: {}", Mapper.mapToJsonString(history));
        }
        catch (Exception e){
           logger.error("TaskHistoryService:logCreateAction: {}", e.getMessage());
           throw new RuntimeException(e.getMessage());
        }

    }

    public void logUpdateAction(TaskHistoryDto taskHistoryDto) {
        TaskHistory history = new TaskHistory();
        history.setTaskId(taskHistoryDto.taskId());
        history.setUserId(taskHistoryDto.userId());
        history.setChangeType(ChangeType.valueOf("UPDATE"));
        history.setChangeTimestamp(LocalDateTime.now());
        history.setOldValue(taskHistoryDto.oldValue());
        history.setNewValue(taskHistoryDto.newValue());

        taskHistoryRepository.save(history);
    }

    public void logDeleteAction(TaskHistoryDto taskHistoryDto) {
        TaskHistory history = new TaskHistory();
        history.setTaskId(taskHistoryDto.taskId());
        history.setUserId(taskHistoryDto.userId());
        history.setChangeType(ChangeType.valueOf("DELETE"));
        history.setChangeTimestamp(LocalDateTime.now());
        history.setOldValue(taskHistoryDto.oldValue());

        taskHistoryRepository.save(history);
    }

    public List<TaskHistory> getAllTaskHistory() {
        return taskHistoryRepository.findAll();
    }

    public List<TaskHistory> getAllTaskHistoryByUserId(Long userId) {
        return taskHistoryRepository.findByUserId(userId);
    }
}
