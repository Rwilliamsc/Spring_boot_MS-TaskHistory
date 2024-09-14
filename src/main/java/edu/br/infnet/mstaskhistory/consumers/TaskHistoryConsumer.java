package edu.br.infnet.mstaskhistory.consumers;

import edu.br.infnet.mstaskhistory.dto.TaskHistoryDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class TaskHistoryConsumer {


    @RabbitListener(queues = "${broker.queue.history.name}")
    public void listenHistoryQueue(@Payload TaskHistoryDto task) {
        System.out.println(task.userId());
    }
}
