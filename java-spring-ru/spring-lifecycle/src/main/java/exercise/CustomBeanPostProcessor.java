// Класс для самостоятельной работы

package exercise;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
// BEGIN

@Component
public class CustomBeanPostProcessor implements BeanPostProcessor{
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("Called PostProcessBeforeInitialization: " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("Called PostProcessAfterInitialization: " + beanName);
        return bean;
    }
}
// END
