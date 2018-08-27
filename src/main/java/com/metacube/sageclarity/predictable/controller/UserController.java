package com.metacube.sageclarity.predictable.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metacube.sageclarity.predictable.entity.Role;
import com.metacube.sageclarity.predictable.entity.User;
import com.metacube.sageclarity.predictable.enums.ExceptionType;
import com.metacube.sageclarity.predictable.enums.UserRoleEnum;
import com.metacube.sageclarity.predictable.helper.RequestHelper;
import com.metacube.sageclarity.predictable.helper.ResponseHelper;
import com.metacube.sageclarity.predictable.service.RoleService;
import com.metacube.sageclarity.predictable.service.UserService;
import com.metacube.sageclarity.predictable.vo.ResponseObject;
import com.metacube.sageclarity.predictable.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/user/create", produces = "application/json",consumes="application/json"
            ,method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseObject createUser(@RequestBody String userStr){
        if(RequestHelper.isEmptyRequestString(userStr))
            return (ResponseObject.getResponse(ExceptionType.INVALID_METHOD_PARAM.getMessage()
                    , ExceptionType.INVALID_METHOD_PARAM.getCode()));
        try {
            UserVO userVO = mapper.readValue(userStr,UserVO.class);
            User obj = new User(userVO);
            obj = userService.saveUser(obj);
            return ResponseObject.getResponse(new UserVO(obj));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseObject.getResponse(ExceptionType.GENERAL_ERROR.getMessage()
                    , ExceptionType.GENERAL_ERROR.getCode());
        }
    }

    @RequestMapping(value = "/role", produces = "application/json" ,method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseObject getAllUserRole(){
        try {
            List<Role> userRoles = roleService.getAllRole();
            return ResponseObject.getResponse(ResponseHelper.getRoleVOList(userRoles));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseObject.getResponse(ExceptionType.GENERAL_ERROR.getMessage()
                    , ExceptionType.GENERAL_ERROR.getCode());
        }
    }


}
