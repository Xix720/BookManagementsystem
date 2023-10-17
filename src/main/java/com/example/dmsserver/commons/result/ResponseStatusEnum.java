package com.example.dmsserver.commons.result;

/**
 * 响应结果枚举，用于提供给GraceJSONResult返回给前端的
 * 本枚举类中包含了很多的不同的状态码供使用，可以自定义
 * 便于更优雅的对状态码进行管理，一目了然
 */
public enum ResponseStatusEnum {

    SUCCESS(200, true, "操作成功！"),
    FAILED(500, false, "操作失败！"),

    // crud通用提示
    UPDATE_SUCCESS(200,true,"数据更新成功"),
    DELETE_SUCCESS(200,true,"数据删除成功"),
    INSERT_SUCCESS(200,true,"数据添加成功"),
    UPDATE_ERROR(500,false,"数据更新失败"),
    DELETE_ERROR(500,false,"数据删除失败"),
    INSERT_ERROR(500,false,"数据添加失败"),

    // 请求参数异常
    REQUEST_PARAM_ERROR(700,false,"请求参数异常"),

    //登录结果信息提示 41X
    LOGIN_SUCCESS(411,true,"登录成功！"),
    LOGIN_FAILED(412,false,"用户名或密码错误！"),
   // CHANGE_DEFAULT_PASSWORD(413,false,"您在使用的是默认密码，请重新设置密码"),
    TOKEN_EXPIRED(415,false,"token已过期，请重新登录！"),
    TOKEN_ISNULL(416,false,"登录信息已失效，请重新登录！"),
    LOGINNAME_IS_EXIST_ERROR(417,false,"登录名已经存在，请重新设置"),
    // 上传、短信 50x
    UN_LOGIN(501,false,"请登录后再继续操作！"),
    TICKET_INVALID(502,false,"会话失效，请重新登录！"),
    USER_UPDATE_ERROR(508,false,"用户信息更新失败，请联系管理员！"),
    FILE_UPLOAD_NULL_ERROR(510,false,"文件不能为空，请选择一个文件再上传！"),
    FILE_UPLOAD_FAILD(511,false,"文件上传失败！"),
    FILE_FORMATTER_FAILD(512,false,"文件图片格式不支持！"),
    FILE_MAX_SIZE_ERROR(513,false,"仅支持500kb大小以下的图片上传！"),
    FILE_NOT_EXIST_ERROR(514,false,"你所查看的文件不存在！"),


    // 自定义系统级别异常 54x
    SYSTEM_INDEX_OUT_OF_BOUNDS(541, false, "系统错误，数组越界！"),
    SYSTEM_ARITHMETIC_BY_ZERO(542, false, "系统错误，无法除零！"),
    SYSTEM_NULL_POINTER(543, false, "系统错误，空指针！"),
    SYSTEM_NUMBER_FORMAT(544, false, "系统错误，数字转换异常！"),
    SYSTEM_PARSE(545, false, "系统错误，解析异常！"),
    SYSTEM_IO(546, false, "系统错误，IO输入输出异常！"),
    SYSTEM_FILE_NOT_FOUND(547, false, "系统错误，文件未找到！"),
    SYSTEM_CLASS_CAST(548, false, "系统错误，类型强制转换错误！"),
    SYSTEM_PARSER_ERROR(549, false, "系统错误，解析出错！"),
    SYSTEM_DATE_PARSER_ERROR(550, false, "系统错误，日期解析出错！"),

    // 用户模块 相关错误 58x
    USER_NOT_EXIST_ERROR(481,false,"用户不存在！"),

    // 角色权限模块 61x
    ROLE_IS_USING(610,false,"有用户正在使用该角色"),


    // 系统错误，未预期的错误 555
    SYSTEM_ERROR(555, false, "系统繁忙，请稍后再试！"),
    SYSTEM_OPERATION_ERROR(556, false, "操作失败，请重试或联系管理员"),
    SYSTEM_RESPONSE_NO_INFO(557, false, "");

    // 响应业务状态
    private Integer status;
    // 调用是否成功
    private Boolean success;
    // 响应消息，可以为成功或者失败的消息
    private String msg;

    ResponseStatusEnum(Integer status, Boolean success, String msg) {
        this.status = status;
        this.success = success;
        this.msg = msg;
    }

    public Integer status() {
        return status;
    }
    public Boolean success() {
        return success;
    }
    public String msg() {
        return msg;
    }
}
