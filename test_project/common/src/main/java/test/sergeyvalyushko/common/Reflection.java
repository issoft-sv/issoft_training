package test.sergeyvalyushko.common;

import org.reflections.Reflections;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Reflection<T> {

    public Set<Class<? extends T>> createClassesSet (Class<T> clas, String location){
        Reflections reflections = new Reflections(location);
        Set<Class<? extends T>> classes = reflections.getSubTypesOf(clas);
        return classes;
    }

    public List<T> createClassesInstances(Class<T> clas, String location){
        List<T> listOfInstances = new ArrayList<T>();
        for (Class<? extends T> cla : this.createClassesSet(clas, location)) {
            try {
                listOfInstances.add((T) cla.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listOfInstances;
    }
}
