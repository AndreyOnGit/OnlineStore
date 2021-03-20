package ru.gb.producer;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class ITBlog {
    private static final String EXCHANGE_NAME = "directExchanger";

    public ITBlog() {
        try {
            go();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    private void go() throws IOException, TimeoutException {
        Logger log = LoggerFactory.getLogger("LOG");
        log.info("Subscriber started...");

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

            Scanner scanner = new Scanner(System.in);
            log.warn("Type like: 'Topic_name' some message. The first word should be topic name.");
            while (true) {
                log.info("You can type...");
                String msg = scanner.nextLine();
                if (!msg.trim().isBlank()) {
                    String words[] = msg.trim().split(" ", 2);
                    String topic = words[0];
                    String text = words[1];
                    channel.basicPublish(EXCHANGE_NAME, topic, null, text.getBytes("UTF-8"));
                    log.info("Current topic: " + topic);
                    log.info("Sent '" + text + "'");
                }
            }
        }

    }
}

