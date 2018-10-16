package com.demo.common.utils;

import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class XmlUtils {

	private static final Logger logger = Logger.getLogger(XmlUtils.class);

	private static final String utf8 = "UTF-8";

	@SuppressWarnings("unchecked")
	public static <T> T xml2Bean(String xml, Class<T> clazz) {

		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (T) unmarshaller.unmarshal(new StringReader(xml));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static String bean2XmlNoHead(Object bean) {

		return bean2Xml(bean, true, false);
	}

	public static String bean2Xml(Object bean) {

		return bean2Xml(bean, false, false);
	}

	private static String bean2Xml(Object bean, boolean noHead, boolean isFormat) {

		if (bean == null) {
			return null;
		}
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(bean.getClass());
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, utf8);
			// 是否格式化生成xml
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, isFormat);
			// 是否省略xml头信息<?xml version="1.0" encoding="utf-8" standalone="yes"?>
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, noHead);

			StringWriter writer = new StringWriter();
			marshaller.marshal(bean, writer);
			return writer.toString();
		} catch (Exception e) {
			logger.error("unconverted bean[" + bean + "]", e);
		}
		return null;
	}

	/**
	 * XML转JAVA Bean
	 * @param xml
	 * @param valueType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromXMLToObject(String xml, Class<T> valueType) {
		try {
			JAXBContext context = JAXBContext.newInstance(valueType);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (T) unmarshaller.unmarshal(new StringReader(xml));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * javaBean转XML
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String beanToxml(Object obj) throws Exception {

		StringWriter sw = new StringWriter();
		// 利用jdk中自带的转换类实现
		JAXBContext context = JAXBContext.newInstance(obj.getClass());

		Marshaller marshaller = context.createMarshaller();
		// 格式化xml输出的格式
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		// 去掉生成xml的默认报文头
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

		// 将对象转换成输出流形式的xml
		marshaller.marshal(obj, sw);
		sw.close();
		// System.out.println(sw.toString());
		return sw.toString();

	}
}
