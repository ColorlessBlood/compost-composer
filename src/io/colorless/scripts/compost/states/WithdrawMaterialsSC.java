package io.colorless.scripts.compost.states;

import io.colorless.scripts.compost.variables.Conditionals;
import org.osbot.rs07.script.Script;

public class WithdrawMaterialsSC
{
    private Conditionals conditionals;
    private Script script;
    public WithdrawMaterialsSC(Script script)
    {
        this.script = script;
    }
    public WithdrawMaterialsSC(Conditionals conditionals)
    {
        this.conditionals = conditionals;
    }

    public void withdrawMaterials()
    {
        if (!script.getInventory().isEmpty())
        {
            script.getBank().depositAll();
        }

        script.getBank().withdraw("Compost potion(4)", 5);
        script.getBank().withdraw("Compost", 20);
        script.getBank().close();
    }
}
