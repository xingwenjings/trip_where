package com.example.kaoshi_a.dbUtils;

import com.example.kaoshi_a.beans.DatasBean;
import com.example.kaoshi_a.dao.DaoSession;
import com.example.kaoshi_a.dao.DatasBeanDao;

import java.util.List;

public class DbUtils {

    private static DaoSession daoSession;

    public static DatasBean query(DatasBean datasBean){
        daoSession = MyApp.getDaoSession();
       return daoSession.queryBuilder(DatasBean.class)
                .where(DatasBeanDao.Properties.Title.eq(datasBean.getTitle()))
                .build()
                .unique();
    }
    public static List<DatasBean> queryList(){
        DaoSession daoSession = MyApp.getDaoSession();
        List<DatasBean> datasBeans = daoSession.loadAll(DatasBean.class);
        return  datasBeans;
    }
    public static void insert(DatasBean datasBean){
        if (query(datasBean)==null){
            daoSession.insert(datasBean);
        }
    }
    public static void delete(DatasBean datasBean){
        if (query(datasBean)!=null){
            daoSession.delete(datasBean);
        }
    }
}
