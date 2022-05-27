package domain.ports

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
