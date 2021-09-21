package com.practice.objectCreationWays;

import java.io.Serializable;

public class ClassInPicture implements Cloneable, Serializable {

    String newMethod = "Object using new";
    String rmi = "Object using class for name";
    String value = "Cloned";
    String value2 = "Serialized";

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public ClassInPicture() {
    }
}
