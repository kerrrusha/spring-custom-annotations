package com.kerrrusha.springcustomannotations.joker;

import com.kerrrusha.springcustomannotations.annotation.InjectRandomInt;
import com.kerrrusha.springcustomannotations.annotation.InjectRandomJoke;
import lombok.Setter;

@Setter
public class FunnyJoker implements Joker {

    @InjectRandomInt(from = 1, to = 5)
    private int repeat;

    @InjectRandomJoke
    private String joke;

    @Override
    public void sayJoke() {
        for (int i = 0; i < repeat; i++) {
            System.out.println(joke);
        }
    }

}
