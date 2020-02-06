package com.mqm.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001,"Your question is not exist, please check or choose another question."),
    TARGET_PARAN_NOT_FOUND(2002,"You don't choose any question to comment."),
    NO_LOGIN(2003,"No login, please login."),
    SYS_ERROR(2004,"Server shutdown, please try it later."),
    TYPE_PARAN_WRONG(2005,"comment type wrong or not exist"),
    COMMENT_NOT_FOUND(2006,"comment not found"),
    CONTENT_IS_EMPTY(2007,"input content cannot be null");



    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }


}
