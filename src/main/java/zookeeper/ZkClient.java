package zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by jiangyuan5 on 2017/8/23.
 */
public class ZkClient implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    private ZooKeeper zk;

    public ZkClient() {
        try {
            zk = new ZooKeeper("10.13.40.53:2181,10.13.2.43:2181", 6000, this);
            connectedSemaphore.await();
        } catch(Exception e) {
            System.out.println("create ZooKeeper object exception! msg=" + e.getMessage());
        }
    }

    public ZooKeeper getZkClient() {
        return zk;
    }

    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            System.out.println("ZooKeeper connect success! ");

            Topics t = new Topics(zk);
            t.output();
        } else if (Event.KeeperState.Expired == event.getState()) {
            System.out.println("ZooKeeper connect expire! ");
        } else {

        }
    }



}
