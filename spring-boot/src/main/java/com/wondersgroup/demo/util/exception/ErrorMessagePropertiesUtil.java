package com.wondersgroup.demo.util.exception;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorMessagePropertiesUtil {
	protected static Logger logger = LoggerFactory.getLogger(ErrorMessagePropertiesUtil.class);

	protected static ResourceBundle configResource = null;
	protected static Map<String, String> propertiesMap = new HashMap<String, String>();
	protected static String PROPERTITIES_FILE_NAME = "error_message";

	/**
	 * 从properties文件加载错误信息进行初始化
	 * 
	 */
	@PostConstruct
	public static void initFromConfig() {
		try {
			configResource = ResourceBundle.getBundle(PROPERTITIES_FILE_NAME);// file
			Set<String> keySet = configResource.keySet();
			if (keySet != null && !keySet.isEmpty()) {
				for (String key : keySet) {
					String value = new String((configResource.getString(key)).getBytes("utf-8"), "utf-8");
					propertiesMap.put(key, value);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 根据error code获取error message内容
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		String value = null;
		if (key != null && propertiesMap != null && propertiesMap.containsKey(key)) {
			value = propertiesMap.get(key);
		}
		return value;
	}

	/**
	 * 根据error code获取error message内容<br>
	 * 附带参数如{0},{1}...
	 * 
	 * @param key
	 * @param parValues
	 * @return
	 */
	public static String getValueWithPars(String key, Object... parValues) {
		String value = getValue(key);
		if (value != null && parValues != null && parValues.length > 0) {
			value = MessageFormat.format(value, parValues);
		}
		return value;
	}

}
