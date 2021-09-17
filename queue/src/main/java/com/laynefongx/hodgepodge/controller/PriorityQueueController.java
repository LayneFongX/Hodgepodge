package com.laynefongx.hodgepodge.controller;

import com.laynefongx.hodgepodge.service.IPriorityQueueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: banchao.feng@tuya.com
 * @description:
 * @date: 2021/05/14
 */
@RestController
@RequestMapping("/priorityQueue")
public class PriorityQueueController {

    @Resource
    private IPriorityQueueService priorityQueueService;

    @GetMapping("/test")
    public void testQueue() {
        priorityQueueService.testQueue();
    }

}
