package life.majiang.community.community.dto;

import life.majiang.community.community.exception.CustomizeErrCode;
import life.majiang.community.community.exception.CustomizeException;
import lombok.Data;

/**
 * @description:
 * @author: Ye YinYong
 * @create: 2019-10-27 15:41
 **/
@Data
public class ResultDTO {
    private Integer code;
    private String message;

    public static ResultDTO errCode(Integer code, String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO err(CustomizeErrCode err) {
        return errCode(err.getCode(), err.getMessage());
    }

    public static ResultDTO ok() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("成功");
        return resultDTO;
    }

    public static ResultDTO err(CustomizeException e) {
        return errCode(e.getCode(), e.getMessage());
    }
}
