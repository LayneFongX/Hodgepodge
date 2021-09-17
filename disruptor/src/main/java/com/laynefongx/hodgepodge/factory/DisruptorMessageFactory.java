package com.laynefongx.hodgepodge.factory;

import com.laynefongx.hodgepodge.domain.DisruptorMessage;
import com.lmax.disruptor.EventFactory;

/**
 * 使用Disruptor创建事件，同时声明一个EventFactory来实例化Event对象
 */
public class DisruptorMessageFactory implements EventFactory<DisruptorMessage> {

    @Override
    public DisruptorMessage newInstance() {
        return new DisruptorMessage();
    }
}
