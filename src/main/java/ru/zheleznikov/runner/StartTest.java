package ru.zheleznikov.runner;

public class StartTest {
    public static void main(String[] args) throws ClassNotFoundException {
        String testClass = "ru.zheleznikov.test.SimpleArrayZheleTest";
        Runner runner = new Runner(testClass);
        runner.go();
    }
}
