package zookeeper;

import org.apache.zookeeper.ZooKeeper;

import java.util.List;

/**
 * Created by jiangyuan5 on 2017/8/24.
 */
public class Topics extends ZkModel {

    public Topics(ZooKeeper zk) {
        pathBase = "/consumers";

        this.zk = zk;
    }

    public void getChildren() {
        try {
            children = zk.getChildren( pathBase, true);
        } catch (Exception e) {
            System.out.println("get children exception! path=" + pathBase + ", mes=" + e.getMessage());
        }
    }

    public void getData() {
        try {
            data = zk.getData(pathBase, true, null);
            if(data == null) {
                data = new byte[0];
            }
        } catch (Exception e) {
            data = new byte[0];
            System.out.println("get data exception! path=" + pathBase + ", mes=" + e.getMessage());
        }
    }

    public void output() {
        getChildren();
        getData();

        System.out.println("节点数据内容：");
        System.out.println("    " + new String(data));

        System.out.println("子节点有：");
        for(String s : children) {
            System.out.println("    " + s);
        }


    }
}
