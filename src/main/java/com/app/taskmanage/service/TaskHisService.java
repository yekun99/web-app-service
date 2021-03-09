package com.app.taskmanage.service;

import com.core.data.model.DataModel;

import java.util.List;

/**
 * @author spring.yuan
 * @version 1.0
 */
public interface TaskHisService {

    void saveCommTaskHis(DataModel saveModel);

    List<DataModel> queryCommTaskHis(DataModel queryModel);
}
