package com.laynefongx.hodgepodge.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class DisruptorMessage implements Serializable {

    private static final long serialVersionUID = -1939515616131704787L;

    private String message;

}
