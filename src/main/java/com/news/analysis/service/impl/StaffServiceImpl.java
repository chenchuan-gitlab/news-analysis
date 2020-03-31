package com.news.analysis.service.impl;

import com.news.analysis.dao.StaffMapper;
import com.news.analysis.pojo.Staff;
import com.news.analysis.service.StaffService;
import com.news.analysis.pojo.Menu;
import com.news.analysis.pojo.Power;
import com.news.analysis.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    StaffMapper staffMapper;

    @Override
    public int createRole(List<Role> roles) {
        return staffMapper.createRole(roles);
    }

    @Override
    public List<Role> getRoleList(String role_id) {
        return staffMapper.getRoleList(role_id);
    }

    @Override
    public int deleteRole(String role_id) {
        return staffMapper.deleteRole(role_id);
    }

    @Override
    public int createStaff(Staff staff) {
        return staffMapper.createStaff(staff);
    }

    @Override
    public List<Staff> getStaffList(String staff_id) {
        return staffMapper.getStaffList(staff_id);
    }

    @Override
    public int deleteStaff(String staff_id) {
        return staffMapper.deleteStaff(staff_id);
    }

    @Override
    public int updateStaffRole(Map<String, Object> params) {
        return staffMapper.updateStaffRole(params);
    }

    @Override
    @Transactional
    public int updateRole(String role_id, List<Role> roles) {
        int i = staffMapper.deleteRole(role_id);
        if (i != 0) {
            i = staffMapper.createRole(roles);
        }
        return i;
    }

    @Override
    public Map<String, Object> staffInfo(String staff_id, String staff_pass) {
        Map<String, Object> staffInformation = null;
        Staff staff = staffMapper.getStaffByIDAndPass(staff_id, staff_pass);
        List<Role> roleList;
        List<Power> powerList;
        List<Power> rolePowerList;
        List<Menu> menuList;
        List<Integer> powerIDList = new ArrayList<>();
        if (staff != null) {
            staffInformation = new HashMap<>();
            roleList = staffMapper.getRoleList(staff.getRole_id());
            for (Role role:roleList){
                powerIDList.add(role.getPower_id());
            }
            rolePowerList = staffMapper.getRolePowerList(powerIDList);
            powerList = staffMapper.getPowerList();
            menuList = staffMapper.getMenuList();
            staffInformation.put("staff",staff);
            staffInformation.put("roleName",roleList.get(0).getRole_name());
            staffInformation.put("rolePowerList",rolePowerList);
            staffInformation.put("powerList",powerList);
            staffInformation.put("menuList",menuList);
        }
        return staffInformation;
    }
}
