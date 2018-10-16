package com.demo.center.controller;

import com.demo.center.config.RedisService;
import com.demo.center.feignservice.BackService;
import com.demo.common.entity.SysUser;
import com.demo.common.entity.SysUserExtend;
import com.demo.common.interfacedto.InterfaceResult;
import com.demo.common.utils.SecuritySecretUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/demo-center")
public class LoginController {

    @Autowired
    private BackService backService;

    @Autowired
    private RedisService redisService;

    private Logger logger= LoggerFactory.getLogger(LoginController.class);

//    @PostMapping(value = "/app/login", consumes = {"application/json"},produces = {"application/json"})
//    @ApiOperation(value = "用户登录操作",notes = "用户登录操作")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "sysUser",  value = "参数Body", required = true, dataType = "NtspAppSysUser",paramType = "post")
//    })
//    public CuscResult appLogin(@RequestHeader("currentTimeStamp") String currentTimeStamp,
//                               @RequestHeader("brandCode") String brandCode, @RequestHeader("appId") String appId,
//                               @RequestHeader("appVersionNo") String appVersionNo, @RequestHeader("deviceNo") String deviceNo,@RequestHeader("sign")String sign,
//                               @RequestHeader("deviceType") String deviceType, @RequestHeader(value = "userName", defaultValue = "") String headUserName, @RequestHeader(value = "token", defaultValue = "") String paramToken, @RequestBody LoginParamVO loginParamVO){
//        return backstageService.loginOn(currentTimeStamp,brandCode,appId,appVersionNo,deviceNo,sign,deviceType,headUserName,paramToken,loginParamVO);
//    }

    @PostMapping(value = "/login", consumes = {"application/json"},produces = {"application/json"})
    @ApiOperation(value = "用户登录操作",notes = "用户登录操作")
    @ApiImplicitParam(name = "sysUser", value = "系统用户", required = true, dataType = "SysUser")
    public InterfaceResult login(@RequestBody SysUser sysUser){

        InterfaceResult interfaceResult=new InterfaceResult();
        try {
            SysUserExtend userExtend = new SysUserExtend();
            SysUser loginSysUser = backService.login(sysUser);

            if (loginSysUser != null && loginSysUser.getSysPassword() != null && sysUser.getSysPassword().equals(loginSysUser.getSysPassword())) {
                String currentTimemillis = System.currentTimeMillis() + "";
                Map<Object, Object> params = new HashMap<>();
                params.put("userName", sysUser.getSysLoginName());
                params.put("userPwd", sysUser.getSysPassword());
                params.put("currentMills", currentTimemillis);
                String token = SecuritySecretUtil.getSignStr(params, currentTimemillis);

                userExtend.setSysUser(loginSysUser);
                userExtend.setToken(token);
//                //获取用户菜单
//                List<NtspAppSysMenu> menus = userService.getMenuByUserId(loginSysUser);
//                List<TreeNode> nodes = new ArrayList<>();
//                for(NtspAppSysMenu menu:menus){
//                    TreeNode treeNode = new TreeNode();
//                    treeNode.setId(menu.getId());
//                    treeNode.setParentId(menu.getParentId());
//                    treeNode.setLabel(menu.getName());
//                    treeNode.setHref(menu.getHref());
//                    treeNode.setRemarks(menu.getRemarks());
//                    nodes.add(treeNode);
//                }
//                List<TreeNode> menusTree = TreeUtil.getTreeList("",nodes);
//                // List<NtspAppSysMenu> TreeMenus = menuToTreeNodeMenu(menus);
//                userExtend.setTreeMenus(menusTree);
//                redisService.set(token,userExtend,1800);

                interfaceResult.setMsg("success");
                interfaceResult.setCode("200");
                interfaceResult.setData(userExtend);
            }else {
                if(null== loginSysUser){
                    interfaceResult.setMsg("账号无效或未授权!");
                    interfaceResult.setCode("202");
                }else{
                    interfaceResult.setMsg("用户名或密码错误!");
                    interfaceResult.setCode("202");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            interfaceResult.setMsg("fail!");
            interfaceResult.setCode("402");
        }finally {
            return  interfaceResult;
        }
    }

//    @PostMapping(value = "/user-change-password", consumes = {"application/json"},produces = {"application/json"})
//    @ApiOperation(value = "用户修改密码操作",notes = "用户修改密码操作")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "sysUser",  value = "参数Body", required = true, dataType = "NtspAppSysUser",paramType = "post")
//    })
//    public CuscResult changePassWord(@RequestBody NtspAppSysUser sysUser){
//        CuscResult result=new CuscResult();
//        try {
//
//            NtspAppSysUserExtend ntspAppSysUserExtend=(NtspAppSysUserExtend) redisService.get(sysUser.getToken());
//            if(ntspAppSysUserExtend.getSysUser().getNewPassword().equals(sysUser.getOldPassword())){
//                ntspAppSysUserExtend.getSysUser().setNewPassword(sysUser.getNewPassword());
//                if(userService.changePassWord(ntspAppSysUserExtend.getSysUser()).getCode().equals("200")){
//                    redisService.set(sysUser.getToken(),ntspAppSysUserExtend);
//                    result.setCode("200");
//                    result.setMessage("success");
//                }else{
//                    result.setCode("202");
//                    result.setMessage("fail");
//                }
//            }else{
//                result.setCode("201");
//                result.setMessage("原密码错误");
//            }
//
//
//        }catch (Exception e){
//            result.setCode("202");
//            result.setMessage("fail");
//            logger.error("修改密码失败，服务器异常！");
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    /**
//     * @author wangjun
//     * @version V1.0.0
//     * @time 15:17
//     * @date 2018/7/12
//     * @description : 退出登录
//     */
//    @PostMapping(value = "/user-logout", consumes = {"application/json"},produces = {"application/json"})
//    @ApiOperation(value = "用户退出操作",notes = "用户退出操作")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "sysUser",  value = "参数Header", required = true, dataType = "String",paramType = "post")
//    })
//    public CuscResult logout(@RequestHeader String token){
//        CuscResult result=new CuscResult();
//        try {
//            redisService.del(token);
//            result.setCode("200");
//            result.setMessage("success");
//        }catch (Exception e){
//            result.setCode("202");
//            result.setMessage("fail");
//            logger.error("系统用户登出操作失败！");
//            e.printStackTrace();
//        }
//        return result;
//    }


}
