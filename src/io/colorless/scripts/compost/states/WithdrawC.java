package io.colorless.scripts.compost.states;

import io.colorless.scripts.compost.variables.Conditionals;
import org.osbot.rs07.script.Script;

public class WithdrawC
{
    private Conditionals conditionals;
    private Script script;
    public WithdrawC(Script script, Conditionals conditionals)
    {
        this.script = script;
        this.conditionals = conditionals;
    }

    public void withdraw()
    {
        script.log("Attempting to withdraw Compost");
        script.getBank().withdraw("Compost", 20);

        if (conditionals.waitC.sleep())
        {
            script.log("Inventory contains Compost");
            script.getBank().close();
        }
        else
        {
            script.log("Withdrawing Compost failed");
        }

    }
}
