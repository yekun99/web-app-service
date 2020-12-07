package com.app.timesheetmanage.service;

import com.core.data.model.DataModel;

import java.util.List;

/**
 * @author dechun.yuan
 * @version 1.0
 */
public interface TimeSheetService {

    /**
     * save time sheet info
     */
    void saveTimeSheet(DataModel saveModel);


    /**
     * query time sheet list
     */
    List<DataModel> queryTimeSheetList(DataModel queryModel);


    /**
     * query time sheet by id
     */
    DataModel queryTimeSheetById(DataModel queryModel);


    /**
     * delete time sheet
     */
    void deleteTimeSheet(DataModel deleteModel);


    /***
     * update time sheet
     */
    void updateTimeSheet(DataModel updateModel);
}
