package io.colorless.scripts.compost.states;

import io.colorless.scripts.compost.variables.Conditionals;
import org.osbot.rs07.script.Script;

public class WithdrawSC
{
    private Conditionals conditionals;
    private Script script;
    public WithdrawSC(Script script)
    {
        this.script = script;
    }
    public WithdrawSC(Conditionals conditionals)
    {
        this.conditionals = conditionals;
    }

    public void withdraw()
    {
        script.log("Attempting to withdraw Supercompost");
        script.getBank().withdrawAll("Supercompost");
        if (conditionals.waitSC.sleep())
        {
            script.log("Inventory now contains Supercompost");
            script.getBank().close();
        }
        else
        {
            script.log("Withdrawing Supercompost failed");
        }
    }
}
