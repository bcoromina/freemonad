 FreeMonad's Mission: Give me a type constructor F[_], a type A. And give me a 
 Natural Transformation from F[_] to G[_] (where G is a Monad)
 and I will wrap F in a FreeMonad so you can use it like a Monad.
 Then you will compose your program in F and just before the eend of the world 
 programInF.foldMap(NaturalTransformation) will give you a program in G. 
 If G is cats's IO, you can run it with unsafeRunSync
 

Based on Daniel Spiewak talk: https://www.youtube.com/watch?v=aKUQUIHRGec
