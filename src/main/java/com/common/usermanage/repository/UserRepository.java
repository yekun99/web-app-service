package com.common.usermanage.repository;

import com.core.data.model.DataModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserRepository {

    void saveUser(DataModel saveModel);

    DataModel findUser(DataModel queryModel);

    List<DataModel> findUserList(DataModel queryModel);

    void deleteUser(DataModel deleteModel);

    void updateUser(DataModel updateModel);
}
