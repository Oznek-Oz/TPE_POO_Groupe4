public class Main {
    public static void main(String[] args) {
        /** CrÃ©ation des points **/
        Point P1 = new Point(4 , 8);
        Point P2 = new Point(5 , 6);
        Point P3 = new Point(14 , 15);
        Point P4 = new Point(20 , 21);

        /** CrÃ©ation des rectangles **/
        Rectangle rect1 = new Rectangle(P1 , 10 , 5);
        Rectangle rect2 = new Rectangle(P2 , P3);
        Rectangle rect3 = new Rectangle(P3 , P4);

        /** CrÃ©ation d'un tableau de rectangles **/
        Rectangle[] tabRect = new Rectangle[3];
        tabRect[0] = rect1; tabRect[1] = rect2; tabRect[2] = rect3;

        /** Produit le plus petit rectangle contenant tous les autres **/
        Rectangle rectMin = Rectangle.hull(tabRect);

        System.out.println("/** RECTANGLE MINIMAL ENGLOBANT UNE SERIE D'AUTRES RECTANGLE **/\n" +
                "Point bas-gauche: (" + rectMin.point.getAbs() + ";" + rectMin.point.getOrd() + ")\n" +
                "Point haut-droit: (" + (rectMin.point.getAbs()+ rectMin.longueur) + ";"
                + (rectMin.point.getOrd() + rectMin.hauteur) + ")\n" +
                "Sa longueur est : " + rectMin.longueur + " et sa hauteur est : " + rectMin.hauteur + "\n");

        System.out.println(rect2.sameAs(rect3) ? "\nCes rectangles sont identiques." : "Ces rectangles sont diffÃ©rents.");

        /** CrÃ©ation des slanted rectangles **/
        SlantedRectangle srect1 = new SlantedRectangle(P1 , 10 , 5);

        srect1.containRect(rect1);
        //Rotation
        System.out.print("\nAVANT ROTATION: \n");
        srect1.rotate(0);
        System.out.print("\nAPRES ROTATION LES NOUVELLES COORDONNEES SONT: \n\n");
        srect1.rotate(2);

        Point basDroit = new Point(srect1.point.getAbs() + srect1.longueur , srect1.point.getOrd());
        Point hautGauche = new Point(srect1.point.getAbs(), srect1.point.getOrd() + srect1.hauteur);
        Point hautDroit = new Point(srect1.point.getAbs() + srect1.longueur , srect1.point.getOrd() + srect1.hauteur);

        System.out.print("\n/** AVANT ROTATION: **/ \n");
        srect1.afficherCoordonnee("bas-gauche" , srect1.point);
        srect1.afficherCoordonnee("bas-droit" , basDroit);
        srect1.afficherCoordonnee("haut-gauche" , hautGauche);
        srect1.afficherCoordonnee("haut-droit" , hautDroit);

        System.out.print("\n/** APRES ROTATION LES NOUVELLES COORDONNEES SONT: **/\n");
        srect1.rotate(45);
      
      //EXERCICE 8-9-10
        A a = new A ();
        A ab =new B ();
        B b = new B ();

        a.f(a);
        a.f(ab);
        a.f(b);
        ab.f(a);
        ab.f(ab);
        ab.f(b);
        b.f(a);
        b.f(ab);
        b.f(b);

        System.out.println(a instanceof A); 
        System.out.println(ab instanceof A); 
        System.out.println(b instanceof A); 
        System.out.println(a instanceof B); 
        System.out.println(ab instanceof B); 
        System.out.println(b instanceof B);
        
/*Exercice11*/
/* Analyse de chaque ligne :

a instanceof A → true
a est une instance directe de A.

ab instanceof A → true
ab est une instance de B, mais B hérite de A, donc ab est aussi une instance de A.

b instanceof A → true
b est une instance de B, et comme B hérite de A, c’est aussi une instance de A.

a instanceof B → false
a est une instance directe de A, pas de B.

ab instanceof B → true
Même si ab est typé comme A, il a été instancié comme new B(), donc il est bien une instance de B.

b instanceof B → true
b est une instance directe de B.
*/

    }
}


/* DEFINITION DES CLASSES */
class Point {
    private float abs, ord;

    Point(float abs , float ord){
        this.abs = abs;
        this.ord = ord;
    }
    @Override
    public boolean equals (Object o){
        if (!(o instanceof Point)) return false;
        Point p = (Point) o;
        return abs == p.getAbs() && ord == p.getOrd();
    }

