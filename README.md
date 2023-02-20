 
 This is an exercice in Scala to understand the FreeMonad concept. 
 Based on Daniel Spiewak talk: https://www.youtube.com/watch?v=aKUQUIHRGec
 
 
 FreeMonad's Mission:  
 
 Give me any type constructor F[_] and a way to transform it to a "true" Monad G (like IO in cats effects),
 and FreeMonad will act as an adapter for you to compose your programs in F[_] in a monadic style.
 
 Then you will compose your program in FreeMonad[F[_]] and just before 'the end of the world' 
 you will transform it into a program in G. If G is cats's IO, you can run it with unsafeRunSync.
 


Other sources:

https://medium.com/@agaro1121/free-monad-vs-tagless-final-623f92313eac
https://underscore.io/blog/posts/2017/03/29/free-inject.html
https://perevillega.com/understanding-free-monads/
https://blog.higher-order.com/assets/trampolines.pdf
https://www.youtube.com/watch?v=IhVdU4Xiz2U

https://www.47deg.com/blog/fp-for-the-average-joe-part3-free-monads/
https://underscore.io/blog/posts/2017/03/29/free-inject.html
