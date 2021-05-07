package com.hcj.springcloud.controller;


import com.hcj.springcloud.entities.CommonResult;
import com.hcj.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/consumer/")
@Slf4j
public class OrderController {
    //刚刚注入spring容器中的RestTemplate对象，现在来实例化它。
    @Autowired(required = false)
    RestTemplate restTemplate;

    public static final String  PAYMENT_URL="http://CLOUD-PROVIDER-SERVICE";//支付模块的调用地址

    @GetMapping(value = "payment/create") //对于客户端来说，只需要去读请求服务端后服务端返回的结果即可，
    // 因此对客户端来说是读操作，用GET
    @ResponseBody
    //这里因为客户要的只是commonresult返回的结果，不知道payment是啥（只需要去提交payment的数据给服务端就好）
    // 因此我们写的时候要照顾到这一点，加了个Payment泛型，让开发人员好辨别这个客户要的是指定payment类型的CommonResult的返回结果
    public CommonResult<Payment> create(Payment payment){
        //                  因为该请求在服务端是对数据库写操作，因此用的是postForObject方法,
        //                  远程调用的接口地址,
        //                  传递的参数,
        //                  返回的参数从restTemplate类型转换到该方法需要的CommonResult类型

        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);

    }

    @GetMapping("getPaymentById/{id}")//客户端要的是读服务端返回的指定Payment类型的CommonResult，因此也是对数据的读操作，用GET
    @ResponseBody
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        //                  因为服务端要执行的应该是对数据库的读操作，读该id对应的payment，因此发的是getForObject
        return restTemplate.getForObject(PAYMENT_URL+"/payment/getPaymentById/get/"+id,CommonResult.class);
    }
    @GetMapping("getPaymentById2/{id}")
    @ResponseBody
    public CommonResult<Payment> getPaymentById2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> res = restTemplate.getForEntity(
                PAYMENT_URL + "/payment/getPaymentById/get/" + id, CommonResult.class);
        if(res.getStatusCode().is2xxSuccessful()){//2xx 即成功

            return res.getBody();//获得具体请求体
        }else{//其他状态码 即失败
            return new CommonResult<>(444,"请求失败");
        }

    }
    @GetMapping("/payment/zipkin")
    @ResponseBody
    public String paymentZipkin()
    {
        String result = restTemplate.getForObject(PAYMENT_URL+"/payment/zipkin/", String.class);
        return result;
    }

}
