package com.metacube.sageclarity.predictable.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metacube.sageclarity.predictable.entity.DemoEntity;
import com.metacube.sageclarity.predictable.enums.ExceptionType;
import com.metacube.sageclarity.predictable.exception.ApplicationLevelException;
import com.metacube.sageclarity.predictable.exception.InvalidParamException;
import com.metacube.sageclarity.predictable.helper.RequestHelper;
import com.metacube.sageclarity.predictable.helper.ResponseHelper;
import com.metacube.sageclarity.predictable.service.DemoEntityService;
import com.metacube.sageclarity.predictable.vo.DemoEntityVO;
import com.metacube.sageclarity.predictable.vo.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DemoEntityController {

    private static final Logger logger = LoggerFactory.getLogger(DemoEntityController.class);
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private DemoEntityService demoEntityService;


    @RequestMapping(value = "/demoentity/save", produces = "application/json",consumes="application/json"
            ,method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseObject saveDemoEntity(@RequestBody String demoEntityStr){
        if(RequestHelper.isEmptyRequestString(demoEntityStr))
            return (ResponseObject.getResponse(ExceptionType.INVALID_METHOD_PARAM.getMessage()
                    , ExceptionType.INVALID_METHOD_PARAM.getCode()));
        try {
            DemoEntityVO demoEntityVO = mapper.readValue(demoEntityStr,DemoEntityVO.class);
            DemoEntity obj = new DemoEntity(demoEntityVO);
            obj = demoEntityService.saveDemoEntity(obj);
            return ResponseObject.getResponse(new DemoEntityVO(obj));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseObject.getResponse(ExceptionType.GENERAL_ERROR.getMessage()
                    , ExceptionType.GENERAL_ERROR.getCode());
        }
    }

    @RequestMapping(value = "/demoentity" , method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ResponseObject getDemoEntityById(@RequestParam(value = "id", required = true) String idStr) {
        Long entityId = Long.parseLong(idStr);
        try {
            DemoEntity entity = demoEntityService.getDemoEntityById(entityId);
            if(entity == null){
                logger.error("No demo entity found for id: " + entity);
                return ResponseObject.getResponse(ExceptionType.NO_DATA_FOUND.getMessage()
                        , ExceptionType.NO_DATA_FOUND.getCode());
            }
            return ResponseObject.getResponse(new DemoEntityVO(entity));
        }catch (InvalidParamException e) {
            logger.error(e.getMessage(), e);
            return ResponseObject.getResponse(ExceptionType.INVALID_METHOD_PARAM.getMessage()
                    , ExceptionType.INVALID_METHOD_PARAM.getCode());
        } catch (ApplicationLevelException e) {
            logger.error(e.getMessage(), e);
            return ResponseObject.getResponse(ExceptionType.GENERAL_ERROR.getMessage()
                    , ExceptionType.GENERAL_ERROR.getCode());
        }
    }

    @RequestMapping(value = "/demoentity/getByType" , method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ResponseObject getDemoEntityByObjectType(@RequestParam(value = "objectType", required = true) String objectTypeStr) {
        try {
            List<DemoEntity> entityList = demoEntityService.getByObjectType(objectTypeStr);
            if(entityList == null){
                logger.error("No demo entity found for type: " + objectTypeStr);
                return ResponseObject.getResponse(ExceptionType.NO_DATA_FOUND.getMessage()
                        , ExceptionType.NO_DATA_FOUND.getCode());
            }
            return ResponseObject.getResponse(ResponseHelper.getDemoEntityVOList(entityList));
        }catch (InvalidParamException e) {
            logger.error(e.getMessage(), e);
            return ResponseObject.getResponse(ExceptionType.INVALID_METHOD_PARAM.getMessage()
                    , ExceptionType.INVALID_METHOD_PARAM.getCode());
        } catch (ApplicationLevelException e) {
            logger.error(e.getMessage(), e);
            return ResponseObject.getResponse(ExceptionType.GENERAL_ERROR.getMessage()
                    , ExceptionType.GENERAL_ERROR.getCode());
        }
    }

}
