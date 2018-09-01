package com.github.artemzi.hw07;

import com.github.artemzi.hw07.annotations.ClearData;
import com.github.artemzi.hw07.annotations.Logged;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.logging.Logger;

public class MathHandler implements InvocationHandler {
    private Box box;
    private static final Logger LOGGER = Logger.getLogger(MathHandler.class.getName());

    MathHandler(Box box) {
        this.box = box;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (box.getClass().getAnnotation(Logged.class) != null) {
            LOGGER.info("Annotation is working for method: "+ method.getName());
        }

        if (box.getClass().getAnnotation(ClearData.class) != null) {
        }


        return method.invoke(box, args);
    }
}
