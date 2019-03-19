package com.lin.core.shop.bean.request;

import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Created by yuanbin.lin on 2018/11/19.
 */
public class FileRequest {
    private int age;
    private MultipartHttpServletRequest fileList;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public MultipartHttpServletRequest getRequest() {
        return fileList;
    }

    public void setRequest(MultipartHttpServletRequest request) {
        this.fileList = request;
    }
}
