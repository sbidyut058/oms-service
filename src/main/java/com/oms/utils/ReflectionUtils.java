package com.oms.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.oms.annotations.EntityMapping;
import com.oms.annotations.FKEntityFieldMapping;
import com.oms.annotations.LinkEntityFieldMapping;
import com.oms.annotations.EntityFieldMapping;
import com.oms.dto.ListViewFieldDTO;


public class ReflectionUtils {
	
	public static boolean isFieldNameExist(Class cls, String fieldNm) {
		try {
			cls.getDeclaredField(fieldNm);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static List<String> getFieldNames(Class cls) {
		return Arrays.asList(cls.getDeclaredFields()).stream().map( m -> m.getName()).collect(Collectors.toList());
	}
	
	public static <T> T getRootEntityMappingAnnotation(Class<?> cls) {
		return (T)cls.getAnnotation(EntityMapping.class);
	}
	
	public static Class<?> getRootEntity(Class<?> cls) {
		EntityMapping entityMapping = getRootEntityMappingAnnotation(cls);
		return entityMapping.entity();
	}
	
	public static List<FKEntityFieldMapping> getFKMappingAnnotations(Class<?> cls) {
		List<FKEntityFieldMapping> list = Arrays.asList(cls.getDeclaredFields())
				.stream()
				.filter( f -> f.getAnnotation(FKEntityFieldMapping.class) != null)
				.map( m -> m.getAnnotation(FKEntityFieldMapping.class)).collect(Collectors.toList());
		
		return list;
	}
	
	public static List<Class<?>> getFKMappingEntities(Class<?> cls) {
		List<FKEntityFieldMapping> fkList = getFKMappingAnnotations(cls);
		return fkList.stream().map(FKEntityFieldMapping::entity).collect(Collectors.toList());
	}
	
	public static Map<String, FKEntityFieldMapping> getFKFieldMappingAnnotations(Class<?> cls) {
		Map<String, FKEntityFieldMapping> map = new HashMap<>();
		
		Arrays.asList(cls.getDeclaredFields())
				.stream()
				.filter( f -> f.getAnnotation(FKEntityFieldMapping.class) != null).forEach( f -> {
					map.put(f.getName(), f.getAnnotation(FKEntityFieldMapping.class));
				});;
		
		return map;
	}
	
	public static Map<String, Class<?>> getFKEntityFieldMappings(Class<?> cls) {
		Map<String, Class<?>> map = new HashMap<>();
		
		Arrays.asList(cls.getDeclaredFields())
		.stream()
		.filter( f -> f.getAnnotation(FKEntityFieldMapping.class) != null).forEach( f -> {
			map.put(f.getName(), f.getAnnotation(FKEntityFieldMapping.class).entity());
		});;
		
		return map;
	}
	
	public static Map<String, FKEntityFieldMapping> getFKEntityFieldMappingsMap(Class<?> cls) {
		Map<String, FKEntityFieldMapping> map = Arrays.asList(cls.getDeclaredFields())
		.stream()
		.filter( f -> f.getAnnotation(FKEntityFieldMapping.class) != null).collect(Collectors.toMap(x -> x.getName(), x -> x.getAnnotation(FKEntityFieldMapping.class)));
		return map;
	}
	
	public static Map<String, LinkEntityFieldMapping> getLinkEntityFieldMappingsMap(Class<?> cls) {
		Map<String, LinkEntityFieldMapping> map = Arrays.asList(cls.getDeclaredFields())
		.stream()
		.filter( f -> f.getAnnotation(LinkEntityFieldMapping.class) != null).collect(Collectors.toMap(x -> x.getName(), x -> x.getAnnotation(LinkEntityFieldMapping.class)));
		return map;
	}
	
	public static <T> T getFKFieldMappingValueAnnotation(Class<?> cls, String fieldName, Class annotationClass) {
		Field field = Arrays.asList(cls.getDeclaredFields())
				.stream()
				.filter( f -> fieldName.equals(f.getName()))
				.findAny().orElse(null);
		
		return (T)field.getAnnotation(annotationClass);
	}

	public static void castValue(Class cls, ListViewFieldDTO fieldDto) {
		try {
			if(fieldDto.getValue() != null && !fieldDto.getValue().toString().trim().isEmpty()) {
				if("Double".equalsIgnoreCase(cls.getDeclaredField(fieldDto.getDataField()).getType().getSimpleName())) {
					fieldDto.setValue(Double.valueOf(fieldDto.getValue().toString()));
				} else if("Integer".equalsIgnoreCase(cls.getDeclaredField(fieldDto.getDataField()).getType().getSimpleName())) {
					fieldDto.setValue(Integer.valueOf(fieldDto.getValue().toString()));
				} else if("Float".equalsIgnoreCase(cls.getDeclaredField(fieldDto.getDataField()).getType().getSimpleName())) {
					fieldDto.setValue(Float.valueOf(fieldDto.getValue().toString()));
				}
			}			
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Map<String, String> getLinkEntityFieldNames(Class trgtCls, Class srcCls) {

		Map<String, String> map = Arrays.asList(trgtCls.getDeclaredFields())
				.stream()
				.filter( f -> f.getAnnotation(LinkEntityFieldMapping.class) != null &&  f.getAnnotation(LinkEntityFieldMapping.class).mappingEntity() == srcCls )
				.collect(Collectors.toMap(x -> x.getName(), x -> "id"));
		return map;
	}
	
	public static Map<String, String> getFKFieldNames(Class trgtCls, Class srcCls) {
//		Map<String, String> map = new HashMap<>();
//		List<Field> fieldList = Arrays.asList(trgtCls.getDeclaredFields())
//				.stream()
//				.filter( f -> f.getAnnotation(EntityFieldMapping.class) != null &&  f.getAnnotation(EntityFieldMapping.class).entity() == srcCls )
//				.toList();
//		fieldList.forEach( f -> {
//			EntityFieldMapping fk = f.getAnnotation(EntityFieldMapping.class);
//			map.put(f.getName(), fk.name());
//		});
		Map<String, String> map = Arrays.asList(trgtCls.getDeclaredFields())
				.stream()
				.filter( f -> f.getAnnotation(EntityFieldMapping.class) != null &&  f.getAnnotation(EntityFieldMapping.class).entity() == srcCls )
				.collect(Collectors.toMap(x -> x.getName(), x -> x.getAnnotation(EntityFieldMapping.class).name()));
		return map;
	}
	
	public static void setFieldValue(Object srcObj, Object trgtObj) {
		Map<String, String> linkFieldMap = ReflectionUtils.getLinkEntityFieldNames(trgtObj.getClass(), srcObj.getClass());
		Map<String, String> fieldMap = ReflectionUtils.getFKFieldNames(trgtObj.getClass(), srcObj.getClass());
		Field srcFld = null;
		Field trgtFld = null;
		
		for(String trgtFieldNm : linkFieldMap.keySet()) {
			try {
				Object val = getFieldValue(srcObj, linkFieldMap.get(trgtFieldNm));
				
				trgtFld = trgtObj.getClass().getDeclaredField(trgtFieldNm);
				trgtFld.setAccessible(true);
				trgtFld.set(trgtObj, val);
			} catch (IllegalArgumentException e) {e.printStackTrace();				
			} catch (IllegalAccessException e) {e.printStackTrace();
			} catch (NoSuchFieldException e) {e.printStackTrace();
			} catch (SecurityException e) {e.printStackTrace();
			}
		}
		
		
		for(String trgtFieldNm : fieldMap.keySet()) {
			try {
				Object val = getFieldValue(srcObj, fieldMap.get(trgtFieldNm));
				
				trgtFld = trgtObj.getClass().getDeclaredField(trgtFieldNm);
				trgtFld.setAccessible(true);
				trgtFld.set(trgtObj, val);
			} catch (IllegalArgumentException e) {e.printStackTrace();				
			} catch (IllegalAccessException e) {e.printStackTrace();
			} catch (NoSuchFieldException e) {e.printStackTrace();
			} catch (SecurityException e) {e.printStackTrace();
			}
		}
	}
	
	public static Object getFieldValue(Object srcObj, String fieldName) {
		//CONCAT(firstName, lastName)
		Field srcFld = null;
		Object rtnVal = null;
		try {
			if(fieldName.trim().startsWith("CONCAT")) {
				fieldName = fieldName.trim().replace("CONCAT", "").replace("(", "").replace(")", "");
				
				String value = "";
				String []arr = fieldName.split(",");
				for(String fld : arr) {
					srcFld = srcObj.getClass().getDeclaredField(fld.trim());
					srcFld.setAccessible(true);
					value += value.length() == 0 ? String.valueOf(srcFld.get(srcObj)) : " " + String.valueOf(srcFld.get(srcObj));
				}
				rtnVal = value;
			} else {
				srcFld = srcObj.getClass().getDeclaredField(fieldName.trim());
				srcFld.setAccessible(true);
				rtnVal = srcFld.get(srcObj);
			}
		} catch (IllegalArgumentException e) {e.printStackTrace();				
		} catch (IllegalAccessException e) {e.printStackTrace();
		} catch (NoSuchFieldException e) {e.printStackTrace();
		} catch (SecurityException e) {e.printStackTrace();
		} catch (Exception e) {e.printStackTrace();
		}
		return rtnVal;
	}

	public static Object getValue(Class cls, Object srcObj, String fieldName){
		Object obj = null;
		try {
			Field fld = cls.getDeclaredField(fieldName);
			fld.setAccessible(true);
			obj = fld.get(srcObj);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;		
	}
	
	public static void setValue(Class cls, Object srcObj, String fieldName, Object value){
		Field fld;
		try {
			fld = cls.getDeclaredField(fieldName);
			fld.setAccessible(true);
			fld.set(srcObj, value);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		// TODO Auto-generated method stub
		//getFieldNames(ReflectionUtils.class);
		
	}

}
