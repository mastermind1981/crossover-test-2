package com.crossover.rajesh_ta_framework.base;

public enum Environments {
    DEMO("https://demo-cupse.cuprodigy.com/demo"),
    QA("https://qa-cupse.cuprodigy.com/qa");

    private String url;

    private Environments(String url) {
        this.url = url;
    }
}
