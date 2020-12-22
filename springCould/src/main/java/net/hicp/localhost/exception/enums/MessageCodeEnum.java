package net.hicp.localhost.exception.enums;

/**
 *
 * @desc 返回码枚举
 * @author cheat
 */

public enum  MessageCodeEnum {
    EXCEPTION("400","系统异常！"),
    OK("200","操作成功！"),
    ;

    private final String code;
    private final String msg;

    MessageCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "{code='" + code + '\'' + ", msg='" + msg + '\'' + '}';
    }
}
