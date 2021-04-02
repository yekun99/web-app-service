package com.common.commnumbergenerate.service;

import com.core.data.model.DataModel;

/**
 * @author spring.yuan
 * @version 1.0
 */
public interface CommNumberGenerateService {

    void saveCommNumberGenerate(DataModel saveModel);

    DataModel queryCommNumberGenerate(DataModel queryModel);

    void updateCommNumberGenerate(DataModel updateModel);

    int generateNumber(DataModel generateModel);
}
