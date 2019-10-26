package life.majiang.community.community.exception;

/**
 * @description:
 * @author: Ye YinYong
 * @create: 2019-10-26 16:53
 **/
public class CustomizeException extends RuntimeException{
    private String message;

    public CustomizeException(String message) {
        this.message = message;
    }

    public CustomizeException(CustomizeErrCode errCode) {
        this.message = errCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
