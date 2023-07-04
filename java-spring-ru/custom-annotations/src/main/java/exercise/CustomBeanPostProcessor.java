package exercise;

import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Indexed;

// BEGIN
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor{
    private Map<String, String> beansWithAnnotation = new HashMap<>();

    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (bean.getClass().isAnnotationPresent(Inspect.class)) {
            Inspect inspectAnnotation = bean.getClass().getAnnotation(Inspect.class);
            beansWithAnnotation.put(beanName, inspectAnnotation.level());
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) {
        Object modifiedBean = null;
        if (beansWithAnnotation.containsKey(beanName)) {
            System.out.println("BIN WHICH MARKED OUR ANNOTATION: " + beanName);
            modifiedBean = Proxy.newProxyInstance(
                    bean.getClass().getClassLoader(),
                    bean.getClass().getInterfaces(),
                    (proxy, method, args) -> {
                        method.setAccessible(true);
                        System.out.println("Was called method: " + method.getName() + "() with arguments: "
                                + Arrays.toString(args));
                        return method.invoke(bean, args);
                    }
            );
        }

        return modifiedBean;
    }
}
// END
