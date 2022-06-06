package domain.ports

import cats.InjectK
import cats.free.Free
import domain.ports.Disk.{Delete, DiskM, Read, Write}
import functional.FreeMonad

sealed trait Disk[A]



object Disk{
  type DiskM[A] = FreeMonad[Disk,A]

  final case class Read(file: String) extends Disk[Array[Byte]]
  final case class Write(file: String, bytes: Array[Byte]) extends Disk[Unit]
  final case class Delete(file: String) extends Disk[Unit]

  def read(file: String): DiskM[Array[Byte]] = FreeMonad.liftM(Read(file))
  def write(file: String, bytes: Array[Byte]): DiskM[Unit] = FreeMonad.liftM(Write(file, bytes))
  def delete(file: String): DiskM[Unit] = FreeMonad.liftM((Delete(file)))
}

object DiskCats{

  final case class Read(file: String) extends Disk[Array[Byte]]
  final case class Write(file: String, bytes: Array[Byte]) extends Disk[Unit]
  final case class Delete(file: String) extends Disk[Unit]
  def read[F[_]](file: String)(implicit I: InjectK[Disk, F]): Free[Disk,Array[Byte]] = Free.liftInject(Read(file))

}

class DiskAlg[F[_]](implicit I: InjectK[Disk, F]) {
  def read(file: String): Free[F,Array[Byte]] = Free.liftInject(Read(file))
  def write(file: String, bytes: Array[Byte]): Free[F,Unit] = Free.liftInject(Write(file, bytes))
  def delete(file: String): Free[F,Unit] = Free.liftInject((Delete(file)))
}


