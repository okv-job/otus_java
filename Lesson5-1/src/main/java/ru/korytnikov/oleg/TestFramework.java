package ru.korytnikov.oleg;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class TestFramework {

    public static void main(String... args) {
        System.out.println("RUN SINGLE TEST");
        runTest("ru.korytnikov.oleg.test.Test");
        System.out.println("RUN ALL TESTS FROM PACKAGE");
        runTest("ru.korytnikov.oleg.test");
    }

    public static void runTest(String name) {
        try {
            Object object = ReflectionHelper.instantiate(Class.forName(name));
            Map<String, List<Method>> res = ReflectionHelper.getMethods(object);
            if (res.get("test").size() > 0) {
                run(res.get("before"), object);
                run(res.get("test"), object);
                run(res.get("after"), object);
            }
        } catch (ClassNotFoundException e) {
            File file = new File(System.getProperty("user.dir") +
                    "/src/main/java/" + name.replace('.', '/'));
            if (file.isDirectory()){
                for (File path : file.listFiles()){
                    if (path.isFile()) runTest(name + "." + path.getName().split("\\.")[0]);
                }
                return;
            }
            System.out.println("Class or package not found");
        }
    }

    private static void run(List<Method> list, Object object, Object... args) {
        list.forEach(method -> {
            if (ReflectionHelper.callMethod(object, method.getName(), args))
                System.out.println(method.getName() + " : IS PASSED");
            else System.out.println(method.getName() + " : IS FAILED");
        });
    }

}
