package com.app.commtaskmanage.service;

import com.core.data.model.DataModel;

import java.util.List;

/**
 * @author dechun.yuan
 * @version 1.0
 */
public interface CommTaskService {

    void saveCommTask(DataModel saveModel);

    List<DataModel> queryCommTask(DataModel queryModel);

    void deleteCommTask(DataModel deleteModel);

    void updateCommTask(DataModel updateModel);
}
