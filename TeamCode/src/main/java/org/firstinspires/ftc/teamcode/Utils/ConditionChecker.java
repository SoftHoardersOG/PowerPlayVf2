package org.firstinspires.ftc.teamcode.Utils;

public class ConditionChecker extends Thread{

    private LambdaBool _lambdaC;
    private Lambda _lambdaA;

//    public static void main(String args[])
//    {
//        StartExp1 t1=new StartExp1();
//        // this will call run() method
//        t1.start();
//    }

    public ConditionChecker(LambdaBool lambdaCondition, Lambda lambdaAction){
        _lambdaC = lambdaCondition;
        _lambdaA = lambdaAction;
    }

    @Override
    public void run() {
        long time = System.currentTimeMillis();
        while (!_lambdaC.run()){
            if (System.currentTimeMillis()-time>5000){
                return;
            }
        }
        _lambdaA.run();
    }
}
