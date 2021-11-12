package org.redhat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CaseDefinition {

    public String caseId;
    public String status;
    public Map caseData;
    public CaseComment caseComments;
    public Date eventTimeStamp;

    public Date getEventTimeStamp() {
        return eventTimeStamp;
    }

    public void setEventTimeStamp(Date eventTimeStamp) {
        this.eventTimeStamp = eventTimeStamp;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map getCaseData() {
        return caseData;
    }

    public void setCaseData(Map caseData) {
        this.caseData = caseData;
    }

    public CaseComment getCaseComments() {
        return caseComments;
    }

    public void setCaseComments(CaseComment caseComments) {
        this.caseComments = caseComments;
    }

    public CaseDefinition(String caseId, String status, Map caseData, CaseComment caseComments, Date createdAt) {
        this.caseId = caseId;
        this.status = status;
        this.caseData = caseData;
        this.caseComments = caseComments;
        this.eventTimeStamp = createdAt;
    }

    public CaseDefinition() {
    }

    @Override
    public String toString() {
        return "CaseDefinition{" +
                "caseId='" + caseId + '\'' +
                ", status='" + status + '\'' +
                ", caseData=" + caseData +
                ", caseComments=" + caseComments +
                '}';
    }
}
