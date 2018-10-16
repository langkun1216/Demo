package com.demo.common.exceptionhandle;

public enum ResultEnum {
    UNKONW_ERROR("10001", "系统内部异常"),
    SUCCESS("00000", "成功"),
    DEVICE_CHANGE_ERROR("00006","设备变更"),
    TOKEN_INVALID_ERROR("00005","token无效"),
    TOKEN_EXPIRE_ERROR("00004","token过期"),
    USER_NOTFUND_ERROR("00003","用户不存在"),
    ACCOUNT_FREEZED_ERROR("00002","账号被禁用，请联系管理员"),
    USERNAME_OR_PWD_INVALID_ERROR("00001","账号或密码错误"),
    ILLEGAL_DATA_ERROR("99999","非法请求"),
    PHONE_UNREGIST_ERROR("00007","手机号未注册"),
    VALIDCODE_ERROR("00008","验证码错误"),
    PHONE_REGISTED_ERROR("00009","该品牌下该手机号已注册过了"),
    ORIGIN_PWD_INVALID_ERROR("00010","原始密码输入错误"),
    VALIDCODE_INVALID_ERROR("00011","验证码已失效，请重新发送"),
    PARAMS_MISSING_ERROR("00012","缺少必要参数"),
    NOT_SELF_PHONE_ERROR("00013","被修改手机号不是本机"),
    NO_VIN("00014","没有此VIN号"),
    CANNOT_RELEVANCE("00015","该车已绑"),
    AUTO_BIND_ERROR("00016","自动绑定失败，注册成功"),
    PHONE_NOTRIGHT("00017","手机号与合同不一致"),
    CITY_NON_EXISTENT("00018","城市不存在或者无该城市天气信息"),
    SITE_NON_EXISTENT("00019","网点不存在或者无该网点信息"),
    VEHICLE_NOTBIND("00020","当前账号未绑定爱车"),
    ACCOUNT_OR_PWD_ISNULL("00021","账号或密码为空"),
    NOT_FOUND_VEHICLE("00022","未找到该车辆信息或该车没有lcdv"),
    NOT_ADVISER("00023","未找到专属顾问"),
    EXISTS_RESCUES("00024","救援单已存在"),
    SET_DEFAULT_VEH("00025","绑定默认车辆失败，注册成功"),
    JUST_OWNEER("00026","互联网车型只能由车主绑定"),
    IS_FINEND("00027","已完成不可修改"),
    IS_CANCEL("00028","已取消不可修改"),
    JUST_WAIT("00029","只有待安排可改为已安排"),
    POSWWORD_NOTRIGHT("00030","旧密码不能与新密码一致"),
    VIN_HAS_NOTFINISH_ORDER_ERROR("00031","该车辆在该网点下有未完成的预约单，请勿重复提交"),
    NOT_EXISTS_PHONE_BUT_THIRD("00032","没有用户手机号但是有openid"),
    DIRTY_USER_DATA("00033","用户数据异常"),
    SITE_IS_OFF_ERROR("00034","您所在的网点已关闭"),
    SITE_NOT_EXISTS_ERROR("00035","您还没有设置网点，请联系管理员"),
    REMOTE_RETURNS_ERROR("00036","远程调用异常，请稍后再试"),
    TIME_NULL_ERROR("00037","您还没有选择一个时间，请选择一个时间"),
    SITENO_NULL_ERROR("00038","您还没有选择网点，请选择一个网点"),
    SITE_USER_NULL_ERROR("00039","您选择的网点没有具有预约权限的人员，请重新选择"),
    BESAPEAK_IS_NULL("00040","该预约单无效"),
    NO_SITE_AROUND("00041","附近没有网点网点"),
    NO_AUTH_USER("00042","该用户没有任何权限"),
    WECHAT_USER_EXISTS("00043","该手机号下的openid和传入的openid不一致"),
    PHONE_EXISTS_OR_ERROR("00044","手机号已存在或者系统调用异常"),
    PHONE_NULL_ERROR("00045","该用户手机号为空"),
    USER_IS_EXISTS("00046","用户已存在"),
    USER_VEHICLE_ERROR("00047","该VIN绑定多个用户"),
    USER_VEHICLE_NULL_ERROR("00048","该VIN未绑定用户"),
    VEHICLE_T_ERROR("00049","该VIN不是T服务车辆"),
    BESPEAK_NOT_DELEAT("00050","改预约单来自于商城，取消该预约单请联系经销商"),
    VEH_LAST_ONE_WARNING("00051","已经是最后一辆车了请不要删除"),
    DIFFERENT_CODE("00052","此车与您注册品牌不一致"),
    MESSAGE_ERROR("00053","该用户未打开消息接收"),
    PUSH_ERROR("00054","消息推送失败"),
    MSG_ERROR("00055","短信发送失败"),
    BRANDCODE_ERROR("00056","品牌错误"),
    VERSCODE_ERROR("00057","当前为最新版本"),
    BESPEAK_EVAL("00058","该预约单已评价"),
    RESCUE_EVAL("00060","该救援单已评价"),
    DATA_ISNOLL("00061","获取数据失败"),
    FALSE_CHANGE("00062","修改失败"),
    HAVING_COLLECT("00063","已收藏");

    private String code;

    private String message;

    ResultEnum(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode(){
        return  this.code;
    }

    public String getMessage(){
        return this.message;
    }
}
