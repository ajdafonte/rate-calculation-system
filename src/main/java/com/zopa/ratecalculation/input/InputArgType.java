package com.zopa.ratecalculation.input;

/**
 *
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
