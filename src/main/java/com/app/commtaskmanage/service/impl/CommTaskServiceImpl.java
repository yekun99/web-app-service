package com.app.commtaskmanage.service.impl;

import com.app.commtaskmanage.enums.TaskStatusEnum;
import com.app.commtaskmanage.repository.CommTaskRepository;
import com.app.commtaskmanage.service.CommTaskHisService;
import com.app.commtaskmanage.service.CommTaskService;
import com.common.commnumbergenerate.service.CommNumberGenerateService;
import com.core.data.model.DataModel;
import com.core.exception.ExceptionHelper;
import com.core.utils.DateUtils;
import com.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
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
    private CommTaskHisService commTaskHisService;
    @Autowired
    private CommNumberGenerateService commNumberGenerateService;


    /***
     * save comm_task
     * @param saveModel
     */
    @Override
    public void saveCommTask(DataModel saveModel) {
        //设置当前用户
        saveModel.setFieldValue("createdBy", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        DataModel generateModel = new DataModel();
        generateModel.setFieldValue("numberName", "taskNo");
        //get task_no Serial number
        int taskSerialNumber = 100000 + commNumberGenerateService.generateNumber(generateModel);
        saveModel.setFieldValue("taskNo", "ITSR" + taskSerialNumber);
        saveModel.setFieldValue("status", TaskStatusEnum.NotStart);
        //validate model
        this.validateSaveOrUpdateCommTask(saveModel);
        //save comm_task
        commTaskRepository.saveCommTask(saveModel);
        //save comm_task_his
        commTaskHisService.saveCommTaskHis(saveModel);
    }


    /***
     * query all comm_task
     * @param queryModel
     * @return
     */
    @Override
    public List<DataModel> queryCommTask(DataModel queryModel) {
        List<DataModel> result = commTaskRepository.queryCommTask(queryModel);
        result.stream().forEach(task -> task.setFieldValue("submissionDate", DateUtils.dateFormat((Date) task.getFieldValue("submissionDate"), "yyyy-MM-dd")));
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
        updateModel.setFieldValue("userName", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        //设置更新时间为系统当前时间
        updateModel.setFieldValue("timestamp", LocalDateTime.now());
        //update comm_task
        commTaskRepository.updateCommTask(updateModel);
        //save comm_task_his
        updateModel.setFieldValue("createdBy", updateModel.getFieldValue("userName"));
        commTaskHisService.saveCommTaskHis(updateModel);
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
