package com.teak.core.pattern;

/**
 * @author 柚mingle木
 * @version 1.0
 * @date 2023/1/28
 */
public class InternalSlothLikeSingleton {

    private InternalSlothLikeSingleton() {
    }

    public static InternalSlothLikeSingleton getInstance() {
        return InternalClass.internalSlothLikeSingleton;
    }

    // TODO: 2023/1/27 静态内部类不会自动加载，只有外部类主动调用内部类里的方法，属性，等才会加载。类的加载只会执行一次，如果没有被加载，
    //  将无法使用，类的加载顺序为：加载，准备，验证，解析，初始化
    private static class InternalClass {
        private static final InternalSlothLikeSingleton internalSlothLikeSingleton = new InternalSlothLikeSingleton();
    }
}
