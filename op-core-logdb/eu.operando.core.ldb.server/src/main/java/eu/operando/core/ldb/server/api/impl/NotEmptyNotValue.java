package eu.operando.core.ldb.server.api.impl;

public class NotEmptyNotValue extends Exception {
    public NotEmptyNotValue(String msg){
        super(msg);
    }
}
