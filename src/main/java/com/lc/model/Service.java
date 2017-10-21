package com.lc.model;

public class Service {
    public Service(){
    }

    public Service(Dao dao) {
        this.dao = dao;
    }

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
