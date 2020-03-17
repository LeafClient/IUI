package com.leafclient.iui.animation;

import java.util.function.Function;

import static java.lang.Math.*;

public enum Transition {

    LINEAR(x -> x),
    /**
     * See https://www.reddit.com/r/gamedev/comments/4je7bz/nonbezier_sigmoid_easing_curves/
     */
    SMOOTH_STEP(x -> 3 * pow(x, 2) - 2 * pow(x, 3)),
    /**
     * See https://hackernoon.com/ease-in-out-the-sigmoid-factory-c5116d8abce9 for eases
     */
    LOW_EASE(a -> (0.5 / (1.0 / (1 + exp(-a * 2.5) - 0.5))) * (1.0 / (1 + exp(-a * 2.5) - 0.5)) + 0.5),
    EASE(a -> (0.5 / (1.0 / (1 + exp(-a * 4.5) - 0.5))) * (1.0 / (1 + exp(-a * 4.5) - 0.5)) + 0.5),
    HIGH_EASE(a -> (0.5 / (1.0 / (1 + exp(-a * 7.5) - 0.5))) * (1.0 / (1 + exp(-a * 7.5) - 0.5)) + 0.5);

    private final Function<Double, Double> function;

    /**
     * Constructors for the {@link Transition} object.
     *
     * @param function Function
     */
    Transition(Function<Double, Double> function) {
        this.function = function;
    }

    /**
     * Applies this transition to specified value and returns it.
     *
     * @param value Value
     * @return Transitioned value
     */
    public double apply(double value) {
        return function.apply(value);
    }

}
