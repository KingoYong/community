package life.majiang.community.community.exception;

public enum CustomizeErrCode implements CustomizeErrCodeInterface {

    QUESTION_NOT_FOUND("你找的问题不在了，要不换一个试试？");

    private String message;

    CustomizeErrCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
