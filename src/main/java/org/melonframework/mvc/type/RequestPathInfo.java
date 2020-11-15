package org.melonframework.mvc.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author czm 2020/11/8.
 *
 * 存储http请求路径和请求方法
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPathInfo {

    //http请求方法
    private String httpMethod;
    //http请求路径
    private String httpPath;

}
