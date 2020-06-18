package com.app.commtaskmanage.service.impl;

import com.app.commtaskmanage.repository.CommTaskHisRepository;
import com.app.commtaskmanage.service.CommTaskHisService;
import com.core.data.model.DataModel;
import com.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
     *
     * @param saveModel
     */
    @Override
    public void saveCommTaskHis(DataModel saveModel) {
        commTaskHisRepository.saveCommTaskHis(saveModel);
    }


    /**
     * query comm_task_his
     *
     * @param queryModel
     * @return
     */
    @Override
    public List<DataModel> queryCommTaskHis(DataModel queryModel) {
        List<DataModel> result = commTaskHisRepository.queryCommTaskHis(queryModel);
        result.stream().forEach(taskHis -> taskHis.setFieldValue("submissionDate", DateUtils.dateFormat((Date) taskHis.getFieldValue("submissionDate"), "yyyy-MM-dd")));
        return result;
    }
}
