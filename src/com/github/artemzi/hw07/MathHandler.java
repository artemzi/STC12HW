package com.github.artemzi.hw07;

import com.github.artemzi.hw07.annotations.ClearData;
import com.github.artemzi.hw07.annotations.Logged;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.TreeSet;
import java.util.logging.Logger;

public class MathHandler implements InvocationHandler {
    private Box box;
    private static final Logger LOGGER = Logger.getLogger(MathHandler.class.getName());

    MathHandler(MathBox box) {
        this.box = box;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (box.getClass().getAnnotation(Logged.class) != null) {
            LOGGER.info("Logger annotation is working for method: "+ method.getName());
        }

        Method[] methods = box.getClass().getMethods();
        for (Method m : methods) {
            if (m.getName().equals(method.getName()) && m.getAnnotation(ClearData.class) != null) {
                try {
                    Field storage = box.getClass().getDeclaredField("storage");
                    if (!storage.isAccessible()) {
                        storage.setAccessible(true);
                    }
                    // TODO: this doesn't work. fix it
                    storage.set(box.getClass(), new TreeSet<Integer>());

                    LOGGER.info("ClearData called from proxy for " + method.getName());
                } catch (NoSuchFieldException |
                        SecurityException |
                        IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            }
        }

        return method.invoke(box, args);
    }
}
