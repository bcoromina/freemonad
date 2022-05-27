package stubs

import cats.effect.IO
import domain.ports.Disk
import domain.ports.Disk.{Delete, Read, Write}
import functional.Nat

import scala.collection.mutable

class DiskIOStub(storage: mutable.Map[String, Array[Byte]]) {

  val nt: Nat[Disk, IO] = new Nat[Disk, IO] {
    override def apply[A](a: Disk[A]): IO[A] = a match {
      case Read(file) => IO(
        storage.get(file).get
      )
      case Write(file, bytes) => IO{
        storage += (file -> bytes)
        ()
      }
      case Delete(file) => IO {
        storage - file
        ()
      }
    }
  }
}
