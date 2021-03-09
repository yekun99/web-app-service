package com.app.taskmanage.repository;

import com.core.data.model.DataModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author spring.yuan
 * @version 1.0
 */
@Repository
@Mapper
public interface TaskHisRepository {

    void saveCommTaskHis(DataModel saveModel);

    List<DataModel> queryCommTaskHis(DataModel queryModel);

}
