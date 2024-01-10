package org.example.sa.rbac.demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String str = "ds >= 20230101 && ds < 20230201, area=ZGGG|ZHHH";
        List<String> partitions = new ArrayList<>(Arrays.asList(str.split(",")));

        for (String partition : partitions) {
            // 如果是时间范围，> 或 < 为范围分隔符，可能只有一个
            if (partition.contains(">") || partition.contains("<")) {
                // 看看有没有
                // 区分是 > 还是 >= 和 < 还是 <= ,取分割的第二个字符

            }
        }
    }

}
