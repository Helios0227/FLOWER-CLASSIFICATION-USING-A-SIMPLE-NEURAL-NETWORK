import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Neuron {


    private double çanakYaprakUzunluğu;
    private double çanakYaprakGenişliği;
    private double taçYaprakUzunluğu;
    private double taçYaprakGenişliği;
    private double[] weight;
    private String name;



    public Neuron() {
        double[] weight = new double[4];
        this.weight = createRandomData(weight);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[] getWeight() {
        return weight;
    }

    public void setWeight(double[] weight) {
        this.weight = weight;
    }

    public double getÇanakYaprakUzunluğu() {
        return çanakYaprakUzunluğu;
    }

    public void setÇanakYaprakUzunluğu(double çanakYaprakUzunluğu) {
        this.çanakYaprakUzunluğu = çanakYaprakUzunluğu;
    }

    public double getÇanakYaprakGenişliği() {
        return çanakYaprakGenişliği;
    }

    public void setÇanakYaprakGenişliği(double çanakYaprakGenişliği) {
        this.çanakYaprakGenişliği = çanakYaprakGenişliği;
    }

    public double getTaçYaprakUzunluğu() {
        return taçYaprakUzunluğu;
    }

    public void setTaçYaprakUzunluğu(double taçYaprakUzunluğu) {
        this.taçYaprakUzunluğu = taçYaprakUzunluğu;
    }

    public double getTaçYaprakGenişliği() {
        return taçYaprakGenişliği;
    }

    public void setTaçYaprakGenişliği(double taçYaprakGenişliği) {
        this.taçYaprakGenişliği = taçYaprakGenişliği;
    }

    public double[] createRandomData(double[] weights) {
        for (int i = 0; i < 4; i++) {
            weights[i] = Math.random();
        }
        return weights;

    }

    public void increase(double learningCoefficient,double cg,double cu,double tu,double tg){
        weight[0]=weight[0]+(learningCoefficient*cg);
        weight[1]=weight[1]+(learningCoefficient*cu);
        weight[2]=weight[2]+(learningCoefficient*tu);
        weight[3]=weight[3]+(learningCoefficient*tg);
    }
    public void decrease(double learningCoefficient,double cg,double cu,double tu,double tg){
        weight[0]=weight[0]-(learningCoefficient*cg);
        weight[1]=weight[1]-(learningCoefficient*cu);
        weight[2]=weight[2]-(learningCoefficient*tu);
        weight[3]=weight[3]-(learningCoefficient*tg);

    }


    public double calculations(double[] weight, double cg, double cu, double tu , double tg) {

        double a = (cu / 10) * weight[0];
        double b = (cg / 10) * weight[1];
        double c = (tu / 10) * weight[2];
        double d = (tg / 10) * weight[3];
        double e = a + b + c + d;


        return e;

    }

}
