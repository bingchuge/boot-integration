package org.example;

public class DCF {
    public static double calculateV1(double FCF0, double g1, double r, int n) {
        double V1 = 0;
        for (int i = 1; i <= n; i++) {
            double FCFi = FCF0 * Math.pow(1 + g1, i);
            V1 += FCFi / Math.pow(1 + r, i);
        }
        return V1;
    }

    public static double calculateV2(double FCFn, double g2, double r, int n) {
        double FCFn1 = FCFn * (1 + g2);
        double V2 = FCFn1 / (r - g2);
        double V2p = V2 / Math.pow(1 + r, n);
        return V2p;
    }

    public static void main(String[] args) {
        double FCF0 = 20; // 第0年的自由现金流
        double g1 = 0.12; // 第一阶段的增长率
        double r = 0.8; // 折现率
        int n = 8; // 第一阶段的年数
        double g2 = 0.05; // 第二阶段的增长率

        double V1 = calculateV1(FCF0, g1, r, n);
        double FCFn = FCF0 * Math.pow(1 + g1, n); // 第n年的自由现金流
        double V2p = calculateV2(FCFn, g2, r, n);

        double V = V1 + V2p; // 公司的总价值

        System.out.println("价值为：" + V);
    }
}
