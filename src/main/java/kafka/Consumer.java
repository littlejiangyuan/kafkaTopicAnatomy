package kafka;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import zookeeper.ZkClient;


import java.util.Arrays;
import java.util.ArrayList;

/**
 * Created by jiangyuan5 on 2017/8/24.
 */

public class Consumer {

    private final static String TOPIC = "uve_stat_log";

    private static Properties properties;

    static {
        properties = new Properties();
        String path = Consumer.class.getResource("/").getFile().toString() + "kafka.properties";

        try {
            FileInputStream fis = new FileInputStream(new File(path));
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取消息
     *
     * @throws Exception
     */
    public void kafkaConsumer() throws Exception {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList("uve_stat_log"));

        final int minBatchSize = 10;
        List<ConsumerRecord<String, String>> buffer = new ArrayList<ConsumerRecord<String, String>>();

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(200);
            for (ConsumerRecord<String, String> record : records) {
                //buffer.add(record);
                System.out.println(record.toString());

                consumer.commitSync();

                Thread.sleep(20000);

                //ZkClient zc = new ZkClient();

                //Thread.sleep(100000);


            }

            /*
            if (buffer.size() >= minBatchSize) {
                consumer.commitSync();
                buffer.clear();
            }
            */
        }

    }
}