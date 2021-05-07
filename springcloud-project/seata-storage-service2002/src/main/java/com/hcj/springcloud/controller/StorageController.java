package com.hcj.springcloud.controller;

import com.hcj.springcloud.domain.CommonResult;
import com.hcj.springcloud.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/storage/")
public class StorageController {
    @Autowired(required = false)
    private StorageService storageService;

    /**
     * 扣减库存
     */
    @PostMapping(value = "decrease")
    @ResponseBody
    public CommonResult decrease(Long productId, Integer count) {
        System.out.println(productId +":" + count);
        storageService.decrease(productId, count);
        return new CommonResult(200,"扣减库存成功！");
    }

}
