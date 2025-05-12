# TPE_DSC_POO_Groupe4
Consigne pour les membres

# Devoir de Groupe – NewSQL

## Membres du groupe

| Nom                | GitHub                  | Partie réalisée      |
|--------------------|-------------------------|----------------------|
| TCHIKAYA FAMI K.F  | @Oznek-Oz               | exercices(1,2,7,8)   |
| TAFFO TEMATIO P.O  | @Taffotematio           | exercices(5,12,13)   |
| NGOUGOURE MEFIRE   | @bicha56                | exercices(3,6,11)    |
| NGOUPAURE SAÏDA    | @goupaure               | exercices(4,9,10)    |

## Objectif

Ce dépôt contient le travail collaboratif sur le devoir de POO (Héritages). Chaque membre a sa propre branche, et les contributions sont fusionnées via pull requests dans `main`.

## Instructions

1. Cloner le dépôt.
2. Basculer sur sa branche personnelle.
3. Faire des commits clairs et réguliers.
4. Créer une pull request pour fusionner son travail dans `main`.

## Exercices

### EXERCICE 3
SlantedRectangle hérite de toutes les méthodes de Rectangle (`contains`, `translate`, `surface`, ...).

### EXERCICE 4
- L'appel de méthode `r.surface()` sera compilé, appelant la méthode `surface()` de la classe Rectangle.
- L'appel de méthode `r.rotate(2)` ne sera pas compilé, car la classe Rectangle ne possède pas la méthode `rotate()`.
- L'appel de méthode `r.contains(p)` sera compilé, faisant appel à la méthode `contains` (de type Point) de Rectangle.
- L'appel de méthode `t.surface()` sera compilé, car il s'agit d'une méthode héritée de la classe Rectangle.
- L'appel de méthode `t.rotate(2)` sera compilé, car la méthode `rotate()` est bien définie dans la classe SlantedRectangle.
- L'appel de méthode `t.contains(p)` sera compilé, car il s'agit d'une méthode héritée de la classe Rectangle.
- L'appel de méthode `s.surface()` sera compilé, car il s'agit d'une méthode héritée de la classe Rectangle.
- L'appel de méthode `s.rotate(2)` sera compilé, car la méthode `rotate()` est bien définie dans la classe SlantedRectangle.
- L'appel de méthode `s.contains(p)` sera compilé, car il s'agit d'une méthode héritée de la classe Rectangle.

### EXERCICE 8
```java
A a = new A();
A ab = new B();
B b = new B();

a.f(a);  // void f(A o) dans A
a.f(ab); // void f(A o) dans A
a.f(b);  // void f(A o) dans A
ab.f(a); // void f(A o) dans B
ab.f(ab); // void f(A o) dans B
ab.f(b);  // void f(A o) dans B
b.f(a);  // void f(A o) dans B
b.f(ab); // void f(A o) dans B
b.f(b);  // void f(A o) dans B
```

### EXERCICE 9
Après avoir ajouté la méthode suivante à la classe `B` :
```java
void f(B o) { 
    System.out.println("void f(B o) dans B"); 
}
```

Le code de l'exercice 8 affichera désormais :
```java
a.f(a);  // void f(A o) dans A
a.f(ab); // void f(A o) dans A
a.f(b);  // void f(A o) dans A
ab.f(a); // void f(A o) dans B
ab.f(ab); // void f(A o) dans B
ab.f(b);  // void f(B o) dans B
b.f(a);  // void f(A o) dans B
b.f(ab); // void f(A o) dans B
b.f(b);  // void f(B o) dans B
```

### EXERCICE 10
Après avoir ajouté la méthode suivante à la classe `A` :
```java
void f(B o) { 
    System.out.println("void f(B o) dans A"); 
}
```

#### Est-ce une redéfinition ou une surcharge ?
Il s'agit d'une **surcharge**, car la méthode `f(B o)` dans `A` a une signature différente de la méthode `f(A o)` déjà définie dans `A`.

#### Affichage du code de l'exercice 8 :
```java
a.f(a);  // void f(A o) dans A
a.f(ab); // void f(A o) dans A
a.f(b);  // void f(B o) dans A
ab.f(a); // void f(A o) dans B
ab.f(ab); // void f(A o) dans B
ab.f(b);  // void f(B o) dans B
b.f(a);  // void f(A o) dans B
b.f(ab); // void f(A o) dans B
b.f(b);  // void f(B o) dans B
```

### EXERCICE 11
Le fragment de code suivant permet de vérifier les relations d'héritage entre les instances des classes `A` et `B` :

```java
System.out.println(a instanceof A);  // true
System.out.println(ab instanceof A); // true
System.out.println(b instanceof A);  // true
System.out.println(a instanceof B);  // false
System.out.println(ab instanceof B); // true
System.out.println(b instanceof B);  // true
```

#### Explications :
1. `a instanceof A` : `true` car `a` est une instance directe de la classe `A`.
2. `ab instanceof A` : `true` car `ab` est une instance de `B`, qui hérite de `A`.
3. `b instanceof A` : `true` car `b` est une instance de `B`, qui hérite de `A`.
4. `a instanceof B` : `false` car `a` est une instance directe de `A` et non de `B`.
5. `ab instanceof B` : `true` car `ab` est une instance de `B`.
6. `b instanceof B` : `true` car `b` est une instance directe de `B`.

Ce test met en évidence la relation d'héritage entre les classes `A` et `B` et montre comment le mot-clé `instanceof` peut être utilisé pour vérifier le type d'une instance.
