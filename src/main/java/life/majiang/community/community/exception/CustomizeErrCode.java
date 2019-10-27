package life.majiang.community.community.exception;

public enum CustomizeErrCode implements CustomizeErrCodeInterface {

    QUESTION_NOT_FOUND(2001,"该问题已不存在，要不换一个试试？"),
    TARGET_PARAM_NOT_FOUND(2002,"请选择要回复的问题"),
    NO_LOGIN(2003,"当前操作需要登录"),
    SYS_ERR(2004,"服务已经冒烟了，要不等会再试一下！"),
    TARGET_PARAM_WRONG(2005,"评论类型错误或者不存在"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在，要不换一个试试"),
    QUESTION_ERR(2007,"回复的问题不存在，要不换一个试试");


    private Integer code;
    private String message;

    CustomizeErrCode(Integer code, String message) {
        this.code = code;
        this.message = message;
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
