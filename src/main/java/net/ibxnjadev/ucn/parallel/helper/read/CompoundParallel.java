package net.ibxnjadev.ucn.parallel.helper.read;

import net.ibxnjadev.ucn.parallel.helper.ParallelClass;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CompoundParallel {

    private final Map<String, ParallelClass> values = new HashMap<>();

    public void add(ParallelClass parallelClass) {
        System.out.println("Added");
        values.put(parallelClass.getIdentifier(), parallelClass);
    }

    public ParallelClass find(String identifier) {
        return values.get(identifier);
    }

    public Collection<ParallelClass> values() {
        return values.values();
    }

}
