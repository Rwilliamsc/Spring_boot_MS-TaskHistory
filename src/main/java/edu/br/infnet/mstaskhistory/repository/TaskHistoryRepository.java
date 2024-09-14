package edu.br.infnet.mstaskhistory.repository;


import edu.br.infnet.mstaskhistory.model.TaskHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskHistoryRepository extends JpaRepository<TaskHistory, Long> {
    List<TaskHistory> findByTaskId(Long taskId);


    List<TaskHistory> findByUserId(Long userId);


    TaskHistory findByIdAndUserId(Long id, Long userId);
}
