package com.cydeo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMapService <T,ID>{

    public Map<ID,T> map = new HashMap<>(); // <- dataBase where all DATA will be saved


    T save(ID id, T object){
        map.put(id,object);   // <GENERIC> .save() method will work for ALL implementations (RoleService, UserService, etc..)
        return object;
    }

    List<T> findAll(){
        return new ArrayList<>(map.values());
    }

    T findById(ID id){
        return map.get(id);
    }

    void deleteById(ID id){
        map.remove(id);
    }


}
