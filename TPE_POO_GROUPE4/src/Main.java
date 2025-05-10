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
        //Rotation
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
