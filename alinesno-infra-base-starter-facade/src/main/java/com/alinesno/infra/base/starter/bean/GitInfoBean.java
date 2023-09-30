package com.alinesno.infra.base.starter.bean;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class GitInfoBean {

    private String type ;
    private String code ;

    private String clientId ;
    private String clientSecrets ;
    private String authUrl ;
    private String redirectUrl ;
    private String userInfo ;

}
