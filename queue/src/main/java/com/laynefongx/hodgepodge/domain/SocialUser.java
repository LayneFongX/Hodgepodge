package com.laynefongx.hodgepodge.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

@Data
@Builder
public class SocialUser implements Serializable {

    private static final long serialVersionUID = 4444105540010368046L;

    private String key;

    private Integer value;


    public SocialUser(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public static class SocialUserComparator implements Comparator {
        @Override
        public int compare(Object arg0, Object arg1) {
            SocialUser SocialUser0 = (SocialUser) arg0;
            SocialUser SocialUser1 = (SocialUser) arg1;
            if (SocialUser0.getValue() > SocialUser1.getValue()) {
                return -1;
            }
            // 身高相同时再比较名字
            if (Objects.equals(SocialUser0.getValue(), SocialUser1.getValue())) {
                return SocialUser0.getKey().compareTo(SocialUser1.getKey());
            }

            if (SocialUser0.getValue() < SocialUser1.getValue()) {
                return 1;
            }

            return 0;
        }

    }

}
