package com.yy.impl;

import com.yy.Axe;
import com.yy.Person;

public class Chinese implements Person {
    private Axe axe;
    public Chinese(Axe axe)
    {
        this.axe = axe;
    }
    public void useAxe()
    {
        System.out.println(axe.chop());
    }
}
