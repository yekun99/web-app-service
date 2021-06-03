package com.app.taskmanage.service.impl;

import com.app.taskmanage.enums.TaskStatusEnum;
import com.app.taskmanage.repository.TaskRepository;
import com.app.taskmanage.service.TaskHisService;
import com.app.taskmanage.service.TaskService;
import com.common.commnumbergenerate.service.CommNumberGenerateService;
import com.core.data.model.DataModel;
import com.core.exception.ExceptionHelper;
import com.core.security.SecurityUtils;
import com.core.utils.DateUtils;
import com.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author spring.yuan
 * @version 1.0
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository commTaskRepository;
    @Autowired
    private TaskHisService taskHisService;
    @Autowired
    private CommNumberGenerateService commNumberGenerateService;


    /***
     * save comm_task
     * @param saveModel
     */
    @Override
    public void saveCommTask(DataModel saveModel) {
        //设置当前用户
        saveModel.setFieldValue("createdBy", SecurityUtils.getCurUsername());
        DataModel generateModel = new DataModel();
        generateModel.setFieldValue("numberName", "taskNo");
        //get task_no Serial number
        int taskSerialNumber = 100000 + commNumberGenerateService.generateNumber(generateModel);
        saveModel.setFieldValue("taskNo", "ITSR" + taskSerialNumber);
        saveModel.setFieldValue("status", TaskStatusEnum.NotStart);
        //validate model
        this.validateSaveOrUpdateCommTask(saveModel);
        //set timestamp
        saveModel.setFieldValue("insertTime", LocalDateTime.now());
        saveModel.setFieldValue("timestamp", LocalDateTime.now());
        //save pms_task
        commTaskRepository.saveCommTask(saveModel);
        //save pms_task_his
        taskHisService.saveCommTaskHis(saveModel);
    }


    /***
     * query all comm_task
     * @param queryModel
     * @return
     */
    @Override
    public List<DataModel> queryCommTask(DataModel queryModel) {
        List<DataModel> result = commTaskRepository.queryCommTask(queryModel);
        result.stream().forEach(task -> task.setFieldValue("insertTime", DateUtils.dateFormat((Date) task.getFieldValue("insertTime"), "yyyy-MM-dd")));
        return result;
    }


    /***
     * delete comm_task
     * @param deleteModel
     */
    @Override
    public void deleteCommTask(DataModel deleteModel) {
        this.validateSaveOrUpdateCommTask(deleteModel);
        commTaskRepository.deleteCommTask(deleteModel);
    }


    /***
     * update comm_task
     * @param updateModel
     */
    @Override
    public void updateCommTask(DataModel updateModel) {
        this.validateSaveOrUpdateCommTask(updateModel);
        //设置当前用户名
        updateModel.setFieldValue("userName", SecurityUtils.getCurUsername());
        //设置更新时间为系统当前时间
        updateModel.setFieldValue("timestamp", LocalDateTime.now());
        //update comm_task
        commTaskRepository.updateCommTask(updateModel);
        //save comm_task_his
        updateModel.setFieldValue("createdBy", updateModel.getFieldValue("userName"));
        taskHisService.saveCommTaskHis(updateModel);
    }


    /***
     * validate comm_task
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
