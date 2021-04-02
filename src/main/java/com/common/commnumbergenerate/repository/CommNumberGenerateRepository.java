package com.common.commnumbergenerate.repository;

import com.core.data.model.DataModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author spring.yuan
 * @version 1.0
 */
@Repository
@Mapper
public interface CommNumberGenerateRepository {

    void saveCommNumberGenerate(DataModel saveModel);

    DataModel queryCommNumberGenerate(DataModel queryModel);

    void updateCommNumberGenerate(DataModel updateModel);
}
