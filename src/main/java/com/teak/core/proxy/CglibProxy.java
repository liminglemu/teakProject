package com.teak.core.proxy;

import org.springframework.cglib.core.Signature;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author 柚mingle木
 * @version 1.0
 * @date 2023/1/28
 */
public class CglibProxy implements MethodInterceptor {

    private final Object target;

    public CglibProxy(Object target) {
        this.target = target;
    }

    public Object getInstance() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.createClass();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("这是cglib前置处理");
        Object invoke = method.invoke(o, objects);
        Signature signature = methodProxy.getSignature();
        System.out.println("signature = " + signature);
        System.out.println("这是cglib后置处理");
        return invoke;
    }
}
