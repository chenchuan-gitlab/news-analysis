package com.news.analysis.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.news.analysis.service.ProductInfoService;
import com.news.analysis.pojo.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 11111
 */
@Controller
@RequestMapping("productInfo")
public class ProductInfoController {
    @Autowired
    ProductInfoService productInfoService;

    @RequestMapping(value = "getRecordByID.action", method = RequestMethod.GET)
    @ResponseBody
    public ProductInfo getRecordByID(@RequestParam("id") int id) {
        return productInfoService.getRecordByID(id);
    }

    @RequestMapping(value = "createRecord.action", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> createRecord(@RequestBody ProductInfo productInfo) {
        int i = productInfoService.createRecord(productInfo);
        Map<String, String> map = new HashMap<>();
        if (i != 0) {
            map.put("msg", "新增记录成功");
        } else {
            map.put("msg", "新增记录失败");
        }
        return map;
    }

    @RequestMapping(value = "getRecordList.action", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<ProductInfo> getRecordList(@RequestParam(value = "product_name",required = false,defaultValue = "") String product_name,
                                               @RequestParam(value = "pageNum", required = false, defaultValue = "1") String pageNum,
                                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") String pageSize) {
        if ("".equals(product_name)) {
            product_name = null;
        }
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("product_name", product_name);
        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        List<ProductInfo> recordList = productInfoService.getRecordList(paramsMap);
        PageInfo<ProductInfo> page = new PageInfo<>(recordList);
        return page;
    }


    @RequestMapping(value = "updateRecord.action", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> updateRecord(@RequestBody ProductInfo productInfo) {
        int i = productInfoService.updateRecord(productInfo);
        Map<String, String> map = new HashMap<>();
        if (i != 0) {
            map.put("msg", "新增记录成功");
        } else {
            map.put("msg", "新增记录失败");
        }
        return map;
    }


    @RequestMapping(value = "fileUpload.action")
    @ResponseBody
    public Map<String, String> fileUpload(MultipartFile file, HttpServletRequest request) throws IOException {
        String filePath = request.getSession().getServletContext().getRealPath("upload");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String currentTime = dateFormat.format(new Date()).toString();
        String originalFilename = file.getOriginalFilename();
        String newFilename = currentTime + originalFilename;
        File dir = new File(filePath, newFilename);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        file.transferTo(dir);
        Map<String, String> map = new HashMap<>();
        map.put("img_url", "upload/" + newFilename);
        return map;
    }

}
