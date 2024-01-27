package org.example.personnages.capacites;

import org.example.personnages.Champion;
import org.example.personnages.Monstre;

public abstract class CapaciteSpeciale{
    public abstract void utiliserCapacite(Champion source, Monstre cible);
}