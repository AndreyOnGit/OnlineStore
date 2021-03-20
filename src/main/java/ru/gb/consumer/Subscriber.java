package ru.gb.consumer;

import com.rabbitmq.client.*;
import org.slf4j.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;


public class Subscriber {
    private static final String EXCHANGE_NAME = "directExchanger";
    private static final String CMD = "set_topic";
    private String topic;

    public Subscriber() {
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
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        String queueName = channel.queueDeclare().getQueue();
        log.info("Queue name: " + queueName);

        log.warn("You should type topic like: " + CMD + " php");

        new Thread(new Runnable() {
            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String message = scanner.nextLine();

                    if (!message.trim().isBlank()) {
                        if (message.trim().startsWith(CMD)) {
                            topic = message.replace(CMD, "").trim();
                            try {
                                channel.queueBind(queueName, EXCHANGE_NAME, topic);
                                log.info("You have added a subscription to the next topic: " + topic);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            log.info("Waiting messages...");
                        }
                        if (message.trim().startsWith("unbind")) {
                            topic = message.replace("unbind", "").trim();
                            log.warn("You have unsubscribed from the following topic: " + topic);
                            try {
                                channel.queueUnbind(queueName, EXCHANGE_NAME, topic);  } catch (IOException e) {
                                e.printStackTrace();
                            }
                            log.info("Waiting messages...");
                        }
                    }
                }
            }
        }).start();

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String msg = new String(delivery.getBody(), "UTF-8");
            log.info("Received: '" + msg + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });

    }
}
