package cz.vasekric.utils;

/**
 * Created by vasek on 01.07.2016.
 */
public aspect NullSafeAspect {

    pointcut callGetName(): call(* *.$*());

    Object around() : callGetName() {
        if (thisJoinPoint.getTarget() == null) return null;
        else return proceed();
    }
}
