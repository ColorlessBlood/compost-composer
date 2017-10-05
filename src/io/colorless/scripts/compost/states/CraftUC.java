package io.colorless.scripts.compost.states;

import io.colorless.scripts.compost.variables.Conditionals;
import org.osbot.rs07.script.Script;

public class CraftUC
{
    private Conditionals conditionals;
    private Script script;
    public CraftUC(Script script)
    {
        this.script = script;
    }

    public CraftUC(Conditionals conditionals)
    {
        this.conditionals = conditionals;
    }

    public void craft()
    {
        script.log("Attempting to craft Ultracompost");
        script.getInventory().interact("Use", "Volcanic ash", "Supercompost");

        if (conditionals.waitMake.sleep())
        {
            script.getWidgets().interact(309, 2, "Make All");

            if (conditionals.waitAll.sleep())
            {
                script.log("Making Ultracompost Complete!");
            }
            else
            {
                script.log("Making Ultracompost failed!");
            }

        }
        else
        {
            script.log("Interface didn't appear!");
        }


    }

}
