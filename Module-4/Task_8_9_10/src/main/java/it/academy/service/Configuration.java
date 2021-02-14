package it.academy.service;

import it.academy.pojos.Person;
import it.academy.util.AddressAnnotated;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@ComponentScan(value = "it.academy",includeFilters = @ComponentScan.Filter(
        type = FilterType.ANNOTATION, classes = AddressAnnotated.class) )
public class Configuration {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext(Configuration.class);
        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
        Person person = context.getBean("person", Person.class);
        System.out.println(person);

        context.close();
    }
}
