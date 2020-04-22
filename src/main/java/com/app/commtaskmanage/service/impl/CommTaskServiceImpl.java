package com.app.commtaskmanage.service.impl;

import com.app.commtaskmanage.enums.TaskStatusEnum;
import com.app.commtaskmanage.repository.CommTaskRepository;
import com.app.commtaskmanage.service.CommTaskService;
import com.common.commnumbermanager.service.CommNumberService;
import com.core.data.model.DataModel;
import com.core.exception.ExceptionHelper;
import com.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author dechun.yuan
 * @version 1.0
 */
@Service
public class CommTaskServiceImpl implements CommTaskService {

    @Autowired
    private CommTaskRepository commTaskRepository;
    @Autowired
    private CommNumberService commNumberService;

    /***
     * save comm task
     * @param saveModel
     */
    @Override
    public void saveCommTask(DataModel saveModel) {
        saveModel.setFieldValue("numberType", "task_no");
        //get task_no Serial number
        int taskSerialNumber = 100000 + commNumberService.generateNumber(saveModel);
        saveModel.setFieldValue("taskNo", "ITSR" + taskSerialNumber);
        saveModel.setFieldValue("status", TaskStatusEnum.NotStart);
        this.validateSaveOrUpdateCommTask(saveModel);
        commTaskRepository.saveCommTask(saveModel);
    }


    /***
     * query comm task
     * @param queryModel
     * @return
     */
    @Override
    public List<DataModel> queryCommTask(DataModel queryModel) {
        return commTaskRepository.queryCommTask(queryModel);
    }


    /***
     * delete comm task
     * @param deleteModel
     */
    @Override
    public void deleteCommTask(DataModel deleteModel) {
        this.validateSaveOrUpdateCommTask(deleteModel);
        commTaskRepository.deleteCommTask(deleteModel);
    }


    /***
     * update comm task
     * @param updateModel
     */
    @Override
    public void updateCommTask(DataModel updateModel) {
        this.validateSaveOrUpdateCommTask(updateModel);
        //设置更新时间为系统当前时间
        updateModel.setFieldValue("timestamp", LocalDateTime.now());
        commTaskRepository.updateCommTask(updateModel);
    }


    /***
     * validate comm task info
     * @param taskModel
     */
    protected void validateSaveOrUpdateCommTask(DataModel taskModel) {
        String validationMsg = "";
        if (StringUtils.isBlank(taskModel.getStringValue("taskNo"))) {
            validationMsg = "task_no can not be null!";
        }
        if (StringUtils.isNotEmpty(validationMsg)) {
            throw ExceptionHelper.getInstance().handleValidationException(validationMsg);
        }
    }
}
