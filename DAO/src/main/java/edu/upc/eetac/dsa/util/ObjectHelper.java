package edu.upc.eetac.dsa.util;


import java.lang.reflect.Field;

public class ObjectHelper {
    public static String[] getFields(Object entity) {

        Class theClass = entity.getClass();

        Field[] fields = theClass.getDeclaredFields();

        String[] sFields = new String[fields.length];
        int i=0;

        for (Field f: fields) sFields[i++]=f.getName();

        return sFields;
    }


    public static void setter(Object object, String property, Object value) throws IllegalAccessException {
        // FALTA CORREGIR

        Class theClass = object.getClass();
        Field[] fields = theClass.getDeclaredFields();
        String[] sFields = new String[fields.length];

        for (Field f: fields) {
            if (f.getName() == property){
                f.set(object, value);
            }
        }

    }

    public static Object getter(Object object, String property) throws IllegalAccessException {
        Object value = null;
        Class theClass = object.getClass();
        Field[] fields = theClass.getDeclaredFields();
        String[] sFields = new String[fields.length];

        for (Field f: fields) {
            if (f.getName() == property)
                value = f.get(object);
        }

        return value;
    }
}
