package com.teak.core.util;

import com.teak.core.function.MyBigFunction;
import com.teak.core.function.MyFunction;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * The type Teak for each reflect utils.
 *
 * @author 柚mingle木
 * @version 1.0
 * @date 2023 /1/15
 */
public final class TeakForEachReflectUtils {

    private TeakForEachReflectUtils() {
    }

    /**
     * Gets all class.
     * 获取该类的所有父类
     * 返回类，如过得到的action是null，就继续获取他的父类
     *
     * @param <R>      the type parameter
     * @param aClass   the class
     * @param function the function
     */
    public static <R> void getAllClass(Class<?> aClass, MyFunction<Class<?>, R> function) {
        Class<?> superClass = aClass;
        while (superClass != null && superClass != Object.class) {
            R action = function.action(superClass);
            if (action != null) {
                return;
            }
            superClass = superClass.getSuperclass();
        }
    }

    /**
     * General.
     *
     *
     * @param <T>         the type parameter
     * @param <R>         the type parameter
     * @param <U>         the type parameter
     * @param aClass      the class
     * @param getter      the getter
     * @param function    the function
     * @param bigFunction the big function
     */
    public static <T, R, U> void general(Class<?> aClass, MyFunction<Class<?>, U[]> getter, MyFunction<U, T> function, MyBigFunction<T, U, R> bigFunction) {
        getAllClass(aClass, new MyFunction<Class<?>, Object>() {
            @Override
            public Object action(Class<?> aClass) {
                U[] action = getter.action(aClass);
                for (U u : action) {
                    T result = function.action(u);
                    R r = bigFunction.bigAction(result, u);
                    if (r != null) {
                        return r;
                    }
                }
                return null;
            }
        });
    }

    /**
     * General declared field.
     *
     * @param <T>         the type parameter
     * @param <R>         the type parameter
     * @param object      the object
     * @param bigFunction the big function
     */
    public static <T, R> void generalDeclaredField(@NotNull T object, MyBigFunction<Field, Object, R> bigFunction) {
        general(object.getClass(), new MyFunction<Class<?>, Field[]>() {
            @Override
            public Field[] action(Class<?> aClass) {
                return aClass.getDeclaredFields();
            }
        }, new MyFunction<Field, Object>() {
            @Override
            public Object action(Field field) {
                field.setAccessible(true);
                Object value;
                try {
                    value = field.get(object);
                } catch (IllegalAccessException e) {
                    return null;
                }
                return value;
            }
        }, new MyBigFunction<Object, Field, Object>() {
            @Override
            public Object bigAction(Object value, Field field) {
                return bigFunction.bigAction(field, value);
            }
        });
    }


    /**
     * For each property.<p>
     * 返回该类的所有属性，返回
     *
     * @param aClass   the class
     * @param consumer the consumer
     */
    public static void forEachProperty(Class<?> aClass, @NotNull Consumer<Field> consumer) {
        if (nullValueError(aClass)) {
            return;
        }
        Field[] declaredFields = aClass.getDeclaredFields();

        Arrays.stream(declaredFields).forEach(field -> {
            field.setAccessible(true);
            consumer.accept(field);
        });
    }


    /**
     * For each super class.<p>
     * 返回该类以及该类的所有父类
     *
     * @param aClass   the a class
     * @param consumer the consumer
     */
    public static void forEachSuperClass(Class<?> aClass, Consumer<Class<?>> consumer) {
        if (nullValueError(aClass)) {
            return;
        }
        Class<?> superclass = aClass;
        while (Objects.nonNull(superclass) && !superclass.equals(Object.class)) {
            consumer.accept(superclass);
            superclass = superclass.getSuperclass();
        }
    }

    private static boolean nullValueError(Class<?> aClass) {
        try {
            Objects.requireNonNull(aClass);
        } catch (Exception e) {
            System.err.println("thisClassIsNull");
            return true;
        }
        return false;
    }

    /**
     * For each super and child property.<p>
     * 返回子类和父类的所有属性
     *
     * @param aClass   the class
     * @param consumer the consumer
     */
    public static void forEachSuperAndChildProperty(Class<?> aClass, Consumer<Field> consumer) {

        forEachSuperClass(aClass, tempClass -> forEachProperty(tempClass, consumer));
    }

    /**
     * For each which property has a value.<p>
     * 返回子类与父类所有属性的值
     *
     * @param <T>      the type parameter
     * @param object   the object
     * @param consumer the consumer
     */
    public static <T> void forEachWhichPropertyHasAValue(T object, Consumer<Object> consumer) {
        Class<?> aClass = object.getClass();
        forEachSuperAndChildProperty(aClass, field -> {
            System.out.println(field);
            field.setAccessible(true);
            Object value;
            try {
                if ((value = field.get(object)) != null) {
                    consumer.accept(value);
                } else {
                    consumer.accept(null);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Sets property value.
     *
     * @param <T>          the type parameter
     * @param <U>          the type parameter
     * @param object       the object
     * @param propertyName the property name
     * @param value        the value
     */
    public static <T, U> void setPropertyValue(T object, String propertyName, U value) {
        forEachSuperAndChildProperty(object.getClass(), new Consumer<Field>() {
            @Override
            public void accept(Field field) {
                if (field.getName().equals(propertyName)) {
                    try {
                        field.set(object, value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }



    /**
     * Gets property.
     *
     * @param <R>      the type parameter
     * @param aClass   the a class
     * @param function the function
     */
    public static <R> void getProperty(Class<?> aClass, MyFunction<Field, R> function) {
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            R action = function.action(declaredField);
            if (action != null) {
                return;
            }
        }
    }

    /**
     * Gets all field.
     *
     * @param <T>         the type parameter
     * @param <R>         the type parameter
     * @param aClass      the a class
     * @param bigFunction the big function
     */
    public static <T, R> void getAllField(Class<?> aClass, MyBigFunction<Class<?>, Field, R> bigFunction) {
        getAllClass(aClass, new MyFunction<Class<?>, Object>() {
            R r = null;

            @Override
            public Object action(Class<?> aClass) {
                getProperty(aClass, new MyFunction<Field, Object>() {
                    @Override
                    public Object action(Field field) {
                        r = bigFunction.bigAction(aClass, field);
                        if (r != null) {
                            return r;
                        }
                        return null;
                    }
                });
                return r;
            }
        });
    }

}
