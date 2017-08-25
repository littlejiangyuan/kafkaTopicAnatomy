import kafka.Consumer;

import org.apache.zookeeper.ZooKeeper;
import zookeeper.ZkClient;
import zookeeper.Topics;

/**
 * Created by jiangyuan5 on 2017/8/23.
 */
public class Start {

    public static void main(String[] args) {
        //ZkClient zc = new ZkClient();

        Consumer cs = new Consumer();

        try {
            cs.kafkaConsumer();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
