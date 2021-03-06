package ru.geekbrains.market.beans;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


// возможно пригодится для отладки
@Aspect
@Component
@Slf4j
public class AppAnalysis {
//    private Map<String, Long> controllerMethodMap = new HashMap<>();

//    @Around("execution(public * ru.geekbrains.market.controllers.*.*(..))")
//    public Object methodProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        long begin = System.currentTimeMillis();
//        Object out = proceedingJoinPoint.proceed();
//        long end = System.currentTimeMillis();
//        long duration = end - begin;
//        controllerMethodMap.put(proceedingJoinPoint.getSignature().toString(), duration);
//        writeFile(controllerMethodMap);
//        return out;
//    }
//
//    private void writeFile(Map<String, Long> methodMap) {
//        File dir = new File(System.getProperty("user.dir")
//                .replace("\\", "/")
//                + "/method_maps/");
//        if (!dir.exists()) dir.mkdir();
//        File file = new File(dir, "duration.txt");
//        if (file.exists()) file.delete();
//        if (!file.exists()) {
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                log.error(e.getMessage());
//            }
//        }
//
//        try {
//            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
//            Date date = new Date();
//            bw.write(date.toString() + "\n");
//            for (Map.Entry entry : methodMap.entrySet()) {
//                bw.write(entry.getKey() + ": \t" + entry.getValue() + "\n");
//            }
//            bw.write(findMaxDuration(controllerMethodMap));
//            bw.close();
//        } catch (IOException e) {
//            log.error(e.getMessage());
//        }
//    }
//
//
//    private String findMaxDuration(Map<String, Long> controllerMethodMap) {
//        long max = 0;
//        for (Map.Entry entry : controllerMethodMap.entrySet()) {
//            if (((Long) entry.getValue()).longValue() > max) max = ((Long) entry.getValue()).longValue();
//        }
//
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(String.format("\nМаксимальное время работы %s: \n", max));
//        for (Map.Entry entry : controllerMethodMap.entrySet()) {
//            if (((Long) entry.getValue()).longValue() == max) stringBuilder.append(entry.getKey().toString() + "\n");
//        }
//        return stringBuilder.toString();
//    }
}
