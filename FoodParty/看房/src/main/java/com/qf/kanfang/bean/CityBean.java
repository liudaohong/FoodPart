package com.qf.kanfang.bean;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/1/17.
 */
public class CityBean {

    /**
     * cityid : 44
     * cityalias : anshan
     * cityname : 鞍山
     * comparename : 鞍山市
     * center_x : 41.11796524
     * center_y : 123.0302855
     * lng : 0.25229
     * lat : 0.27025
     * mobiletype : 0
     * citypinyin : an shan
     * esfalias : anshan
     * xiaomarent : 0
     * xiaomazhuangxiu : 0
     * xiaomaurl :
     */
    //首字母
    private String firstLetter;
    //分类id
    private int letterId;
    private String cityid;
    private String cityalias;
    private String cityname;
    private String comparename;
    private String center_x;
    private String center_y;
    private String lng;
    private String lat;
    private String mobiletype;
    private String citypinyin;
    private String esfalias;
    private int xiaomarent;
    private int xiaomazhuangxiu;
    private String xiaomaurl;

    public CityBean() {

    }

    /**
     * @param letter 分类字母，即首字母
     * @param id     分类id
     * @param json   具体数据的JSON
     */
    //提供一个带参数的构造方法
    public CityBean(String letter, int id, JSONObject json) {
        firstLetter = letter;
        letterId = id;
        cityid = json.optString("cityid");
        cityalias = json.optString("cityalias");
        cityname = json.optString("cityname");
        comparename = json.optString("comparename");
        center_x = json.optString("center_x");
        center_y = json.optString("center_y");
        lng = json.optString("lng");
        lat = json.optString("lat");
        mobiletype = json.optString("mobiletype");
        citypinyin = json.optString("citypinyin");
        esfalias = json.optString("esfalias");
        xiaomarent = json.optInt("xiaomarent");
        xiaomazhuangxiu = json.optInt("xiaomazhuangxiu");
        xiaomaurl = json.optString("xiaomaurl");

    }


    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public int getLetterId() {
        return letterId;
    }

    public void setLetterId(int letterId) {
        this.letterId = letterId;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public void setCityalias(String cityalias) {
        this.cityalias = cityalias;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public void setComparename(String comparename) {
        this.comparename = comparename;
    }

    public void setCenter_x(String center_x) {
        this.center_x = center_x;
    }

    public void setCenter_y(String center_y) {
        this.center_y = center_y;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setMobiletype(String mobiletype) {
        this.mobiletype = mobiletype;
    }

    public void setCitypinyin(String citypinyin) {
        this.citypinyin = citypinyin;
    }

    public void setEsfalias(String esfalias) {
        this.esfalias = esfalias;
    }

    public void setXiaomarent(int xiaomarent) {
        this.xiaomarent = xiaomarent;
    }

    public void setXiaomazhuangxiu(int xiaomazhuangxiu) {
        this.xiaomazhuangxiu = xiaomazhuangxiu;
    }

    public void setXiaomaurl(String xiaomaurl) {
        this.xiaomaurl = xiaomaurl;
    }

    public String getCityid() {
        return cityid;
    }

    public String getCityalias() {
        return cityalias;
    }

    public String getCityname() {
        return cityname;
    }

    public String getComparename() {
        return comparename;
    }

    public String getCenter_x() {
        return center_x;
    }

    public String getCenter_y() {
        return center_y;
    }

    public String getLng() {
        return lng;
    }

    public String getLat() {
        return lat;
    }

    public String getMobiletype() {
        return mobiletype;
    }

    public String getCitypinyin() {
        return citypinyin;
    }

    public String getEsfalias() {
        return esfalias;
    }

    public int getXiaomarent() {
        return xiaomarent;
    }

    public int getXiaomazhuangxiu() {
        return xiaomazhuangxiu;
    }

    public String getXiaomaurl() {
        return xiaomaurl;
    }
}
