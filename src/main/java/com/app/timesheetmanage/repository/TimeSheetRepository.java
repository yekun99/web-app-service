package com.app.timesheetmanage.repository;

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
public interface TimeSheetRepository {

    void saveTimeSheet(DataModel saveModel);

    List<DataModel> queryTimeSheetList(DataModel queryModel);

    DataModel queryTimeSheetById(DataModel queryModel);

    void deleteTimeSheet(DataModel deleteModel);

    void updateTimeSheet(DataModel updateModel);
}
