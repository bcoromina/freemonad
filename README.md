 FreeMonad's Mission: Give me a type constructor F[_], a type A. And give me a 
 Natural Transformation from F[_] to G[_] (where G is a Monad)
 and I will wrap F in a FreeMonad so you can use it like a Monad.
 Then you will compose your program in F and just before the eend of the world 
 programInF.foldMap(NaturalTransformation) will give you a program in G. 
 If G is cats's IO, you can run it with unsafeRunSync
 

Based on Daniel Spiewak talk: https://www.youtube.com/watch?v=aKUQUIHRGec


Other sources:

https://medium.com/@agaro1121/free-monad-vs-tagless-final-623f92313eac
https://underscore.io/blog/posts/2017/03/29/free-inject.html
https://perevillega.com/understanding-free-monads/
https://blog.higher-order.com/assets/trampolines.pdf
https://www.youtube.com/watch?v=IhVdU4Xiz2U

https://www.47deg.com/blog/fp-for-the-average-joe-part3-free-monads/
https://underscore.io/blog/posts/2017/03/29/free-inject.html
