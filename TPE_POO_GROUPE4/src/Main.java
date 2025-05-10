public class Main {
    public static void main(String[] args) {

        DefPoint P1 = new DefPoint(4 , 8);
        DefPoint P2 = new DefPoint(14 , 15);

        Rectangle rect1 = new Rectangle(P1 , 10 , 5);

        SlantedRectangle srect1 = new SlantedRectangle(P1 , 10 , 5);

        P2.setAbs(P1.getAbs() + srect1.longueur);
        P2.setOrd(P1.getOrd() + srect1.hauteur);

        srect1.afficherCoordonnee("P2" , P2);
        srect1.containRect(rect1);

        System.out.print("\nAVANT ROTATION: \n");
        srect1.rotate(0);
        System.out.print("\nAPRES ROTATION LES NOUVELLES COORDONNEES SONT: \n\n");
        srect1.rotate(2);

    }
}

/* DEFINITION DES CLASSES */
class DefPoint {
    private float abs, ord;

    DefPoint (float abs , float ord){
        this.abs = abs;
        this.ord = ord;
    }
    //getters
    public float getAbs (){return this.abs;}
    public float getOrd (){return this.ord;}

    /* Setters */
    public float setAbs (float x){return this.abs = x;}
    public float setOrd (float y){return this.ord = y;}
}

class Rectangle{
    DefPoint point;
    float longueur , hauteur;

    //CONSTRUCTEURS DE LA CLASSE RECTANGLE
    Rectangle (DefPoint p1, DefPoint p2){
        this.point = p1;
        longueur = Math.abs(p1.getAbs() - p2.getAbs());
        hauteur = Math.abs(p1.getOrd() - p2.getOrd()) ;
        p2.setAbs((p1.getAbs() + longueur));
        p2.setOrd(p1.getOrd() + hauteur);
    }
    Rectangle (DefPoint point , float longueur , float hauteur){
        this.point = point;
        this.longueur = longueur;
        this.hauteur = hauteur;
    }
    Rectangle (float x , float y , float longueur , float hauteur){
        this(null, longueur, hauteur);
        this.point = new DefPoint(x , y);
    }
    /* METHODES DE RECTANGLES */
    float Surface () {
        return this.longueur * this.hauteur ;
    }
    boolean containPoint (DefPoint point){
        return ((point.getAbs() >= this.point.getAbs()) && (point.getAbs() <= (this.point.getAbs() + this.longueur))) && ((point.getOrd() >= this.point.getOrd()) && (point.getOrd() <= (this.point.getOrd() + this.hauteur)));
    }
    boolean containRect ( Rectangle rectangle){
        DefPoint upRight = new DefPoint(rectangle.point.getAbs() + rectangle.longueur , rectangle.point.getOrd() + rectangle.hauteur);
        return (this.containPoint(upRight) && this.containPoint(rectangle.point));
    }

    void translateof(float x, float y){
        this.point.setAbs(x + point.getAbs());
        this.point.setOrd(y + point.getOrd());
    }
}

/* HERITAGES */
/* CONSTRUCTEURS DE SLANTEDRECTANGLE */
class SlantedRectangle extends Rectangle {
    SlantedRectangle(DefPoint p1 , float longueur , float hauteur){
        super(p1 , longueur , hauteur);
        //this.angle = angle;
    }

    /* Méthode de la rotation */
    /* L'expression analytique de la rotation est :
    *  X' = (X-Xa)cos(ø) - (Y-Ya)sin(ø) + Xa
    *  Y' = (X-Xa)sin(ø) + (Y-Ya)cos(ø) + Ya
    *
    *  avec (Xa,Ya) les coordonnées du centre de la rotation
    *  qui coorespond dans notre cas au point "bas gauche"
    *
    * M(X,Y) représente le point à faire roter et M'(X',Y') l'image de M par la rotation
    * */

    /* Pour la rotate, il s'agit de faire tourner chacun des points "bas droite",
       "Haut gauche" et "Haut droite" d'un angle "angle" */

