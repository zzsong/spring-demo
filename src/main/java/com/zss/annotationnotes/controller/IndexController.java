package com.zss.annotationnotes.controller;

import com.zss.annotationnotes.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@RestController
public class IndexController {

    /**
     *
     *@Resource 表示按名称获取已知资源。
     * 名称从带注释的 setter 或字段的名称中提取，或者从名称参数中获取。
     *  顺序：
     * Matches by Name
     * Matches by Type
     * Restricts by Qualifiers (ignored if match is found by name)
     *
     * @Inject 或 @Autowired 尝试按类型连接合适的其他组件。
     * 顺序：
     * Matches by Type
     * Restricts by Qualifiers
     * Matches by Name
     *  如果我们想使用按照名称（byName）来装配，可以结合@Qualifier注解一起使用。
     * @Autowired + @Qualifier 仅适用于 spring DI
     * @Qualifier 不支持动态 bean 连接，因为 @Qualifier 不支持占位符
     *
     */

    @Autowired
    @Qualifier("appleServiceIml")
    private FruitService fruitService;

    @Resource(name = "appleServiceIml")
    private FruitService fruitService2;

    @Resource(name = "${fruit.service.name}")
    private FruitService fruitService3;

    @RequestMapping("index")
    public String index(){
        System.out.println(fruitService.fruitName());
        System.out.println(fruitService2.fruitName());
        System.out.println(fruitService3.fruitName());
        return LocalDateTime.now().toString();
    }
}
