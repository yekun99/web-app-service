package com.app.commtaskmanage.controller;

import com.app.commtaskmanage.service.CommTaskService;
import com.core.controller.BaseController;
import com.core.data.model.DataModel;
import com.core.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author dechun.yuan
 * @version 1.0
 */
@Controller
@RequestMapping("/api/task")
public class CommTaskController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CommTaskService commTaskService;


    /***
     * save comm task
     * @param request
     * @param requestMap
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    @ResponseBody
    public Map<String, Object> saveCommTask(HttpServletRequest request, @RequestBody Map<String, Object> requestMap) {
        DataModel resultModel = new DataModel();
        try {
            DataModel saveModel = this.getInputData(requestMap);
            commTaskService.saveCommTask(saveModel);
            this.handleSuccess(resultModel);
        } catch (ValidationException ve) {
            this.handleValidationExcpetion(ve, resultModel);
        } catch (Exception e) {
            this.handleException(e, resultModel);
        }
        return resultModel;
    }


    /***
     * query comm task
     * @param request
     * @param requestMap
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/query")
    @ResponseBody
    public Map<String, Object> queryCommTask(HttpServletRequest request, @RequestBody Map<String, Object> requestMap) {
        DataModel resultModel = new DataModel();
        try {
            DataModel queryModel = this.getInputData(requestMap);
            List<DataModel> userModel = commTaskService.queryCommTask(queryModel);
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
    @ResponseBody
    public Map<String, Object> deleteCommTask(HttpServletRequest request) {
        DataModel resultModel = new DataModel();
        try {
            DataModel deleteModel = this.getInputData(request);
            commTaskService.deleteCommTask(deleteModel);
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
     * @param request
     * @param requestMap
     * @return
     */
    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    @ResponseBody
    public Map<String, Object> updateCommTask(HttpServletRequest request, @RequestBody Map<String, Object> requestMap) {
        DataModel resultModel = new DataModel();
        try {
            DataModel updateModel = this.getInputData(requestMap);
            commTaskService.updateCommTask(updateModel);
            this.handleSuccess(null, resultModel);
        } catch (ValidationException ve) {
            this.handleValidationExcpetion(ve, resultModel);
        } catch (Exception e) {
            this.handleException(e, resultModel);
        }
        return resultModel;
    }
}