    void rotate(float angle) {
        float ANGLE = angle * ((float)Math.PI) / 180;

        DefPoint basDroit = new DefPoint(this.point.getAbs() + this.longueur , this.point.getOrd());
        DefPoint hautGauche = new DefPoint(this.point.getAbs() , this.point.getOrd() + this.hauteur);
        DefPoint hautDroit = new DefPoint(this.point.getAbs() + this.longueur , this.point.getOrd() + hauteur);

        imageX(ANGLE , basDroit);
        imageY(ANGLE , basDroit);

        imageX(ANGLE , hautDroit);
        imageY(ANGLE , hautDroit);

        imageX(ANGLE , hautGauche);
        imageY(ANGLE , hautGauche);

        afficherCoordonnee("basDroit" , basDroit);
        afficherCoordonnee("hautGauche" , hautGauche);
        afficherCoordonnee("hautDroit" , hautDroit);
    }
    private void imageX (float ANGLE , DefPoint p){
        p.setAbs( (( p.getAbs() - this.point.getAbs()) * ((float) Math.cos(ANGLE)) - (p.getOrd() - this.point.getOrd()) * ((float) Math.sin(ANGLE)) + point.getAbs()) );
    }
    private void imageY (float ANGLE , DefPoint p){
        p.setOrd( (( p.getAbs() - this.point.getAbs()) * ((float) Math.sin(ANGLE)) + (p.getOrd() - this.point.getOrd()) * ((float) Math.cos(ANGLE)) + point.getOrd()) );
    }
    public void afficherCoordonnee (String nomPoint , DefPoint p) {
        System.out.println("Les coordonnées du point " + nomPoint + " sont : (" + p.getAbs() + " ; " + p.getOrd() + ")");
    }

}

/*
                                        TPE : PROGRAMMATION ORIENTE OBJECT


                                                    Exercice 5


  La réponse est oui, la classe DESSIN peut contenir des slantedRectangle. En effet, grâce au polymorphisme, un tableau de Rectangle peut contenir des objects de la classe SlandedRectangle qui héritent de Rectangle.
Cependant, les méthodes surface, contains et hull de la classe Dessin peuvent ne pas fonctionner correctement avec des SlantedRectangle. Ces méthodes ont été définies en supposant que les  rectangles inclinés, ces méthodes nécessiteraient une adaptation pour prendre en   compte l’angle d’inclinaison.

  
                                                                                             
                                                             EXERCICE 12

1. Redéfinition de contains(Rectangle)
La classe Rectangle possède une méthode contains(Rectangle) qui vérifie si un rectangle donné est entièrement contenu dans l'instance actuelle. Lorsque nous introduisons SlantedRectangle, cette méthode doit être adaptée pour prendre en compte l'inclinaison du rectangle. Dans sa forme actuelle, elle ne fonctionne qu'avec des rectangles aux côtés parallèles aux axes, ce qui signifie qu'elle ne peut pas correctement déterminer si un SlantedRectangle est contenu dans un Rectangle.
2. Ajout de contains(SlantedRectangle)
Pour pallier cette limitation, on ajoute une méthode contains(SlantedRectangle) à Rectangle et à SlantedRectangle. Cependant, cette approche a encore des limitations:
•	Un SlantedRectangle pourrait être incliné d'une manière qui ne permet pas une simple comparaison avec les bords du Rectangle.
•	La méthode pourrait ne pas gérer tous les cas où les rectangles inclinés s'entrecroisent ou touchent sans être strictement à l'intérieur.
3. Cas non couverts
Même après l'ajout de contains(SlantedRectangle), certains cas restent problématiques :
•	Si un SlantedRectangle chevauche un Rectangle mais dépasse légèrement en dehors.
•	Si deux SlantedRectangle ont des inclinaisons qui rendent la vérification complexe, par exemple lorsque leurs sommets sont contenus mais que leurs bords dépassent.
Une solution serait d'utiliser des tests géométriques avancés basés sur les polygones et les intersections, au lieu de simples comparaisons de coordonnées

*/







class C {
    char ch = 'C';
    char getCh() { return ch; }
}

class D extends C {
    char ch = 'D';  
    char getCh() { return ch; }  
}
C c = new C();
C cd = new D();
D d = new D();
public static void main(String[] args){
System.out.println(c.ch);    
System.out.println(c.getCh()); 

System.out.println(cd.ch);   
System.out.println(cd.getCh()); 

System.out.println(d.ch);    
System.out.println(d.getCh()); 
}
