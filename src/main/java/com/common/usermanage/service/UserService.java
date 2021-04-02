package com.common.usermanage.service;

import com.core.data.model.DataModel;

import java.util.List;

public interface UserService {

    void saveUser(DataModel saveModel);

    DataModel findUser(DataModel queryModel);

    List<DataModel> findUserList(DataModel queryModel);

    void updateUser(DataModel updateModel);

    void deleteUser(DataModel deleteModel);
}
