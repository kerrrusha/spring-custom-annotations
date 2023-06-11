package com.kerrrusha.springcustomannotations.joker;

import com.kerrrusha.springcustomannotations.annotation.InjectRandomInt;
import com.kerrrusha.springcustomannotations.annotation.InjectRandomJoke;
import com.kerrrusha.springcustomannotations.annotation.PostProxiesCreated;
import jakarta.annotation.PostConstruct;
import lombok.Setter;

import static com.kerrrusha.springcustomannotations.SpringCustomAnnotationsApplication.logApplicationContextCreated;
import static java.util.Objects.nonNull;

@Setter
public class FunnyJoker implements Joker {

    @InjectRandomInt(from = 1, to = 5)
    private int repeat;

    @InjectRandomJoke
    private String joke;

    public FunnyJoker() {
        System.out.println("Constructor Phase I");
        logInnerFieldsAreInitialized();
        logApplicationContextCreated();
        System.out.println();
    }

    @PostConstruct
    public void init() {
        System.out.println("Constructor Phase II");
        logInnerFieldsAreInitialized();
        logApplicationContextCreated();
        System.out.println();
    }

    @PostProxiesCreated
    public void lateInit() {
        System.out.println("Constructor Phase III");
        logInnerFieldsAreInitialized();
        logApplicationContextCreated();
        System.out.println();
    }

    private void logInnerFieldsAreInitialized() {
        System.out.println("Inner fields are initialized: " + innerFieldsAreInitialized());
    }

    private boolean innerFieldsAreInitialized() {
        return nonNull(joke);
    }

    @Override
    public void sayJoke() {
        for (int i = 0; i < repeat; i++) {
            System.out.println(joke);
        }
    }

}