    /*public void rotate (Point centreRotation , float angle){
        float ANGLE = angle * ((float)Math.PI) / 180;

        this.abs = ( abs - centreRotation.getAbs()) * ((float) Math.cos(ANGLE)) - (ord - centreRotation.getOrd()) * ((float) Math.sin(ANGLE)) + centreRotation.getAbs() ;
        this.ord = ( abs - centreRotation.getAbs()) * ((float) Math.sin(ANGLE)) + (ord - centreRotation.getOrd()) * ((float) Math.cos(ANGLE)) + centreRotation.getOrd() ;
    }*/
    public Point rotate (Point centreRotation , float angle){
        float ANGLE = angle * ((float)Math.PI) / 180;

        this.abs = ( abs - centreRotation.getAbs()) * ((float) Math.cos(ANGLE)) - (ord - centreRotation.getOrd()) * ((float) Math.sin(ANGLE)) + centreRotation.getAbs() ;
        this.ord = ( abs - centreRotation.getAbs()) * ((float) Math.sin(ANGLE)) + (ord - centreRotation.getOrd()) * ((float) Math.cos(ANGLE)) + centreRotation.getOrd() ;
        return new Point(abs , ord);
    }

    //getters
    public float getAbs (){return this.abs;}
    public float getOrd (){return this.ord;}

    /* Setters */
    public float setAbs (float x){return this.abs = x;}
    public float setOrd (float y){return this.ord = y;}
}

class Rectangle{
    private static int nbr=0;
    Point point;
    float longueur , hauteur;

    //CONSTRUCTEURS DE LA CLASSE RECTANGLE
    Rectangle (Point p1, Point p2){
        this.point = p1;
        longueur = Math.abs(p1.getAbs() - p2.getAbs());
        hauteur = Math.abs(p1.getOrd() - p2.getOrd()) ;
        p2.setAbs((p1.getAbs() + longueur));
        p2.setOrd(p1.getOrd() + hauteur);
        nbr++;
    }
    Rectangle (Point point , float longueur , float hauteur){
        this.point = point;
        this.longueur = longueur;
        this.hauteur = hauteur;
        nbr++;
    }
    Rectangle (float x , float y , float longueur , float hauteur){
        this(null, longueur, hauteur);
        this.point = new Point(x , y);
        nbr++;
    }
    static int getNbr(){return nbr;}

    /* METHODES DE RECTANGLES */
    float Surface () {
        return this.longueur * this.hauteur ;
    }

    boolean containPoint (Point point){
        return ((point.getAbs() >= this.point.getAbs()) && (point.getAbs() <= (this.point.getAbs() + this.longueur))) && ((point.getOrd() >= this.point.getOrd()) && (point.getOrd() <= (this.point.getOrd() + this.hauteur)));
    }

    boolean containRect ( Rectangle rectangle){
        Point upRight = new Point(rectangle.point.getAbs() + rectangle.longueur , rectangle.point.getOrd() + rectangle.hauteur);
        return (this.containPoint(upRight) && this.containPoint(rectangle.point));
    }

    void translateof(float x, float y){
        this.point.setAbs(x + point.getAbs());
        this.point.setOrd(y + point.getOrd());
    }

    boolean sameAs (Rectangle r){
        if (this == r){
            return true;
        }
        if (this == null || r.getClass() != this.getClass()) {
            return false;
        }
        return point.equals(r.point) && (longueur == r.longueur) && (hauteur == r.hauteur);
    }

    static Rectangle hull (Rectangle[] tabRect ) {
        float minX = tabRect[0].point.getAbs();
        float minY = tabRect[0].point.getOrd();
        float maxX = tabRect[getNbr()-1].point.getAbs();
        float maxY = tabRect[getNbr()-1].point.getOrd();

        for (int i = 0; i < getNbr(); i++) {
            Point p = tabRect[i].point;
            if (p.getAbs() < minX) { minX = p.getAbs() ; }
            if (p.getOrd() < minY) { minY = p.getOrd() ; }
            if ((p.getAbs() + tabRect[i].longueur) > maxX) { maxX = p.getAbs() + tabRect[i].longueur; }
            if ((p.getOrd() + tabRect[i].hauteur)  > maxY) { maxY = p.getOrd() + tabRect[i].hauteur; }
        }
        Point pmin = new Point(minX , minY);
        Point pmax = new Point(maxX , maxY);

        return new Rectangle(pmin , pmax);
    }

