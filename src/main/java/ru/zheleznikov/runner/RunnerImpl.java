package ru.zheleznikov.runner;

import ru.zheleznikov.annotations.AfterZhele;
import ru.zheleznikov.annotations.BeforeZhele;
import ru.zheleznikov.annotations.TestZhele;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для запуска методов с аннотациями TestZhele, BeforeZhele, AfterZhele
 * В конструктор класса передается строка с полным названием класса.
 * После этого требуется запустить у класса метод go()
 */
public class RunnerImpl implements Runner {

    private final List<Method> testMethods = new ArrayList<>();
    private final List<Method> beforeMethods = new ArrayList<>();
    private final List<Method> afterMethods = new ArrayList<>();
    private final Class<?> testClass;
    private final Invoker invoker;
    private final Audit audit;


    public RunnerImpl(String testClassName) throws ClassNotFoundException {
        this.testClass = Class.forName(testClassName);
        this.invoker = new Invoker();
        this.audit = new Audit();
        // разместил метод здесь потому что это по сути заполнение массивов. Как вариант - вызывать этот метод в go()
        sortMethodsByAnnotations();
    }

    public void go() {
        for (Method method : testMethods) {
            try {
                Object obj = this.testClass.getDeclaredConstructor().newInstance();
                boolean isBeforePassed = invoker.callTargetMethod(beforeMethods, obj);
                if (isBeforePassed) invoker.callTargetMethod(method, obj, audit);
                invoker.callTargetMethod(afterMethods, obj);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        audit.showResult();
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
