package io.colorless.scripts.compost.states;

import io.colorless.scripts.compost.variables.Conditionals;
import org.osbot.rs07.script.Script;

public class WithdrawMaterialsUC
{
    private Conditionals conditionals;
    private Script script;
    public WithdrawMaterialsUC(Script script, Conditionals conditionals)
    {
        this.script = script;
        this.conditionals = conditionals;
    }
    public void withdrawMaterials()
    {
        if (!script.getInventory().isEmpty())
        {
            script.getBank().depositAll();
        }

        script.getBank().withdrawAll("Volcanic ash");
        script.getBank().withdrawAll("Supercompost");
        script.getBank().close();
    }
}
