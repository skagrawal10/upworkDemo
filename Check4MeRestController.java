package com.check4me.web.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.annotations.SortType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.kamranzafar.otp.OTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;


import ch.qos.logback.classic.pattern.Util;

import com.check4me.apiresponse.ChkGeneralResponse;
import com.check4me.apiresponse.ChkListResponse;
import com.check4me.apiresponse.ChkResponseMapper;
import com.check4me.apiresponse.ChkResponseToEntityMapper;
import com.check4me.apiresponse.ChkTaskCategoryResponse;
import com.check4me.apiresponse.ChkTaskReportResponse;
import com.check4me.apiresponse.ChkTaskReportShortResponse;
import com.check4me.apiresponse.ChkTaskResponse;
import com.check4me.apiresponse.ChkTaskTypeResponse;
import com.check4me.apiresponse.ChkUserResponse;
import com.check4me.common.AlgAccessDeniedException;
import com.check4me.common.AlgInternalErrorException;
import com.check4me.common.AlgPermissionObject;
import com.check4me.common.Utilities;
import com.check4me.config.AlgConstants;
import com.check4me.config.AlgResourceHandler;
import com.check4me.entities.ChkTask;
import com.check4me.entities.ChkTaskCategory;
import com.check4me.entities.ChkTaskReport;
import com.check4me.entities.ChkTaskType;
import com.check4me.entities.ChkUser;
import com.check4me.repo.ChkServiceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RestController
public class Check4MeRestController{

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private RequestMappingHandlerMapping handlerMapping;
    private static final Logger logger = LoggerFactory.getLogger(Check4MeRestController.class);
    
    
    @Autowired
    private ApplicationContext appContext;
    
    @Autowired
    private ChkServiceImpl serviceCategory;
    
    @Autowired
    private ChkResponseToEntityMapper chkResponseToEntityMapper;

    
    @RequestMapping(value="/convertListObject", method=RequestMethod.GET)
    public List<CustomObject> convertListObject(HttpServletRequest request) {
    	logger.debug("convertListObject()");
		List<Object> objectList = UpworkObject.list;
		List<CustomObject> customObjectList = new ArrayList<CustomObject>();
		for(Object object : objectList)
		{
			if(object instanceof CustomObject)
			{
				customObjectList.add((CustomObject)object);
			}
		}
    	return customObjectList;
        
    }
    
}
