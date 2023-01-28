package com.teak.core.proxy;

import com.teak.core.function.MyInterfaceProxy;
import com.teak.core.service.ChildrenService;

/**
 * @author 柚mingle木
 * @version 1.0
 * @date 2023/1/28
 */
public class ChildrenServiceProxy implements MyInterfaceProxy {

    private final ChildrenService childrenService;

    public ChildrenServiceProxy(ChildrenService childrenService) {
        this.childrenService = childrenService;
    }

    @Override
    public void myInterfaceProxy() {
        System.out.println("这是子代理类的前置");
        childrenService.myInterfaceProxy();
        System.out.println("这是子代理类的后置");
    }
}
