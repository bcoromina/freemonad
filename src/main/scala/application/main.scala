package application

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import cats.free.Free
import domain.ports.Disk.DiskM
import domain.ports.DiskAndKVStorage.DiskAndKVStorage
import domain.ports.{DiskAlg, DiskAndKVStorage, KVStorageAlg}
import domain.services.DocumentService

object mainApp {
  def main(args: Array[String]): Unit = {
    import domain.ports.DiskAlgInjects._
    implicit val d = new DiskAlg[DiskAndKVStorage]
    implicit val k = new KVStorageAlg[DiskAndKVStorage]
    val progam: Free[DiskAndKVStorage, Unit] = DocumentService.doSometingElse("test.txt")

    val programIO: IO[Unit] = progam.foldMap(adapters.DiskAndKVStorage.ioInterpreter)
    programIO.unsafeRunSync()


    val program2: DiskM[Unit] = DocumentService.doSomeOperations("hola.txt")
    val programIO2 = program2.foldMap(adapters.DiskIO.nt)
    programIO.unsafeRunSync()
  }
}
