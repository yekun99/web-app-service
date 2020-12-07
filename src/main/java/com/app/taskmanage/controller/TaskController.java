package com.app.taskmanage.controller;

import com.app.taskmanage.service.TaskService;
import com.core.controller.BaseController;
import com.core.data.model.DataModel;
import com.core.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author dechun.yuan
 * @version 1.0
 */
@RestController
@RequestMapping("/api/task")
public class TaskController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskService taskService;


    /***
     * save comm task
     * @param requestMap
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public Map<String, Object> saveCommTask(@RequestBody Map<String, Object> requestMap) {
        DataModel resultModel = new DataModel();
        try {
            DataModel saveModel = this.getInputData(requestMap);
            taskService.saveCommTask(saveModel);
            this.handleSuccess(resultModel);
        } catch (ValidationException ve) {
            this.handleValidationExcpetion(ve, resultModel);
        } catch (Exception e) {
            this.handleException(e, resultModel);
        }
        return resultModel;
    }


    /***
     * query all comm_task
     * @param requestMap
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/query/all")
    public Map<String, Object> queryCommTask(@RequestBody Map<String, Object> requestMap) {
        DataModel resultModel = new DataModel();
        try {
            DataModel queryModel = this.getInputData(requestMap);
            List<DataModel> userModel = taskService.queryCommTask(queryModel);
            this.handleSuccess(userModel, resultModel);
        } catch (ValidationException ve) {
            this.handleValidationExcpetion(ve, resultModel);
        } catch (Exception e) {
            this.handleException(e, resultModel);
        }
        return resultModel;
    }


    /***
     * query comm_task by user_name
     */
    @RequestMapping(method = RequestMethod.POST, value = "/query/user-task")
    public Map<String, Object> queryCommTaskByUserName(@RequestBody Map<String, Object> requestMap) {
        DataModel resultModel = new DataModel();
        try {
            DataModel queryModel = this.getInputData(requestMap);
            queryModel.setFieldValue("assignee", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            List<DataModel> userModel = taskService.queryCommTask(queryModel);
            this.handleSuccess(userModel, resultModel);
        } catch (ValidationException ve) {
            this.handleValidationExcpetion(ve, resultModel);
        } catch (Exception e) {
            this.handleException(e, resultModel);
        }
        return resultModel;
    }


    /***
     * delete comm task
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public Map<String, Object> deleteCommTask(HttpServletRequest request) {
        DataModel resultModel = new DataModel();
        try {
            DataModel deleteModel = this.getInputData(request);
            taskService.deleteCommTask(deleteModel);
            this.handleSuccess(null, resultModel);
        } catch (ValidationException ve) {
            this.handleValidationExcpetion(ve, resultModel);
        } catch (Exception e) {
            this.handleException(e, resultModel);
        }
        return resultModel;
    }


    /***
     * update comm task
     * @param requestMap
     * @return
     */
    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public Map<String, Object> updateCommTask(@RequestBody Map<String, Object> requestMap) {
        DataModel resultModel = new DataModel();
        try {
            DataModel updateModel = this.getInputData(requestMap);
            taskService.updateCommTask(updateModel);
            this.handleSuccess(null, resultModel);
        } catch (ValidationException ve) {
            this.handleValidationExcpetion(ve, resultModel);
        } catch (Exception e) {
            this.handleException(e, resultModel);
        }
        return resultModel;
    }
}
