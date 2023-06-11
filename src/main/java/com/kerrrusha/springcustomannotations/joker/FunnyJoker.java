package com.kerrrusha.springcustomannotations.joker;

import com.kerrrusha.springcustomannotations.annotation.InjectRandomInt;
import com.kerrrusha.springcustomannotations.annotation.InjectRandomJoke;
import com.kerrrusha.springcustomannotations.annotation.PostProxiesCreated;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Setter;

import static com.kerrrusha.springcustomannotations.SpringCustomAnnotationsApplication.logApplicationContextCreated;
import static java.util.Objects.nonNull;

@Setter
public class FunnyJoker implements Joker {

    @InjectRandomInt(from = 1, to = 5)
    private int repeatJokeCount;

    @InjectRandomJoke
    private String joke;

    public FunnyJoker() {
        System.out.println("Constructor Phase I");
        logInnerFieldsAreInitialized();
        logApplicationContextCreated();
        System.out.println();
    }

    /**
     * At Phase II constructor we can access fields,
     * that must be initialized by Spring or in Phase I constructor
     */
    @PostConstruct
    public void init() {
        System.out.println("Constructor Phase II");
        logInnerFieldsAreInitialized();
        logApplicationContextCreated();
        System.out.println();
    }

    /**
     * Only at Phase III constructor we can access annotations, that uses proxies
     * (e.g @Transactional)
    */
    @PostProxiesCreated
    public void lateInit() {
        System.out.println("Constructor Phase III");
        logInnerFieldsAreInitialized();
        logApplicationContextCreated();
        System.out.println();
    }

    @PreDestroy
    public void destructor() {
        System.out.println("I'm being destroyed...(");
    }

    private void logInnerFieldsAreInitialized() {
        System.out.println("Inner fields are initialized: " + innerFieldsAreInitialized());
    }

    private boolean innerFieldsAreInitialized() {
        return nonNull(joke);
    }

    @Override
    public void sayJoke() {
        for (int i = 0; i < repeatJokeCount; i++) {
            System.out.println(joke);
        }
    }

}
