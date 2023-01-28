package com.teak.core.service.serviceImpl;

import com.teak.core.service.ChildrenService;

/**
 * @author 柚mingle木
 * @version 1.0
 * @date 2023/1/28
 */
public class ChildrenServiceImpl implements ChildrenService {

    @Override
    public void myInterfaceProxy() {
        System.out.println("这是子实现类");
    }


    @Override
    public void show() {
        System.out.println("这是子类实现类");
    }
}
