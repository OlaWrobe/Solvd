package com.solvd.realestate;

import com.solvd.realestate.agency.RealEstateAgency;
import com.solvd.realestate.person.CityLocation;
import com.solvd.realestate.person.ClientForm;
import com.solvd.realestate.transactions.Bill;
import com.solvd.realestate.transactions.TransactionFee;

import java.lang.reflect.*;

public class MyAppRunner {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        String className = "com.solvd.realestate.transactions.Bill";

        Class<Bill> companyClass = (Class<Bill>) Class.forName(className);

        Field[] fields = companyClass.getDeclaredFields();
        System.out.println("Fields:");
        for (Field field : fields) {
            System.out.println("Name: " + field.getName());
            System.out.println("Type: " + field.getType().getSimpleName());
            System.out.println("Modifiers: " + Modifier.toString(field.getModifiers()));
            System.out.println("---");
        }

        Constructor<?>[] constructors = companyClass.getDeclaredConstructors();
        System.out.println("\nConstructors:");
        for (Constructor<?> constructor : constructors) {
            System.out.println("Name: " + constructor.getName());
            System.out.println("Modifiers: " + Modifier.toString(constructor.getModifiers()));
            Parameter[] parameters = constructor.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println("Parameter: " + parameter.getName() + " (" + parameter.getType().getSimpleName() + ")");
            }
            System.out.println("---");
        }

        Method[] methods = companyClass.getDeclaredMethods();
        System.out.println("\nMethods:");
        for (Method method : methods) {
            System.out.println("Name: " + method.getName());
            System.out.println("Return Type: " + method.getReturnType().getSimpleName());
            System.out.println("Modifiers: " + Modifier.toString(method.getModifiers()));

            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println("Parameter: " + parameter.getName() + " (" + parameter.getType().getSimpleName() + ")");
            }
            System.out.println("---");
        }

        Constructor<Bill> billConstructor = companyClass.getDeclaredConstructor();
        Bill bill = billConstructor.newInstance();


        Method myMethod = companyClass.getDeclaredMethod("setAmount", double.class);
        myMethod.setAccessible(true);
        myMethod.invoke(bill, 42.0);

        Method myMethod2 = companyClass.getDeclaredMethod("calculateBillForMisc", TransactionFee.class);
        myMethod2.setAccessible(true);
        Object result = myMethod2.invoke(bill, TransactionFee.CONSULTATION);
        System.out.println("Result: " + result);
    }
}
