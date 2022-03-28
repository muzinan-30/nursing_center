package daoimpl;

import po.Record;
import utils.Tools;

import java.util.Vector;
import dao.*;
/*
* 记录Dao的实现*/
public class RecordDaoimpl implements RecordDao{
    //记录
    @Override
    public int addRecord(Record r) {
        try {
            Tools.write("record", r.toString(), true);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Vector<Vector> getAllRecord() {
        Vector<Vector> vectorrecord = Tools.getRecordVector("record");
        return vectorrecord;
    }

}
