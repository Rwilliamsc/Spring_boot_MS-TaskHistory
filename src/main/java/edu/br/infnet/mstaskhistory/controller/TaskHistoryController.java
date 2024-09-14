package edu.br.infnet.mstaskhistory.controller;


import edu.br.infnet.mstaskhistory.model.TaskHistory;
import edu.br.infnet.mstaskhistory.service.TaskHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskHistoryController {

    @Autowired
    private TaskHistoryService taskHistoryService;


    @GetMapping("/history")
    public ResponseEntity<List<TaskHistory>> getAllTaskHistory() {
        List<TaskHistory> taskHistory = taskHistoryService.getAllTaskHistory();
        return ResponseEntity.ok(taskHistory);
    }

    @GetMapping("/history/user/{id}")
    public ResponseEntity<List<TaskHistory>> getAllTaskHistory(@PathVariable Long id) {
        List<TaskHistory> taskHistory = taskHistoryService.getAllTaskHistoryByUserId(id);
        return ResponseEntity.ok(taskHistory);
    }
}
