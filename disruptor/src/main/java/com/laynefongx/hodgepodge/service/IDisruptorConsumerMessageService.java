package com.laynefongx.hodgepodge.service;

public interface IDisruptorConsumerMessageService {
    void singleConsumeMessage(int producerCount, int consumerCount, int msgCount);

    void multiRepeatConsumeMessage(int producerCount, int consumerCount, int msgCount);

    void multiCompeteConsumeMessage(int producerCount, int consumerCount, int msgCount);
}
