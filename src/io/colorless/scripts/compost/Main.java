package io.colorless.scripts.compost;

import io.colorless.scripts.compost.states.*;
import io.colorless.scripts.compost.variables.Conditionals;
import org.osbot.rs07.script.Script;

import org.osbot.rs07.script.ScriptManifest;

import java.awt.*;

@ScriptManifest(name = "Compost Composer", author = "Colorless", version = 1.0, info = "", logo = "")

public class Main extends Script
{

    private Conditionals conditionals = new Conditionals(this);

    private CraftUC craftUC = new CraftUC(this, conditionals);
    private CraftSC craftSC = new CraftSC(this, conditionals);
    private DepositExceptVA depositExceptVA = new DepositExceptVA(this, conditionals);
    private DepositUC depositUC = new DepositUC(this, conditionals);
    private WithdrawSC withdrawSC = new WithdrawSC(this, conditionals);
    private WithdrawC withdrawC = new WithdrawC(this, conditionals);
    private WithdrawMaterialsUC withdrawMaterialsUC = new WithdrawMaterialsUC(this, conditionals);
    private WithdrawMaterialsSC withdrawMaterialsSC = new WithdrawMaterialsSC(this, conditionals);

    private long ultraCompost;
    private long startTime;

    private String formatTime(final long ms)
    {
        long s = ms / 1000, m = s / 60, h = m / 60;
        s %= 60; m %= 60; h %= 24;
        return String.format("%02d:%02d:%02d", h, m, s);
    }



    private enum State
    {
        DEPOSIT_UC,
        OPEN_BANK,
        CRAFT_UC,
        WITHDRAW_SC,
        DEPOSIT_FOR_VA,
        CRAFT_SC,
        WITHDRAW_C,
        DEPOSIT_ALL,
        DEPOSIT_FOR_CP,
        WITH_SC_MATERIALS,
        WITH_UC_MATERIALS,
        EXIT
    }

    private State getState()
    {
        if (getInventory().contains("Volcanic ash"))
        {
            if (getInventory().contains("Ultracompost"))
            {
                if (getBank().isOpen())
                {
                    return State.DEPOSIT_UC;
                }
                else
                {
                    return State.OPEN_BANK;
                }
            }
            else
            {
                if (getInventory().contains("Supercompost"))
                {
                    return State.CRAFT_UC;
                }
                else
                {
                    if (getBank().isOpen())
                    {
                        if (getInventory().isEmptyExcept("Volcanic ash"))
                        {
                            if (getBank().contains("Supercompost"))
                            {
                                return State.WITHDRAW_SC;
                            }
                            else
                            {
                                return State.EXIT;
                            }
                        }
                        else
                        {
                            return State.DEPOSIT_FOR_VA;
                        }
                    }
                    else
                    {
                        return State.OPEN_BANK;
                    }
                }
            }
        }
        else
        {
            if (getInventory().contains("Compost potion(4)") || getInventory().contains("Compost potion(3)") || getInventory().contains("Compost potion(2)") || getInventory().contains("Compost potion(1)"))
            {
                if (getInventory().contains("Compost"))
                {
                    return State.CRAFT_SC;
                }
                else
                {
                    if (getBank().isOpen())
                    {
                        if (getInventory().isEmptyExcept("Compost potion(4") || getInventory().isEmptyExcept("Compost potion(3") || getInventory().isEmptyExcept("Compost potion(2") || getInventory().isEmptyExcept("Compost potion(1"))
                        {
                            if (getBank().contains("Compost"))
                            {
                                return State.WITHDRAW_C;
                            }
                            else
                            {
                                return State.DEPOSIT_ALL;
                            }
                        }
                        else
                        {
                            return State.DEPOSIT_FOR_CP;
                        }
                    }
                    else
                    {
                        return State.OPEN_BANK;
                    }
                }
            }
            else
            {
                if (getBank().isOpen())
                {
                    if (getBank().contains("Compost potion(4)"))
                    {
                        if (getBank().contains("Compost"))
                        {
                            return State.WITH_SC_MATERIALS;
                        }
                        else
                        {
                            if (getBank().contains("Volcanic ash"))
                            {
                                if (getBank().contains("Supercompost"))
                                {
                                    return State.WITH_UC_MATERIALS;
                                }
                                else
                                {
                                    return State.EXIT;
                                }
                            }
                            else
                            {
                                return State.EXIT;
                            }
                        }
                    }
                    else
                    {
                        if (getBank().contains("Volcanic ash"))
                        {
                            if (getBank().contains("Supercompost"))
                            {
                                return State.WITH_UC_MATERIALS;
                            }
                            else
                            {
                                return State.EXIT;
                            }
                        }
                        else
                        {
                            return State.EXIT;
                        }
                    }
                }
                else
                {
                    return State.OPEN_BANK;
                }
            }
        }
    }

    @Override
    public void onStart()
    {
        log("Starting script");
        startTime = System.currentTimeMillis();

    }



    @Override
    public void onExit()
    {
        log("Script is stopping");
    }

    @Override
    public int onLoop() throws InterruptedException
    {
        switch (getState())
        {
            case DEPOSIT_UC:
                depositUC.deposit();
                break;

            case DEPOSIT_FOR_VA:
                depositExceptVA.depositAll();
                break;

            case WITHDRAW_SC:
                withdrawSC.withdraw();
                break;

            case WITHDRAW_C:
                withdrawC.withdraw();
                break;

            case OPEN_BANK:
                log("Openning Bank");
                getBank().open();
                ultraCompost = getBank().getAmount("Ultracompost");
                break;

            case CRAFT_UC:
                craftUC.craft();
                break;

            case CRAFT_SC:
                craftSC.craft();
                break;

            case EXIT:
                stop();
                break;

            case DEPOSIT_ALL:
                getBank().depositAll();
                break;

            case WITH_UC_MATERIALS:
                withdrawMaterialsUC.withdrawMaterials();
                break;

            case WITH_SC_MATERIALS:
                withdrawMaterialsSC.withdrawMaterials();
                break;

            case DEPOSIT_FOR_CP:
                log("Not setup correctly");
                stop();

        }

        return 200; //The amount of time in milliseconds before the loop starts over

    }

    @Override
    public void onPaint(Graphics2D g)
    {
        final long runTime = System.currentTimeMillis() - startTime;
        String time = formatTime(runTime);
        g.drawString(time, 35, 300);
        g.drawString("Current Ultracompost: " + ultraCompost, 35, 320);

        // Get current mouse position
        Point mP = getMouse().getPosition();

        // Draw a line from top of screen (0), to bottom (500), with mouse x coordinate
        g.drawLine(mP.x, 0, mP.x, 500);

        // Draw a line from left of screen (0), to right (800), with mouse y coordinate
        g.drawLine(0, mP.y, 800, mP.y);

    }
}