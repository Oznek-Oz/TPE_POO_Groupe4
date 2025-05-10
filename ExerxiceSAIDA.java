class ExerxiceSAIDA{
    public static void main(String args[]){
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

    }
}
// *************exercice 9************

class A {
    // Ajout de la methode suivante a la class B
    void f(A o) {
        System.out.println("void f(A o) dans A classe:"+o.getClass());
    }
    //1)Il s'agit ici d'une redefinition

    //2)le fragment de programme de l'exercice 8 va afficher
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


}