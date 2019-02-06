package com.d0tplist.cases;


import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HackyClass extends JPanel {


    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        Method[] declaredMethods = ButtonTest.class.getDeclaredMethods();

        JButton buttonTest = new JButton();

        for (Method declaredMethod : declaredMethods) {
            if(declaredMethod.getParameterCount() == 0){
                declaredMethod.invoke(buttonTest);
            }
        }

        Field[] declaredFields = buttonTest.getClass().getDeclaredFields();


    }
}