    /* EXERICE8 */
    @Override
    public boolean equals (Object o){
        if (!(o instanceof Object Rectangle)) return false;
        Rectangle r = (Rectangle) o;
        Point hautDroit = new Point(point.getAbs() + longueur, point.getOrd() + hauteur);
        Point rHautDroit = new Point(r.point.getAbs() + r.longueur , r.point.getOrd() + hauteur);
        return this.point.equals(r.point) && hautDroit.equals(rHautDroit) ;
    }
}


/** TPE DE POO : FAIRE LES 13 EXERCICES SUR LA PARTIE HERITAGE**/


/* EXERICE1 : CONSTRUCTEURS DE SLANTEDRECTANGLE */
class SlantedRectangle extends Rectangle {
    float angle;
    SlantedRectangle(Point p1 , float longueur , float hauteur){
        super(p1 , longueur , hauteur);
    }
    SlantedRectangle(Point p1 , float longueur , float hauteur , float angle){
        super(p1 , longueur , hauteur);
        this.angle = angle;
        float ANGLE = angle * (float)Math.PI / 180 ;
    }

    /** MÃ©thode de la rotation
    /* L'expression analytique de la rotation est :
    *  X' = (X-Xa)cos(Ã¸) - (Y-Ya)sin(Ã¸) + Xa
    *  Y' = (X-Xa)sin(Ã¸) + (Y-Ya)cos(Ã¸) + Ya

    *  avec (Xa,Ya) les coordonnÃ©es du centre de la rotation
    *  qui coorespond dans notre cas au point "bas gauche"

    * M(X,Y) reprÃ©sente le point Ã  faire roter et M'(X',Y') l'image de M par la rotation

    /* Pour la rotate, il s'agit de faire tourner chacun des points "bas droite",
       "Haut gauche" et "Haut droite" d'un angle "angle"
     **/

    /* EXERCICE2 */
    void rotate(float angle) {
        Point basDroit = new Point(this.point.getAbs() + this.longueur , this.point.getOrd());
        Point hautGauche = new Point(this.point.getAbs() , this.point.getOrd() + this.hauteur);
        Point hautDroit = new Point(this.point.getAbs() + this.longueur , this.point.getOrd() + hauteur);

        basDroit.rotate(this.point , angle);
        hautGauche.rotate(this.point , angle);
        hautDroit.rotate(this.point , angle);

        afficherCoordonnee("bas-droit" , basDroit);
        afficherCoordonnee("haut-gauche" , hautGauche);
        afficherCoordonnee("haut-droit" , hautDroit);
    }
    public void afficherCoordonnee (String nomPoint , Point p) {
        System.out.println("Les coordonnÃ©es du point " + nomPoint + " sont : (" + p.getAbs() + " ; " + p.getOrd() + ")");
    }


}



// *************exercice 9************

class A {
    // Ajout de la methode suivante a la class B
    void f(A o) {
        System.out.println("void f(A o) dans A classe:"+o.getClass());
    }
    //1)Il s'agit ici d'une redefinition

    //2)le fragment de programme de l'exercice 8 va afficher apres l'ajout de la methode B
    /*  void f(A o) dans A classe:class A
        void f(A o) dans A classe:class B
        void f(A o) dans A classe:class B
        void f(A o) dans B 
        void f(A o) dans B 
        void f(A o) dans B 
        void f(A o) dans B 
        void f(A o) dans B 
        void f(A o) dans B 
*/
}
//***********EXERCICE 10***********
class B extends A {
     // Ajout de la methode suivante a la class A
   // void f(A o) {
    void f(A o) {
        System.out.println("void f(A o) dans B ");
    }
    void f(B o) {
        System.out.println("void f(B o) dans B");

}
    //1)Il s'agit ici d'une Surcharge
    

    //2)le fragment de programme de l'exercice 8 va afficher apres l'ajout de la methode A
    /*  void f(A o) dans A classe:class A
        void f(A o) dans A classe:class B
        void f(A o) dans A classe:class B
        void f(A o) dans B 
        void f(A o) dans B 
        void f(A o) dans B 
        void f(A o) dans B 
        void f(A o) dans B 
        void f(A o) dans B */
}
