package org.HearthStone.personnages;

import org.HearthStone.structure.*;

public interface Vie {
    public void subirDegats(int pvPerdu, PlateauV2 plateau, Joueur joueur);
    public void soignerPv(int pvGagne);
}
