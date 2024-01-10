package org.example;

public class Main {
    public static void main(String[] args) {
        // 当期每股自由现金流
        int d0 = 10;
        // 一阶段企业增长率
        double g1 = 0.1;
        // 二阶段企业增长率
        double g2 = 0.1;
        // 预期回报率
        double r = 0.12;
        // 一阶段贴现期数
        int n = 5;

        // 第n阶段的每股自由现金流
        int dn = 0;
        for (int i = 0; i < n; i++) {
            dn += d0 * Math.pow(1 + g1, i);
        }

    }
}
