package ru.zheleznikov.runner;

public class Audit {

    private int passed = 0;
    private int failed = 0;
    private int all = 0;
    private String errLog = "";

    public void increasePassed() {
        this.passed++;
        this.all++;
    }

    public void increaseFailed() {
        this.failed++;
        this.all++;
    }

    public void showInfoAboutFail(String name, Exception e) {
        this.errLog += "\nTest " + name + "() failed with exception:\n" + e.toString() + "\n";
    }

    public void showResult() {
        System.out.println("\n====\nTEST RESULTS:");
        System.out.println("ALL: " + this.all);
        System.out.println("PASSED: " + this.passed);
        System.out.println("FAILED: " + this.failed);
        System.out.println(this.errLog);
        System.out.println("END TEST RESULTS\n====");
    }



}
