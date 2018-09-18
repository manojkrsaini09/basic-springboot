package com.metacube.sageclarity.predictable.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metacube.sageclarity.predictable.entity.ProductionScheduleMaster;
import com.metacube.sageclarity.predictable.enums.ExceptionType;
import com.metacube.sageclarity.predictable.exception.ApplicationLevelException;
import com.metacube.sageclarity.predictable.exception.InvalidParamException;
import com.metacube.sageclarity.predictable.helper.RequestHelper;
import com.metacube.sageclarity.predictable.helper.ResponseHelper;
import com.metacube.sageclarity.predictable.service.ProductionScheduleMasterService;
import com.metacube.sageclarity.predictable.vo.ProductionScheduleMasterVO;
import com.metacube.sageclarity.predictable.vo.ResponseObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductionScheduleController {
    private static final Logger logger = LoggerFactory.getLogger(ProductionScheduleController.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ProductionScheduleMasterService scheduleService;

    @RequestMapping(value = "/schedule/create", produces = "application/json",consumes="application/json"
            ,method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseObject createSchedule(@RequestBody String scheduleStr){
        if(RequestHelper.isEmptyRequestString(scheduleStr))
            return (ResponseObject.getResponse(ExceptionType.INVALID_METHOD_PARAM.getMessage()
                    , ExceptionType.INVALID_METHOD_PARAM.getCode()));
        try {
            ProductionScheduleMasterVO scheduleVO = mapper.readValue(scheduleStr,ProductionScheduleMasterVO.class);
            ProductionScheduleMaster obj = new ProductionScheduleMaster(scheduleVO);
            obj = scheduleService.save(obj);
            return ResponseObject.getResponse(new ProductionScheduleMasterVO(obj));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseObject.getResponse(ExceptionType.GENERAL_ERROR.getMessage()
                    , ExceptionType.GENERAL_ERROR.getCode());
        }
    }

    @RequestMapping(value = "/schedule/update", produces = "application/json",consumes="application/json"
            ,method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseObject updateSchedule(@RequestBody String scheduleStr){
        if(RequestHelper.isEmptyRequestString(scheduleStr))
            return (ResponseObject.getResponse(ExceptionType.INVALID_METHOD_PARAM.getMessage()
                    , ExceptionType.INVALID_METHOD_PARAM.getCode()));
        try {
            ProductionScheduleMasterVO productScheduleVO = mapper.readValue(scheduleStr,ProductionScheduleMasterVO.class);
            Long scheduleId = productScheduleVO.getId();
            ProductionScheduleMaster schedule = scheduleService.getById(scheduleId);
            if(schedule == null){
                logger.error("No Schedule found for id: " + scheduleId);
                return ResponseObject.getResponse(ExceptionType.NO_DATA_FOUND.getMessage()
                        , ExceptionType.NO_DATA_FOUND.getCode());
            }

            schedule = new ProductionScheduleMaster(productScheduleVO,schedule);
            schedule = scheduleService.save(schedule);
            return ResponseObject.getResponse(new ProductionScheduleMasterVO(schedule));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseObject.getResponse(ExceptionType.GENERAL_ERROR.getMessage()
                    , ExceptionType.GENERAL_ERROR.getCode());
        }
    }

    @RequestMapping(value = "/schedule/all", produces = "application/json",method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseObject getAllSchedules(@RequestParam(value = "companyId") String  companyIdStr){
        Long companyId = null;
        if(StringUtils.isNotBlank(companyIdStr)){
            companyId = Long.parseLong(companyIdStr);
        }
        try {
            List<ProductionScheduleMaster> schedules = null;
            if(companyId!=null){
                schedules = scheduleService.getAllByCompanyId(companyId);
            }else{
                schedules = scheduleService.getAll();
            }
            return ResponseObject.getResponse(ResponseHelper.getScheduleVOList(schedules));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseObject.getResponse(ExceptionType.GENERAL_ERROR.getMessage()
                    , ExceptionType.GENERAL_ERROR.getCode());
        }
    }

    @RequestMapping(value = "/schedule" , method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ResponseObject getScheduleById(@RequestParam(value = "id", required = true) String idStr) {
        Long entityId = Long.parseLong(idStr);
        try {
            ProductionScheduleMaster schedule= scheduleService.getById(entityId);
            if(schedule == null){
                logger.error("No schedule found for id: " + entityId);
                return ResponseObject.getResponse(ExceptionType.NO_DATA_FOUND.getMessage()
                        , ExceptionType.NO_DATA_FOUND.getCode());
            }
            return ResponseObject.getResponse(new ProductionScheduleMasterVO(schedule));
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

   /* @RequestMapping(value = "/schedule/delete" , method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ResponseObject deleteSchedule(@RequestParam(value = "id", required = true) String idStr) {
        Long entityId = Long.parseLong(idStr);
        try {
            ProductionScheduleMaster entity = scheduleService.getById(entityId);
            if(entity == null){
                logger.error("No schedule found for id: " + entityId);
                return ResponseObject.getResponse(ExceptionType.NO_DATA_FOUND.getMessage()
                        , ExceptionType.NO_DATA_FOUND.getCode());
            }
            scheduleService.deleteSchedule(entity);
            return ResponseObject.getResponse(true);
        }catch (InvalidParamException e) {
            logger.error(e.getMessage(), e);
            return ResponseObject.getResponse(ExceptionType.INVALID_METHOD_PARAM.getMessage()
                    , ExceptionType.INVALID_METHOD_PARAM.getCode());
        } catch (ApplicationLevelException e) {
            logger.error(e.getMessage(), e);
            return ResponseObject.getResponse(ExceptionType.GENERAL_ERROR.getMessage()
                    , ExceptionType.GENERAL_ERROR.getCode());
        }
    }*/

}
