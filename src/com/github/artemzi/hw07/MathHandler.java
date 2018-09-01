package com.github.artemzi.hw07;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MathHandler implements InvocationHandler {
    private Box box;

    public MathHandler(MathBox box) {
        this.box = box;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Proxy is used.");
        return method.invoke(box, args);
    }
}
