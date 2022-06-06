package adapters

import cats.arrow.FunctionK
import cats.effect.IO
import domain.ports.{Disk, KVStorage}
import domain.ports.KVStorage.{Delete, Exists, Get, Put}
import functional.Nat

object KVStorageInMemoryIO {
  val catsKVStorageNt : FunctionK[KVStorage,IO] = new FunctionK[KVStorage, IO] {
    override def apply[A](a: KVStorage[A]): IO[A] = naturalTransformation(a)
  }

  val nt = new Nat[KVStorage, IO] {
    override def apply[A](a: KVStorage[A]): IO[A] = naturalTransformation(a)
  }


  private def naturalTransformation[A](a: KVStorage[A]): IO[A] = a match {
    case Put(key, value) => IO(
      println("KVStorage-Put")
    )
    case Get(key) => IO {
      println("KVStorage-Get")
      "KVStorage-Get"
    }
    case Delete(key) => IO(println("KVStorage-Delete"))
    case Exists(key) => IO{
      println("KVStorage-Exists")
      false
    }
  }

}
