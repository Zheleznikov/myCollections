package ru.zheleznikov.runner;

import ru.zheleznikov.annotations.AfterZhele;
import ru.zheleznikov.annotations.BeforeZhele;
import ru.zheleznikov.annotations.TestZhele;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для запуска методов с аннотациями TestZhele, BeforeZhele, AfterZhele
 * В конструктор класса передается строка с полным названием класса.
 * После этого требуется запустить у класса метод go()
 */
public class Runner implements RunnerImpl {

    private final List<Method> testMethods = new ArrayList<>();
    private final List<Method> beforeMethods = new ArrayList<>();
    private final List<Method> afterMethods = new ArrayList<>();
    private final Class<?> testClass;

    public Runner(String testClassName) throws ClassNotFoundException {
        this.testClass = Class.forName(testClassName);
        sortMethodsByAnnotations();
    }

    public void go() {
        Audit audit = new Audit();
        for (Method method : testMethods) {
            try {
                Object obj = this.testClass.getDeclaredConstructor().newInstance();
                callTargetMethod(beforeMethods, obj);
                callTargetMethod(method, obj, audit);
                callTargetMethod(afterMethods, obj);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        audit.showResult();
    }

    /**
     * Вызов метода с аннотацией @TestZhele с аудитом и занесением информации о прохождении теста
     */
    private void callTargetMethod(Method method, Object obj, Audit audit) {
        try {
            method.invoke(obj);
            audit.increasePassed();
        }
        catch (Exception e) {
            e.printStackTrace();
            audit.showInfoAboutFail(method.getName(), e);
            audit.increaseFailed();
        }
    }

    private void callTargetMethod(Method method, Object obj) {
        try {
            method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void callTargetMethod(List<Method> methods, Object obj) {
        methods.forEach(method -> callTargetMethod(method, obj));
    }

    /**
     * Метод для сортировки методов тестируемого класса по группам.
     * Методы в зависимости от аннотаций разбрасываются по трем массивам: test, after, before.
     */
    private void sortMethodsByAnnotations() {
        for (Method method : this.testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(TestZhele.class)) {
                testMethods.add(method);
            } else if (method.isAnnotationPresent(BeforeZhele.class)) {
                beforeMethods.add(method);
            } else if (method.isAnnotationPresent(AfterZhele.class)) {
                afterMethods.add(method);
            }
        }
    }


}
