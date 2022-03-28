package service;

import po.Record;

import java.util.Vector;
/*
* 记录管理服务层
*
* */
public interface RecordService {
    // 记录管理
    //添加
    public int addRecord(Record r);
    //获取所有的记录
    public Vector<Vector> getAllRecord();
}
