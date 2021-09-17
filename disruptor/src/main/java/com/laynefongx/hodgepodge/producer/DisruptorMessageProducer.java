package com.laynefongx.hodgepodge.producer;

import com.laynefongx.hodgepodge.constant.Constant;
import com.laynefongx.hodgepodge.consumer.DisruptorEventConsumerHandler;
import com.laynefongx.hodgepodge.consumer.DisruptorWorkConsumerHandler;
import com.laynefongx.hodgepodge.domain.DisruptorMessage;
import com.laynefongx.hodgepodge.factory.DisruptorMessageFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class DisruptorMessageProducer {

    private RingBuffer<DisruptorMessage> ringBuffer;

    private ExecutorService executor;

    private Disruptor<DisruptorMessage> disruptor;

    public DisruptorMessageProducer(int producerCount, int consumerCount) {
        initDisruptor(producerCount, consumerCount, false);
    }

    public DisruptorMessageProducer(int producerCount, int consumerCount, boolean isRepeatConsume) {
        initDisruptor(producerCount, consumerCount, isRepeatConsume);
    }

    public void initDisruptor(int producerCount, int consumerCount, boolean isRepeatConsume) {
        // 1.创建一个可缓存的线程 提供线程来触发Consumer的事件处理
        executor = Executors.newCachedThreadPool();

        // 2.构建工厂对象
        DisruptorMessageFactory eventFactory = new DisruptorMessageFactory();

        // 3.创建ringBuffer大小 大小一定是2的N次方  &运算 参考HashMap
        // 4.创建Disruptor consumerCount为1时 对应1个消费者
        ProducerType producerType = consumerCount == 1 ? ProducerType.SINGLE : ProducerType.MULTI;
        disruptor = new Disruptor<DisruptorMessage>(eventFactory, Constant.RING_BUFFER_SIZE, executor,
                producerType, new YieldingWaitStrategy());
        if (isRepeatConsume) {
            // isRepeatConsumer为true时，多个消费者重复消费
            DisruptorEventConsumerHandler[] disruptorEventHandlers = new DisruptorEventConsumerHandler[consumerCount];
            for (int i = 0; i < consumerCount; i++) {
                disruptorEventHandlers[i] = new DisruptorEventConsumerHandler(i + 1);
            }
            // 5.连接消费端方法 注册消费者
            disruptor.handleEventsWith(disruptorEventHandlers);
        } else {
            // isRepeatConsumer为false时，多个消费者竞争消费
            DisruptorWorkConsumerHandler[] disruptorWorkHandlers = new DisruptorWorkConsumerHandler[consumerCount];
            for (int i = 0; i < consumerCount; i++) {
                disruptorWorkHandlers[i] = new DisruptorWorkConsumerHandler(i + 1);
            }
            disruptor.handleEventsWithWorkerPool(disruptorWorkHandlers);
        }
        // 6.启动
        disruptor.start();

        // 7.创建RingBuffer容器
        this.ringBuffer = disruptor.getRingBuffer();
    }

    public void sendMessage(String message) {
        // 1.ringBuffer 事件队列 下一个槽
        long sequence = ringBuffer.next();
        try {
            // 2.取出空的事件队列
            DisruptorMessage msg = ringBuffer.get(sequence);
            // 3.获取事件队列传递的数据
            msg.setMessage(message);
            Thread.sleep(Constant.THREAD_SLEEP_10);
            ringBuffer.publish(sequence);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeDisruptor() {
        // 10.关闭disruptor和executor
        disruptor.shutdown();
        executor.shutdown();
    }
}
