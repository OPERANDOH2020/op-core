package eu.operando.core.ldb.server.api.impl;

public class RequiredNotSet extends Exception {
    public RequiredNotSet(String msg){
        super(msg);
    }
}
