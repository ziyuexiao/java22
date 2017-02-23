package com.kaishengit.dto;

import java.util.List;

/**
 * Created by lenovo on 2017/2/22.
 */
public class WorkerDispatchDto {

    /**
     * workerArray : [{"id":"1","workername":"卡车司机","workerprice":"300","workernum":"1","totalprice":900},{"id":"2","workername":"小车司机","workerprice":"200","workernum":"1","totalprice":600}]
     * fileArray : [{"sourcename":"javaee试题.rtf","newname":"9c122149-98be-4b08-b8a5-b64fa6e4282e.rtf"},{"sourcename":"echarts.js","newname":"d2d94a28-415e-4710-bc34-c751dd342d51.js"}]
     * companyName : 华锐
     * companyTel : 0011
     * address : 郑州
     * linkMan : 陈经理
     * personTel : 1122
     * cardNum : 411
     * startDate : 2017-02-22
     * endDate : 2017-02-25
     * totalDays : 3
     */

    private String companyName;
    private String companyTel;
    private String address;
    private String linkMan;
    private String personTel;
    private String cardNum;
    private String startDate;
    private String endDate;
    private Integer totalDays;
    private List<WorkerArrayBean> workerArray;
    private List<FileArrayBean> fileArray;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getPersonTel() {
        return personTel;
    }

    public void setPersonTel(String personTel) {
        this.personTel = personTel;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    public List<WorkerArrayBean> getWorkerArray() {
        return workerArray;
    }

    public void setWorkerArray(List<WorkerArrayBean> workerArray) {
        this.workerArray = workerArray;
    }

    public List<FileArrayBean> getFileArray() {
        return fileArray;
    }

    public void setFileArray(List<FileArrayBean> fileArray) {
        this.fileArray = fileArray;
    }

    public static class WorkerArrayBean {
        /**
         * id : 1
         * workername : 卡车司机
         * workerprice : 300
         * workernum : 1
         * totalprice : 900
         */

        private Integer id;
        private String workername;
        private Float workerprice;
        private Integer workernum;
        private float totalprice;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getWorkername() {
            return workername;
        }

        public void setWorkername(String workername) {
            this.workername = workername;
        }

        public Float getWorkerprice() {
            return workerprice;
        }

        public void setWorkerprice(Float workerprice) {
            this.workerprice = workerprice;
        }

        public Integer getWorkernum() {
            return workernum;
        }

        public void setWorkernum(Integer workernum) {
            this.workernum = workernum;
        }

        public float getTotalprice() {
            return totalprice;
        }

        public void setTotalprice(float totalprice) {
            this.totalprice = totalprice;
        }
    }

    public static class FileArrayBean {
        /**
         * sourcename : javaee试题.rtf
         * newname : 9c122149-98be-4b08-b8a5-b64fa6e4282e.rtf
         */

        private String sourcename;
        private String newname;

        public String getSourcename() {
            return sourcename;
        }

        public void setSourcename(String sourcename) {
            this.sourcename = sourcename;
        }

        public String getNewname() {
            return newname;
        }

        public void setNewname(String newname) {
            this.newname = newname;
        }
    }
}
