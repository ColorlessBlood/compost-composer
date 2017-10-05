package io.colorless.scripts.compost.variables;

import io.colorless.scripts.compost.states.*;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;

public class Conditionals
{

    private CraftSC craftSC1 = new CraftSC(this);
    private CraftUC craftUC1 = new CraftUC(this);
    private DepositExceptVA depositExceptVA1 = new DepositExceptVA(this);
    private DepositUC depositUC1 = new DepositUC(this);
    private WithdrawC withdrawC1 = new WithdrawC(this);
    private WithdrawMaterialsSC withdrawMaterialsSC1 = new WithdrawMaterialsSC(this);
    private WithdrawMaterialsUC withdrawMaterialsUC1 = new WithdrawMaterialsUC(this);
    private WithdrawSC withdrawSC1 = new WithdrawSC(this);

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
            return script.getWidgets().isVisible(309, 2);
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

}
