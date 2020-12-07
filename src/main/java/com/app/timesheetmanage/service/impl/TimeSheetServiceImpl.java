package com.app.timesheetmanage.service.impl;

import com.app.timesheetmanage.repository.TimeSheetRepository;
import com.app.timesheetmanage.service.TimeSheetService;
import com.core.data.model.DataModel;
import com.core.exception.ExceptionHelper;
import com.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dechun.yuan
 * @version 1.0
 */
@Service
public class TimeSheetServiceImpl implements TimeSheetService {

    @Autowired
    private TimeSheetRepository timeSheetRepository;


    @Override
    public void saveTimeSheet(DataModel saveModel) {
        //validate model
        this.validateSaveOrUpdateTimeSheet(saveModel);
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
        //update data
        timeSheetRepository.updateTimeSheet(updateModel);
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
