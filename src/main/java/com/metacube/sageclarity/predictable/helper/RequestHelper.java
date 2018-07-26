package com.metacube.sageclarity.predictable.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RequestHelper {
    private static final Logger logger= LoggerFactory.getLogger(RequestHelper.class);
    private final ObjectMapper mapper = new ObjectMapper();
    public static Boolean isEmptyRequestString(String str){
        if (StringUtils.isBlank(str)){
            logger.error("Request data is null or empty.");
            return  true;
        }
        return false;
    }
}
