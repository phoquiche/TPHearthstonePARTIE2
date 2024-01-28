package org.HearthStone.personnages.capacites;

import org.HearthStone.personnages.Champion;
import org.HearthStone.personnages.Monstre;

public abstract class CapaciteSpeciale{
    public abstract void utiliserCapacite(Champion source, Monstre cible);
}