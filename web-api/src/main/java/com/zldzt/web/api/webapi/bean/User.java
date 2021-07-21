package com.zldzt.web.api.webapi.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class User implements Serializable {


    private static final long serialVersionUID = 1L;

    private Long id;

    private String uuid;

    private String userName;

    private String phone;

    private LocalDateTime addTime;

    private LocalDateTime updateTime;

    private String demo;

}