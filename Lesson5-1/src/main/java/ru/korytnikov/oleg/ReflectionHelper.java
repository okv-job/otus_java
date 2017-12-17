package ru.korytnikov.oleg;

import ru.korytnikov.oleg.annotations.After;
import ru.korytnikov.oleg.annotations.Before;
import ru.korytnikov.oleg.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by tully.
 */
@SuppressWarnings("SameParameterValue")
class ReflectionHelper {
    private ReflectionHelper() {
    }

    static <T> T instantiate(Class<T> type, Object... args) {
        try {
            if (args.length == 0) {
                return type.newInstance();
            } else {
                return type.getConstructor(toClasses(args)).newInstance(args);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    static Object getFieldValue(Object object, String name) {
        Field field = null;
        boolean isAccessible = true;
        try {
            field = object.getClass().getDeclaredField(name); //getField() for public fields
            isAccessible = field.isAccessible();
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            if (field != null && !isAccessible) {
                field.setAccessible(false);
            }
        }
        return null;
    }

    static void setFieldValue(Object object, String name, Object value) {
        Field field = null;
        boolean isAccessible = true;
        try {
            field = object.getClass().getDeclaredField(name); //getField() for public fields
            isAccessible = field.isAccessible();
            field.setAccessible(true);
            field.set(object, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            if (field != null && !isAccessible) {
                field.setAccessible(false);
            }
        }
    }

    static Boolean callMethod(Object object, String name, Object... args) {
        Method method = null;
        boolean isAccessible = true;
        try {
            method = object.getClass().getDeclaredMethod(name, toClasses(args));
            isAccessible = method.isAccessible();
            method.setAccessible(true);
            method.invoke(object, args);
            return true;
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            System.out.println(e.getCause());
            return false;
        } finally {
            if (method != null && !isAccessible) {
                method.setAccessible(false);
            }
        }
    }

    static Map<String, List<Method>> getMethods(Object object) {
        Method[] methods = object.getClass().getDeclaredMethods();
        Map<String, List<Method>> result = new HashMap<>();
        result.put("before", new ArrayList<>());
        result.put("test", new ArrayList<>());
        result.put("after", new ArrayList<>());
        for (Method method : methods) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Before) {
                    result.get("before").add(method);
                }
                if (annotation instanceof Test) {
                    Test test = method.getAnnotation(Test.class);
                    if (test.enabled()) result.get("test").add(method);
                }
                if (annotation instanceof After) {
                    result.get("after").add(method);
                }
            }
        }
        return result;
    }


    static private Class<?>[] toClasses(Object[] args) {
        List<Class<?>> classes = Arrays.stream(args)
                .map(Object::getClass)
                .collect(Collectors.toList());
        return classes.toArray(new Class<?>[classes.size()]);
    }
}
