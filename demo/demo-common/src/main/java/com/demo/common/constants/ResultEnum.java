package com.demo.common.constants;

/**
 * 系统接口结果常量枚举类
 */
public enum ResultEnum {
 
	FAILED("500", "系统异常"),
	SUCCESS("200", "success"),
	ERROR_POST_PARAM("400","访问参数异常"),
    FORBIDDEN("403","无权限访问"),
	ERROR_POST_METHOD("405","访问方法异常"),
	USER_VEHICLE_NULL_ERROR("406","该VIN 没有绑定用户" ),
	VEHICLE_NOTBIND("407","该VIN 没有绑定车型" ),
	NO_VIN("408","VIN为空" ),
	PARAMS_MISSING_ERROR("409","缺少参数" ),
	UNKONW_ERROR("410","系统内部错误" ),
	PRODUCT_NO_SPECIFIC("411","该商品没有指定规格");


	private String code;

	private String message;

	ResultEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	} 

}
