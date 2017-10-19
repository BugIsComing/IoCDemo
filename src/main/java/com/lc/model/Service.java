package com.lc.model;

public class Service {
    public Dao getDao() {
        return dao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    private Dao dao;

    public void save(){
        dao.save();
    }

    public void delete(){
        dao.delete();
    }
}
