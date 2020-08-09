package com.data.collection.sys.web;

import com.data.collection.sys.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collection/sys")
public class RoleController {

    @Autowired
    private SystemService systemService;


}
