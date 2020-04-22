package com.app.commtaskmanage.repository;

import com.core.data.model.DataModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author dechun.yuan
 * @version 1.0
 */
@Mapper
public interface CommTaskRepository {

    void saveCommTask(DataModel saveModel);

    List<DataModel> queryCommTask(DataModel queryModel);

    void deleteCommTask(DataModel deleteModel);

    void updateCommTask(DataModel updateModel);
}
