package com.online.edu.eduservice.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EduException extends RuntimeException{

    private Integer code;//状态码
    private String message;//异常信息

}
