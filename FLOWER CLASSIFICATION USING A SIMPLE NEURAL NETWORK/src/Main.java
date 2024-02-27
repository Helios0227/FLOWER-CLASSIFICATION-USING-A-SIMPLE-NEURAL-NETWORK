import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int accuracy = 0;
        Neuron neuron1 = new Neuron();
        Neuron neuron2 = new Neuron();
        Neuron neuron3 = new Neuron();
        ArrayList<Neuron> neurons = new ArrayList<>();
        neurons.add(neuron1);
        neurons.add(neuron2);
        neurons.add(neuron3);
        ArrayList<String> neuronTypes = new ArrayList<>();
        Integer[] accuracyArray = new Integer[150];
        Arrays.fill(accuracyArray, 0);
        double[] tu = new double[150];
        double[] tg = new double[150];
        double[] cu = new double[150];
        double[] cg = new double[150];
        String[] names = new String[150];
        readData(tu, tg, cu, cg, names, neuronTypes);
        double learningCoeffient = 0.01;
        compareNeurons(150, neuron1, neuron2, neuron3, cu, cg, tu, tg, names, neurons, learningCoeffient,neuronTypes, accuracy,1, accuracyArray);
        int totalCheck=0;
        for (int i = 0; i<accuracyArray.length;i++){
            if (accuracyArray[i]==1){
                totalCheck++;
            }
        }
        System.out.println("check: %" + (totalCheck/accuracyArray.length)*100);

    }

    public static Integer[] compareNeurons(int dataCount, Neuron n1, Neuron n2, Neuron n3, double[] cu, double[] cg, double[] tu, double[] tg, String[] names, ArrayList<Neuron> neurons, double learningCoefficent, ArrayList<String> neuronTypes ,int accuracy, int epocCount, Integer[] accuracyArray) {


        ArrayList<Double> computations = new ArrayList<>();
        for (int p=0;p<dataCount;p++){
            int highestIndex = 0;
            int targetIndex = 0;
            for (int i = 0; i < epocCount; i++) {
                double[] weightN1 = neurons.get(0).getWeight();
                double[] weightN2 = neurons.get(1).getWeight();
                double[] weightN3 = neurons.get(2).getWeight();
                computations.add(neurons.get(0).calculations(weightN1, cu[p], cg[p], tu[p], tg[p]));
                computations.add(neurons.get(1).calculations(weightN2, cu[p], cg[p], tu[p], tg[p]));
                computations.add(neurons.get(2).calculations(weightN3, cu[p], cg[p], tu[p], tg[p]));

                targetIndex = neuronTypes.indexOf(names[i]);
                highestIndex = computations.indexOf(Collections.max(computations));
                if (targetIndex != highestIndex) {
                    for (int a = 0; a < computations.size(); a++) {
                        if (a != targetIndex && computations.get(targetIndex) < computations.get(a))
                            neurons.get(a).decrease(learningCoefficent, cg[p], cu[p], tu[p], tg[p]);
                    }
                    neurons.get(targetIndex).increase(learningCoefficent, cg[p], cu[p], tu[p], tg[p]);
                }
                else{
                    accuracyArray[p] = 1;
                }
                computations.clear();
            }
        }
        return accuracyArray;
    }


    public static void readData(double[] tu, double[] tg, double[] cu, double[] cg, String[] names, ArrayList<String> neuronTypes) throws IOException {
        int y = 0;
        BufferedReader br = new BufferedReader(new FileReader("iris.data"));
        String line = br.readLine();
        while (line != null && line.trim() != "") {
            String[] split = line.split(",");
            tu[y] = Double.parseDouble(split[0]);
            tg[y] = Double.parseDouble(split[1]);
            cu[y] = Double.parseDouble(split[2]);
            cg[y] = Double.parseDouble(split[3]);
            names[y] = split[4];
            if (!neuronTypes.contains(split[4])) neuronTypes.add(split[4]);
            y++;
            line = br.readLine();
        }


        br.close();
    }
}
