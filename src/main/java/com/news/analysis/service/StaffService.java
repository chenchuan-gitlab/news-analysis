package com.news.analysis.service;

import com.news.analysis.pojo.Staff;
import com.news.analysis.pojo.Role;

import java.util.List;
import java.util.Map;

public interface StaffService {
    int createRole(List<Role> roles);

    List<Role> getRoleList(String role_id);

    int deleteRole(String role_id);

    int createStaff(Staff staff);

    List<Staff> getStaffList(String staff_id);

    int deleteStaff(String staff_id);


    int updateStaffRole(Map<String,Object> params);

    int updateRole(String role_id,List<Role> roles);

    Map<String,Object> staffInfo(String staff_id,String staff_pass);
}
