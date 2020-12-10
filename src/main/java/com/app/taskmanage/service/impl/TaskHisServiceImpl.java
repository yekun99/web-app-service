package com.app.taskmanage.service.impl;

import com.app.taskmanage.repository.TaskHisRepository;
import com.app.taskmanage.service.TaskHisService;
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
public class TaskHisServiceImpl implements TaskHisService {

    @Autowired
    private TaskHisRepository taskHisRepository;


    /**
     * save comm_task_his
     *
     * @param saveModel
     */
    @Override
    public void saveCommTaskHis(DataModel saveModel) {
        taskHisRepository.saveCommTaskHis(saveModel);
    }


    /**
     * query comm_task_his
     *
     * @param queryModel
     * @return
     */
    @Override
    public List<DataModel> queryCommTaskHis(DataModel queryModel) {
        List<DataModel> result = taskHisRepository.queryCommTaskHis(queryModel);
        result.stream().forEach(taskHis -> {
            taskHis.setFieldValue("insertDate", DateUtils.dateFormat((Date) taskHis.getFieldValue("insertDate"), "yyyy-MM-dd"));
            taskHis.setFieldValue("timestamp", DateUtils.dateFormat((Date) taskHis.getFieldValue("timestamp"), "yyyy-MM-dd HH:mm:ss"));
        });
        return result;
    }
}
