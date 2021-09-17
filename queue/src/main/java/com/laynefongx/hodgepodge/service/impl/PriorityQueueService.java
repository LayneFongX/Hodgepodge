package com.laynefongx.hodgepodge.service.impl;

import com.laynefongx.hodgepodge.domain.SocialUser;
import com.laynefongx.hodgepodge.service.IPriorityQueueService;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.function.Predicate;

@Service
public class PriorityQueueService implements IPriorityQueueService {

    @Override
    public void testQueue() {
        PriorityQueue<SocialUser> priorityQueue = new PriorityQueue<SocialUser>(10, new SocialUser.SocialUserComparator());
        SocialUser socialUser1 = new SocialUser("a", 130);
        priorityQueue.add(socialUser1);
        SocialUser socialUser2 = new SocialUser("b", 145);
        priorityQueue.add(socialUser2);
        SocialUser socialUser3 = new SocialUser("c", 100);
        priorityQueue.add(socialUser3);
        SocialUser socialUser4 = new SocialUser("d", 120);
        priorityQueue.add(socialUser4);
        SocialUser socialUser5 = new SocialUser("e", 110);
        priorityQueue.add(socialUser5);
        SocialUser socialUser6 = new SocialUser("f", 86);
        priorityQueue.add(socialUser6);

        SocialUser socialUser7 = new SocialUser("g", 120);
        priorityQueue.add(socialUser7);
        SocialUser socialUser8 = new SocialUser("h", 110);
        priorityQueue.add(socialUser8);
        SocialUser socialUser9 = new SocialUser("i", 45);
        priorityQueue.add(socialUser9);

        SocialUser socialUser10 = new SocialUser("j", 178);
        priorityQueue.add(socialUser10);
        SocialUser socialUser11 = new SocialUser("k", 213);
        priorityQueue.add(socialUser11);
        SocialUser socialUser12 = new SocialUser("m", 19);
        priorityQueue.add(socialUser12);
        Predicate<SocialUser> condition = socialUser -> socialUser.getKey().equals("l");
        priorityQueue.removeIf(condition);
        socialUser12 = new SocialUser("l", 27);
        priorityQueue.add(socialUser12);
        priorityQueue.remove();
        Iterator<SocialUser> iterator = priorityQueue.iterator();
        while (iterator.hasNext()){
            SocialUser poll = priorityQueue.poll();
            System.out.println(poll);
        }
    }
}
