package com.common.usermanage.service.impl;

import com.common.usermanage.repository.UserRepository;
import com.common.usermanage.service.UserService;
import com.core.data.model.DataModel;
import com.core.exception.ExceptionHelper;
import com.core.message.service.MessageManager;
import com.core.utils.JWTTokenUtils;
import com.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    /***
     * save user info
     * @param saveModel
     */
    @Override
    public void saveUser(DataModel saveModel) {
        this.validateSaveOrUpdateUser(saveModel);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        saveModel.setFieldValue("password", bCryptPasswordEncoder.encode(saveModel.getStringValue("password")));
        //set insert timestamp
        saveModel.setFieldValue("insertDate", LocalDateTime.now());
        saveModel.setFieldValue("timestamp", LocalDateTime.now());
        userRepository.saveUser(saveModel);
    }


    /***
     * find user by user name
     * @param queryModel
     * @return
     */
    @Override
    public DataModel findUser(DataModel queryModel) {
        this.validateSaveOrUpdateUser(queryModel);
        DataModel userModel = userRepository.findUser(queryModel);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (userModel != null && bCryptPasswordEncoder.matches(queryModel.getStringValue("password"), userModel.getStringValue("password"))) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("userName", userModel.getStringValue("userName"));
            String token = JWTTokenUtils.sign(null, claims, 0);
            userModel.setFieldValue("token", token);
            MessageManager.getInstance().addSuccessMessage("sys.user.query.success");
        } else {
            MessageManager.getInstance().addErrorMessage("sys.user.query.fail");
            throw ExceptionHelper.getInstance().handleValidationException("user name or password is not right!");
        }
        return userModel;
    }


    /***
     * find user by user other info
     * @param queryModel
     * @return
     */
    @Override
    public List<DataModel> findUserList(DataModel queryModel) {
        return userRepository.findUserList(queryModel);
    }


    /***
     * update user info by user name
     * @param updateModel
     */
    @Override
    public void updateUser(DataModel updateModel) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (StringUtils.isNotBlank(updateModel.getStringValue("password"))) {
            updateModel.setFieldValue("password", bCryptPasswordEncoder.encode(updateModel.getStringValue("password")));
        }
        //设置更新时间为系统当前时间
        updateModel.setFieldValue("timestamp", LocalDateTime.now());
        //更新账号锁定时间
        if (StringUtils.isNotBlank(updateModel.getStringValue("lockInd")) && "Y".equals(updateModel.getStringValue("lockInd"))) {
            updateModel.setFieldValue("lockDate", LocalDateTime.now());
        } else {
            updateModel.setFieldValue("lockDate", null);
        }
        userRepository.updateUser(updateModel);
    }


    /***
     * delete user by user name
     * @param deleteModel
     */
    @Override
    public void deleteUser(DataModel deleteModel) {
        userRepository.deleteUser(deleteModel);
    }


    /***
     * check user info
     * @param userModel
     */
    protected void validateSaveOrUpdateUser(DataModel userModel) {
        String validationMsg = "";
        if (StringUtils.isBlank(userModel.getStringValue("userName"))) {
            validationMsg = "user name can not be null!";
        } else if (StringUtils.isBlank(userModel.getStringValue("password"))) {
            validationMsg = "password can not be null!";
        }
        if (StringUtils.isNotEmpty(validationMsg)) {
            throw ExceptionHelper.getInstance().handleValidationException(validationMsg);
        }
    }
}
