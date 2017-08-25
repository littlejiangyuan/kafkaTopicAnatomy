package zookeeper;

import org.apache.zookeeper.ZooKeeper;

import java.util.List;

/**
 * Created by jiangyuan5 on 2017/8/24.
 * zk每个节点基本的数据结构
 */
public class ZkModel {
    public List<String> children = null;

    public byte [] data = null;

    public String pathBase = null;

    public ZooKeeper zk;
}
