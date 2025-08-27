package org.example.elec5619_practical03_group_7.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDatetime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDatetime;

    /**
     * 出生
     */
    private Date birth;

    /**
     * 大学
     */
    private String college;

    /**
     * 年级
     */
    private String grade;

    /**
     * 名字
     */
    private String name;

    private String password;

    /**
     * 角色id
     */
    private Integer roleId;

    private String sex;

    private String username;

    private String mail;

    private String mailCode;

    /**
     * 文化水平
     */
    private String cultureLevel;

    /**
     * 国家
     */
    private String nation;

    /**
     * 籍贯
     */
    private String nativePlace;

    /**
     * 目前地址
     */
    private String presentAddress;

    /**
     * 工作地方
     */
    private String workPlace;

    /**
     * 照片
     */
    private String photo;

    /**
     * 真正年龄
     */
    private Integer realAge;



}

