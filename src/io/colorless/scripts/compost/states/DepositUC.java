package io.colorless.scripts.compost.states;

import io.colorless.scripts.compost.variables.Conditionals;
import org.osbot.rs07.script.Script;

public class DepositUC
{
    private Conditionals conditionals;
    private Script script;
    public DepositUC(Script script)
    {
        this.script = script;
    }
    public DepositUC(Conditionals conditionals)
    {
        this.conditionals = conditionals;
    }


    public void deposit()
    {
        script.log("Depositing all Ultracompost");
        script.getBank().depositAll("Ultracompost");
        if (conditionals.waitVA.sleep())
        {
            script.log("Inventory is clear of Ultracompost!");
            script.getBank().close();
        }
    }
}
