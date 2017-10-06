package io.colorless.scripts.compost.states;

import io.colorless.scripts.compost.variables.Conditionals;
import org.osbot.rs07.script.Script;

public class WithdrawMaterialsSC
{
    private Conditionals conditionals;
    private Script script;
    public WithdrawMaterialsSC(Script script, Conditionals conditionals)
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

        script.getBank().withdraw("Compost potion(4)", 5);
        script.log("Withdrawing Compost Potions");
        if (conditionals.waitPotion.sleep())
        {
            script.log("Withdrawing Compost");
            script.getBank().withdraw("Compost", 20);
            if (conditionals.waitCompost.sleep())
            {
                script.log("Withdrrew Compost");
                script.getBank().close();
            }
        }


    }
}
