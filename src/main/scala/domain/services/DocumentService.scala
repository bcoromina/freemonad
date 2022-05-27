package domain.services

import domain.ports.Disk
import domain.ports.Disk.DiskM
import functional.FreeMonadSyntax._

object DocumentService {

  def doSomeOperations(file: String): DiskM[Unit] = {
    for{
      contents <- Disk.Read(file).lift
      _ <- Disk.Write(file, contents ++ "hola".getBytes).lift
      _ <- Disk.Delete("").lift
    }yield ()
  }

  def doSometingElse(file: String): DiskM[Unit] = {
    for{
      contents <- Disk.read(file)
      _ <- Disk.write(file, contents ++ "hola".getBytes)
      _ <- Disk.delete("")
    }yield ()
  }

}
