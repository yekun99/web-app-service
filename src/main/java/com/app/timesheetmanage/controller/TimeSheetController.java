package com.app.timesheetmanage.controller;

import com.app.timesheetmanage.service.TimeSheetService;
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
 * @author spring
 * @version 1.0
 */
@RestController
@RequestMapping("/api/timeSheet")
public class TimeSheetController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TimeSheetService timeSheetService;


    /***
     * save time sheet
     * @param requestMap
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public Map<String, Object> saveTimeSheet(@RequestBody Map<String, Object> requestMap) {
        DataModel resultModel = new DataModel();
        try {
            DataModel saveModel = this.getInputData(requestMap);
            saveModel.setFieldValue("userName", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            timeSheetService.saveTimeSheet(saveModel);
            this.handleSuccess(resultModel);
        } catch (ValidationException ve) {
            this.handleValidationExcpetion(ve, resultModel);
        } catch (Exception e) {
            this.handleException(e, resultModel);
        }
        return resultModel;
    }


    /***
     * query time sheet list
     * @param requestMap
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/query-list")
    public Map<String, Object> queryTimeSheetList(@RequestBody Map<String, Object> requestMap) {
        DataModel resultModel = new DataModel();
        try {
            DataModel queryModel = this.getInputData(requestMap);
            queryModel.setFieldValue("userName", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            List<DataModel> userModel = timeSheetService.queryTimeSheetList(queryModel);
            this.handleSuccess(userModel, resultModel);
        } catch (ValidationException ve) {
            this.handleValidationExcpetion(ve, resultModel);
        } catch (Exception e) {
            this.handleException(e, resultModel);
        }
        return resultModel;
    }


    /***
     * query time sheet by id
     */
    @RequestMapping(method = RequestMethod.POST, value = "/query-id")
    public Map<String, Object> queryTimeSheetById(@RequestBody Map<String, Object> requestMap) {
        DataModel resultModel = new DataModel();
        try {
            DataModel queryModel = this.getInputData(requestMap);
            DataModel userModel = timeSheetService.queryTimeSheetById(queryModel);
            this.handleSuccess(userModel, resultModel);
        } catch (ValidationException ve) {
            this.handleValidationExcpetion(ve, resultModel);
        } catch (Exception e) {
            this.handleException(e, resultModel);
        }
        return resultModel;
    }


    /***
     * delete time sheet
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public Map<String, Object> deleteTimeSheet(HttpServletRequest request) {
        DataModel resultModel = new DataModel();
        try {
            DataModel deleteModel = this.getInputData(request);
            timeSheetService.deleteTimeSheet(deleteModel);
            this.handleSuccess(null, resultModel);
        } catch (ValidationException ve) {
            this.handleValidationExcpetion(ve, resultModel);
        } catch (Exception e) {
            this.handleException(e, resultModel);
        }
        return resultModel;
    }


    /***
     * update time sheet
     * @param requestMap
     * @return
     */
    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public Map<String, Object> updateTimeSheet(@RequestBody Map<String, Object> requestMap) {
        DataModel resultModel = new DataModel();
        try {
            DataModel updateModel = this.getInputData(requestMap);
            timeSheetService.updateTimeSheet(updateModel);
            this.handleSuccess(null, resultModel);
        } catch (ValidationException ve) {
            this.handleValidationExcpetion(ve, resultModel);
        } catch (Exception e) {
            this.handleException(e, resultModel);
        }
        return resultModel;
    }
}
