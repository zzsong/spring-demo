package com.zss.springboot.conditional;

import com.zss.springboot.conditional.custom.CustomConfig;
import com.zss.springboot.conditional.custom.service.SystemStorage;
import com.zss.springboot.conditional.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    private StorageService storageService;


    @RequestMapping("conditional/index")
    public String index(){
        StringBuilder builder = new StringBuilder();
        builder.append("service:\t").append(storageService.loadStorage());

        //
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CustomConfig.class);
        String[] names = context.getBeanNamesForType(SystemStorage.class);
        for(String name : names){
            builder.append("\t\t");
            builder.append("system:\t").append(name);
        }
        return builder.toString();
    }
}
