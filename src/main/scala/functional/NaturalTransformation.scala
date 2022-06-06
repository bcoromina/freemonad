package functional

import cats.data.EitherK


trait Nat[F[_], G[_]] {
  def apply[A](a: F[A]): G[A]

  def or[H[_]](n: Nat[H, G]): Nat[EitherK[F, H, *], G] = ???
}