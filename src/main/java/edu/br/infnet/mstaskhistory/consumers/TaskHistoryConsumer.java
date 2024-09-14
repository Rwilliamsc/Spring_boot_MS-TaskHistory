package edu.br.infnet.mstaskhistory.consumers;

import edu.br.infnet.mstaskhistory.dto.TaskHistoryDto;
import edu.br.infnet.mstaskhistory.model.ChangeType;
import edu.br.infnet.mstaskhistory.service.TaskHistoryService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class TaskHistoryConsumer {

    @Autowired
    private TaskHistoryService taskHistoryService;

    @RabbitListener(queues = "${broker.queue.history.name}")
    public void listenHistoryQueue(@Payload TaskHistoryDto task) {
       System.out.println(task.toString());

        if (task.changeType() == ChangeType.valueOf("CREATE")) {
            taskHistoryService.logCreateAction(task);
        }

        if (task.changeType() == ChangeType.valueOf("UPDATE")) {
            taskHistoryService.logUpdateAction(task);
        }

        if (task.changeType() == ChangeType.valueOf("DELETE")) {
            taskHistoryService.logDeleteAction(task);
        }


    }
}
