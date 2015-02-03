package com.e_lliam.app.video.server.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class BeanTools {
	public static<T extends Annotation> Object getPropertyValueByAnnotation(Object obj,Class<T> annotationClass){
		Field[] fields = obj.getClass().getDeclaredFields();
		try {
			for(Field f:fields){
				if(f.getAnnotation(annotationClass)!=null){
					f.setAccessible(true);
					return f.get(obj);
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Long getPrimaryKeyValue(Object obj){
		return (Long)getPropertyValueByAnnotation(obj, javax.persistence.Id.class);
	}
}
