package domain.ports

import cats.InjectK
import cats.free.Free
import domain.ports.KVStorage.{Delete, Exists, Get, KVStorageM, Put}
import functional.FreeMonad

sealed trait KVStorage[A]


object KVStorage{
  type KVStorageM[A] = FreeMonad[KVStorage, A]

  final case class Put(key: String, value: String) extends KVStorage[Unit]
  final case class Get(key: String) extends KVStorage[String]
  final case class Delete(key: String) extends KVStorage[Unit]
  final case class Exists(key: String) extends KVStorage[Boolean]

  def put(key: String, value: String): KVStorageM[Unit] = FreeMonad.liftM(Put(key, value))
  def get(key: String): KVStorageM[String] = FreeMonad.liftM(Get(key))
  def delete(key: String): KVStorageM[Unit] = FreeMonad.liftM(Delete(key))
  def exists(key: String): KVStorageM[Boolean] = FreeMonad.liftM(Exists(key))
}

object KVStorageCats{
  final case class Put(key: String, value: String) extends KVStorage[Unit]
  final case class Get(key: String) extends KVStorage[String]
  final case class Delete(key: String) extends KVStorage[Unit]
  final case class Exists(key: String) extends KVStorage[Boolean]
}

class KVStorageAlg[F[_]](implicit I:InjectK[KVStorage, F]){
  def put(key: String, value: String): Free[F,Unit] = Free.liftInject(Put(key, value))
  def get(key: String): Free[F,String] = Free.liftInject(Get(key))
  def delete(key: String): Free[F,Unit] = Free.liftInject(Delete(key))
  def exists(key: String): Free[F,Boolean] = Free.liftInject(Exists(key))
}