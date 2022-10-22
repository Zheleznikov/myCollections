package ru.zheleznikov.runner;

import java.lang.reflect.Method;
import java.util.List;

public class Invoker {

    public boolean callTargetMethod(Method method, Object obj, Audit audit) {
        try {
            method.invoke(obj);
            audit.increasePassed();
        } catch (Exception e) {
            e.printStackTrace();
            audit.addInfoAboutFails(method.getName(), e);
            audit.increaseFailed();
            return false;
        }
        return true;
    }

    public boolean callTargetMethod(Method method, Object obj) {
        try {
            method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean callTargetMethod(List<Method> methods, Object obj) {
        for (Method method : methods) {
            if (!callTargetMethod(method, obj)) return false;
        }
        return true;
    }
}
