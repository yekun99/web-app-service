package com.app.commtaskmanage.service.impl;

import com.app.commtaskmanage.repository.CommTaskHisRepository;
import com.app.commtaskmanage.service.CommTaskHisService;
import com.core.data.model.DataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dechun.yuan
 * @version 1.0
 */
@Service
public class CommTaskHisServiceImpl implements CommTaskHisService {

    @Autowired
    private CommTaskHisRepository commTaskHisRepository;

    /**
     * save comm_task_his
     * @param saveModel
     */
    @Override
    public void saveCommTaskHis(DataModel saveModel) {
        commTaskHisRepository.saveCommTaskHis(saveModel);
    }


    /**
     * query comm_task_his
     * @param queryModel
     * @return
     */
    @Override
    public List<DataModel> queryCommTaskHis(DataModel queryModel) {
        return commTaskHisRepository.queryCommTaskHis(queryModel);
    }
}
