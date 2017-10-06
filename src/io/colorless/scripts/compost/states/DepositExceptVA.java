package io.colorless.scripts.compost.states;

import io.colorless.scripts.compost.variables.Conditionals;
import org.osbot.rs07.script.Script;

public class DepositExceptVA
{
    private Conditionals conditionals;
    private Script script;
    public DepositExceptVA(Script script, Conditionals conditionals)
    {
        this.script = script;
        this.conditionals = conditionals;
    }

    public void depositAll()
    {
        script.log("Depositing all except for volcanic ash");
        script.getBank().depositAllExcept("Volcanic ash");

        if (conditionals.waitVA.sleep())
        {
            script.log("Inventory only contains volcanic ash");
            script.getBank().close();
        }
        else
        {
            script.log("Something went wrong depositing all!");
        }
    }
}
