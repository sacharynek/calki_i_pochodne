package tlach;

public class Main {

    static float squareFunction(float x) {
        // Declaring the function f(x) = 1/(1+x*x)
//        return 1 / (1 + x * x);
        return x*x*x;
    }

    static float getStep(float beginValue, float endValue, int numberOfSteps){
        return (endValue - beginValue) / numberOfSteps;
    }

    static float[] prepareTableOfArguments(float beginValue, float endValue, int numberOfSteps) {
        float[] output_tab = new float[numberOfSteps + 1];
        float step = (endValue - beginValue) / numberOfSteps;

        output_tab[0] = squareFunction(beginValue);

        for (int i = 1; i < numberOfSteps; i++) {
            output_tab[i] = squareFunction(beginValue + i * step);
        }

        output_tab[numberOfSteps] = squareFunction(endValue);

        return output_tab;
    }

    static float leftPointMethod(float[] tab, float step) {
        float output = 0;

        for (int i = 0; i < tab.length - 1;i++) {
            output += tab[i] * step;
        }

        return output;
    }

    static float rightPointMethod(float[] tab, float step) {
        float output = 0;
        for (int i = 1; i < tab.length;i++) {
            output += tab[i] * step;
        }
        return output;
    }

    static float midPointMethod(float[] tab, float step) {
        float output = 0;
        for (int i = 0; i < tab.length-1;i++) {
            float mid = (tab[i]+tab[i+1])/2;
            output += mid * step;
        }
        return output;
    }

    // Function to evalute the value of integral
    static float trapezoidal(float beginValue, float endValue, int numberOfSteps) {

        // krok obliczen
        float step = (endValue - beginValue) / numberOfSteps;
        float s = 0;

        s += squareFunction(beginValue);
        s += squareFunction(endValue);

        // dodawanie środkowych wartości
        for (int i = 1; i < numberOfSteps; i++) {
            s += 2 * squareFunction(beginValue + i * step);

        }
        // h/2 indicates (b-a)/2n. Multiplying h/2
        // with s.
        return (step / 2) * s;
    }

    public static void main(String[] args) {
        // Range of definite integral
        float x0 = 0;
        float xn = 1;
        int n = 10;
        float[] table = prepareTableOfArguments(x0, xn, n);
        float step = getStep(x0, xn, n);

        System.out.format("Value of leftpoint integral is %6.4f\n", leftPointMethod(table,step));
        System.out.format("Value of rightpoint integral is %6.4f\n", rightPointMethod(table, step));
        System.out.format("Value of average integral is %6.4f\n", (rightPointMethod(table, step)+leftPointMethod(table,step))/2);
        System.out.format("Value of midpoint integral is %6.4f\n", midPointMethod(table, step));

        System.out.format("Value of trapeze integral is %6.4f\n", trapezoidal(x0, xn, n));

    }
}
