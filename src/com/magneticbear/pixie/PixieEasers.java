package com.magneticbear.pixie;

import android.util.FloatMath;

public class PixieEasers {
	public static final float PI = (float)Math.PI;
	
	public static float LINEAR             (float CurrentStep, float TotalSteps, float StartValue, float ValueChange)
    {
        return ValueChange * (CurrentStep / TotalSteps) + StartValue;
    }
    public static float SIN_IN             (float CurrentStep, float TotalSteps, float StartValue, float ValueChange)
    {
        return -ValueChange * FloatMath.cos(CurrentStep / TotalSteps * (PI / 2)) + ValueChange + StartValue;
    }
    public static float SIN_OUT            (float CurrentStep, float TotalSteps, float StartValue, float ValueChange)
    {
        return ValueChange * FloatMath.sin(CurrentStep / TotalSteps * (PI / 2)) + StartValue;
    }
    public static float SIN_IN_OUT         (float CurrentStep, float TotalSteps, float StartValue, float ValueChange)
    {
        return -ValueChange / 2 * (FloatMath.cos(PI * CurrentStep / TotalSteps) - 1) + StartValue;
    }
    public static float QUADRATIC_IN       (float CurrentStep, float TotalSteps, float StartValue, float ValueChange)
    {
        return ValueChange * (CurrentStep /= TotalSteps) * CurrentStep + StartValue;
    }
    public static float QUADRATIC_OUT      (float CurrentStep, float TotalSteps, float StartValue, float ValueChange)
    {
        return -ValueChange * (CurrentStep /= TotalSteps) * (CurrentStep - 2) + StartValue;
    }
    public static float QUADRATIC_IN_OUT   (float CurrentStep, float TotalSteps, float StartValue, float ValueChange)
    {
        if ((CurrentStep /= TotalSteps / 2) < 1) 
        {   
            return ValueChange / 2 * CurrentStep * CurrentStep + StartValue;
        }
        else
        {
            return -ValueChange / 2 * ((--CurrentStep) * (CurrentStep - 2) - 1) + StartValue;
        }
    }
    public static float CUBIC_IN           (float CurrentStep, float TotalSteps, float StartValue, float ValueChange)
    {
        return ValueChange * (CurrentStep /= TotalSteps) * CurrentStep * CurrentStep + StartValue;
    }
    public static float CUBIC_OUT          (float CurrentStep, float TotalSteps, float StartValue, float ValueChange)
    {
        return ValueChange * ((CurrentStep = CurrentStep / TotalSteps - 1) * CurrentStep * CurrentStep + 1) + StartValue;
    }
    public static float CUBIC_IN_OUT       (float CurrentStep, float TotalSteps, float StartValue, float ValueChange)
    {
        if((CurrentStep /= TotalSteps / 2) < 1)
        {
            return ValueChange / 2 * CurrentStep * CurrentStep * CurrentStep + StartValue;
        }
        else
        {
            return ValueChange / 2 * ((CurrentStep -= 2) * CurrentStep * CurrentStep + 2) + StartValue;
        }
    }
    public static float QUARTIC_IN         (float CurrentStep, float TotalSteps, float StartValue, float ValueChange)
    {
        return ValueChange * (CurrentStep /= TotalSteps) * CurrentStep * CurrentStep * CurrentStep + StartValue;
    }
    public static float QUARTIC_OUT        (float CurrentStep, float TotalSteps, float StartValue, float ValueChange)
    {
        return -ValueChange * ((CurrentStep = CurrentStep / TotalSteps - 1) * CurrentStep * CurrentStep * CurrentStep - 1) + StartValue;
    }
    public static float QUARTIC_IN_OUT     (float CurrentStep, float TotalSteps, float StartValue, float ValueChange)
    {
        if ((CurrentStep /= TotalSteps / 2) < 1)
        {
            return ValueChange / 2 * CurrentStep * CurrentStep * CurrentStep * CurrentStep + StartValue;
        }
        else
        {
            return -ValueChange / 2 * ((CurrentStep -= 2) * CurrentStep * CurrentStep * CurrentStep - 2) + StartValue;
        }
    }
    public static float QUINTIC_IN         (float CurrentStep, float TotalSteps, float StartValue, float ValueChange)
    {
        return ValueChange * (CurrentStep /= TotalSteps) * CurrentStep * CurrentStep * CurrentStep * CurrentStep + StartValue;
    }
    public static float QUINTIC_OUT        (float CurrentStep, float TotalSteps, float StartValue, float ValueChange)
    {
        return ValueChange * ((CurrentStep = CurrentStep / TotalSteps - 1) * CurrentStep * CurrentStep * CurrentStep * CurrentStep + 1) + StartValue;
    }
    public static float QUINTIC_IN_OUT     (float CurrentStep, float TotalSteps, float StartValue, float ValueChange)
    {
        if ((CurrentStep /= TotalSteps / 2) < 1)
        {
            return ValueChange / 2 * CurrentStep * CurrentStep * CurrentStep * CurrentStep * CurrentStep + StartValue;
        }
        else
        {
            return ValueChange / 2 * ((CurrentStep -= 2) * CurrentStep * CurrentStep * CurrentStep * CurrentStep + 2) + StartValue;
        }
    }
    public static float EXPONENTIAL_IN     (float CurrentStep, float TotalSteps, float StartValue, float ValueChange)
    {
        if (CurrentStep == 0)
        {
            return StartValue;
        }
        else
        {
            return ValueChange * (float)Math.pow(2, 10 * (CurrentStep / TotalSteps - 1)) + StartValue;
        }
    }
    public static float EXPONENTIAL_OUT    (float CurrentStep, float TotalSteps, float StartValue, float ValueChange)
    {
        if (CurrentStep == TotalSteps)
        {
            return StartValue + ValueChange;
        }
        else
        {
            return ValueChange * (-(float)Math.pow(2, -10 * CurrentStep / TotalSteps) + 1) + StartValue;
        }
    }
    public static float EXPONENTIAL_IN_OUT (float CurrentStep, float TotalSteps, float StartValue, float ValueChange)
    {
        if (CurrentStep == 0)
        {
            return StartValue;
        }
        else if (CurrentStep == TotalSteps)
        {
            return StartValue + ValueChange;
        }
        else if ((CurrentStep /= TotalSteps / 2) < 1)
        {
            return ValueChange / 2 * (float)Math.pow(2, 10 * (CurrentStep - 1)) + StartValue;
        }
        else
        {
            return ValueChange / 2 * ((float)-Math.pow(2, -10 * --CurrentStep) + 2) + StartValue;
        }
    }
    public static float CIRCULAR_IN        (float CurrentStep, float TotalSteps, float StartValue, float ValueChange)
    {
        return -ValueChange * (FloatMath.sqrt(1 - (CurrentStep /= TotalSteps) * CurrentStep) - 1) + StartValue;
    }
    public static float CIRCULAR_OUT       (float CurrentStep, float TotalSteps, float StartValue, float ValueChange)
    {
        return ValueChange * FloatMath.sqrt(1 - (CurrentStep = CurrentStep / TotalSteps - 1) * CurrentStep) + StartValue;
    }

}
