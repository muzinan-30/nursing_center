package serviceimpl;

import dao.RecordDao;
import daoimpl.RecordDaoimpl;
import po.Record;

import java.util.Vector;
import service.*;
/*
 * 记录管理服务层的实现类
 * */
public class RecordServiceimpl implements RecordService{
    RecordDao Dao=new RecordDaoimpl();
    @Override
    public Vector<Vector> getAllRecord(){
        return Dao.getAllRecord();
    }

    @Override
    public int addRecord(Record r) {
        return Dao.addRecord(r);
    }


}
