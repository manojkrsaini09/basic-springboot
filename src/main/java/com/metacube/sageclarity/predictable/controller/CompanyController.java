package com.metacube.sageclarity.predictable.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metacube.sageclarity.predictable.entity.Company;
import com.metacube.sageclarity.predictable.enums.ExceptionType;
import com.metacube.sageclarity.predictable.exception.ApplicationLevelException;
import com.metacube.sageclarity.predictable.exception.InvalidParamException;
import com.metacube.sageclarity.predictable.helper.RequestHelper;
import com.metacube.sageclarity.predictable.helper.ResponseHelper;
import com.metacube.sageclarity.predictable.service.CompanyService;
import com.metacube.sageclarity.predictable.vo.CompanyVO;
import com.metacube.sageclarity.predictable.vo.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
public class CompanyController {
    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "/company/create", produces = "application/json",consumes="application/json"
            ,method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseObject createCompany(@RequestBody String companyStr){
        if(RequestHelper.isEmptyRequestString(companyStr))
            return (ResponseObject.getResponse(ExceptionType.INVALID_METHOD_PARAM.getMessage()
                    , ExceptionType.INVALID_METHOD_PARAM.getCode()));
        try {
            CompanyVO companyVO = mapper.readValue(companyStr,CompanyVO.class);
            Company obj = new Company(companyVO);
            obj = companyService.save(obj);
            return ResponseObject.getResponse(new CompanyVO(obj));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseObject.getResponse(ExceptionType.GENERAL_ERROR.getMessage()
                    , ExceptionType.GENERAL_ERROR.getCode());
        }
    }

    @Secured({ "ROLE_Admin", "ROLE_ADMIN" , "Admin" , "ADMIN" , "admin"})
    @RequestMapping(value = "/company/all", produces = "application/json",method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseObject getAllCompanies(){
        try {
            List<Company> companies = null;
            companies = companyService.getAll();
            return ResponseObject.getResponse(ResponseHelper.getCompanyVOList(companies));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseObject.getResponse(ExceptionType.GENERAL_ERROR.getMessage()
                    , ExceptionType.GENERAL_ERROR.getCode());
        }
    }

    @RequestMapping(value = "/company" , method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ResponseObject getCompanyById(@RequestParam(value = "id", required = true) String idStr) {
        Long entityId = Long.parseLong(idStr);
        try {
            Company entity = companyService.getById(entityId);
            if(entity == null){
                logger.error("No Company found for id: " + entityId);
                return ResponseObject.getResponse(ExceptionType.NO_DATA_FOUND.getMessage()
                        , ExceptionType.NO_DATA_FOUND.getCode());
            }
            return ResponseObject.getResponse(new CompanyVO(entity));
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


    @RequestMapping(value = "/company/update", produces = "application/json",consumes="application/json"
            ,method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseObject updateProduct(@RequestBody String companyStr){
        if(RequestHelper.isEmptyRequestString(companyStr))
            return (ResponseObject.getResponse(ExceptionType.INVALID_METHOD_PARAM.getMessage()
                    , ExceptionType.INVALID_METHOD_PARAM.getCode()));
        try {
            CompanyVO companyVO = mapper.readValue(companyStr,CompanyVO.class);
            Long companyId = companyVO.getId();
            Company company = companyService.getById(companyId);
            if(company == null){
                logger.error("No company found for id: " + companyId);
                return ResponseObject.getResponse(ExceptionType.NO_DATA_FOUND.getMessage()
                        , ExceptionType.NO_DATA_FOUND.getCode());
            }

            company = new Company(companyVO,company);
            company = companyService.save(company);
            return ResponseObject.getResponse(new CompanyVO(company));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseObject.getResponse(ExceptionType.GENERAL_ERROR.getMessage()
                    , ExceptionType.GENERAL_ERROR.getCode());
        }
    }

}
