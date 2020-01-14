package com.xu.springbootdemo.authorize.controller;

import com.xu.springbootdemo.authorize.domain.Permission;
import com.xu.springbootdemo.authorize.service.AuthorizeService;
import com.xu.springbootdemo.domain.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/authorize")
public class AuthorizeControler {

    @Autowired
    AuthorizeService authorizeServiceImpl;

    @ResponseBody
    @PostMapping("/getInitCheckBox")
    public HashMap<String, Object> getInitCheckBox(){
        HashMap<String,Object> res = new HashMap<>();
        HashSet<String> set = new HashSet<>();

        LinkedList<Permission> all = authorizeServiceImpl.getAllPermissions();
        for(Permission item : all){
            if(!set.contains(item.getPermissionGroup())){
                set.add(item.getPermissionGroup());
            }
        }

        HashMap<String, LinkedList<Permission>> map = new HashMap<>();
        for(String item : set){
            LinkedList<Permission> list = new LinkedList<>();
            for(Permission items :all){
                if(item.equals(items.getPermissionGroup())){
                    list.add(items);
                }
            }
            map.put(item,list);
        }
        res.put("code","0");
        res.put("msg","ok");
        res.put("data",map);
        return res;
    }


    @PostMapping("/getUserAuthorize")
    @ResponseBody
    public HashMap<String,Object> getUserAuthorize(){
        HashMap<String, Object> map = new HashMap<>();
        HashSet<String> set = new HashSet();
        User subject = (User) SecurityUtils.getSubject().getPrincipal();
        String userName = subject.getUserName();
        List<Permission> permissionList = authorizeServiceImpl.getUserAuthorize(subject.getId());
        for(Permission item : permissionList){
            if(!set.contains(item.getDesc())){
                set.add(item.getPermissionGroup());
            }
        }

        for(String s :set){
            LinkedList<Integer> tempList= new LinkedList<>();
            for(Permission item: permissionList){
                if(s.equals(item.getPermissionGroup())){
                    tempList.add(item.getPermissionId());
                }
            }
            map.put(s,tempList);
        }
        map.put("code",0);
        map.put("msg","ok");
        return map;
    }

    @PostMapping("/getUserAuthorizeDetail")
    @ResponseBody
    public HashMap<String,Object> getUserAuthorizeDetail(){
        HashMap<String, Object> map = new HashMap<>();
        HashSet<String> set = new HashSet();
        User subject = (User) SecurityUtils.getSubject().getPrincipal();
        String userName = subject.getUserName();
        List<Permission> permissionList = authorizeServiceImpl.getUserAuthorize(subject.getId());
        for(Permission item : permissionList){
            if(!set.contains(item.getDesc())){
                set.add(item.getPermissionGroup());
            }
        }

        for(String s :set){
            LinkedList<Permission> tempList= new LinkedList<>();
            for(Permission item: permissionList){
                if(s.equals(item.getPermissionGroup())){
                    tempList.add(item);
                }
            }
            map.put(s,tempList);
        }
        return map;
    }

    @PostMapping("updataAuthroizeOperation")
    @ResponseBody
    public Integer updataAuthroizeOperation(@RequestBody HashMap map){
        int res = authorizeServiceImpl.updataAuthroizeOperation(map);
        return res;
    }
}
