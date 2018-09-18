package com.metacube.sageclarity.predictable.config;

import com.metacube.sageclarity.predictable.helper.constant.CommonConstant;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
   @Override
    public Optional<String> getCurrentAuditor() {
       if(SecurityContextHolder.getContext().getAuthentication()!=null){
           return Optional.of(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
       }else{
           return Optional.of(CommonConstant.DUMMY_AUDITOR_NAME);
       }

    }

   /* @Override
    public String getCurrentAuditor() {
        // Can use Spring Security to return currently logged in user
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        //return "manu";
    }*/
}

