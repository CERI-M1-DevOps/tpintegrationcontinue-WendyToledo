package liste;

public class ListeSimple {
    private long size;
    Noeud tete;

    /**
    * Retourne la taille de la liste
    * @return le nombre d'élément dans la liste
    */
    public long getSize() {
        return size;
    }
    
    /**
    * Ajute un nouvel élément en début de liste
    * @param element l'élément à ajouter
    */
    public void ajout(int element) {
        tete = new Noeud(element, tete);
        size++;
    }

    /**
    * modifie la première occurence d'un élément
    * @param element l'élément à modifier
    * @param nouvelleValeur nouvelle de l'élément
    */
    public void modifiePremier(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null && courant.getElement() != (element))
            courant = courant.getSuivant();
        if (courant != null)
            courant.setElement(nouvelleValeur);
    }

    /**
    * Modifie toutes les occurences d'un élément
    * @param element l'élément à modifier
    * @param nouvelleValeur nouvelle de l'élément
    */
    public void modifieTous(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null) {
            if (courant.getElement() == element)
                courant.setElement(nouvelleValeur);
            courant = courant.getSuivant();
        }
    }

    /**
    * renvoie la liste sous forme de chaîne
    * @return Lliste sous forne de chaîne de caractère
    */
    public String toString() {
        StringBuilder sb = new StringBuilder("ListeSimple(");
        Noeud n = tete;
        while (n != null) {
            sb.append(n);
            n = n.getSuivant();
            if (n != null)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    /**
    * Supprime la première occurrence d'un élément
    * @param element l'élément à supprimer
    */
    public void supprimePremier(Object element) {
        if (tete != null) {
            if (tete.getElement() == element) {
                tete = tete.getSuivant();
                size--;
                return;
            }
            Noeud precedent = tete;
            Noeud courant = tete.getSuivant();
            while (courant != null && courant.getElement() != element) {
                precedent = precedent.getSuivant();
                courant = courant.getSuivant();
            }
            if (courant != null) {
                precedent.setSuivant(courant.getSuivant());
                size--;
            }
        }
    }
    
    /**
    * Supprime toutes les occurrences d'un élément
    * @param element l'élément à supprimer
    */
    public void supprimeTous(int element) {
       tete = supprimeTousRecurs(element, tete);
    }

    /**
    * Méthode récursive pour supprimer toutes les occurrences d'un élément.
    * @param element l'élément à supprimer
    * @param tete le noeud de départ de la recherche
    * @return la tête de la liste après suppression
    */
    public Noeud supprimeTousRecurs(Object element, Noeud tete) {
        if (tete != null) {
            Noeud suiteListe = supprimeTousRecurs(element, tete.getSuivant());
            if (tete.getElement() == element) {
                size--;
                return suiteListe;
            } else {
                tete.setSuivant(suiteListe);
                return tete;
            }
        } else return null;
    }
    
    /**
    * Retourne le noeud avant-dernier dans la liste.
    * @return le noeud avant-dernier ou null si la liste est trop courte
    */
    public Noeud getAvantDernier() {
        if (tete == null || tete.getSuivant() == null)
            return null;
        else {
            Noeud courant = tete;
            Noeud suivant = courant.getSuivant();
            while (suivant.getSuivant() != null) {
                courant = suivant;
                suivant = suivant.getSuivant();
            }
            return courant;
        }
    }
    /**
    * Inverse l'ordre des éléments
    */
    public void inverser() {
        Noeud precedent = null;
        Noeud courant = tete;
        while (courant != null) {
            Noeud next = courant.getSuivant();
            courant.setSuivant(precedent);
            precedent = courant;
            courant = next;
        }
        tete = precedent;
    }

    /**
    * Retourne le noeud précédant un noeud donné dans la liste.
    * @param r le noeud dont on souhaite trouver le précédent
    * @return le noeud précédant le noeud r
    */
    public Noeud getPrecedent(Noeud r) {
    // la liste n'est pas vide puisqu'on transmet un Node de la liste et le Node existe obligatoirement
        Noeud precedent = tete;
        Noeud courant = precedent.getSuivant();
        while (courant != r) {
            precedent = courant;
            courant = courant.getSuivant();
        }
        return precedent;
    }

    /**
    * Échange les positions de deux noeuds dans la liste.
    * @param r1 le premier noeud à échanger
    * @param r2 le second noeud à échanger
    */
    public void echanger(Noeud r1, Noeud r2) {
        if (r1 == r2)
            return;
        Noeud precedentR1;
        Noeud precedentR2;
        if (r1 != tete && r2 != tete) {
            precedentR1 = getPrecedent(r1);
            precedentR2 = getPrecedent(r2);
            precedentR1.setSuivant(r2);
            precedentR2.setSuivant(r1);
        } else if (r1 == tete) {
            precedentR2 = getPrecedent(r2);
            precedentR2.setSuivant(tete);
            tete = r2;
        }
        else {
            precedentR1 = getPrecedent(r1);
            precedentR1.setSuivant(tete);
            tete = r1;
        }
        Noeud temp = r2.getSuivant();
        r2.setSuivant(r1.getSuivant());
        r1.setSuivant(temp);
    }

}
