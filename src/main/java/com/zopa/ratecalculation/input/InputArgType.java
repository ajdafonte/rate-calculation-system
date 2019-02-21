package com.zopa.ratecalculation.input;

/**
 * InputArgType enum - Describes and contains details about the allowed input arguments.
 */
public enum InputArgType
{
    FILEPATH(0),
    LOAN_AMOUNT(1);

    private final int position;

    InputArgType(final int position)
    {
        this.position = position;
    }

    public int getPosition()
    {
        return position;
    }

    public static int getNumberOfArgs()
    {
        return InputArgType.values().length;
    }

}
