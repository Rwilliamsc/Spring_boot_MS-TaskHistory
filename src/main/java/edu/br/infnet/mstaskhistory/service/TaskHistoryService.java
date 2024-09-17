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

        try{
            taskHistoryRepository.save(history);
            logger.info("TaskHistoryService:logUpdateAction: {}", Mapper.mapToJsonString(history));
        }
        catch(Exception e){
            logger.error("TaskHistoryService:logUpdateAction: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

    }

    public void logDeleteAction(TaskHistoryDto taskHistoryDto) {
        TaskHistory history = new TaskHistory();
        history.setTaskId(taskHistoryDto.taskId());
        history.setUserId(taskHistoryDto.userId());
        history.setChangeType(ChangeType.valueOf("DELETE"));
        history.setChangeTimestamp(LocalDateTime.now());
        history.setOldValue(taskHistoryDto.oldValue());
        try{
            taskHistoryRepository.save(history);
            logger.info("TaskHistoryService:logDeleteAction: {}", Mapper.mapToJsonString(history));
        }
        catch(Exception e){
            logger.error("TaskHistoryService:logDeleteAction: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<TaskHistory> getAllTaskHistory() {
        try {
            List<TaskHistory> taskHistories = taskHistoryRepository.findAll();
            return taskHistories;
        }
        catch (Exception e) {
            logger.error("TaskHistoryService:getAllTaskHistory: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<TaskHistory> getAllTaskHistoryByUserId(Long userId) {
        try{
            List<TaskHistory> taskHistories = taskHistoryRepository.findByUserId(userId);
            logger.info("TaskHistoryService:getAllTaskHistoryByUserId: {}", taskHistories.toString());
            return taskHistories;
        }
        catch (Exception e){
            logger.error("TaskHistoryService:getAllTaskHistoryByUserId: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
