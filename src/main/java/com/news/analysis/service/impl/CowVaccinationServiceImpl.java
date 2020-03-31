package com.news.analysis.service.impl;

import com.news.analysis.service.CowVaccinationService;
import com.news.analysis.dao.CowVaccinationMapper;
import com.news.analysis.pojo.CowVaccination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CowVaccinationServiceImpl implements CowVaccinationService {

    @Autowired
    CowVaccinationMapper cowVaccinationMapper;

    @Override
    public CowVaccination getRecordByID(int id) {
        return cowVaccinationMapper.getRecordByID(id);
    }

    @Override
    public int createRecord(CowVaccination cowVaccination) {
        return cowVaccinationMapper.createRecord(cowVaccination);
    }

    @Override
    public List<CowVaccination> getRecordList(Map<String, Object> paramsMap) {
        return cowVaccinationMapper.getRecordList(paramsMap);
    }

    @Override
    public int updateRecord(CowVaccination cowVaccination) {
        return cowVaccinationMapper.updateRecord(cowVaccination);
    }
}
