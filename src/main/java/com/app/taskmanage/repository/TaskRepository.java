package com.app.taskmanage.repository;

import com.core.data.model.DataModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author spring
 * @version 1.0
 */
@Repository
@Mapper
public interface TaskRepository {

    void saveCommTask(DataModel saveModel);

    List<DataModel> queryCommTask(DataModel queryModel);

    void deleteCommTask(DataModel deleteModel);

    void updateCommTask(DataModel updateModel);
}
