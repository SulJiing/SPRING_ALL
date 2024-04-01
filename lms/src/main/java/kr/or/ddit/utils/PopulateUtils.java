package kr.or.ddit.utils;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.AbstractConverter;

public class PopulateUtils {
	
	// 해당 클래스가 상수 메모리에 올라갈때 생성됨
	static{
		
		AbstractConverter converter = new AbstractConverter() {

			@Override
			protected <T> T convertToType(Class<T> type, Object value) throws Throwable {
				if(value==null || value.toString().isEmpty()) return null;
				else
					
					return (T) type.getDeclaredMethod("parse", CharSequence.class).invoke(null, value.toString());
//					return (T) LocalDateTime.parse(value.toString());
//					return (T) LocalDate.parse(value.toString());
			}

			@Override
			protected Class<?> getDefaultType() {
				return LocalDate.class;
			}
			
		};
		
		ConvertUtils.register(converter, LocalDate.class);
		ConvertUtils.register(converter, LocalDateTime.class);
		
	}

	public static <T> void populate(T bean, Map<String, ? extends Object> parameterMap) {
		try {
			BeanUtils.populate(bean, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
}
