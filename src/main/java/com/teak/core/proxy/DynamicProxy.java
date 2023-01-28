package com.teak.core.proxy;

import com.teak.core.function.MyInterfaceProxy;
import com.teak.core.reportErrors.GlobalExceptionHandle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 柚mingle木
 * @version 1.0
 * @date 2023/1/28
 */
public class DynamicProxy implements MyInterfaceProxy {

    private final Object objectProxy;
    private final String methodName;
    private final Object[] args;

    public DynamicProxy(Object o, String methodName, Object[] args) {
        this.objectProxy = o;
        this.methodName = methodName;
        this.args = args;
    }

    @Override
    public void myInterfaceProxy() {
        Class<?> aClass = objectProxy.getClass();
        Method declaredMethod;
        try {
            declaredMethod = aClass.getDeclaredMethod(methodName);
            System.out.println("这是动态代理的前置处理");
            declaredMethod.invoke(objectProxy, args);
            System.out.println("这是动态代理的后置处理");
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new GlobalExceptionHandle(e.getMessage() + "\n" + e.getLocalizedMessage());
        }
    }
}
