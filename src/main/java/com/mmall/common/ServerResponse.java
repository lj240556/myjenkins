package com.mmall.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * Created by lj
 */

//如果该类中有属性为NULL则不参与序列化
@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
//保证序列化json的时候,如果是null的对象,key也会消失
//服务端响应对象封装
//泛型类  使得该ServerResponse类可以容纳任何类型的参数
//T 表示要封装的是什么数据类型
public class ServerResponse<T> implements Serializable {
//服务器响应对象
//注意只所以生成这样一个对象，是为了用SpringMVc的插件，直接将属性化成json对象
//包括状态，字符串类型的信息，还有一种任意类型的数据。
    private int status;
    private String msg;
    private T data;

    private ServerResponse(int status){
        this.status = status;
    }
    private ServerResponse(int status,T data){
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status,String msg,T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private ServerResponse(int status,String msg){
        this.status = status;
        this.msg = msg;
    }

    @JsonIgnore
    //使之不在json序列化结果当中
    public boolean isSuccess(){
        // this.status==0
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public int getStatus(){

        return status;
    }

    public T getData(){

        return data;
    }

    public String getMsg(){

        return msg;
    }
     //返回值是一个泛型  T只是一个类型  int类型 可以返回任何类型
    //ServerResponse<T> 中的T只是用来表明 类中的泛型
    //通过成功来创建，即前面的已经成功了，但不放回任何数据，只返回一个状态码，比如登录成功的时候
    // 方法返回值前的<T>的作用是告诉编译器，当前的方法的值传入类型可以和类初始化的泛型类不同，
     // 也就是该方法的泛型类可以自定义，不需要跟类初始化的泛型类相同
    public static  <T>  ServerResponse<T> createBySuccess()
    {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }
    //成功，但返回一个文本或者一个字符串和状态码给前端，
    public static <T>   ServerResponse<T> createBySuccessMessage(String msg){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }
    //成功响应，返回一个成功状态码和一个泛型数据 如查询商品数据
    public static <T> ServerResponse<T> createBySuccess(T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }
    //成功响应，返回一个成功状态码和一个泛型数据 以及一个字符串 如查询商品数据
    public static <T> ServerResponse<T> createBySuccess(String msg,T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

   //失败响应，返回一个错误代码，错误描述
    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    //失败响应，返回一个错误代码，自定义的错误描述
    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }
    //失败响应，返回一个自定义的错误代码，自定义的错误描述
    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode,String errorMessage){
        return new ServerResponse<T>(errorCode,errorMessage);
    }













}
