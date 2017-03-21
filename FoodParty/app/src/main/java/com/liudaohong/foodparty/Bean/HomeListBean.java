package com.liudaohong.foodparty.Bean;

/**
 * Created by liuhong on 2017/3/18.
 */

public class HomeListBean {
    private String  name;
    private HomeBean.GroupBean  groupBean;

    public HomeListBean() {
    }

    public HomeListBean(String name, HomeBean.GroupBean groupBean) {
        this.name = name;
        this.groupBean = groupBean;
    }

    @Override
    public String toString() {
        return "HomeListBean{" +
                "name='" + name + '\'' +
                ", groupBean=" + groupBean +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HomeBean.GroupBean getGroupBean() {
        return groupBean;
    }

    public void setGroupBean(HomeBean.GroupBean groupBean) {
        this.groupBean = groupBean;
    }
}
