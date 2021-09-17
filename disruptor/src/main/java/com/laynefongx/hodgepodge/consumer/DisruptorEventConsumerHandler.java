package com.laynefongx.hodgepodge.consumer;

import com.laynefongx.hodgepodge.domain.DisruptorMessage;
import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DisruptorEventConsumerHandler implements EventHandler<DisruptorMessage> {

    private int consumerId;

    /**
     * @param consumerId ： 消费者ID
     */
    public DisruptorEventConsumerHandler(int consumerId) {
        this.consumerId = consumerId;
    }

    @Override
    public void onEvent(DisruptorMessage disruptorMessage, long sequence, boolean b) throws Exception {
        log.info("consumer-{}消费的消息内容为:{}", consumerId, disruptorMessage.getMessage());
    }
}
