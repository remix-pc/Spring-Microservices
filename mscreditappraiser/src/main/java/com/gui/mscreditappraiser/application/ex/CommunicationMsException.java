package com.gui.mscreditappraiser.application.ex;

import lombok.Getter;

public class CommunicationMsException extends Exception{

    @Getter
    private Integer status;

    public CommunicationMsException(String msg, Integer status) {
        super(msg);
        this.status = status;
    }
}
