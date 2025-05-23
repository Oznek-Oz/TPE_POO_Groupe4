public class Main {
    public static void main(String[] args) {
        /** Création des points **/
        Point P1 = new Point(4, 8);
        Point P2 = new Point(5, 6);
        Point P3 = new Point(14, 15);
        Point P4 = new Point(20, 21);

        /** Création des rectangles **/
        Rectangle rect1 = new Rectangle(P1, 10, 5);
        Rectangle rect2 = new Rectangle(P2, P3);
        Rectangle rect3 = new Rectangle(P3, P4);

        /** Création d'un tableau de rectangles **/
        Rectangle[] tabRect = new Rectangle[3];
        tabRect[0] = rect1;
        tabRect[1] = rect2;
        tabRect[2] = rect3;

        /** Produit le plus petit rectangle contenant tous les autres **/
        Rectangle rectMin = Rectangle.hull(tabRect);

        System.out.println("/** RECTANGLE MINIMAL ENGLOBANT UNE SERIE D'AUTRES RECTANGLES **/\n" +
                "Point bas-gauche: (" + rectMin.point.getAbs() + ";" + rectMin.point.getOrd() + ")\n" +
                "Point haut-droit: (" + (rectMin.point.getAbs() + rectMin.longueur) + ";" +
                (rectMin.point.getOrd() + rectMin.hauteur) + ")\n" +
                "Sa longueur est : " + rectMin.longueur + " et sa hauteur est : " + rectMin.hauteur + "\n");

        System.out.println(rect2.sameAs(rect3) ? "\nCes rectangles sont identiques." : "Ces rectangles sont différents.");

        /** Création des slanted rectangles **/
        SlantedRectangle srect1 = new SlantedRectangle(P1, 10, 5);

        srect1.contains(rect1);

        Point basDroit = new Point(srect1.point.getAbs() + srect1.longueur, srect1.point.getOrd());
        Point hautGauche = new Point(srect1.point.getAbs(), srect1.point.getOrd() + srect1.hauteur);
        Point hautDroit = new Point(srect1.point.getAbs() + srect1.longueur, srect1.point.getOrd() + srect1.hauteur);

        System.out.print("\n/** AVANT ROTATION: **/ \n");
        srect1.afficherCoordonnee("bas-gauche", srect1.point);
        srect1.afficherCoordonnee("bas-droit", basDroit);
        srect1.afficherCoordonnee("haut-gauche", hautGauche);
        srect1.afficherCoordonnee("haut-droit", hautDroit);

        System.out.print("\n/** APRES ROTATION LES NOUVELLES COORDONNEES SONT: **/\n");
        srect1.rotate(45);

        /* EXERCICE3 */
        // SlantedRectangle hérite de toute les méthodes de Rectangle (contains, translate, surface, ...)

        /* EXERCICE4 */
        // L'appel de méthode r.surface() sera compilé appelant la méthode surface() de la classe Rectangle
        // L'appel de méthode r.rotate(2) ne sera pas compilé car la classe Rectangle ne possède pas la méthode rotate()
        // L'appel de méthode r.contains(p) sera compilé faisant appel à la méthode contains (de type Point) de Rectangle
        // L'appel de méthode t.surface() sera compilé car il s'agit d'une méthode héritée de la classe Rectangle
        // L'appel de méthode t.rotate(2) sera compilé car la méthode rotate() est bien défini dans la classe SlantedRectangle
        // L'appel de méthode t.contains(p) sera compilé car il s'agit d'une méthode héritée de la classe Rectangle
        // L'appel de méthode s.surface() sera compilé car il s'agit d'une méthode héritée de la classe Rectangle
        // L'appel de méthode s.rotate(2) sera compilé car la méthode rotate() est bien défini dans la classe SlantedRectangle
        // L'appel de méthode s.contains(p) sera compilé car il s'agit d'une méthode héritée de la classe Rectangle


        /* EXERCICE8 */
        A a = new A();
        A ab = new B();
        B b = new B();
        a.f(a); /* void f(A o) dans A */
        a.f(ab); /* void f(A o) dans A */ //car ab est une instance de B (qui hérite de A et de ses méthodes)
        a.f(b); /* void f(A o) dans A */
        ab.f(a); /* void f(A o) dans B */
        ab.f(ab); /* void f(A o) dans B */
        ab.f(b); /* void f(A o) dans B */
        b.f(a); /* void f(A o) dans B */
        b.f(ab); /* void f(A o) dans B */
        b.f(b); /* void f(A o) dans B */

        /* EXERCICE9 */
        // IL s'agit d'une surchage
        // void f(A o) dans A
        // void f(A o) dans A  //car ab est une instance de B (qui hérite de A et de ses méthodes)
        // void f(A o) dans A
        // void f(A o) dans B
        // void f(A o) dans B
        // void f(A o) dans B
        // void f(A o) dans B
        // void f(A o) dans B
        // void f(B o) dans B

        /* EXERCICE10 */
        // IL s'agit d'une redéfinition
        // void f(A o) dans A
        // void f(A o) dans A
        // void f(B o) dans A
        // void f(A o) dans B
        // void f(A o) dans B
        // void f(B o) dans B
        // void f(A o) dans B
        // void f(A o) dans B
        // void f(B o) dans B

        /* EXERCICE11 */
        System.out.println(a instanceof A);  // true
        System.out.println(ab instanceof A); // true
        System.out.println(b instanceof A);  // true
        System.out.println(a instanceof B);  // false
        System.out.println(ab instanceof B); // true
        System.out.println(b instanceof B);  // true

        /* EXERCICE13 */
        C c = new C();
        C cd = new D();
        D d = new D();

        System.out.println(c.ch);       // C
        System.out.println(c.getCh()); // C
        System.out.println(cd.ch);     // C
        System.out.println(cd.getCh());// D
        System.out.println(d.ch);      // D
        System.out.println(d.getCh()); // D
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

    /** Méthode de la rotation
     /* L'expression analytique de la rotation est :
     *  X' = (X-Xa)cos(ø) - (Y-Ya)sin(ø) + Xa
     *  Y' = (X-Xa)sin(ø) + (Y-Ya)cos(ø) + Ya

     *  avec (Xa,Ya) les coordonnées du centre de la rotation
     *  qui coorespond dans notre cas au point "bas gauche"

     * M(X,Y) représente le point à faire roter et M'(X',Y') l'image de M par la rotation

     /* Pour la rotate, il s'agit de faire tourner chacun des points "bas droite",
     "Haut gauche" et "Haut droite" d'un angle "angle"
     **/
    public Point rotate (Point centreRotation , float angle){
        float ANGLE = (float) Math.toRadians(angle);

        this.abs = ( abs - centreRotation.getAbs()) * ((float) Math.cos(ANGLE)) - (ord - centreRotation.getOrd()) * ((float) Math.sin(ANGLE)) + centreRotation.getAbs() ;
        this.ord = ( abs - centreRotation.getAbs()) * ((float) Math.sin(ANGLE)) + (ord - centreRotation.getOrd()) * ((float) Math.cos(ANGLE)) + centreRotation.getOrd() ;
        return new Point(abs , ord);
    }
    public void afficherCoordonnee (String nomPoint) {
        System.out.println("Les coordonnées du point " + nomPoint + " sont : (" + getAbs() + " ; " + getOrd() + ")");
    }

    // Soustraction de vecteurs
    public Point subtract(Point autre) { return new Point(this.abs - autre.abs, this.ord - autre.ord); }

    // Produit scalaire
    public double dot(Point autre) { return this.abs * autre.abs + this.ord * autre.ord; }

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

    boolean contains(Point point){
        return ((point.getAbs() >= this.point.getAbs()) && (point.getAbs() <= (this.point.getAbs() + this.longueur))) && ((point.getOrd() >= this.point.getOrd()) && (point.getOrd() <= (this.point.getOrd() + this.hauteur)));
    }

    boolean contains(Rectangle rectangle){
        Point upRight = new Point(rectangle.point.getAbs() + rectangle.longueur , rectangle.point.getOrd() + rectangle.hauteur);
        return (this.contains(upRight) && this.contains(rectangle.point));
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
    // Produit le plus petit rectangle contenant tous les autres
    static Rectangle hull (Rectangle[] tabRect ) {
        float minX = tabRect[0].point.getAbs();
        float minY = tabRect[0].point.getOrd();
        float maxX = tabRect[getNbr()-1].point.getAbs();
        float maxY = tabRect[getNbr()-1].point.getOrd();

        for (int i = 0; i < getNbr(); i++) {
            Point p = tabRect[i].point;
            minX = Math.min(p.getAbs() , minX);
            minY = Math.min(p.getOrd() , minY);
            maxX = Math.max(p.getAbs() + tabRect[i].longueur , maxX);
            maxY = Math.max(p.getOrd() + tabRect[i].hauteur , maxY);
        }

        return new Rectangle(new Point(minX,minY) , new Point(maxX , maxY));
    }

    public String toString() {
        return "Rectangle d'origine "  +  this.point + " de longueur " + this.longueur + " et de hauteur " + this.hauteur;
    }

    /* EXERICE8 */
    @Override
    public boolean equals (Object o){
        if (!(o instanceof Rectangle)) return false;
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
    }

    //Le point P est dans le rectangle si le vecteur P0P peut s'exprimer comme une combinaison linéaire des vecteurs u et v, avec des coefficients α et β dans [0,1].
    //P0 est le point bas-gauche du rectangle
    //P0P = alpha * u + beta * v avec 0 <= alpha <= 1 et 0 <= beta <= 1
    @Override
    boolean contains(Point point) {
        float ANGLE = (float) Math.toRadians(angle);
        // Vecteur entre le point et le point bas-gauche du rectangle
        Point vecteur = point.subtract(this.point);
        Point u = new Point(0,0);
        Point v = new Point(0,0);
        // Vecteurs u et v
        // u = vecteur horizontal
        // v = vecteur vertical
        if (angle<=90 && angle >= 0){
            u = new Point(this.point.getAbs() + longueur * (float)Math.cos(ANGLE) , point.getOrd() + hauteur * (float) Math.sin(ANGLE)).subtract(this.point);
            v = new Point(this.point.getAbs() - longueur * (float)Math.sin(ANGLE) , point.getOrd() + hauteur * (float) Math.cos(ANGLE)).subtract(this.point);
        }else if (angle<=180 && angle > 90){
            u = new Point(this.point.getAbs() - longueur * (float)Math.cos(ANGLE) , point.getOrd() + hauteur * (float) Math.sin(ANGLE)).subtract(this.point);
            v = new Point(this.point.getAbs() - longueur * (float)Math.sin(ANGLE) , point.getOrd() - hauteur * (float) Math.cos(ANGLE)).subtract(this.point);
        } else if (angle<=-90 && angle > -180) {
            u = new Point(this.point.getAbs() - longueur * (float)Math.cos(ANGLE) , point.getOrd() - hauteur * (float) Math.sin(ANGLE)).subtract(this.point);
            v = new Point(this.point.getAbs() + longueur * (float)Math.sin(ANGLE) , point.getOrd() - hauteur * (float) Math.cos(ANGLE)).subtract(this.point);
        }else if (angle<=0 && angle > -90){
            u = new Point(this.point.getAbs() + longueur * (float)Math.cos(ANGLE) , point.getOrd() - hauteur * (float) Math.sin(ANGLE)).subtract(this.point);
            v = new Point(this.point.getAbs() + longueur * (float)Math.sin(ANGLE) , point.getOrd() + hauteur * (float) Math.cos(ANGLE)).subtract(this.point);
        }
        // Produit scalaire
        double uu = u.dot(u);
        double uv = u.dot(v);
        double vv = v.dot(v);
        double wu = vecteur.dot(u);
        double wv = vecteur.dot(v);

        double denom = uv * uv - uu * vv;
        if (denom == 0) {
            return false; // Rectangle dégénéré
        }
        // Calcul des coefficients alpha et beta
        double alpha = (uv * wv - vv * wu) / denom;
        double beta  = (uv * wu - uu * wv) / denom;
        // Vérification des conditions d'appartenance
        // alpha et beta doivent être compris entre 0 et 1
        return alpha >= 0 && alpha <= 1 && beta >= 0 && beta <= 1;
    }

    /* EXERCICE2 */
    void rotate(float angle) {
        //Point bas-droit
        new Point(this.point.getAbs() + this.longueur , this.point.getOrd()).rotate(this.point, angle).afficherCoordonnee("bas-droit");
        //Point haut-gauche
        new Point(this.point.getAbs() , this.point.getOrd() + this.hauteur).rotate(this.point, angle).afficherCoordonnee("haut-gauche");
        //Point haut-gauche
        new Point(this.point.getAbs() + this.longueur , this.point.getOrd() + hauteur).rotate(this.point, angle).afficherCoordonnee("haut-droit");
    }
    public void afficherCoordonnee (String nomPoint , Point p) {
        System.out.println("Les coordonnées du point " + nomPoint + " sont : (" + p.getAbs() + " ; " + p.getOrd() + ")");
    }
}

/* EXERCICE3 */
class A {
    void f(A o) {
        System.out.println("void f(A o) dans A");
    }
    void f(B o) { System.out.println("void f(B o) dans A"); }
}
class B extends A {
    void f(A o) {
        System.out.println("void f(A o) dans B");
    }
    void f(B o) { System.out.println("void f(B o) dans B"); }
}
class C {
    char ch = 'C';
    char getCh() { return ch; }
}
class D extends C {
    char ch = 'D';
    char getCh() { return ch; }
}