/*
 * The MIT License
 *
 * Copyright 2017 University of Southampton IT Innovation Centre.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package eu.operando.pq;

import io.swagger.model.Questionobject;
import java.util.List;
/**
 * Reusable questionnaire implementation. Called by the Web Service Rest
 * endpoint.
 * @author pjg
 */
public class SensitivityCalculation {

    public enum Sensitivity{
        UNCONCERNED(0),
        PRAGMATIC(1),
        CONCERNED(2);

        private final int value;

        Sensitivity(final int newValue) {
            value = newValue;
        }

        public int getValue() { return value; }
    }


    /**
     * Evaluates the three responses to the general questions. If there is
     * insufficient information -1 is returned otherwise a label of
     * {unconcerned (0), pragmatic (1) or concerned (2) is returned.
     * @param ansList The list containing the three general question's answers.
     * @return An integer classifying the user sens
     * @throws eu.operando.pq.InvalidAnswerException When an answer is an integer
     * score between 1 and 4
     */
    public static Sensitivity westinGeneralSensitivityIndex(List<Questionobject> ansList)
        throws InvalidAnswerException {

        int G1 = 0;
        int G2 = 0;
        int G3 = 0;

        for(Questionobject answer: ansList) {
            int score = Integer.valueOf(answer.getScore());
            if(score<=0 || score>4)
                throw new InvalidAnswerException("Answer for question" + answer.getWeight() + "is not a value in the range 1 to 4");
            if(answer.getWeight().equalsIgnoreCase("G1")) {
                G1 = score;
            } else if (answer.getWeight().equalsIgnoreCase("G2")) {
                G2 = score;
            } else if (answer.getWeight().equalsIgnoreCase("G3")) {
                G3= score;
            }
        }
        System.out.println("G1 = " + G1 + ";G2= "+ G2 + ";G3 = " + G3);
        if( (G1>2) && (G2<=2) && (G3<=2)) {
            return Sensitivity.CONCERNED;
        }
        if( G1<=2 && G2>2 && G3>2) {
            return Sensitivity.UNCONCERNED;
        }
        else {
            return Sensitivity.PRAGMATIC;
        }

    }

     /**
     * Evaluates the three responses to the general questions. If there is
     * insufficient information -1 is returned otherwise a label of
     * {unconcerned (0), pragmatic (1) or concerned (2) is returned.
     * @param ansList The list containing the three general question's answers.
     * @return An integer classifying the user sens
     * @throws eu.operando.pq.InvalidAnswerException When an answer is an integer
     * score between 1 and 4
     */
    public static Sensitivity CategorySensitivityIndex(List<Questionobject> ansList)
        throws InvalidAnswerException {

        int strongAgree = 0;
        int agree = 0;
        int disagree = 0;
        int strongDisagree = 0;

        for(Questionobject answer: ansList) {
            int score = Integer.valueOf(answer.getScore());
            switch (score) {
                case 1: strongDisagree++;
                    break;
                case 2: disagree++;
                    break;
                case 3: agree++;
                    break;
                case 4: strongAgree++;
            }
        }
        if( (strongDisagree == 0) && (disagree == 0)) {
            return Sensitivity.CONCERNED;
        }
        if( (strongAgree == 0) && (agree == 0)) {
            return Sensitivity.UNCONCERNED;
        }
        else {
            return Sensitivity.PRAGMATIC;
        }

    }


}
