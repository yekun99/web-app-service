package com.app.taskmanage.service;

import com.core.data.model.DataModel;

import java.util.List;

/**
 * @author spring
 * @version 1.0
 */
public interface TaskService {

    void saveCommTask(DataModel saveModel);

    List<DataModel> queryCommTask(DataModel queryModel);

    void deleteCommTask(DataModel deleteModel);

    void updateCommTask(DataModel updateModel);
}
