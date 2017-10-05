package io.colorless.scripts.compost.states;

import io.colorless.scripts.compost.variables.Conditionals;
import org.osbot.rs07.script.Script;

import static org.osbot.rs07.script.MethodProvider.random;
import static org.osbot.rs07.script.MethodProvider.sleep;

public class CraftSC
{
    private Conditionals conditionals;
    private Script script;
    public CraftSC(Script script)
    {
        this.script = script;
    }

    public CraftSC(Conditionals conditionals)
    {
        this.conditionals = conditionals;
    }

    public void craft() throws InterruptedException
    {


        while (script.getInventory().contains("Compost potion(4)"))
        {
            sleep(250);

            script.getInventory().interact("Use", "Compost potion(4)");
            if (conditionals.waitSelected.sleep())
            {
                script.log("Making Supercompost!");
                script.getInventory().interact("Use", "Compost");
            }

            sleep(random(750,1000));
        }

        while (script.getInventory().contains("Compost potion(3)"))
        {
            if (script.getInventory().isItemSelected())
            {
                script.log("Deselecting item!");
                script.getInventory().deselectItem();
            }
            sleep(250);

            script.getInventory().interact("Use", "Compost potion(3)");
            if (conditionals.waitSelected.sleep())
            {
                script.log("Making Supercompost!");
                script.getInventory().interact("Use", "Compost");
            }
            sleep(random(750,1000));
        }

        while (script.getInventory().contains("Compost potion(2)"))
        {
            if (script.getInventory().isItemSelected())
            {
                script.log("Deselecting item!");
                script.getInventory().deselectItem();
            }
            sleep(250);

            script.getInventory().interact("Use", "Compost potion(2)");
            if (conditionals.waitSelected.sleep())
            {
                script.log("Making Supercompost!");
                script.getInventory().interact("Use", "Compost");
            }
            sleep(random(750,1000));
        }

        while (script.getInventory().contains("Compost potion(1)"))
        {
            if (script.getInventory().isItemSelected())
            {
                script.log("Deselecting item!");
                script.getInventory().deselectItem();
            }
            sleep(250);

            script.getInventory().interact("Use", "Compost potion(1)");
            if (conditionals.waitSelected.sleep())
            {
                script.log("Making Supercompost!");
                script.getInventory().interact("Use", "Compost");
            }
            sleep(random(750,1000));
        }

        script.log("Used all Compost Potions!");
    }
}
