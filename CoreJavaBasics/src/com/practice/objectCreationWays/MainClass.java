package com.practice.objectCreationWays;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MainClass {

    public static void main(String[] args) {

        //Creating class using new keyword - 1st method
        ClassInPicture ob = new ClassInPicture();
        System.out.println(ob.newMethod);

        usingClassForNameMethod();
        usingCloneMethod();
        usingSerializationMethod();
        usingConstructorNewinstanceMethod();
    }

    /*Creating using Class.forName. Here Class.forName does not create the class instead loads the class.
       newInstance method is to be used for creating the actual object. */
    private static void usingClassForNameMethod() {
        try {
            ClassInPicture ob = ClassInPicture.class.newInstance(); //way one- not using Class.forName
            Class<ClassInPicture> cl = (Class<ClassInPicture>) Class.forName("com.practice.objectCreationWays.ClassInPicture");
            ClassInPicture ob2 = cl.newInstance();
            System.out.println(ob2.rmi);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /*Whenever clone() is called on any object, the JVM actually creates a new object and copies all content of the previous object into it.
     Creating an object using the clone method does not invoke any constructor. In order to use the clone() method on an object we need
     to implement Cloneable and define the clone() method in it.*/
    private static void usingCloneMethod() {
        ClassInPicture mainObject = new ClassInPicture();
        System.out.println("Main Object - " + mainObject);
        try {
            ClassInPicture ob3 = (ClassInPicture) mainObject.clone();
            System.out.println("Cloned Object - " + ob3 + "----" + ob3.value);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    /*Whenever we serialize and then deserialize an object, JVM creates a separate object. In deserialization,
      JVM doesn’t use any constructor to create the object. To deserialize an object we need to implement the
      Serializable interface in the class.*/
    private static void usingSerializationMethod() {
        serializeObject();
        ClassInPicture ob4 = deserializeObject();
        System.out.println("Deserialized Object - " + ob4 + " ----" + ob4.value2);
    }

    /*This is similar to the newInstance() method of a class. There is one newInstance() method in the
    java.lang.reflect.Constructor class which we can use to create objects. It can also call the parameterized constructor,
     and private constructor by using this newInstance() method. Both newInstance() methods are known as reflective ways
      to create objects. In fact newInstance() method of Class internally uses newInstance() method of Constructor class.
     */
    private static void usingConstructorNewinstanceMethod() {
        try {
            Constructor<ClassInPicture> declaredConstructor = ClassInPicture.class.getDeclaredConstructor();
            ClassInPicture classInPicture = declaredConstructor.newInstance();
            System.out.println("Object usingConstructorNewinstanceMethod is - " + classInPicture);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static ClassInPicture deserializeObject() {
        ClassInPicture deserializedObject = null;
        try {
            FileInputStream fi = new FileInputStream("serializedFolder/SerializedObject.txt");
            ObjectInputStream oos = new ObjectInputStream(fi);
            deserializedObject = (ClassInPicture) oos.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return deserializedObject;
    }

    private static void serializeObject() {
        ClassInPicture serializedClass = new ClassInPicture();
        System.out.println("Serialized Object - " + serializedClass);
        try {
            FileOutputStream fo = new FileOutputStream("serializedFolder/SerializedObject.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fo);
            oos.writeObject(serializedClass);
            oos.close();
            fo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
