package com.kaishengit.dto;

import java.util.List;

/**
 * Created by lenovo on 2017/2/17.
 */
public class DeviceRentDto {


    /**
     * deviceArray : [{"id":"1","devicename":"拖拉机","deviceunit":"台","deviceprice":"10","devicenum":"10","total":100},{"id":"4","devicename":"吊车","deviceunit":"辆","deviceprice":"30","devicenum":"1","total":30}]
     * fileArray : ["e04f7943-b0ce-4b1e-bac4-9db8dd995f3b.txt","2cb9a9e3-720b-49f2-920e-51f32851dc5b.rtf"]
     * companyName : 速运
     * tel : 11
     * linkMan : 谢
     * cardNum : 11
     * address : 广州
     * fax : 333
     * rentDate : 2017-02-17
     * backDate : 2017-02-26
     * totalDate : 9
     */

    private String companyName;
    private String tel;
    private String linkMan;
    private String cardNum;
    private String address;
    private String fax;
    private String rentDate;
    private String backDate;
    private Integer totalDate;
    private List<DeviceArrayBean> deviceArray;
    private List<DocsBean> fileArray;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public String getBackDate() {
        return backDate;
    }

    public void setBackDate(String backDate) {
        this.backDate = backDate;
    }

    public Integer getTotalDate() {
        return totalDate;
    }

    public void setTotalDate(Integer totalDate) {
        this.totalDate = totalDate;
    }

    public List<DeviceArrayBean> getDeviceArray() {
        return deviceArray;
    }

    public void setDeviceArray(List<DeviceArrayBean> deviceArray) {
        this.deviceArray = deviceArray;
    }

    public List<DocsBean> getFileArray() {
        return fileArray;
    }

    public void setFileArray(List<DocsBean> fileArray) {
        this.fileArray = fileArray;
    }

    public static class DeviceArrayBean {
        /**
         * id : 1
         * devicename : 拖拉机
         * deviceunit : 台
         * deviceprice : 10
         * devicenum : 10
         * total : 100
         */

        private Integer id;
        private String devicename;
        private String deviceunit;
        private Float deviceprice;
        private Integer devicenum;
        private Float totalprice;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getDevicename() {
            return devicename;
        }

        public void setDevicename(String devicename) {
            this.devicename = devicename;
        }

        public String getDeviceunit() {
            return deviceunit;
        }

        public void setDeviceunit(String deviceunit) {
            this.deviceunit = deviceunit;
        }

        public Float getDeviceprice() {
            return deviceprice;
        }

        public void setDeviceprice(Float deviceprice) {
            this.deviceprice = deviceprice;
        }

        public Integer getDevicenum() {
            return devicenum;
        }

        public void setDevicenum(Integer devicenum) {
            this.devicenum = devicenum;
        }

        public Float getTotalprice() {
            return totalprice;
        }

        public void setTotal(Float totalprice) {
            this.totalprice = totalprice;
        }
    }
    public static class DocsBean {
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
    /*private String companyName;
    private String tel;
    private String linkMan;
    private String cardNum;
    private String address;
    private String fax;
    private String rentDate;
    private String backDate;
    private Integer totalDate;
    private List<DeviceArrayBean> deviceArray;
    private List<DocsBean> fileArray;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public String getBackDate() {
        return backDate;
    }

    public void setBackDate(String backDate) {
        this.backDate = backDate;
    }

    public Integer getTotalDate() {
        return totalDate;
    }

    public void setTotalDate(Integer totalDate) {
        this.totalDate = totalDate;
    }

    public List<DeviceArrayBean> getDeviceArray() {
        return deviceArray;
    }

    public void setDeviceArray(List<DeviceArrayBean> deviceArray) {
        this.deviceArray = deviceArray;
    }

    public List<DocsBean> getFileArray() {
        return fileArray;
    }

    public void setFileArray(List<DocsBean> fileArray) {
        this.fileArray = fileArray;
    }

    public static class DeviceArrayBean {
        *//**
         * id : 1
         * name : 拖拉机
         * unit : 辆
         * price : 150
         * num : 1
         * total : 150
         *//*

        private Integer id;
        private String name;
        private String unit;
        private Float price;
        private Integer num;
        private Float totalprice;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public Float getPrice() {
            return price;
        }

        public void setPrice(Float price) {
            this.price = price;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }

        public Float getTotalprice() {

            return totalprice;
        }

        public void setTotalprice(Float totalprice) {
            this.totalprice = totalprice;
        }
    }

    public static class DocsBean {
        private String sourcename;
        private String newname;

        public String getSourcename() {
            return sourcename;
        }

        public void setSourceName(String sourcename) {
            this.sourcename = sourcename;
        }

        public String getNewname() {
            return newname;
        }

        public void setNewname(String newname) {
            this.newname = newname;
        }
    }*/
}
