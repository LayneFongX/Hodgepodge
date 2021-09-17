package com.laynefongx.hodgepodge.service.impl;


import com.laynefongx.hodgepodge.producer.DisruptorMessageProducer;
import com.laynefongx.hodgepodge.service.IDisruptorConsumerMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DisruptorConsumerMessageServiceImpl implements IDisruptorConsumerMessageService {

    /**
     * 单个消费者消费数据
     */
    @Override
    public void singleConsumeMessage(int producerCount, int consumerCount, int msgCount) {
        // 8.创建生产者
        DisruptorMessageProducer producer = new DisruptorMessageProducer(producerCount, consumerCount);
        for (int i = 1; i <= msgCount; i++) {
            producer.sendMessage("producer生产的第" + i + "条消息");
        }
        producer.closeDisruptor();

    }

    /**
     * 多个消费者重复消费数据
     */
    @Override
    public void multiRepeatConsumeMessage(int producerCount, int consumerCount, int msgCount) {
        // 8.创建生产者
        DisruptorMessageProducer producer = new DisruptorMessageProducer(producerCount, consumerCount, true);
        for (int i = 1; i <= msgCount; i++) {
            producer.sendMessage("producer生产的第" + i + "条消息");
        }
        producer.closeDisruptor();
    }

    /**
     * 多个消费者竞争消费数据
     */
    @Override
    public void multiCompeteConsumeMessage(int producerCount, int consumerCount, int msgCount) {
        // 8.创建生产者
        DisruptorMessageProducer producer = new DisruptorMessageProducer(producerCount, consumerCount, false);
        for (int i = 1; i <= msgCount; i++) {
            producer.sendMessage("producer生产的第" + i + "条消息");
        }
        producer.closeDisruptor();
    }
}
