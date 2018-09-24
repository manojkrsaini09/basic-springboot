package com.metacube.sageclarity.predictable.entity;

import com.metacube.sageclarity.predictable.enums.EvaluationStatus;
import com.metacube.sageclarity.predictable.vo.ProductionScheduleMasterVO;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class ProductionScheduleMaster extends BaseEntity<String>{
    @Column
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private Company company;

    @Column
    private String  fileName;

    @Column
    @Enumerated(EnumType.STRING)
    private EvaluationStatus evaluationStatus;

    @Column
    private Boolean isActive=true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "date")
    private LocalDateTime date;

    @Column
    @OneToMany(
            mappedBy = "scheduleMaster",
            cascade = CascadeType.ALL,
            orphanRemoval = true
          )
    private List<ProductionScheduleData> scheduleData = new ArrayList<>();

    public ProductionScheduleMaster() {
    }

    public ProductionScheduleMaster(ProductionScheduleMasterVO scheduleMasterVO) {
        if(scheduleMasterVO.getId()!=null){
            super.setId(scheduleMasterVO.getId());
        }
        this.name = scheduleMasterVO.getName();
        this.fileName = scheduleMasterVO.getFileName();
        if(scheduleMasterVO.getEvaluationStatus()!=null){
            this.evaluationStatus = EvaluationStatus.getEvaluationStatusFromStringValue(scheduleMasterVO.getEvaluationStatus());
        }
       this.isActive = scheduleMasterVO.getActive();
        if(scheduleMasterVO.getCompanyVO()!=null){
            this.company = new Company(scheduleMasterVO.getCompanyVO());
        }else if(scheduleMasterVO.getCompanyId()!=null){
            this.company = new Company(scheduleMasterVO.getCompanyId());
        }

        if(scheduleMasterVO.getUserVO()!=null){
            this.user = new User(scheduleMasterVO.getUserVO());
        }else{
            this.user = new User(scheduleMasterVO.getUserId());
        }
    }

    public ProductionScheduleMaster(ProductionScheduleMasterVO scheduleMasterVO,ProductionScheduleMaster schedule){
        BeanUtils.copyProperties(schedule,this);
        if(scheduleMasterVO.getId()!=null){
            super.setId(scheduleMasterVO.getId());
        }
        this.name = scheduleMasterVO.getName();
        this.fileName = scheduleMasterVO.getFileName();
        if(scheduleMasterVO.getEvaluationStatus()!=null){
            this.evaluationStatus = EvaluationStatus.getEvaluationStatusFromStringValue(scheduleMasterVO.getEvaluationStatus());
        }
        this.isActive = scheduleMasterVO.getActive();
        if(scheduleMasterVO.getCompanyVO()!=null){
            this.company = new Company(scheduleMasterVO.getCompanyVO());
        }else if(scheduleMasterVO.getCompanyId()!=null){
            this.company = new Company(scheduleMasterVO.getCompanyId());
        }
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public EvaluationStatus getEvaluationStatus() {
        return evaluationStatus;
    }

    public void setEvaluationStatus(EvaluationStatus evaluationStatus) {
        this.evaluationStatus = evaluationStatus;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<ProductionScheduleData> getScheduleData() {
        return scheduleData;
    }

    public void setScheduleData(List<ProductionScheduleData> scheduleData) {
        this.scheduleData = scheduleData;
    }
}
