package com.metacube.sageclarity.predictable.vo;

import com.metacube.sageclarity.predictable.entity.ProductionScheduleMaster;

public class ProductionScheduleMasterVO {
    private Long id;
    private CompanyVO companyVO;
    private String  fileName;
    private String evaluationStatus;
    private Boolean isActive;
    private String name;
    private Long companyId;
    private UserVO userVO;
    private Long userId;

    public ProductionScheduleMasterVO() {
    }


    public ProductionScheduleMasterVO(ProductionScheduleMaster schedule) {
        this.id = schedule.getId();
        this.name = schedule.getName();
        this.fileName = schedule.getFileName();
        if(schedule.getEvaluationStatus()!=null){
            this.evaluationStatus = schedule.getEvaluationStatus().getValue();
        }
        this.isActive = schedule.getActive();
        if(schedule.getCompany()!=null){
            this.companyVO = new CompanyVO(schedule.getCompany());
            this.companyId = schedule.getCompany().getId();
        }

        if (schedule.getUser()!=null){
            this.userVO = new UserVO(schedule.getUser());
            this.userId = schedule.getUser().getId();
        }
    }

    public CompanyVO getCompanyVO() {
        return companyVO;
    }

    public void setCompanyVO(CompanyVO companyVO) {
        this.companyVO = companyVO;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getEvaluationStatus() {
        return evaluationStatus;
    }

    public void setEvaluationStatus(String evaluationStatus) {
        this.evaluationStatus = evaluationStatus;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

