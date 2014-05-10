package paramonov.valentine.cglib;

import net.sf.cglib.proxy.Enhancer;

public class Main {
    public static void main(String[] args) {
        final SomeClass someObject = new SomeClass();
        final SomeClass modifiedObject = createProxy(someObject);
        modifiedObject.doStuff();
    }

    private static <T> T createProxy(T someT) {
        final Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(someT.getClass());
        final CglibInterceptor callback = new CglibInterceptor(someT);
        enhancer.setCallback(callback);
        final T modifiedObject = (T) enhancer.create();

        return modifiedObject;
    }
}
