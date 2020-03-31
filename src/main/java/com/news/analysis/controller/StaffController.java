package com.news.analysis.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.news.analysis.pojo.Role;
import com.news.analysis.pojo.Staff;
import com.news.analysis.service.StaffService;
import com.news.analysis.utils.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("staff")
public class StaffController {
    @Autowired
    StaffService staffService;

    /**
     * 新增角色
     *
     * @param roles
     * @return
     */
    @RequestMapping(value = "createRole.action", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> createRole(@RequestBody List<Role> roles) {
        String role_id = IDUtil.getRoleID();
        for (Role role : roles) {
            role.setRole_id(role_id);
        }
        int i = staffService.createRole(roles);
        Map<String, String> map = new HashMap<>();
        if (i != 0) {
            map.put("success", "新增成功");
        } else {
            map.put("error", "新增失败");
        }
        return map;
    }

    /**
     * 获取角色
     *
     * @param role_id
     * @return
     */
    @RequestMapping(value = "getRoleList.action", method = RequestMethod.GET)
    @ResponseBody
    public List<Role> getRoleList(String role_id) {
        return staffService.getRoleList(role_id);
    }

    /**
     * 删除角色
     *
     * @param role_id
     * @return
     */
    @RequestMapping(value = "deleteRole", method = RequestMethod.GET)
    public Map<String, String> deleteRole(String role_id) {
        int i = staffService.deleteRole(role_id);
        Map<String, String> map = new HashMap<>();
        if (i != 0) {
            map.put("success", "删除成功");
        } else {
            map.put("error", "删除失败");
        }
        return map;
    }

    /**
     * 新增员工
     *
     * @param staff
     * @return
     */
    @RequestMapping(value = "createStaff.action", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> createStaff(@RequestBody Staff staff) {
        int i = staffService.createStaff(staff);
        Map<String, String> map = new HashMap<>();
        if (i != 0) {
            map.put("success", "新增成功");
        } else {
            map.put("error", "新增失败");
        }
        return map;
    }

    /**
     * 获取员工信息
     * @param staff_id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "getStaffList.action",method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<Staff> getStaffList(@RequestParam(value = "staff_id", required = false) String staff_id,
                                        @RequestParam(value = "pageNum", required = false, defaultValue = "1") String pageNum,
                                        @RequestParam(value = "pageSize", required = false, defaultValue = "10") String pageSize) {
        PageHelper.startPage(Integer.parseInt(pageNum),Integer.parseInt(pageSize));
        List<Staff> staffList = staffService.getStaffList(staff_id);
        PageInfo<Staff> page = new PageInfo<>(staffList);
        return page;
    }

    /**
     * 删除员工
     * @param staff_id
     * @return
     */
    @RequestMapping(value = "deleteStaff.action",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> deleteStaff(String staff_id){
        int i = staffService.deleteStaff(staff_id);
        Map<String, String> map = new HashMap<>();
        if (i != 0) {
            map.put("success", "删除成功");
        } else {
            map.put("error", "删除失败");
        }
        return map;
    }


    /**
     * 修改角色信息
     * @param requestParam
     * @return
     */
    @RequestMapping(value = "updateRole.action",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> updateRole(@RequestBody Map<String,Object> requestParam){
        String role_id = (String)requestParam.get("role_id");
        List<Role> roles = JSONObject.parseArray(JSONObject.toJSONString(requestParam.get("roles")),Role.class);
        int i = staffService.updateRole(role_id,roles);
        Map<String, String> map = new HashMap<>();
        if (i != 0) {
            map.put("success", "修改成功");
        } else {
            map.put("error", "修改失败");
        }
        return map;
    }

    /**
     * 登陆
     * @param staff_id
     * @param staff_pass
     * @return
     */
    @RequestMapping(value = "login.action",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> login(@RequestParam("staff_id") String staff_id,@RequestParam("staff_pass") String staff_pass){
        Map<String,Object> staffInfo = staffService.staffInfo(staff_id,staff_pass);
        return staffInfo;
    }

}
