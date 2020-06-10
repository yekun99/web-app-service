package com.app.commtaskmanage.service;

import com.core.data.model.DataModel;

import java.util.List;

/**
 * @author dechun.yuan
 * @version 1.0
 */
public interface CommTaskHisService {

    void saveCommTaskHis(DataModel saveModel);


    List<DataModel> queryCommTaskHis(DataModel queryModel);
}
