package ru.zheleznikov.runner;

public class RunnerDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        String testClass = "ru.zheleznikov.test.SimpleArrayZheleTest";
        RunnerImpl runner = new RunnerImpl(testClass);
        runner.go();
    }
}
