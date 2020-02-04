package edu.eci.arsw.highlandersim;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Immortal extends Thread {

    private ImmortalUpdateReportCallback updateCallback=null;
    
    private int health;
    
    private int defaultDamageValue;

    private final CopyOnWriteArrayList<Immortal> immortalsPopulation;

    private final String name;

    private final Random r = new Random(System.currentTimeMillis());
    
    private boolean pausa = false;


    public Immortal(String name, CopyOnWriteArrayList<Immortal> immortalsPopulation, int health, int defaultDamageValue, ImmortalUpdateReportCallback ucb) {
        super(name);
        this.updateCallback=ucb;
        this.name = name;
        this.immortalsPopulation = immortalsPopulation;
        this.health = health;
        this.defaultDamageValue=defaultDamageValue;
}

    public void run() {

        while (true) {
            Immortal im;


            if (immortalsPopulation.size() > 1) {
                int myIndex = immortalsPopulation.indexOf(this);

                int nextFighterIndex = r.nextInt(immortalsPopulation.size());

                //avoid self-fight
                if (nextFighterIndex == myIndex) {
                    nextFighterIndex = ((nextFighterIndex + 1) % immortalsPopulation.size());
                }

                im = immortalsPopulation.get(nextFighterIndex);


                this.fight(im);

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (this) {
                    while (this.pausa) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            } else {
                for (Immortal ime : immortalsPopulation) {
                    try {
                        ime.detener();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }

        }
    }

    public void fight(Immortal i2) {
        if (this.getHealth() == 0) {
            immortalsPopulation.remove(this);
        } else {
            Immortal primero;
            Immortal segundo;
            if (this.getId() < i2.getId()) {
                primero = this;
                segundo = i2;
            } else {
                primero = i2;
                segundo = this;
            }

            synchronized (primero) {
                synchronized (segundo) {

                    if (i2.getHealth() > 0) {
                        i2.changeHealth(i2.getHealth() - defaultDamageValue);
                        this.health += defaultDamageValue;
                        updateCallback.processReport("Fight: " + this + " vs " + i2 + "\n");
                        if (i2.getHealth() == 0) {
                            immortalsPopulation.remove(i2);
                        }
                    } else {
                        updateCallback.processReport(this + " says:" + i2 + " is already dead!\n");
                    }
                }
            }
        }
    }

    public void changeHealth(int v) {
        health = v;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public String toString() {

        return name + "[" + health + "]";
    }
    
    public synchronized void detener() throws InterruptedException {
    	 this.pausa = true;
         notifyAll();
    }
    
    public synchronized void continuar() {
    	 this.pausa = false;
         notifyAll();
    }

}
