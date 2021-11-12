package org.redhat;

import java.util.Date;

public class CaseComment {
    String caseComment;
    String author;


    public String getCaseComment() {
        return caseComment;
    }

    public void setCaseComment(String caseComment) {
        this.caseComment = caseComment;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public CaseComment(String caseComment, String author) {
        this.caseComment = caseComment;
        this.author = author;
    }
}
