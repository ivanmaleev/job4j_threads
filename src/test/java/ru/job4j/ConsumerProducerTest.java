package ru.job4j;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.produserconsumer.SimpleBlockingQueue;

import static org.hamcrest.Matchers.is;

public class ConsumerProducerTest {

    @Test
    public void main() {
        String[] messages = {
                "Oracle today announced",
                " the availability of Java 17",
                ", the latest version of the world’s",
                " number one programming language ",
                "and development platform. ",
                "Java 17 delivers thousands of",
                " performance, stability, and security updates,",
                " as well as 14 JEPs (JDK Enhancement Proposals)",
                " that further improve the Java language",
                " and platform to help developers be more productive.",
                "done"
        };

        ConsumerProducer.setQueue(new SimpleBlockingQueue<>(5));
        ConsumerProducer.setMes(messages);
        ConsumerProducer.Consumer consumer = new ConsumerProducer.Consumer();
        ConsumerProducer.Producer producer = new ConsumerProducer.Producer();
        Thread threadConsumer = new Thread(consumer);
        Thread threadProducer = new Thread(producer);
        threadProducer.start();
        threadConsumer.start();
        try {
            threadProducer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            threadConsumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertThat(ConsumerProducer.getQueue().size(), is(0));
    }
}