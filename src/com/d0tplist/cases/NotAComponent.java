package com.d0tplist.cases;

import java.lang.reflect.Constructor;

public class NotAComponent {


    public static void main(String[] args) {

        Constructor<?>[] constructors = ButtonTest.class.getDeclaredConstructors();

        Constructor<?> result = null;



        for (Constructor<?> constructor : constructors) {
            if(constructor.getParameterCount() == 0){
                System.out.println(constructor.isAccessible());
                result = constructor;
            }
        }

        if(result == null){
            try {
                ButtonTest buttonTest = ButtonTest.class.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }
}
