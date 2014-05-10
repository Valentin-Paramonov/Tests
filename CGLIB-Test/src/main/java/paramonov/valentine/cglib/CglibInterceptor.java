package paramonov.valentine.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibInterceptor implements MethodInterceptor {
    private final Object someObject;

    public CglibInterceptor(Object someObject) {
        this.someObject = someObject;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before interception.");
        final long before = System.currentTimeMillis();
        final Object modifiedObject = method.invoke(someObject, objects);
        System.out.println("After interception.");
        final long after = System.currentTimeMillis();
        System.out.println("Method execution took: " + (after - before) + "ms");

        return modifiedObject;
    }
}
