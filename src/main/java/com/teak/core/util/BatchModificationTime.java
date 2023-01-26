package com.teak.core.util;

import org.joda.time.DateTime;

import java.io.*;

/**
 * @author 柚mingle木
 * @version 1.0
 * @date 2023/1/17
 */
public final class BatchModificationTime {

    private BatchModificationTime() {
    }

    public static void operation(String inputDataPath, String outputDataPath) {
        /*String inputDataPath = "E:\\BaiduNetdiskDownload\\07尚医通预约挂号平台【完结】\\资料\\资料【瑞客论坛 www.ruike1.com】\\资料\\尚硅谷预约挂号平台-day06资料\\day06\\资料\\03-示例数据\\schedule.json";
        String outputDataPath = "E:\\BaiduNetdiskDownload\\07尚医通预约挂号平台【完结】\\资料\\资料【瑞客论坛 www.ruike1.com】\\资料\\尚硅谷预约挂号平台-day06资料\\day06\\资料\\03-示例数据\\schedule2.json";*/

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputDataPath));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputDataPath));
            String reader;
            int i = 0;
            int temp = -1;
            while ((reader = bufferedReader.readLine()) != null) {
                System.out.println("reader = " + reader);
                temp++;
                if ((temp % 3) == 0) {
                    ++i;
                }
                /*\d{4}(\-|\/|.)\d{1,2}\1\d{1,2}*/
                String resultsAfterReplaceAll = reader.replaceAll("\\d{4}(\\-|\\/|.)\\d{1,2}\\1\\d{1,2}", new DateTime().plusDays(i++).toString("yyyy-MM-dd"));
                bufferedWriter.write(resultsAfterReplaceAll);
                bufferedWriter.flush();
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
