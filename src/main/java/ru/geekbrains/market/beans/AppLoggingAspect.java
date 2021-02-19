package ru.geekbrains.market.beans;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Aspect
@Component
@Slf4j
public class AppLoggingAspect {
    private Map<String, Integer> methodMap = new HashMap<>();

    @Before("execution(public * ru.geekbrains.market.*.*.*(..))")
    public void beforeAnyMethod(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        if (methodMap.containsKey(methodSignature.toString()))
            methodMap.put(methodSignature.toString(), methodMap.get(methodSignature.toString()) + 1);
        else methodMap.put(methodSignature.toString(), 1);
        writeFile(methodMap);
    }

    private void writeFile(Map<String, Integer> methodMap) {
        File dir = new File(System.getProperty("user.dir")
                .replace("\\", "/")
                + "/method_maps/");
        if (!dir.exists()) dir.mkdir();
        File file = new File(dir, "log.txt");
        if (file.exists()) file.delete();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            Date date = new Date();
            bw.write(date.toString() + "\n");
            for (Map.Entry entry : methodMap.entrySet()) {
                bw.write(entry.getKey() + ": \t" + entry.getValue() + "\n");
            }
            bw.write(findMaxRunningMethod(methodMap));
            bw.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private String findMaxRunningMethod(Map<String, Integer> methodMap) {
        int max = 0;
        for (Map.Entry entry : methodMap.entrySet()) {
            if (((Integer) entry.getValue()).intValue() > max) max = ((Integer) entry.getValue()).intValue();
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("\nМаксимальное количество вызовов %s: \n", max));
        for (Map.Entry entry : methodMap.entrySet()) {
            if (((Integer) entry.getValue()).intValue() == max) stringBuilder.append(entry.getKey().toString() + "\n");
        }
        return stringBuilder.toString();
    }

}
