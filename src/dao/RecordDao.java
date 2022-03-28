package dao;

import po.Record;

import java.util.Vector;
/*
 * 记录管理操作接口
 *
 * */
public interface RecordDao {
    //评估记录
    //添加记录
    public int addRecord(Record r);
    //获取所有的记录
    public Vector<Vector> getAllRecord();
}
