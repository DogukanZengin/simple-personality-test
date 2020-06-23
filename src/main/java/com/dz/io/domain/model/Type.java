package com.dz.io.domain.model;

public enum Type {
    SINGLE_CHOICE,
    SINGLE_CHOICE_CONDITIONAL,
    NUMBER_RANGE,
    NONE;
    public static Type getType(String value){
        for(Type type : Type.values()){
            if(type.name().equalsIgnoreCase(value)){
                return type;
            }
        }
        return Type.NONE;
    }
}
