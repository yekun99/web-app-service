package com.common.commnumbergenerate.service.impl;

import com.common.commnumbergenerate.repository.CommNumberGenerateRepository;
import com.common.commnumbergenerate.service.CommNumberGenerateService;
import com.core.data.model.DataModel;
import com.core.exception.ExceptionHelper;
import com.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * @author spring.yuan
 * @version 1.0
 */
@Service
public class CommNumberGenerateServiceImpl implements CommNumberGenerateService {

    @Autowired
    private CommNumberGenerateRepository commNumberGenerateRepository;


    /***
     * save comm_number_generate
     * @param saveModel
     */
    @Override
    public void saveCommNumberGenerate(DataModel saveModel) {
        this.validateSaveOrUpdateNumberGenerate(saveModel);
        //set insert timestamp
        saveModel.setFieldValue("insertTime", LocalDateTime.now());
        saveModel.setFieldValue("timestamp", LocalDateTime.now());
        commNumberGenerateRepository.saveCommNumberGenerate(saveModel);
    }


    /***
     * query comm_number_generate
     * @param queryModel
     * @return
     */
    @Override
    public DataModel queryCommNumberGenerate(DataModel queryModel) {
        return commNumberGenerateRepository.queryCommNumberGenerate(queryModel);
    }


    /***
     * update comm_number_generate
     * @param updateModel
     */
    @Override
    public void updateCommNumberGenerate(DataModel updateModel) {
        this.validateSaveOrUpdateNumberGenerate(updateModel);
        //set update timestamp
        updateModel.setFieldValue("timestamp", LocalDateTime.now());
        commNumberGenerateRepository.updateCommNumberGenerate(updateModel);
    }


    /***
     * generate number
     * @return
     */
    @Override
    public int generateNumber(DataModel generateModel) {
        this.validateSaveOrUpdateNumberGenerate(generateModel);
        int generateNumber = 1;
        DataModel queryModel = this.queryCommNumberGenerate(generateModel);
        if (queryModel != null) {
            generateNumber = queryModel.getIntegerValue("currentValue") + queryModel.getIntegerValue("span");
            queryModel.setFieldValue("timestamp", LocalDateTime.now());
            this.updateCommNumberGenerate(queryModel);
        } else {
            if (!generateModel.containsKey("currentValue")) {
                generateModel.setFieldValue("currentValue", 1);
            }
            if (!generateModel.containsKey("span")) {
                generateModel.setFieldValue("span", 1);
            }
            generateModel.setFieldValue("numberDesc", "");
            this.saveCommNumberGenerate(generateModel);
        }
        return generateNumber;
    }


    /***
     * validate param
     * @param numberModel
     */
    protected void validateSaveOrUpdateNumberGenerate(DataModel numberModel) {
        String validationMsg = "";
        if (numberModel == null) {
            validationMsg = "params can not be null!";
        }
        if (numberModel != null && StringUtils.isBlank(numberModel.getStringValue("numberName"))) {
            validationMsg = "numberName can not be null!";
        }
        if (StringUtils.isNotEmpty(validationMsg)) {
            throw ExceptionHelper.getInstance().handleValidationException(validationMsg);
        }
    }
}
