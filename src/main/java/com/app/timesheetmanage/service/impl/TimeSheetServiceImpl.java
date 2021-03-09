package com.app.timesheetmanage.service.impl;

import com.app.taskmanage.service.TaskService;
import com.app.timesheetmanage.repository.TimeSheetRepository;
import com.app.timesheetmanage.service.TimeSheetService;
import com.core.data.model.DataModel;
import com.core.exception.ExceptionHelper;
import com.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author spring.yuan
 * @version 1.0
 */
@Service
public class TimeSheetServiceImpl implements TimeSheetService {

    @Autowired
    private TimeSheetRepository timeSheetRepository;
    @Autowired
    private TaskService taskService;


    @Override
    public void saveTimeSheet(DataModel saveModel) {
        //validate model
        this.validateSaveOrUpdateTimeSheet(saveModel);
        //set task info
        this.setTaskInfo(saveModel);
        //set insert timestamp
        saveModel.setFieldValue("insertDate", LocalDateTime.now());
        saveModel.setFieldValue("timestamp", LocalDateTime.now());
        //save data
        timeSheetRepository.saveTimeSheet(saveModel);
    }


    @Override
    public List<DataModel> queryTimeSheetList(DataModel queryModel) {
        return timeSheetRepository.queryTimeSheetList(queryModel);
    }


    @Override
    public DataModel queryTimeSheetById(DataModel queryModel) {
        return timeSheetRepository.queryTimeSheetById(queryModel);
    }


    @Override
    public void deleteTimeSheet(DataModel deleteModel) {
        //delete data
        timeSheetRepository.deleteTimeSheet(deleteModel);
    }


    @Override
    public void updateTimeSheet(DataModel updateModel) {
        //validate update model
        this.validateSaveOrUpdateTimeSheet(updateModel);
        //set update timestamp
        updateModel.setFieldValue("timestamp", LocalDateTime.now());
        //set task info
        this.setTaskInfo(updateModel);
        //update data
        timeSheetRepository.updateTimeSheet(updateModel);
    }


    /**
     * set info
     */
    public void setTaskInfo(DataModel setModel) {
        //set task info
        DataModel queryTask = new DataModel();
        queryTask.setFieldValue("taskNo", setModel.getStringValue("taskNo"));
        List<DataModel> taskList = taskService.queryCommTask(queryTask);
        if (taskList != null && taskList.size() > 0) {
            DataModel taskModel = taskList.get(0);
            setModel.setFieldValue("taskType", taskModel.getStringValue("taskType"));
            setModel.setFieldValue("taskSubject", taskModel.getStringValue("taskSubject"));
            setModel.setFieldValue("taskContent", taskModel.getStringValue("taskContent"));
            setModel.setFieldValue("systemName", taskModel.getStringValue("systemName"));
        }
    }

    /***
     * validate time sheet param
     */
    protected void validateSaveOrUpdateTimeSheet(DataModel taskModel) {
        String validationMsg = "";
        if (StringUtils.isBlank(taskModel.getStringValue("workDay"))) {
            validationMsg = "work_day can not be null!";
        }
        if (StringUtils.isBlank(taskModel.getStringValue("taskNo"))) {
            validationMsg = "task_no can not be null!";
        }
        if (StringUtils.isNotEmpty(validationMsg)) {
            throw ExceptionHelper.getInstance().handleValidationException(validationMsg);
        }
    }
}
