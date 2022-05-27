package application

import adapters.DiskIO
import cats.effect.IO
import cats.effect.unsafe.implicits.global
import domain.ports.Disk.DiskM
import domain.services.DocumentService

object mainApp {
  def main(args: Array[String]): Unit = {
    val progam: DiskM[Unit] = DocumentService.doSomeOperations("test.txt")

    val programIO: IO[Unit] = progam.foldMap(DiskIO.nt)
    programIO.unsafeRunSync()
  }
}
