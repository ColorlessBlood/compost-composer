package io.colorless.scripts.compost.variables;

import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;

public class Conditionals
{

    private Script script;
    public Conditionals(Script script)
    {
        this.script = script;
    }


    //Checks to ensure that the inventory only contains volcanic ash
    public ConditionalSleep waitVA = new ConditionalSleep(4000)
    {
        @Override
        public boolean condition() throws InterruptedException
        {
            return script.getInventory().isEmptyExcept("Volcanic ash");
        }
    };

    //Checks to ensure that the interface to craft Ultracompost is visible
    public ConditionalSleep waitMake = new ConditionalSleep(5000)
    {
        @Override
        public boolean condition() throws InterruptedException
        {
            return script.getWidgets().isVisible(270, 14, 38);
        }
    };

    //Checks to ensure that Ultracompost was made
    public ConditionalSleep waitAll = new ConditionalSleep(60000)
    {
        @Override
        public boolean condition() throws InterruptedException
        {
            return script.getInventory().onlyContains("Volcanic ash", "Ultracompost");
        }
    };

    //Checks to ensure that the inventory contains Supercompost
    public ConditionalSleep waitSC = new ConditionalSleep(4000)
    {
        @Override
        public boolean condition() throws InterruptedException
        {
            return script.getInventory().contains("Supercompost");
        }
    };

    public ConditionalSleep waitC = new ConditionalSleep(4000)
    {
        @Override
        public boolean condition() throws InterruptedException
        {
            return script.getInventory().contains("Compost");
        }
    };

    public ConditionalSleep waitSelected = new ConditionalSleep(3000)
    {
        @Override
        public boolean condition() throws InterruptedException
        {
            return script.getInventory().isItemSelected();
        }
    };

    public ConditionalSleep waitPotion = new ConditionalSleep(2500)
    {
        @Override
        public boolean condition() throws InterruptedException
        {
            return script.getInventory().contains("Compost potion(4)");
        }
    };

    public ConditionalSleep waitCompost = new ConditionalSleep(2500)
    {
        @Override
        public boolean condition() throws InterruptedException
        {
            return script.getInventory().contains("Compost");
        }
    };

}
