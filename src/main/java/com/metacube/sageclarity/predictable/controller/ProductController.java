package com.metacube.sageclarity.predictable.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metacube.sageclarity.predictable.entity.Company;
import com.metacube.sageclarity.predictable.entity.Product;
import com.metacube.sageclarity.predictable.enums.ExceptionType;
import com.metacube.sageclarity.predictable.exception.ApplicationLevelException;
import com.metacube.sageclarity.predictable.exception.InvalidParamException;
import com.metacube.sageclarity.predictable.helper.RequestHelper;
import com.metacube.sageclarity.predictable.helper.ResponseHelper;
import com.metacube.sageclarity.predictable.service.CompanyService;
import com.metacube.sageclarity.predictable.service.ProductService;
import com.metacube.sageclarity.predictable.vo.CompanyVO;
import com.metacube.sageclarity.predictable.vo.ProductVO;
import com.metacube.sageclarity.predictable.vo.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/product/create", produces = "application/json",consumes="application/json"
            ,method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseObject createProduct(@RequestBody String productStr){
        if(RequestHelper.isEmptyRequestString(productStr))
            return (ResponseObject.getResponse(ExceptionType.INVALID_METHOD_PARAM.getMessage()
                    , ExceptionType.INVALID_METHOD_PARAM.getCode()));
        try {
            ProductVO productVO = mapper.readValue(productStr,ProductVO.class);
            Product obj = new Product(productVO);
            obj = productService.save(obj);
            return ResponseObject.getResponse(new ProductVO(obj));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseObject.getResponse(ExceptionType.GENERAL_ERROR.getMessage()
                    , ExceptionType.GENERAL_ERROR.getCode());
        }
    }

    @RequestMapping(value = "/product/update", produces = "application/json",consumes="application/json"
            ,method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseObject updateProduct(@RequestBody String productStr, @RequestParam(value = "id") String productIdStr){
        if(RequestHelper.isEmptyRequestString(productStr) || RequestHelper.isEmptyRequestString(productIdStr))
            return (ResponseObject.getResponse(ExceptionType.INVALID_METHOD_PARAM.getMessage()
                    , ExceptionType.INVALID_METHOD_PARAM.getCode()));
        try {
            Long productId = Long.valueOf(productIdStr);
            Product product = productService.getById(productId);
            if(product == null){
                logger.error("No Product found for id: " + productId);
                return ResponseObject.getResponse(ExceptionType.NO_DATA_FOUND.getMessage()
                        , ExceptionType.NO_DATA_FOUND.getCode());
            }
            ProductVO productVO = mapper.readValue(productStr,ProductVO.class);
             product = new Product(productVO,product);
            product = productService.save(product);
            return ResponseObject.getResponse(new ProductVO(product));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseObject.getResponse(ExceptionType.GENERAL_ERROR.getMessage()
                    , ExceptionType.GENERAL_ERROR.getCode());
        }
    }

    @RequestMapping(value = "/product/all", produces = "application/json",method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseObject getAllProducts(){
        try {
            List<Product> products = null;
            products = productService.getAll();
            return ResponseObject.getResponse(ResponseHelper.getProductVOList(products));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseObject.getResponse(ExceptionType.GENERAL_ERROR.getMessage()
                    , ExceptionType.GENERAL_ERROR.getCode());
        }
    }

    @RequestMapping(value = "/product" , method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ResponseObject getProductById(@RequestParam(value = "id", required = true) String idStr) {
        Long entityId = Long.parseLong(idStr);
        try {
            Product entity = productService.getById(entityId);
            if(entity == null){
                logger.error("No Product found for id: " + entityId);
                return ResponseObject.getResponse(ExceptionType.NO_DATA_FOUND.getMessage()
                        , ExceptionType.NO_DATA_FOUND.getCode());
            }
            return ResponseObject.getResponse(new ProductVO(entity));
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
