package com.gui.mscreditappraiser.application.ex;


public class CustomerDataNotFoundException extends Exception{

    public CustomerDataNotFoundException() {
        super("Dados do cliente não encontrados para o CPF informado");
    }
}
