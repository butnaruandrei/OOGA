import binary.BinaryGeneticAlgorithm;
import real.RSAGeneticAlgorithm;
import real.RealGeneticAlgorithm;
import rsa.FermatGeneticAlgorithm;
import rsa.RSABinaryGeneticAlgorithm;
import utils.RSA;

import java.math.BigInteger;


/**
 * Created by Butnaru Andrei-Madalin.
 */
public class Main {
    public static void main(String[] args) {
        BinaryRSAGA();
    }

    public static void BinaryGA(){
        int geneLength = 8;

        BinaryGeneticAlgorithm ga = new BinaryGeneticAlgorithm("singlePointCrossover", true, true, 100, 10, 1, geneLength, 0.8, 1d / geneLength);
        ga.run();
        System.out.println(ga.getFittest());
    }

    public static void RealGA(){
        RealGeneticAlgorithm ga = new RealGeneticAlgorithm(true, true, 10000, 10, 1, 0, 256, 0.75, 0.01);
        ga.run();
        System.out.println(ga.getFittest());
    }

    public static void RSAGA(){
        int rsaBitLength = 16;
        int rsaParamsLength = rsaBitLength / 2;

        BigInteger maxValue = new BigInteger("2").pow(rsaParamsLength).subtract(BigInteger.ONE);
        RSA rsa = new RSA();
        rsa.Initialize(rsaParamsLength);

        System.out.println(rsa.p + " " + rsa.q);

        RSAGeneticAlgorithm ga = new RSAGeneticAlgorithm(false, true, 10000, 25, 2, new BigInteger("2"), maxValue, 0.75, 0.3, rsa);
        ga.run();
        System.out.println(ga.getFittest());
    }

    public static void BinaryRSAGA(){
        int rsaBitLength = 16;
        int rsaParamsLength = rsaBitLength / 2;

        BigInteger maxValue = new BigInteger("2").pow(rsaParamsLength).subtract(BigInteger.ONE);
        RSA rsa = new RSA();
        rsa.Initialize(rsaParamsLength);

        System.out.println(rsa.p + " " + rsa.q);

        RSABinaryGeneticAlgorithm ga = new RSABinaryGeneticAlgorithm("singlePointCrossover", false, true, 10000, 100, 2, rsaParamsLength, 0.75, 0.03, rsa);
        ga.run();
        System.out.println(ga.getFittest());
    }

    public static void FermatGA(){
        int rsaBitLength = 16;
        int rsaParamsLength = rsaBitLength / 2;

        BigInteger maxValue = new BigInteger("2").pow(rsaParamsLength).subtract(BigInteger.ONE);
        RSA rsa = new RSA();
        rsa.Initialize(rsaParamsLength);

        System.out.println(rsa.p + " " + rsa.q);

        FermatGeneticAlgorithm ga = new FermatGeneticAlgorithm(true, true, 10000, 25, 1, new BigInteger("2"), maxValue, 0.75, 0.03, rsa);
        ga.run();
        System.out.println(ga.getFittest());
    }
}
