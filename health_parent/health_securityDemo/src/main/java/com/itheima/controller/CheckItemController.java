package com.itheima.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkItem")
public class CheckItemController {
    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('新增检查项')")
    public String add(){return "add";}
    @RequestMapping("/del")
    @PreAuthorize("hasAuthority('删除检查项')")
    public String del(){return "del";}
}
