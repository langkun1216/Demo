package com.demo.common.utils;

import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class TemplateFileUtil {

    public static FileInputStream getXlsTemplates(String tempName) throws FileNotFoundException {
        return new FileInputStream(ResourceUtils.getFile("classpath:static/excel-templates/"+tempName));
    }
    
    public static FileInputStream getEmailTemplates(String tempName) throws FileNotFoundException {
        return new FileInputStream(ResourceUtils.getFile("classpath:static/templates/temp/"+tempName));
    }
}