package functional

import cats.Monad

sealed trait FreeMonad[F[_], A]{
  import FreeMonad._

  def flatMap[B](f: A => FreeMonad[F, B]): FreeMonad[F,B] = FlatMap(this, f)

  def foldMap[G[_]: Monad](nt: Nat[F,G]): G[A] = this match {
    case Pure(a) => Monad[G].pure(a)
    case Lift(fa) => nt(fa)
    case FlatMap(fa: FreeMonad[F, Any], f: ( Any => FreeMonad[F,A])) =>
      Monad[G].flatMap(fa.foldMap(nt))( e => f(e).foldMap(nt))
  }

  def map[B](f: A => B): FreeMonad[F,B] = this.flatMap( a => FreeMonad.Pure(f(a)))
}

object FreeMonad{
  def pure[F[_], A](a: A) : FreeMonad[F,A]= Pure(a)
  def liftM[F[_], A](fa: F[A]): FreeMonad[F,A] = Lift(fa)

  final case class Pure[F[_],A](a: A) extends FreeMonad[F,A]
  final case class Lift[F[_],A](a: F[A]) extends FreeMonad[F,A]
  final case class FlatMap[F[_], E, A](ffa: FreeMonad[F,E], f: E => FreeMonad[F,A]) extends FreeMonad[F,A]
}

object FreeMonadSyntax{
  implicit class Lifter[F[_], A]( d: F[A]){
    def lift: FreeMonad[F, A] = FreeMonad.liftM(d)
  }
}